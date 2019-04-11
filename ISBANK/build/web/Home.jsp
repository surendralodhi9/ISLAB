<%-- 
    Document   : Home.jsp
    Created on : 07/04/2019, 11:32:12 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Banking</title>
    </head>
    <body>
    
    <%
        String username=request.getParameter("username");
        //username="205117066";
     %>
    <center> <h1><%=username%> Welcome to Online Banking!</h1></center>
  
       
    <a href="Summary.jsp?username=<%=username%>" style="margin: 20px " >Summary</a>
    <a href="Debit.jsp?username=<%=username%>" style="margin: 20px">Debit</a>
    <a href="Credit.jsp?username=<%=username%>" style="margin: 20px">Credit</a>
    <a href="Transactions.jsp?username=<%=username%>" style="margin: 20px">Transactions</a>
    <a href="Transfer.jsp?username=<%=username%>" style="margin: 20px">Transfer</a>
    
    </body>
</html>
