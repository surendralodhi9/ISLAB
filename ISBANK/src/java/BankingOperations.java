import java.sql.*;
import java.text.SimpleDateFormat;


public class BankingOperations {
 
    static Connection con=null;
    static Statement st=null;
    static ResultSet rs=null;
    static String query=null;
    
    public static void main(String []args)
    {
         try
            {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:Banking.sqlite");//To create database if not exist
            String table="Accounts";
            
            
            st = con.createStatement();
            //query="delete from users";
            //st.executeUpdate(query);
            
            //query = "CREATE TABLE Accounts" +"(username varchar(30)," +" accountno VARCHAR(30)," +"balance float)";
            
            //st.executeUpdate(query);
            
            /*query = "INSERT INTO "+table+" (username,accountno,balance) VALUES (?, ?, ?)";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,"205117066");
            pst.setString(2,"11212114");
            pst.setFloat(3,0);  
            pst.executeUpdate();*/
           /* String acn="";
            String username="205117066";
            String dbalance="100";
                 Class.forName("org.sqlite.JDBC");
                  String path="C://Users/Administrator/Documents/NetBeansProjects/ISBANK/Banking.sqlite";
                  con=DriverManager.getConnection("jdbc:sqlite:"+path);//To create database if not exist
            
                  
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
                   pst.executeUpdate();*/
                   
                
            query="SELECT *from Accounts";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getDouble(3));
            
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
