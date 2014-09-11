package com.nrift.banking.utility;

import java.sql.SQLException;
import java.util.Map;

/**
 * The Class QueryBuilder.
 */
public class QueryBuilder {
	/**
     * Build query to fetch customer details.
     * @param reqParams the request params
     * @return the query string of type string
     * @throws SQLException the SQL exception
     */
	public String select(Map<String, Object> reqParams){
		String baseQuery="Select * from";
        StringBuffer query= new StringBuffer(baseQuery);
        query.append(" customer ")
        .append(" natural join ")
        .append(" account ")
        .append(" natural join ")
        .append(" account_type ");
        if(!reqParams.isEmpty())
            query.append(" where ");
        if(reqParams.containsKey("customerName"))
            query.append("name=?");
        if(reqParams.containsKey("customerId"))
            query.append("customer_id=?");
        if(reqParams.containsKey("accountNo"))
            query.append("account_number=?");
        if(reqParams.containsKey("panNo"))
            query.append("pan_number=?");
        return query.toString();
	}
}
