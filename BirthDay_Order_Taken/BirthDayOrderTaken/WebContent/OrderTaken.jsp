<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body onload="changeHashOnLoad()" onmousedown="disableRightClick(event)"
	onkeydown="disableRefresh(event)">
	<form method="post" action="CommonServlet" onsubmit=""
		autocomplete="off">
		<div id="display" style="width: 20%; height: 20%"></div>
		<center>
			<h1 style="color: orange">
				<u>Order Taken Page</u>
			</h1>
		</center>
	</form>
</body>
<c:remove var="msg" scope="session" />
</html>