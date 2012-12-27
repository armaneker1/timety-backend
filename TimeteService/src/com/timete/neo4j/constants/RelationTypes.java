package com.timete.neo4j.constants;

import org.neo4j.graphdb.RelationshipType;

public enum RelationTypes implements RelationshipType {
	INTERESTS, USER_ROOT, USER, CATEGORY_ROOT, CATEGORY_LEVEL1, CATEGORY_LEVEL2, OBJECTS, FOLLOWS, GROUP_ROOT, GROUPS

}
