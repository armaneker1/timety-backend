package com.timete.neo4j.services;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import com.timete.neo4j.constants.RelationTypes;
import com.timete.neo4j.utils.Neo4jUtils;
import com.timete.utils.Constants;

public class GroupService {
	private static Logger log = Logger.getLogger(GroupService.class.getName());

	/*
	 * Group Root Node
	 */
	private static Node groupRootNode;

	/*
	 * Group Indexes
	 */
	private static Index<Node> groupIndex;

	static {
		/*
		 * chek user root node exist
		 */
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			groupRootNode = Constants.rootIndex.get(Constants.PROP_ROOT_ID,
					Constants.PROP_ROOT_GRP).getSingle();

			if (groupRootNode == null) {
				groupRootNode = Neo4jUtils.graphDb.createNode();
				groupRootNode.setProperty(Constants.PROP_ROOT_ID,
						Constants.PROP_ROOT_GRP);
				Neo4jUtils.graphDb.getReferenceNode().createRelationshipTo(
						groupRootNode, RelationTypes.GROUP_ROOT);
				Constants.rootIndex.add(groupRootNode, Constants.PROP_ROOT_ID,
						Constants.PROP_ROOT_GRP);
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
			groupIndex = Neo4jUtils.graphDb.index().forNodes("GROUP_INDEX");
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
			tx.failure();
		} finally {
			tx.finish();
		}
	}
	

	public static Node getGroup(String type, String param) {
		Node result = null;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			result = groupIndex.get(type, param.toLowerCase()).getSingle();
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			tx.finish();
		}
		return result;
	}

}
