package com.timete.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;

import com.timete.neo4j.utils.Neo4jUtils;

public class CypherTest {
 
	public static void main(String[] args) {
		
		try {
			ExecutionEngine engine = new ExecutionEngine(  Neo4jUtils.graphDb );
			SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss.SSS");
			
			
			Date a=new Date();
			ExecutionResult result = engine.execute( "START event=node(0) RETURN event " );
			Date b=new Date();
			System.err.println(format.format(a));
			System.err.println(format.format(b));
			System.err.println(b.getTime()-a.getTime());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
