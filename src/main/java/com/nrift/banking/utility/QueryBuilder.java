package com.nrift.banking.utility;

public class QueryBuilder {
	String tableNames="";
	public QueryBuilder(String ...tables ) {
		tableNames=tables[0];
		for(int i=1; i < tables.length; i++)
			tableNames=tableNames + " NATURAL JOIN " + tables[i];
	}
	public String select(String...columns ) {
		String query="SELECT * FROM " + tableNames;
		for(int i=1; i < columns.length; i++)
			query=query+" WHERE " + columns[0] + " =?";
		return query;
	}

}
