<%-- 
    Document   : Credit.jsp
    Created on : 09/04/2019, 6:46:58 PM
    Author     : Administrator
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.*;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Banking</title>
    </head>
    <body>
    <center>   <h1>Credit</h1> </center>
    
     
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

            public String Customer1(String username,String dbalance)
           {
               
            try
            {
            
                
            Class.forName("org.sqlite.JDBC");
            String path="C://Users/Administrator/Documents/NetBeansProjects/ISBANK/Banking.sqlite";
            con=DriverManager.getConnection("jdbc:sqlite:"+path);//To create database if not exist
            String acn="";
            String query="SELECT *from Accounts where username='"+username+"'";
                 
                  st = con.createStatement();
                  rs=st.executeQuery(query);
                  double balance=0;
                  while(rs.next())
                  {
                      acn=rs.getString(2);
                      balance=rs.getDouble(3);
                      
                  }
                

                 double rebalance=Double.parseDouble(dbalance);
                 if(balance<rebalance)
                     return "No sufficient balance!!";
                 balance=balance-rebalance;
                 
                   
                  String account="T"+String.valueOf(acn);
                   
                   query = "INSERT INTO "+account+" (datetime,ttype,amount,balance) VALUES (?, ?, ?, ?)";
                   PreparedStatement pst=con.prepareStatement(query);
                   String timeStamp = new SimpleDateFormat("dd/MM/yyyy::HH:mm:ss").format(new java.util.Date());
                   pst.setString(1,timeStamp);
                   pst.setString(2,"Credit");
                   pst.setDouble(3,rebalance);  
                   pst.setDouble(4,balance);
                   pst.executeUpdate();
                   
                  query="UPDATE Accounts SET balance='"+balance+"'"+"where username='"+username+"'";
                  
                   st.executeUpdate(query);
                   con.close();
                   return "Transaction completed successfully!!";
           
           
        }
        catch(Exception e)
        {
            System.out.println(e);
            
        }
            return "Transaction completed successfully!!";    
       }
     }
    %>
    <%  
        String username="";
        String msg="";
       try{
       username=request.getParameter("username");
       String dbalance=request.getParameter("dbalance");
       
       
       
       if(username.length()>5&&dbalance!=null)
       {
        Customer obj=new Customer();
        msg=obj.Customer1(username,dbalance);
       
        
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
      <br>
      <h3><%=msg%> </h3>
       <form name="formdebit" action="Credit.jsp" >
        <table border="0">
            
            <tbody>
                <tr>
                    <td>Enter the amount to be credited</td>
                    <td><input type="text" name="dbalance" value="" /> </td>
                </tr>
            </tbody>
        </table>
        <input type="hidden" name="username" value="<%=username%>" />
        <br>
        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
        <input type="submit" value="Credit" name="credit_btn" />
        
    </form>
   
        
    </body>
</html>
