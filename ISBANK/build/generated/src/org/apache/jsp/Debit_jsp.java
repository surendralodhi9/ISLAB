package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.sql.*;;

public final class Debit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


        class Customer
        {
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            String query=null;
           public Customer(String username,String dbalance)
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
                  
                  balance=balance+Double.parseDouble(dbalance);
                 
                  query="UPDATE Accounts SET balance='"+balance+"'"+"where username='"+username+"'";
                  st.executeUpdate(query);
                  
                   String account="T"+String.valueOf(acn);
                   query = "INSERT INTO "+account+" (datetime,ttype,amount,balance) VALUES (?, ?, ?, ?)";
                   PreparedStatement pst=con.prepareStatement(query);
                   String timeStamp = new SimpleDateFormat("dd/MM/yyyy::HH:mm:ss").format(new java.util.Date());
                   pst.setString(1,timeStamp);
                   pst.setString(2,"Debit");
                   pst.setDouble(3,Double.parseDouble(dbalance));  
                   pst.setDouble(4,balance);
                   pst.executeUpdate();
              
                    
            
           }
           
        }
        catch(Exception e)
        {
            System.out.println(e);
            
        }
       }
     }
    
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Online Banking</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("    <center> <h1> Debit</h1></center>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    ");
      out.write("\n");
      out.write("    ");
  
        String username="";
       try{
       username=request.getParameter("username");
       String dbalance=request.getParameter("dbalance");
       
       
      out.print(username);

       
      out.print(dbalance);

       
       if(username.length()>5&&dbalance!=null)
       {
        Customer obj=new Customer(username,dbalance);
        String msg="Data inserted successfully!!";
        
      out.print(msg);

       }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
        
      out.write("\n");
      out.write("       <form name=\"formdebit\" action=\"Debit.jsp\" >\n");
      out.write("        <table border=\"0\">\n");
      out.write("            \n");
      out.write("            <tbody>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Enter the amount</td>\n");
      out.write("                    <td><input type=\"text\" name=\"dbalance\" value=\"\" /> </td>\n");
      out.write("                </tr>\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("        <input type=\"hidden\" name=\"username\" value=\"");
      out.print(username);
      out.write("\" />\n");
      out.write("        <input type=\"submit\" value=\"Deposit\" name=\"deposit_btn\" />\n");
      out.write("        \n");
      out.write("    </form>\n");
      out.write("     \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
