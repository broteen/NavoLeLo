package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.utility.QueryBuilder;

/**
 * The Class SearchAccountService.
 */
public class SearchAccountService {

    /**
     * Search customer details.
     *
     * @param connection is a parameter of type java.sql.connection
     * @param reqParams the request params
     * @return the list of type CustomerDTO
     * @throws SQLException the SQL exception
     */
    public List<CustomerDTO> searchCustomerDetails(Connection connection,Map<String, Object> reqParams) throws SQLException {

        String query= new QueryBuilder().select(reqParams);
        return new AdminService().getCustomerSearchDetails(connection, query, reqParams.values().toArray());

    }	

}
