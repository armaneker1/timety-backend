package com.timete.neo4j.models;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.timete.neo4j.utils.Neo4jUtils;

public class Group {
	private static Logger log = Logger.getLogger(Group.class.getName());

	public int id;
	public String name;
	public Node node;

	public static String idString = "id";
	public static String nameString = "name";

	public Group(int id, String name) {
		this.id = id;
		this.name = name;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			node = Neo4jUtils.graphDb.createNode();
			node.setProperty(idString, id);
			node.setProperty(nameString, name);
			tx.success();
		} catch (Exception e) {
			tx.failure();
			log.error("Error", e);
		} finally {
			tx.finish();
		}
	}

}
