package com.timete.neo4j.models;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.timete.neo4j.utils.Neo4jUtils;


public class Category {
	private static Logger log = Logger.getLogger(Category.class.getName());


	public int id;
	public String name;
	public String socialType;
	public Category parent;
	public Node node;

	public static String idString = "id";
	public static String nameString = "name";
	public static String socialTypeString = "socialType";

	public Category(int id, String name, String socialType, Category parent) {
		this.id = id;
		this.name = name;
		this.socialType = socialType;
		if (parent != null) {
			this.parent = parent;
		}
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			node = Neo4jUtils.graphDb.createNode();
			node.setProperty(idString, id);
			node.setProperty(nameString, name);
			node.setProperty(socialTypeString, socialType);
			tx.success();
		} catch (Exception e) {
			tx.failure();
			log.error("Error", e);
		} finally {
			tx.finish();
		}
	}
	
	public Category(Node node) {
		this.id = Integer.parseInt(node.getProperty(idString).toString());
		this.name = node.getProperty(nameString).toString();
		this.socialType = node.getProperty(socialType).toString();
		this.node=node;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

}
