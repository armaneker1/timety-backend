package com.timete.neo4j.services;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import com.timete.neo4j.constants.RelationTypes;
import com.timete.neo4j.models.Object;
import com.timete.neo4j.utils.Neo4jUtils;
import com.timete.utils.Constants;

public class ObjectService {
	private static Logger log = Logger.getLogger(ObjectService.class.getName());

	/*
	 * Object Indexes
	 */
	private static Index<Node> objectIndex;

	static {
		/*
		 * cretae indexes
		 */
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			objectIndex = Neo4jUtils.graphDb.index().forNodes("OBJECT_INDEX");
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
			tx.failure();
		} finally {
			tx.finish();
		}
	}

	public static boolean insertUser(Object object, Node user, Double weight) {
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			// check if user and event related before
			user.createRelationshipTo(object.getNode(), RelationTypes.INTERESTS)
					.setProperty(Constants.PROP_INTEREST_WEIGHT, weight);
			// if(!event.iscreated)
			// {
			// category.createRelationshipTo(event.getNode(),
			// RelationTypes.EVENTS);
			// }
			objectIndex.add(object.getNode(), Object.nameString, object.getName()
					.toLowerCase());
			objectIndex.add(object.getNode(), Object.idString, object.getId());

			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
			tx.failure();
			return false;
		} finally {
			tx.finish();
		}
		return true;
	}

	public static Node getEvent(String type, String param) {
		Node result = null;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			result = objectIndex.get(type, param.toLowerCase()).getSingle();
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			tx.finish();
		}
		return result;
	}

	public static IndexHits<Node> getEvents(String type, String param) {
		IndexHits<Node> result = null;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			result = objectIndex.get(type, param.toLowerCase());
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			tx.finish();
		}
		return result;
	}
}
