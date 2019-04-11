<%-- 
    Document   : Debits.jsp
    Created on : 09/04/2019, 8:55:00 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Hello World!</h1>
        <%
            String username=request.getParameter("username");
            
        %>
        <table border="0">
            
            <tbody>
                <tr>
                    <td>Enter the amount to be debited</td>
                    <td><input type="text" name="dbalance" value="" /></td>
                </tr>
            </tbody>
          </table>
            
           <a href="Debit.jsp?username=<%=username%>" style="margin: 20px">Debit</a>

    </body>
</html>
