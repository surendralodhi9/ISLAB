<%-- 
    Document   : Signup.jsp
    Created on : 06/04/2019, 10:09:09 PM
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
    <center>
        <h1>Sign up page</h1>
    <form action="Signup.jsp">
        <table border="0">
            
            <tbody>
                <tr>
                    <td>Username</td>
                    <td> <input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" value="" /> </td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td> <input type="text" name="cname" value="" /></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" value="" /> </td>
                </tr>
                <tr>
                    <td>Mobile</td>
                    <td><input type="text" name="mobile" value="" /> </td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><select name="genderlist">
                        <option>Male</option>
                        <option>Female</option>
                        <option>Other</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Date of birth</td>
                    <td><input type="text" name="dob" placeholder="DD/MM/YYY" /> </td>
                </tr>
            </tbody>
        </table> 
        <input type="reset" value="Clear" name="clear_btn" style="margin:20px" />
       
        <input type="submit" value="Submit" name="submit_btn" style="margin:20px" />
        
        <button type="submit" formaction="index.jsp">Login</button>
        
    </form>
        
        </center>
    
    <%!
        class Customer
        {
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            String query=null;
           public Customer(String username,String password,String cname,String mobile,String email,String gender,String dob)
           {
               
            try
            {
            if(username.length()>5)
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
		
            String table="Users";
            int ct=0;
            st = con.createStatement();
            query="SELECT *from Accounts";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                ct++;
            }
            ct++;
            
            query = "INSERT INTO "+table+" (username,password,cname,mobile,email,gender,dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,cname);  
            pst.setString(4,mobile);
            pst.setString(5,email);
            pst.setString(6,gender);
            pst.setString(7,dob);
            pst.executeUpdate();
            int acn=112121+ct;
            String account="T"+String.valueOf(acn);
            query = "INSERT INTO "+"Accounts"+" (username,accountno,balance) VALUES (?, ?, ?)";
            pst=con.prepareStatement(query);
            pst.setString(1,username);
            pst.setString(2,String.valueOf(acn));
            pst.setFloat(3,0);  
            pst.executeUpdate();
            
            query = "CREATE TABLE "+account +"(datetime varchar(50)," +" ttype VARCHAR(10)," +"amount float," +"balance float)";
            st.executeUpdate(query);
            
            }
           
        }
        catch(Exception e)
        {
            System.out.println(e);
            
            
            
        }
       }
     }
    %>
    <%  
       try{
       String username=request.getParameter("username");
       String password=request.getParameter("password");
       String mobile=request.getParameter("mobile");
       String cname=request.getParameter("cname");
       
       String email=request.getParameter("email");
       String gender=request.getParameter("genderlist");
       String dob=request.getParameter("dob");
       
       if(username.length()>5)
       {
        Customer obj=new Customer(username,password,cname,mobile,email,gender,dob);
        String msg="Data inserted successfully!!";
        %><%=msg%><%
       }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
        %>
        
    </body>
</html>
