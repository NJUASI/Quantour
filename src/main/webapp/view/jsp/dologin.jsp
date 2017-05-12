<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/12
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userID="";
    String password="";
    userID=request.getParameter("userID");
    password=request.getParameter("password");
    if("123".equals(userID)&&"123".equals(password)){
        request.getRequestDispatcher("login_success.jsp").forward(request,response);
    }
        %>