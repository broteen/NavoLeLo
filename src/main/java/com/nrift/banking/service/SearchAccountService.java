package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nrift.banking.dto.CustomerDTO;

/**
 * The Class SearchAccountService.
 */
public class SearchAccountService {

    /**
     * Search customer details.
     *
     * @param connection the connection
     * @param reqParams the req params
     * @return the list
     * @throws SQLException the SQL exception
     */
    public List<CustomerDTO> searchCustomerDetails(Connection connection,Map<String, Object> reqParams) throws SQLException {

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
        return new AdminService().getCustomerSearchDetails(connection, query.toString(), reqParams.values().toArray());

    }	

}
