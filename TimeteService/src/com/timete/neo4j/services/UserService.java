package com.timete.neo4j.services;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import com.timete.neo4j.constants.RelationTypes;
import com.timete.neo4j.models.User;
import com.timete.neo4j.utils.Neo4jUtils;
import com.timete.utils.Constants;

public class UserService {
	private static Logger log = Logger.getLogger(UserService.class.getName());
	/*
	 * User Root Node
	 */
	private static Node userRootNode;

	/*
	 * User Indexes
	 */
	private static Index<Node> userIndex;

	static {
		/*
		 * chek user root node exist
		 */
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			userRootNode = Constants.rootIndex.get(Constants.PROP_ROOT_ID,
					Constants.PROP_ROOT_USR).getSingle();

			if (userRootNode == null) {
				userRootNode = Neo4jUtils.graphDb.createNode();
				userRootNode.setProperty(Constants.PROP_ROOT_ID, Constants.PROP_ROOT_USR);
				Neo4jUtils.graphDb.getReferenceNode().createRelationshipTo(
						userRootNode, RelationTypes.USER_ROOT);
				Constants.rootIndex.add(userRootNode, Constants.PROP_ROOT_ID,
						Constants.PROP_ROOT_USR);
			}
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
			tx.failure();
		} finally {
			tx.finish();
		}

		/*
		 * cretae indexes
		 */
		tx = Neo4jUtils.graphDb.beginTx();
		try {
			userIndex = Neo4jUtils.graphDb.index().forNodes("USER_INDEX");
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
			tx.failure();
		} finally {
			tx.finish();
		}
	}

	public static boolean insertUser(User user) {
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			if (null == getUser(User.userNameString, user.getUserName())) {
				userRootNode.createRelationshipTo(user.getNode(),
						RelationTypes.USER);
				userIndex.add(user.getNode(), User.userNameString, user
						.getUserName().toLowerCase());
				userIndex.add(user.getNode(), User.idString, user.getId());
			}
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

	public static Node getUser(String type, String param) {
		Node result = null;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			result = userIndex.get(type, param.toLowerCase()).getSingle();
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			tx.finish();
		}
		return result;
	}

}
