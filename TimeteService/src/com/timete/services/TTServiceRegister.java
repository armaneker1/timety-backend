package com.timete.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.restfb.types.CategorizedFacebookType;
import com.timete.models.fq.FoursquareVenue;
import com.timete.models.timete.TTSocialProvider;
import com.timete.neo4j.models.Category;
import com.timete.neo4j.models.Object;
import com.timete.neo4j.models.User;
import com.timete.neo4j.services.CategoryService;
import com.timete.neo4j.services.ObjectService;
import com.timete.neo4j.services.UserService;
import com.timete.utils.Constants;
import com.timete.utils.FacebookUtils;
import com.timete.utils.FousquareUtils;
import com.timete.utils.MySQLUtils;
import com.timete.utils.PropertyUtils;
import com.timete.utils.PropertyUtils.Property;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.io.DefaultIOHandler;

public class TTServiceRegister implements Runnable {
	public static int LIMIT = 20;
	private static Logger log = Logger.getLogger(TTServiceRegister.class
			.getName());
	private List<TTSocialProvider> failProviders = new ArrayList<TTSocialProvider>();

	@Override
	public void run() {
		log.info("Task started");
		/*
		 * social providers
		 */
		List<TTSocialProvider> providers = null;
		try {
			log.info("Social Provider LIMIT : " + LIMIT);
			providers = MySQLUtils.getProviderListForRegister(LIMIT);
			MySQLUtils.lockSocialProviderStatus(providers);
			log.info("Social provider count  : "
					+ (providers != null ? providers.size() : 0));
			registerUserSoial(providers);
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			MySQLUtils.unlockSocialProviderStatus(providers, 2);
			MySQLUtils.unlockSocialProviderStatus(failProviders, 1);
		}
		log.info("Task ended");
	}

	private void registerUserSoial(List<TTSocialProvider> providers) {
		if (null != providers && providers.size() > 0) {
			for (int i = 0; i < providers.size(); i++) {
				TTSocialProvider provider = providers.get(i);
				log.info("Provider LIMIT : " + LIMIT);
				if (null != provider) {
					if (provider.getOauth_provider().equals(Constants.FACEBOOK)) {
						if (!registerUserFacebook(provider)) {
							failProviders.add(provider);
						}
					} else if (provider.getOauth_provider().equals(
							Constants.TWITTER)) {
						if (!registerUserTwitter(provider)) {
							failProviders.add(provider);
						}
					} else if (provider.getOauth_provider().equals(
							Constants.FOURSQUARE)) {
						if (!registerUserFoursquare(provider)) {
							failProviders.add(provider);
						}
					}
				}
			}
		}
	}

	private boolean registerUserFacebook(TTSocialProvider provider) {
		if (null != provider) {
			try {
				String aToken = provider.getOauth_token();
				List<CategorizedFacebookType> likes = FacebookUtils
						.getUserLikes(aToken);

				Node user = UserService.getUser(User.idString,
						provider.getUser_id() + "");
				List<String> unknownCats = new ArrayList<String>();
				if (user != null) {
					for (int i = 0; i < likes.size(); i++) {
						try {
							CategorizedFacebookType fbObject = likes.get(i);
							Node category = CategoryService.getCategoryL2(
									Category.nameString, fbObject.getCategory());
							if (category == null) {
								// ignore event do it later
								// to save the mysql db to add categories
								unknownCats
										.add((fbObject.getCategory()
												+ "~"
												+ user.getProperty(
														User.idString)
														.toString() + "~"
												+ fbObject.getId() + "~"
												+ Constants.FACEBOOK + "~"
												+ "0" + "~" + "0"));
							} else {
								Object obj = new Object(fbObject.getId(),
										fbObject.getName(), Constants.FACEBOOK,
										category);
								obj.setFbObject(fbObject);
								ObjectService.insertUser(obj, user,
										Constants.FACEBOOK_INTEREST_WEIGHT);
							}
						} catch (Exception e) {
							log.error("Error", e);
						}
					}

					try {
						UnknownCategoryService unServ = new UnknownCategoryService();
						unServ.unknownCats = unknownCats;
						Thread t = new Thread(unServ);
						t.start();
					} catch (Exception e) {
						log.error("Error", e);
					}
					return true;
				}
			} catch (Exception e) {
				log.error("Error", e);
			}
		}
		return false;
	}

