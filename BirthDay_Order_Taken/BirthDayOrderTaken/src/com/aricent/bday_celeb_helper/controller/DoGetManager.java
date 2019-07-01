package com.aricent.bday_celeb_helper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.bday_celeb_helper.hardcode.HardCodeValues;
import com.aricent.bday_celeb_helper.logfiles.StoreUserDetail;

public class DoGetManager {
	//ManageUserDAO userObject = new ManageUserDAO();
	//ManageSubjectDAO subjectObject = new ManageSubjectDAO();
	UserSideManager userAreaObject = new UserSideManager();
	/**
	 * This method is used for get the input name and based on that it will do
	 * some operation and transfer the page
	 * @param commonName
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 * @throws ServletException
	 * @throws ClassNotFoundException
	 */
	public void getCommonName(String commonName, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException, ClassNotFoundException {
		try {
			session = request.getSession();
			if (commonName.equals(HardCodeValues.NEW_USER)) {
				userAreaObject.newUser(request, response, session);
			}

			/**
			 * this function is called when we click the logout button .
			 */
			else if (commonName.equals(HardCodeValues.LOGOUT)) {
				StoreUserDetail.storeUserInfo("Logout..."
						+ session.getAttribute(HardCodeValues.NAME));
				session.invalidate();

				response.sendRedirect("LoginPage.jsp");
			}
		} catch (NullPointerException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."
			+ "---> Error Detail..." + e);
			response.sendRedirect("ErrorDisplayPage.jsp");
		}
	}
}
