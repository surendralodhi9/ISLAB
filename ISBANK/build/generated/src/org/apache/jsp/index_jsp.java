package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Online Banking</title>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("   ");
      out.write("\n");
      out.write("    ");
  
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
            
      out.write("\n");
      out.write("            ");
      if (true) {
        _jspx_page_context.forward("Home.jsp");
        return;
      }
      out.write("\n");
      out.write("            ");

        
       }
       else
        {
            
      out.write("<center>");
      out.print(msg);
      out.write("</center>");

        }
       }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
        
      out.write("\n");
      out.write("     \n");
      out.write("    <body>\n");
      out.write("       \n");
      out.write("    <center>\n");
      out.write("         <h1>Welcome to Online Bank</h1>\n");
      out.write("        <form name=\"loginform\" action=\"index.jsp\" method=\"POST\">\n");
      out.write("        <table border=\"0\">\n");
      out.write("           \n");
      out.write("            \n");
      out.write("            <tbody>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Username</td>\n");
      out.write("                    <td><input type=\"text\" name=\"username\" value=\"\" /></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Password</td>\n");
      out.write("                    <td>\n");
      out.write("                        <input type=\"password\" name=\"password\" value=\"\" />  \n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("            <input type=\"submit\" value=\"Login\" name=\"login_btn\" />\n");
      out.write("            <button type=\"submit\" formaction=\"Signup.jsp\">Sign up</button>\n");
      out.write("        </form>\n");
      out.write("        \n");
      out.write("    </center>\n");
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
