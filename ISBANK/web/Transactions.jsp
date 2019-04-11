<%-- 
    Document   : Transactions.jsp
    Created on : 09/04/2019, 3:04:35 PM
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
            String username="";
            String acn="2051170";
              
              Connection con=null;
              Statement st=null;
              ResultSet rs=null;
            String [][]tran=new String[14][4];
            try
            {
                  username=request.getParameter("username");
                  
                  Class.forName("org.sqlite.JDBC");
                  String path="C://Users/Administrator/Documents/NetBeansProjects/ISBANK/Banking.sqlite";
                  con=DriverManager.getConnection("jdbc:sqlite:"+path);//To create database if not exist
            
                  
                  String query="SELECT *from Accounts where username='"+username+"'";
            
                  st = con.createStatement();
                  rs=st.executeQuery(query);
                  while(rs.next())
                  {
                      acn=rs.getString(2);
                      
                  }
                  
                  String account="T"+String.valueOf(acn);
                  
                  query="select *from "+account+" order by datetime desc ";
                  int i=0;
                  rs=st.executeQuery(query);
                  
                  while(rs.next())
                  {
                      if(i<10)
                      {
                      tran[i][0]=rs.getString(1);
                      tran[i][1]=rs.getString(2);
                      tran[i][2]=rs.getString(3);
                      tran[i][3]=rs.getString(4);
                     
                      }
                 
                      i++;
                  }
                 
                 con.close();
                
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
        <h1>Last 10 transactions</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Transaction</th>
                    <th>Amount</th>
                    <th>Balance</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                        <%
                       for(int i=0;i<10;i++)
                      {
                    %><td><%=tran[i][0]%></td>
                    <td><%=tran[i][1]%></td>
                    <td><%=tran[i][2]%></td>
                    <td><%=tran[i][3]%></td>
                  </tr>
                    <%
                      }
                    %>
               
                   
               
            </tbody>
        </table>
         </center> 
    </body>
</html>
