package com.timete.neo4j.models;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.IndexHits;

import com.restfb.types.CategorizedFacebookType;
import com.timete.models.fq.FoursquareVenue;
import com.timete.neo4j.constants.RelationTypes;
import com.timete.neo4j.services.ObjectService;
import com.timete.neo4j.utils.Neo4jUtils;

public class Object {

	private static Logger log = Logger.getLogger(Object.class.getName());

	public String id;
	public String name;
	public String socialType;
	public Node node;

	public static String idString = "id";
	public static String nameString = "name";
	public static String socialTypeString = "socialType";

	/*
	 * holder
	 */
	public CategorizedFacebookType fbObject;
	public FoursquareVenue fqObject;
	public boolean iscreated;

	public Object(String id, String name, String socialType, Node category) {
		this.id = id;
		this.name = name;
		this.socialType = socialType;
		// check event is already saved
		iscreated = false;
		IndexHits<Node> events = ObjectService.getEvents(Object.idString, id);
		if (events != null && events.size() > 0) {
			while (events.hasNext()) {
				node = events.next();
				if (node != null
						&& name.equals(node.getProperty(Object.nameString))
						&& socialType.equals(node
								.getProperty(Object.socialTypeString))) {
					iscreated = true;
				}
			}
		}
		if (!iscreated) {
			node = null;
			Transaction tx = Neo4jUtils.graphDb.beginTx();
			try {
				node = Neo4jUtils.graphDb.createNode();
				node.setProperty(idString, id);
				node.setProperty(nameString, name);
				node.setProperty(socialTypeString, socialType);
				category.createRelationshipTo(getNode(), RelationTypes.OBJECTS);
				tx.success();
			} catch (Exception e) {
				tx.failure();
				log.error("Error", e);
			} finally {
				tx.finish();
			}
		}
	}

	public Object(Node node) {
		this.id = node.getProperty(idString).toString();
		this.name = node.getProperty(nameString).toString();
		this.socialType = node.getProperty(socialType).toString();
		this.node = node;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialType() {
		return socialType;
	}

	public void setSocialType(String socialType) {
		this.socialType = socialType;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public CategorizedFacebookType getFbObject() {
		return fbObject;
	}

	public void setFbObject(CategorizedFacebookType fbObject) {
		this.fbObject = fbObject;
	}

	public FoursquareVenue getFqObject() {
		return fqObject;
	}

	public void setFqObject(FoursquareVenue fqObject) {
		this.fqObject = fqObject;
	}

}