	private boolean registerUserFoursquare(TTSocialProvider provider) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -6);

			FoursquareApi foursquareApi = new FoursquareApi(
					PropertyUtils
							.getProperty(Property.FOURSQUARE_CLIENTID.value),
					PropertyUtils
							.getProperty(Property.FOURSQUARE_CLIENTSECRET.value),
					"", provider.getOauth_token(), new DefaultIOHandler());

			Result<CheckinGroup> result = foursquareApi.usersCheckins(null,
					Integer.MAX_VALUE, 0, calendar.getTime().getTime() / 1000,
					Long.MAX_VALUE);
			HashMap<String, FoursquareVenue> venues = new HashMap<String, FoursquareVenue>();
			if (result.getMeta().getCode() == 200) {
				Checkin[] checkins = result.getResult().getItems();
				for (Checkin checkin : checkins) {
					if (venues.containsKey(checkin.getVenue().getId())) {
						venues.get(checkin.getVenue().getId()).checkinCount++;
					} else {
						venues.put(checkin.getVenue().getId(),
								FoursquareVenue.populate(checkin));
					}
				}

			} else {
				throw new Exception("Error occured:" + "  code: "
						+ result.getMeta().getCode() + "  type: "
						+ result.getMeta().getErrorType() + "  detail: "
						+ result.getMeta().getErrorDetail());
			}

			Node user = UserService.getUser(User.idString,
					provider.getUser_id() + "");
			List<String> unknownCats = new ArrayList<String>();
			HashMap<String, String> FQCategoryMapping = Constants
					.getFQCategoryMapping();
			if (FQCategoryMapping != null) {

				for (FoursquareVenue venue : venues.values()) {
					boolean check = true;
					for (int i = 0; i < venue.getCategoryIDs().length && check; i++) {
						String fbCategory = FQCategoryMapping.get(venue
								.getCategoryNames()[i]);
						if (fbCategory != null) {
							check = false;
							Node category = CategoryService.getCategoryL2(
									Category.nameString, fbCategory);
							if (category != null) {
								Object obj = new Object(venue.getId(),
										venue.getName(), Constants.FOURSQUARE,
										category);
								obj.setFqObject(venue);
								double fqInterestWeight = FousquareUtils
										.getFoursquareWeight(fbCategory,
												venue.getCheckinCount());
								// gecici olarak kaldır
								if (fqInterestWeight > 0)
									ObjectService.insertUser(obj, user,
											fqInterestWeight);

							} else {
								unknownCats.add((venue.getCategoryNames()[i]
										+ "~"
										+ user.getProperty(User.idString)
												.toString() + "~"
										+ venue.getId() + "~"
										+ Constants.FOURSQUARE + "~" + "0"
										+ "~" + venue.getCategoryIDs()[i]));
							}
						} else {
							unknownCats.add((venue.getCategoryNames()[i]
									+ "~"
									+ user.getProperty(User.idString)
											.toString() + "~" + venue.getId()
									+ "~" + Constants.FOURSQUARE + "~" + "0"
									+ "~" + venue.getCategoryIDs()[i]));
						}
					}
				}
				try {
					UnknownCategoryService unServ = new UnknownCategoryService();
					unServ.unknownCats = unknownCats;
					Thread t = new Thread(unServ);
					t.start();
				} catch (Exception e) {
					log.error("Error", e);
				}
				return true;
			}

		} catch (Exception e) {
			log.error("Error", e);
		}
		return false;
	}

	private boolean registerUserTwitter(TTSocialProvider provider) {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(false)
					.setOAuthConsumerKey(
							PropertyUtils
									.getProperty(Property.TWITTER_CONSUMERKEY.value))
					.setOAuthConsumerSecret(
							PropertyUtils
									.getProperty(Property.TWITTER_CONSUMERSECRET.value))
					.setOAuthAccessToken(provider.getOauth_token())
					.setOAuthAccessTokenSecret(provider.getOauth_token_secret());
			TwitterFactory tf = new TwitterFactory(cb.build());

			Twitter twitter = tf.getInstance();
			IDs friends = twitter.getFriendsIDs(-1);
			for (long id : friends.getIDs()) {
				System.out.println(id);
			}
		} catch (Exception e) {
			log.error("Error", e);
		}
		return false;
	}

}
