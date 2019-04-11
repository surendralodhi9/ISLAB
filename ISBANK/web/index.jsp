<%-- 
    Document   : index
    Created on : 06/04/2019, 8:56:59 PM
    Author     : Administrator
--%>

<%@page import="java.sql.*;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Banking</title>
    </head>
    
   <%!
        class Customer
        {
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            String query=null;
           public Customer()
           {
           }
           public boolean Customer1(String username,String password)
           {
            try
            {
            Class.forName("org.sqlite.JDBC");
            String path="C://Users/Administrator/Documents/NetBeansProjects/ISBANK/Banking.sqlite";
            con=DriverManager.getConnection("jdbc:sqlite:"+path);//To create database if not exist
            //con=DriverManager.getConnection("jdbc:sqlite:Banking.sqlite");
            /*String driver="com.mysql.jdbc.Driver";
	    String url="jdbc:mysql://localhost:3306/mysql";
	    String usern="root";
	    String passwo="surendra";
	    Class.forName(driver);
					
            con=DriverManager.getConnection(url,usern, passwo);*/
		
           
            
            query="SELECT *from Users where username='"+username+"'and password='"+password+"'"; 
            st=con.createStatement();
            rs=st.executeQuery(query);
            if(rs.next())
            {
             con.close();
             return true; 
            }
            else
            {
              con.close();
              return false;
            }
          
        }
        catch(Exception e)
        {
            System.out.println(e);
            
        }
            return false;
       }
     }
    %>
    <%  
       try{
           
       String username=request.getParameter("username");
       String password=request.getParameter("password");
       
       if(username!=null)
       {
        Customer obj=new Customer();
        boolean result=obj.Customer1(username,password);
        String msg="Username or password is wrong!";
        if(result)
        {
            %>
            <jsp:forward page="Home.jsp"/>
            <%
        
       }
       else
        {
            %><center><%=msg%></center><%
        }
       }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
        %>
     
    <body>
       
    <center>
         <h1>Welcome to Online Bank</h1>
        <form name="loginform" action="index.jsp" method="POST">
        <table border="0">
           
            
            <tbody>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>
                        <input type="password" name="password" value="" />  
                    </td>
                </tr>
            </tbody>
        </table>
            <input type="submit" value="Login" name="login_btn" />
            <button type="submit" formaction="Signup.jsp">Sign up</button>
        </form>
        
    </center>
    </body>
</html>
