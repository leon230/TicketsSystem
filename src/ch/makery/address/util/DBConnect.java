package ch.makery.address.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection conn;
    private static String url = "jdbc:mysql://localhost/ticketsystem";
//    private static String url = "jdbc:mysql://leon230.ayz.pl/leon230_test";
    private static String user = "root";
    private static String pass = "";
//    private static String user = "leon230_test";
//    private static String pass = "Haslo123";
    //Class.forName("oracle.jdbc.driver.OracleDriver");
    public static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }catch(InstantiationException ie){
            System.err.println("Error: "+ie.getMessage());
        }catch(IllegalAccessException iae){
            System.err.println("Error: "+iae.getMessage());
        }
        conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;

    }
}
//            Connection con=DriverManager.getConnection(
//                    "jdbc:oracle:thin:@localhost:1521:xe","system","oracle");