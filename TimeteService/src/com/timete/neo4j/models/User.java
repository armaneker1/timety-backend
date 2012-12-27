package com.timete.neo4j.models;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.timete.neo4j.constants.Contants.USER_TYPE;
import com.timete.neo4j.utils.Neo4jUtils;

public class User {

	private static Logger log = Logger.getLogger(User.class.getName());

	public int id;
	public String userName;
	public String firstName;
	public String lastName;
	public USER_TYPE type;
	public Node node;

	public static String idString = "id";
	public static String userNameString = "userName";
	public static String firstNameString = "firstName";
	public static String lastNameString = "lastName";
	public static String typeString = "type";

	public User(int id, String userName, String firstName, String lastName,
			USER_TYPE type) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;

		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			node = Neo4jUtils.graphDb.createNode();
			node.setProperty(idString, id);
			node.setProperty(userNameString, userName);
			node.setProperty(firstNameString, firstName);
			node.setProperty(lastNameString, lastName);
			node.setProperty(typeString, type.value);
			tx.success();
		} catch (Exception e) {
			tx.failure();
			log.error("Error", e);
		} finally {
			tx.finish();
		}
	}

	public User(Node node) {
		this.id = Integer.parseInt(node.getProperty(idString).toString());
		this.userName = node.getProperty(userNameString).toString();
		this.firstName = node.getProperty(firstNameString).toString();
		this.lastName = node.getProperty(lastNameString).toString();
		this.type = USER_TYPE.getFormValue(node.getProperty(typeString));
		this.node = node;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public USER_TYPE getType() {
		return type;
	}

	public void setType(USER_TYPE type) {
		this.type = type;
	}

	
}
