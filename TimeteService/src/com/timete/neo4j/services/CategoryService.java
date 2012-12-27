package com.timete.neo4j.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import com.timete.neo4j.constants.RelationTypes;
import com.timete.neo4j.models.Category;
import com.timete.neo4j.utils.Neo4jUtils;
import com.timete.utils.Constants;

public class CategoryService {
	private static Logger log = Logger.getLogger(CategoryService.class
			.getName());
	/*
	 * Category Root Node
	 */
	private static Node categoryRootNode;

	/*
	 * Category Indexes
	 */
	private static Index<Node> categoryLevel1Index;
	private static Index<Node> categoryLevel2Index;

	static {
		/*
		 * chek category root node exist
		 */
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			categoryRootNode = Constants.rootIndex.get(Constants.PROP_ROOT_ID,
					Constants.PROP_ROOT_CAT).getSingle();

			if (categoryRootNode == null) {
				categoryRootNode = Neo4jUtils.graphDb.createNode();
				categoryRootNode.setProperty(Constants.PROP_ROOT_ID,
						Constants.PROP_ROOT_CAT);
				Neo4jUtils.graphDb.getReferenceNode().createRelationshipTo(
						categoryRootNode, RelationTypes.CATEGORY_ROOT);
				Constants.rootIndex.add(categoryRootNode,
						Constants.PROP_ROOT_ID, Constants.PROP_ROOT_CAT);
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
			categoryLevel1Index = Neo4jUtils.graphDb.index().forNodes(
					"CATEGORY_LEVEL1");

			categoryLevel2Index = Neo4jUtils.graphDb.index().forNodes(
					"CATEGORY_LEVEL2");

			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
			tx.failure();
		} finally {
			tx.finish();
		}
	}

	/*
	 * Level 1 category functions
	 */
	public static boolean insertCategoryL1(Category cat) {
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			if (null == getCategoryL1(Category.nameString, cat.getName())) {
				categoryRootNode.createRelationshipTo(cat.getNode(),
						RelationTypes.CATEGORY_LEVEL1);
				categoryLevel1Index.add(cat.getNode(), Category.nameString, cat
						.getName().toLowerCase());
				categoryLevel1Index.add(cat.getNode(), Category.idString,
						cat.getId());
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

	public static boolean insertCategoryL1(List<Category> cats) {
		boolean result = true;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			for (int i = 0; null != cats && i < cats.size(); i++) {
				try {
					if (null == getCategoryL1(Category.nameString, cats.get(i)
							.getName())) {
						categoryRootNode.createRelationshipTo(cats.get(i)
								.getNode(), RelationTypes.CATEGORY_LEVEL1);
						categoryLevel1Index.add(cats.get(i).getNode(),
								Category.nameString, cats.get(i).getName()
										.toLowerCase());
						categoryLevel1Index.add(cats.get(i).getNode(),
								Category.idString, cats.get(i).getId());
					}
				} catch (Exception e) {
					log.error("Error", e);
					return false;
				}
			}
			if (result) {
				tx.success();
			} else {
				tx.failure();
			}
		} catch (Exception e) {
			log.error("Error", e);
			result = false;
		} finally {
			tx.finish();
		}
		return result;
	}

	public static Node getCategoryL1(String type, String param) {
		Node result = null;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			result = categoryLevel1Index.get(type, param.toLowerCase())
					.getSingle();
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			tx.finish();
		}
		return result;
	}

	/*
	 * Level2 category functionss
	 */
	public static Node getCategoryL2(String type, String param) {
		Node result = null;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			result = categoryLevel2Index.get(type, param.toLowerCase())
					.getSingle();
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			tx.finish();
		}
		return result;
	}

	public static boolean insertCategoryL2(Category cat) {
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			if (null == getCategoryL2(Category.nameString, cat.getName())) {
				getCategoryL1(Category.nameString, cat.getParent().getName())
						.createRelationshipTo(cat.getNode(),
								RelationTypes.CATEGORY_LEVEL2);
				categoryLevel2Index.add(cat.getNode(), Category.nameString, cat
						.getName().toLowerCase());
				categoryLevel2Index.add(cat.getNode(), Category.idString,
						cat.getId());
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

	public static boolean insertCategoryL2(List<Category> cats) {
		boolean result = true;
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			for (int i = 0; null != cats && i < cats.size(); i++) {
				if (null == getCategoryL2(Category.nameString, cats.get(i)
						.getName())) {
					getCategoryL1(Category.nameString,
							cats.get(i).getParent().getName())
							.createRelationshipTo(cats.get(i).getNode(),
									RelationTypes.CATEGORY_LEVEL2);
					categoryLevel2Index.add(cats.get(i).getNode(),
							Category.nameString, cats.get(i).getName()
									.toLowerCase());
					categoryLevel2Index.add(cats.get(i).getNode(),
							Category.idString, cats.get(i).getId());
				}
			}
			if (result) {
				tx.success();
			} else {
				tx.failure();
			}
		} catch (Exception e) {
			log.error("Error", e);
			result = false;
		} finally {
			tx.finish();
		}
		return result;
	}

}
