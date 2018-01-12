<%@page import="org.news.service.impl.NewsServiceImpl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deleteNews.jsp' starting page</title>
    
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
    <%
    String id=request.getParameter("nid");
    int nid=Integer.parseInt(id);
    NewsServiceImpl impl=new NewsServiceImpl();
    int num=impl.deleteNews(nid);
    if(num>0){
    request.getRequestDispatcher("admin.jsp").forward(request, response);
    }else{
    request.getRequestDispatcher("doNotDelete.jsp").forward(request, response);
    }
     %>
  </body>
</html>
