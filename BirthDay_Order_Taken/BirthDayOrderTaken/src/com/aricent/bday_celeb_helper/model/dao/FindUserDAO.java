package com.aricent.bday_celeb_helper.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aricent.bday_celeb_helper.hardcode.HardCodeValues;
import com.aricent.bday_celeb_helper.logfiles.StoreUserDetail;

public class FindUserDAO {
	private Connection connectionObject;
	private PreparedStatement statementObject;
	private ResultSet resultSetObject;
	private ArrayList<Object> getListOfUser;
	private final String USER_SELECT = "select * from User where name=?";

	/**
	 * check the user is valid or not. valid mean return the password and admin
	 * or not status
	 * 
	 * @param userName
	 * @return ListOfUser
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Object> validUserOrNot(String userName)
			throws ClassNotFoundException, IOException {
		getListOfUser = new ArrayList<Object>();
		String passWord = "";
		Boolean isAdmin = false;
		int databasePossition = 1;
		int arrayListPossition = 0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement(USER_SELECT);
			statementObject.setString(databasePossition, userName);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				passWord = resultSetObject.getString(HardCodeValues.PASSWORD);
				isAdmin = resultSetObject.getBoolean(HardCodeValues.IS_ADMIN);
			}
			// add password and admin or not status in arraylist
			getListOfUser.add(arrayListPossition, passWord);
			getListOfUser.add(++arrayListPossition, (Boolean) isAdmin);
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist value to the calling method
		return getListOfUser;
	}
	
	public boolean uniqueIdentifier(String tableName,String filterName,String stringCheckField,long intCheckField) throws ClassNotFoundException, IOException{
		boolean isExsist=false;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement("select * from "+tableName+" where "+filterName+"=?");
			if(!(stringCheckField.equals("null"))){
				statementObject.setString(1,stringCheckField);
			}
			else{
				statementObject.setLong(1,intCheckField);
			}
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				isExsist=true;
			}
			
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		return isExsist;
	}
	
	public int uniqueIdentifier1(String filterName,String stringCheckField,long intCheckField) throws ClassNotFoundException, IOException{
		int userId=0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement("select userId from user where "+filterName+"=?");
			if(!(stringCheckField.equals("null"))){
				statementObject.setString(1,stringCheckField);
			}
			else{
				statementObject.setLong(1,intCheckField);
			}
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				userId=resultSetObject.getInt("userId");
			}
			
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		return userId;
	}
}
