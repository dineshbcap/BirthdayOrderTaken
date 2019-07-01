package com.aricent.bday_celeb_helper.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.bday_celeb_helper.hardcode.HardCodeValues;
import com.aricent.bday_celeb_helper.model.beanclass.CommonBeanClass;
import com.aricent.bday_celeb_helper.logfiles.StoreUserDetail;
import com.aricent.bday_celeb_helper.model.dao.FindUserDAO;

public class UserSideManager {
	/*ManageUserDAO userObject = new ManageUserDAO();*/
	FindUserDAO findUserObject = new FindUserDAO();
	/*ManageQuestionsDAO questionObject = new ManageQuestionsDAO();
	ManageOrganizationDAO organizationObject = new ManageOrganizationDAO();
	ManageSubjectDAO subjectObject = new ManageSubjectDAO();*/

	/**
	 * this method will be called when, Submit button equal "signin"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public void signin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ClassNotFoundException {
		// get some detail from the selected user
		ArrayList<Object> selectedUserDetail;
		// get list of admin
		ArrayList<String> listOfAdmin;
		// get list of user
		ArrayList<String> listOfUser;
		// declare the needed variables
		String userName = request.getParameter(HardCodeValues.USER_NAME);
		String passWord = request.getParameter(HardCodeValues.PASSWORD);
		selectedUserDetail = findUserObject.validUserOrNot(userName);
		String correctPassWord = (String) selectedUserDetail.get(0);
		Boolean isAdmin = (Boolean) selectedUserDetail.get(1);
		 
		if ((userName == null) || (passWord == null)) {
			response.sendRedirect("LoginPage.jsp");
		} else if (("".equals(userName))&&("".equals(passWord))) {
			session.setAttribute("msg", "Please Enter The User Name And Password");
			response.sendRedirect("LoginPage.jsp");
		}else if (("".equals(userName))) {
			session.setAttribute("msg", "Please Enter The User Name");
			response.sendRedirect("LoginPage.jsp");
		} else if (("".equals(passWord))) {
			session.setAttribute("msg", "Please Enter The Password");
			response.sendRedirect("LoginPage.jsp?userName="+userName);
		}else if (passWord.equals(correctPassWord)) {
			session.setAttribute(HardCodeValues.NAME, userName);
			/*session.setAttribute("firstName",userObject.getListOfUserDetails(userName).get(0));
			String organizationName=organizationObject.getOrganizationName(organizationId);
			session.setAttribute("orgName",organizationName);*/
			if (isAdmin == false) {
				StoreUserDetail.storeUserInfo("User Login..." + userName);
				/*listOfSubjectForUser = subjectObject
						.getListOfSubjectName(organizationId);
				session.setAttribute(HardCodeValues.LIST_OF_SUBJECT_FOR_USER,
						listOfSubjectForUser);*/
				
				response.sendRedirect("OrderTaken.jsp");
			} else if (isAdmin == true) {
				StoreUserDetail.storeUserInfo("Admin Login..." + userName);
				/*listOfOrganization = organizationObject
						.getListOfOrganizationName();
				session.setAttribute(HardCodeValues.LIST_OF_ORGANIZATION,
						listOfOrganization);
				listOfUser = userObject.getListOfUser(0);
				session.setAttribute(HardCodeValues.LIST_OF_USER, listOfUser);
				listOfAdmin = userObject.getListOfUser(1);
				session.setAttribute(HardCodeValues.LIST_OF_ADMIN, listOfAdmin);
				listOfSubject = subjectObject.getListOfSubjectName();
				session.setAttribute(HardCodeValues.LIST_OF_SUBJECT,
						listOfSubject);
				
				
				ArrayList<StoreUsersDetails> completeUserDetails=new ArrayList<StoreUsersDetails>();
				ArrayList<StoreOrganizationDetails> completeOrganizationDetails=new ArrayList<StoreOrganizationDetails>();
				ArrayList<StoreSubjectDetails> completeSubjectDetails=new ArrayList<StoreSubjectDetails>();
				
				completeUserDetails=userObject.getUserDatas();
				completeOrganizationDetails=organizationObject.getOrganizationDatas();
				completeSubjectDetails=subjectObject.getSubjectDatas();
				session.setAttribute("completeUserDetails",completeUserDetails);
				session.setAttribute("completeOrganizationDetails", completeOrganizationDetails);
				session.setAttribute("completeSubjectDetails", completeSubjectDetails);
				
				//set the initial number and last number( used for display the whole details of user, subject and organisation)
				session.setAttribute("numberOfOrganization",completeOrganizationDetails.size());
				session.setAttribute("numberOfSubject",completeSubjectDetails.size());
				session.setAttribute("numberOfUser",completeUserDetails.size());
				
				session.setAttribute("initialNumberUser",0);
				session.setAttribute("initialNumberSubject",0);
				session.setAttribute("initialNumberOrganization",0);
				
				if(completeUserDetails.size()>10){
					session.setAttribute("lastNumberUser",10);
				}
				else{
					session.setAttribute("lastNumberUser",completeUserDetails.size());
				}
				
				if(completeSubjectDetails.size()>10){
					session.setAttribute("lastNumberSubject",10);
				}
				else{
					session.setAttribute("lastNumberSubject",completeSubjectDetails.size());
				}
				
				if(completeOrganizationDetails.size()>10){
					session.setAttribute("lastNumberOrganization",10);
				}
				else{
					session.setAttribute("lastNumberOrganization",completeOrganizationDetails.size());
				}*/
				
				response.sendRedirect("AdminPage.jsp");
			}
		} else {
			session.setAttribute("msg", HardCodeValues.ERROR_MSG);
			response.sendRedirect("LoginPage.jsp?userName="+userName);
		}
	}

	/**
	 * this method will be called when, Submit button equal "newUser"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void newUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ClassNotFoundException {
		/*ArrayList<String> arrayListObjectForOrganization;
		arrayListObjectForOrganization = organizationObject
				.getListOfOrganizationName();
		session.setAttribute(HardCodeValues.LIST_OF_ORGANIZATION,
				arrayListObjectForOrganization);*/
		response.sendRedirect("NewUser.jsp");
	}
}

