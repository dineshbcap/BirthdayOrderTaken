package com.aricent.bday_celeb_helper.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.aricent.bday_celeb_helper.logfiles.StoreUserDetail;

public class CreateTables {
	public static void main(String ar[])throws IOException, ClassNotFoundException{
		Connection connectionObject = null;
		Statement statementObject = null;
		//create object for the properties class 
		Properties prop = new Properties();
		try {
			//load the properties file
			prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/com/aricent/bday_celeb_helper/model/dao/DBCredentialsAndTables.properties"));

			//get the values from the properties file 
			String connectionString = prop.getProperty("connectionString");
			String driverName = prop.getProperty("driverName");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String createDataBase=prop.getProperty("createDataBase");
			String userTable= prop.getProperty("userTable");
			String projectTable= prop.getProperty("projectTable");
			String snacksCategoryTable= prop.getProperty("snacksCategoryTable");
			String snacksTable= prop.getProperty("snacksTable");
			String projectInsert= prop.getProperty("projectInsert");
			String snacksCategoryInsert= prop.getProperty("snacksCategoryInsert");
			String snacksInsert= prop.getProperty("snacksInsert");
			String userAdminInsert=prop.getProperty("adminInsert");
			
			Class.forName(driverName);
			connectionObject = DriverManager.getConnection(connectionString, user, password);
			statementObject = connectionObject.createStatement();
			
			try{
				statementObject.executeUpdate(createDataBase);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Already present in the DB--->"+e);
			}finally {
				ConnectionDAO.DBClose(statementObject, connectionObject);
				connectionObject = ConnectionDAO.DBConnectionMethod();
				statementObject = connectionObject.createStatement();
			}
			
			try{
				statementObject.executeUpdate(projectTable);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'Project Table' Already present in the DB--->"+e);
			}
			
			try{
				statementObject.executeUpdate(userTable);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'User Table' Already present in the DB--->"+e);
			}
			
			try{
				statementObject.executeUpdate(snacksCategoryTable);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'Snacks Category Table' Already present in the DB--->"+e);
			}
			
			try{
				statementObject.executeUpdate(snacksTable);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'Snacks Table' Already present in the DB--->"+e);
			}
			
			try{
				statementObject.executeUpdate(projectInsert);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'Project Table' Intial insertion data Already present in the DB--->"+e);
			}
			
			try{
				statementObject.executeUpdate(snacksCategoryInsert);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'Snacks Category Table' Intial insertion data Already present in the DB--->"+e);
			}
			
			try{
				statementObject.executeUpdate(snacksInsert);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'Snacks Table' Intial insertion data Already present in the DB--->"+e);
			}
			
			try{
				statementObject.executeUpdate(userAdminInsert);
			}catch (SQLException e) {
				StoreUserDetail.storeUserInfo("'User Table' Intial insertion data Already present in the DB--->"+e);
			}

		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} catch (FileNotFoundException e) {
			StoreUserDetail.storeUserInfo("File not found error Occured--->"+e);
		} catch (IOException e) {
			StoreUserDetail.storeUserInfo("IOException Error Occured--->"+e);
		}finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database connection Close Error Occured--->"+e);
			}
		}
	}
}