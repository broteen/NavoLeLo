package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public final class DBHelper {

	/**
	 * Creates the prepared statement from the given SQL string.
	 *
	 * @param con the Database {@link Connection}
	 * @param sqlString the sql string
	 * @return the {@link PreparedStatement}
	 * @throws SQLException the SQL exception
	 */
	public static PreparedStatement createPreparedStatement(Connection con, String sqlString) throws SQLException {
		return con.prepareStatement(sqlString);        
	}

	/**
	 * Gets the result set from the given {@link PreparedStatement} .
	 *
	 * @param ps the {@link PreparedStatement} from which the {@link ResultSet} is to be extracted
	 * @param objects the parameters in the order they are to be set
	 * @return the {@link ResultSet}
	 * @throws SQLException the SQL exception
	 */

	public static PreparedStatement insertIntoPreparedStatement(PreparedStatement ps,Object... objects) throws SQLException{
		if(objects !=null){
			for(int i=0; i < objects.length; i++) {
				if(objects[i] instanceof Long) {
					ps.setLong(i+1, (Long)objects[i]);
				} else if(objects[i] instanceof String) {
					ps.setString(i+1, (String)objects[i]);
				} else if(objects[i] instanceof Timestamp) {
					ps.setTimestamp(i+1, (Timestamp)objects[i]);
				} else if(objects[i] instanceof Date) {
					ps.setDate(i+1, (Date)objects[i]);
				}else if(objects[i] instanceof Integer) {
					ps.setInt(i+1, (int)objects[i]);
				}else if(objects[i] instanceof Double) {
					ps.setDouble(i+1, (Double)objects[i]);
				}
			}
		}
		return ps;
	}

	/**
	 * Gets the result set.
	 *
	 * @param ps the ps
	 * @param objects the objects
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	public static ResultSet getResultSet(PreparedStatement ps, Object... objects) throws SQLException {
		PreparedStatement prepareStatement=insertIntoPreparedStatement(ps,objects); 
		return prepareStatement.executeQuery();
	}

	/**
	 * Gets the update info.
	 *
	 * @param ps the ps
	 * @param objects the objects
	 * @return the update info
	 * @throws SQLException the SQL exception
	 */
	public static int getUpdateInfo(PreparedStatement ps, Object... objects) throws SQLException {
		PreparedStatement prepareStatement=insertIntoPreparedStatement(ps,objects);
		int updatedrows= prepareStatement.executeUpdate();
		prepareStatement.close();
		return updatedrows;
	}

	/**
	 * Gets the result set from the sql.
	 *
	 * @param con the database {@link Connection}
	 * @param sqlString the sql string
	 * @param objects the parameters in the order they are to be set
	 * @return the result set from sql
	 * @throws SQLException the SQL exception
	 */
	public static ResultSet getResultSetFromSQL(Connection con, String sqlString, Object... objects) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(sqlString);
		ResultSet resultSet =  getResultSet(preparedStatement, objects);
		return resultSet;
	}

	/**
	 * Gets the update info from sql.
	 *
	 * @param con the con
	 * @param sqlString the sql string
	 * @param objects the objects
	 * @return the update info from sql
	 * @throws SQLException the SQL exception
	 */
	public static int getUpdateInfoFromSQL(Connection con, String sqlString, Object... objects) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(sqlString);
		return getUpdateInfo(preparedStatement, objects);

	}

	/**
	 * Closes result set.
	 *
	 * @param resultSet the resultant {@link ResultSet}
	 * @throws SQLException the SQL exception
	 */
	public static void closeResultSet(ResultSet resultSet) throws SQLException {
		if(resultSet == null) {
			return;
		} else {
			Statement st = resultSet.getStatement();
			st.close();
		}        
	}
}

