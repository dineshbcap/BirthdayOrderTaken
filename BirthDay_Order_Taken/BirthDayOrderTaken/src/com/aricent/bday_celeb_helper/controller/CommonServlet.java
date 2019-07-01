package com.aricent.bday_celeb_helper.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.bday_celeb_helper.hardcode.HardCodeValues;
import com.aricent.bday_celeb_helper.logfiles.StoreUserDetail;
import com.aricent.bday_celeb_helper.controller.UsersManager;
import com.aricent.bday_celeb_helper.controller.UserSideManager;

/**
 * Servlet implementation class CommonServlet
 * 
 * This program is a controller for this("Birthday Celebration Helper") project.
 * 
 * below we use, doGet() and doPost() doGet is used, when the user click
 * button or dropdown in the UI, JavaScript will called then servlet (doGet()
 * will called) with some needed parameter
 * 
 * doPost is used when submit the form.
 */

public class CommonServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;
	UsersManager usersObject = new UsersManager();
	DoGetManager doGetObject = new DoGetManager();
	UserSideManager userAreaObject = new UserSideManager();
	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public CommonServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) set the session values where needed in this doGet()
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			session = request.getSession();
			String commonName = request
					.getParameter(HardCodeValues.COMMON_NAME);
				try {
					doGetObject.getCommonName(commonName, request, response,
							session);
				} catch (ClassNotFoundException e) {
					StoreUserDetail
							.storeUserInfo("Error Occured In This User..."
									+ (String) session.getAttribute("name")
									+ "---> Error Detail..." + e);
				}
		} catch (NullPointerException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."

			+ "---> Error Detail..." + e);
			response.sendRedirect("ErrorDisplayPage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) set the session values where needed in this doPost()
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// get the submit button value
			String submitButton = request.getParameter(HardCodeValues.SUBMIT);
			session = request.getSession();
			
			/**
			 * if submit button value equal signin mean enter to this
			 * function(Login.jsp->submit Button)
			 */
			if (submitButton.equals(HardCodeValues.SIGNIN)) {
				userAreaObject.signin(request, response, session);
			}
			/**
			 * if submit button value equal userSubmit mean enter to this
			 * function(AddUser.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.ADD_USER)) {
				usersObject.addUser(request, response, session);
			}
			/**
			 * if submit button value equal Modify mean enter to this
			 * function(ModifyUser.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.MODIFY_USER)) {
				usersObject.modifyUser(request, response, session);
			} else if (submitButton.equals(HardCodeValues.DELETE_USER)) {
				usersObject.deleteUser(request, response, session);
			}
		} catch (NullPointerException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."
					+ "---> Error Detail..." + e);
			response.sendRedirect("ErrorDisplayPage.jsp");
		} catch (ClassNotFoundException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."
					+ (String) session.getAttribute("name")
					+ "---> Error Detail..." + e);
		}
	}
} 
