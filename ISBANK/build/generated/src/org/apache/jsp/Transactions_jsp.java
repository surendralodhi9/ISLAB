package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;;

public final class Transactions_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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
      out.write("    <title>Transactions</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Hello World!</h1>\n");
      out.write("        \n");
      out.write("        ");

            String username=request.getParameter("username");
            String acn="2051170";
              
              Connection con=null;
              Statement st=null;
              ResultSet rs=null;
            String [][]tran=new String[14][4];
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
                      
                  }
                  String account="T"+String.valueOf(acn);
                  query="select *from "+account+" where rownum<=10 order by datetime ";
                  int i=0;
                  rs=st.executeQuery(query);
                  while(rs.next())
                  {
                      tran[i][0]=rs.getString(1);
                      tran[i][1]=rs.getString(2);
                      tran[i][2]=rs.getString(3);
                      tran[i][3]=rs.getString(4);
                      i++;
                  }
                 
                
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        
        
      out.write("\n");
      out.write("    <a href=\"Summary.jsp?username=");
      out.print(username);
      out.write("\" style=\"margin: 20px \" >Summary</a>\n");
      out.write("    <a href=\"Debit.jsp?username=");
      out.print(username);
      out.write("\" style=\"margin: 20px\">Debit</a>\n");
      out.write("    <a href=\"Credit.jsp?username=");
      out.print(username);
      out.write("\" style=\"margin: 20px\">Credit</a>\n");
      out.write("    <a href=\"Transactions.jsp?username=");
      out.print(username);
      out.write("\" style=\"margin: 20px\">Transactions</a>\n");
      out.write("    <a href=\"Transfer.jsp?username=");
      out.print(username);
      out.write("\" style=\"margin: 20px\">Transfer</a>\n");
      out.write("    \n");
      out.write("        <table border=\"1\">\n");
      out.write("            <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Date</th>\n");
      out.write("                    <th>Transaction</th>\n");
      out.write("                    <th>Amount</th>\n");
      out.write("                    <th>Balance</th>\n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("            <tbody>\n");
      out.write("                <tr>\n");
      out.write("                        ");

                       for(int i=0;i<10;i++)
                      {
                    
      out.write("<td>");
      out.print(tran[i][0]);
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(tran[i][1]);
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(tran[i][2]);
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(tran[i][3]);
      out.write("</td>\n");
      out.write("                  </tr>\n");
      out.write("                    ");

                      }
                    
      out.write("\n");
      out.write("               \n");
      out.write("                \n");
      out.write("               \n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
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
