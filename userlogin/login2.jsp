<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<h1 align="center">用户意见反馈(Post方式)</h1>
	<form action="servlet/LoginServlet" method="post">
		<table>
			<tr>
				<td>意见：</td>
				<td><input type="text" name="comment">
				</td>
			</tr>
			<tr>
				<td>联系方式：</td>
				<td><input type="text" name="contact">
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="一键提交">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
