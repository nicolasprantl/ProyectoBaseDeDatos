
/**
 *
 * @author nprantl
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PSQL_Connection {

    private static Connection con = null;

    public static Connection getConnection() {
        try {
            if (con == null) {
                String driver = "org.postgresql.Driver"; //el driver varia segun la DB que usemos
                String url = "jdbc:postgresql://localhost:5432/UCU_DB?autoReconnect=true";
                String pwd = "admin123";
                String usr = "admin";
                Class.forName(driver);
                con = DriverManager.getConnection(url, usr, pwd);
                System.out.println("Conection Succesfull");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ((Throwable) ex).printStackTrace();
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException ex) {
            ((Throwable) ex).printStackTrace();
        }
    }

    public String Select() {
        Statement stmt = null;

        String tableName = null;

        String createTableQuery = null;
        
        String text = "";
        
        String columnCount = "";
        
//        try {
//            Statement execute = con.createStatement();
//            ResultSet response = execute.executeQuery("Select * from links");
//            
//
//            while (response.next()) {
//                text = text + response.getString("url") + " | " + response.getString("name") + System.getProperty("line.separator");
//            }
//
//        } catch (Exception e) {
//
//        }
//        return text;
        
        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.println("Enter TableName : ");

            tableName = in.nextLine();
            
            String tableQuery = ("select count(*) from information_schema.columns where table_name=" + "'" + tableName + "';");
            ResultSet response = stmt.executeQuery(tableQuery);
            int columnCountIndex = response.findColumn("count");
            //response.next();
            //columnCount = response.getInt(columnCountIndex);
            
            
//            if (!tableName.isEmpty()) {
//                System.out.println("Enter Number Of Column : ");
//
//                int colNumber = Integer.parseInt(in.nextLine());
//
//                createTableQuery = "CREATE TABLE  \"" + tableName + "\"  ( ";
//
//                for (int i = 1; i <= colNumber; i++) {
//
//                    System.out.println("Enter Column" + i + " Name and Data Type :");
//
//                    String ColValue = in.nextLine();
//
//                    createTableQuery = createTableQuery + ColValue + " ,";
//
//                }
//
//                createTableQuery = createTableQuery + " );";
//
//                createTableQuery = createTableQuery.replace(" , ", "");
//
//                System.out.println("Create Table Query is :" + createTableQuery);
//
//                stmt.executeUpdate(createTableQuery);
//
//                stmt.close();
//            } else {
//                stmt.close();
//            }
//
        } catch (Exception e) {
//
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//
//            System.exit(0);
//
        }
        return "";
    }

    public void CreateTable() {

        Statement stmt = null;

        String tableName = null;

        String createTableQuery = null;

        Integer colNumber = 0;

        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.println("Enter TableName : ");

            tableName = in.nextLine();

            if (!tableName.isEmpty()) {
                System.out.println("Enter Number Of Column : ");

                colNumber = Integer.parseInt(in.nextLine());

                createTableQuery = "CREATE TABLE  \"" + tableName + "\"  ( ";

                for (int i = 1; i <= colNumber; i++) {

                    System.out.println("Enter Column" + i + " Name and Data Type :");

                    String ColValue = in.nextLine();

                    createTableQuery = createTableQuery + ColValue + " ,";

                }

                createTableQuery = createTableQuery + " );";

                createTableQuery = createTableQuery.replace(" , ", "");

                System.out.println("Create Table Query is :" + createTableQuery);

                stmt.executeUpdate(createTableQuery);

                stmt.close();
            } else {
                stmt.close();
            }

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }

        System.out.println("Table Creation Done.");
    }

}
