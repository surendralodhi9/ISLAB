<%-- 
    Document   : Summary.jsp
    Created on : 07/04/2019, 11:49:52 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*;"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Banking</title>
    </head>
    <body>
        <%
              String username=request.getParameter("username");
              String cname=null;
              String acn="2051170";
              double balance=0;
              Connection con=null;
              Statement st=null;
              ResultSet rs=null;
              
              try
              {
                  
                  
                  Class.forName("org.sqlite.JDBC");
                  String path="C://Users/Administrator/Documents/NetBeansProjects/ISBANK/Banking.sqlite";
                  con=DriverManager.getConnection("jdbc:sqlite:"+path);//To create database if not exist
            
                  
                  String query="SELECT *from Accounts where username='"+username+"'";
            
                  st = con.createStatement();
                  rs=st.executeQuery(query);
                  while(rs.next())
                  {
                      acn=rs.getString(2);
                      balance=rs.getDouble(3);
                  }
                  query="SELECT *from Users where username='"+username+"'";
                  rs=st.executeQuery(query);
                  while(rs.next())
                  {
                      cname=rs.getString(3);
                  }
                  
                  
              }
              catch(Exception e)
              {
                  System.out.println(e);
              }
        
        
        %>
     <a href="Summary.jsp?username=<%=username%>" style="margin: 20px " >Summary</a>
    <a href="Debit.jsp?username=<%=username%>" style="margin: 20px">Debit</a>
    <a href="Credit.jsp?username=<%=username%>" style="margin: 20px">Credit</a>
    <a href="Transactions.jsp?username=<%=username%>" style="margin: 20px">Transactions</a>
    <a href="Transfer.jsp?username=<%=username%>" style="margin: 20px">Transfer</a>
    <center>
          <h1>Summary</h1>
   
        <table border="0">
            
            <tbody>
                <tr>
                    <td>Name:</td>
                    <td><%=cname%></td>
                </tr>
                <tr>
                    <td>Account No:</td>
                    <td><%=acn%></td>
                </tr>
                <tr>
                    <td>Bank:</td>
                    <td>Online Banking</td>
                </tr>
                <tr>
                    <td>Account type:</td>
                    <td>Current Account</td>
                </tr>
                <tr>
                    <td>Balance:</td>
                    <td><%=balance%></td>
                </tr>
                <tr>
                    <td>Avg monthly balance</td>
                    <td>000</td>
                </tr>
            </tbody>
        </table>

   
        </center>
    </body>
</html>
