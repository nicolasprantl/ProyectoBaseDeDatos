
/**
 *
 * @author nprantl
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@SuppressWarnings("all")
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
                System.out.println("Conexión exitosa");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ((Throwable) ex).printStackTrace();
        }
        return con;
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Conexion cerradas");
            }
        } catch (SQLException ex) {
            ((Throwable) ex).printStackTrace();
        }
    }

    public void selectAll() {
        Statement stmt = null;

        String tableName = null;

        String tableQuery = null;
        
        String text = "";

        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.print("Ingrese el nombre de la tabla : ");

            tableName = in.nextLine();
           
            tableQuery = "SELECT * FROM  " + tableName + ";";
            
            ResultSet response = stmt.executeQuery(tableQuery);
            while (response.next()) {
                text = "";
                for (int i = 1; i <= response.getMetaData().getColumnCount(); i++) {
                    text = text + response.getString(i) + " | ";
                }
                System.out.println(text);

            }
        } catch (Exception e) {
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
           System.exit(0);
        }
    }

    public void createTable() {

        Statement stmt = null;

        String tableName = null;

        String createTableQuery = null;

        Integer colNumber = 0;

        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.print("Nombre de la tabla: ");

            tableName = in.nextLine();

            if (!tableName.isEmpty()) {
                System.out.print("Numero de columnas : ");

                colNumber = Integer.parseInt(in.nextLine());

                createTableQuery = "CREATE TABLE  \"" + tableName + "\"  ( ";

                for (int i = 1; i <= colNumber; i++) {

                    System.out.print("Ingrese la columna" + i + " Nombre y tipo de dato EJ(ejemplo integer):");

                    String ColValue = in.nextLine();

                    createTableQuery = createTableQuery + ColValue + " ,";

                }

                createTableQuery = createTableQuery + " );";

                createTableQuery = createTableQuery.replace(" , ", "");

                stmt.executeUpdate(createTableQuery);

                stmt.close();
            } else {
                stmt.close();
            }

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }

        System.out.println("Tabla creada con exito.");

    }

    public void deleteRow() {

        Statement stmt = null;

        String tableName = null;

        String deleteRowQuery = null;

        String colName = null;

        String colValue = null;

        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.print("Ingrese el nombre de la tabla : ");

            tableName = in.nextLine();

            System.out.print("Ingrese el nombre de la columna : ");

            colName = in.nextLine();

            System.out.print("Ingrese el valor de la columna : ");

            colValue = in.nextLine();

            deleteRowQuery = "DELETE FROM  " + tableName + " WHERE " + colName + " = " + colValue + ";";

            stmt.executeUpdate(deleteRowQuery);

            stmt.close();

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }

        System.out.println("Fila eliminada con exito.");

    }

    public void insertRow() {

        Statement stmt = null;

        String tableName = null;

        String insertRowQuery = null;

        String rowValues = null;

        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.print("Ingrese el nombre de la tabla : ");

            tableName = in.nextLine();

            System.out.print("Ingrese los valores de la fila a insertar separados por comas (EJ:123,'EJEMPLO','EJEMPLO2'): ");

            rowValues = in.nextLine();

            insertRowQuery = "INSERT INTO  " + tableName + " VALUES (" + rowValues + ");";

            stmt.executeUpdate(insertRowQuery);

            stmt.close();

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }

        System.out.println("Fila insertada con exito.");

    }

    public void updateRow() {

        Statement stmt = null;

        String tableName = null;

        String updateRowQuery = null;

        String colName = null;

        String colValue = null;

        String colName2 = null;

        String colValue2 = null;

        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.print("Ingrese el nombre de la tabla : ");

            tableName = in.nextLine();

            System.out.print("Ingrese el nombre de la columna : ");

            colName = in.nextLine();

            System.out.print("Ingrese el valor de la columna : ");

            colValue = in.nextLine();

            System.out.print("Ingrese el nombre de la columna a modificar : ");

            colName2 = in.nextLine();

            System.out.print("Ingrese el valor de la columna a modificar : ");

            colValue2 = in.nextLine();

            updateRowQuery = "UPDATE  " + tableName + " SET " + colName2 + " = " + colValue2 + " WHERE " + colName + " = " + colValue + ";";

            stmt.executeUpdate(updateRowQuery);

            stmt.close();

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }

        System.out.println("Fila modificada con exito.");

    }
    
    public void selectByPrimaryKey() {

        Statement stmt = null;

        String tableName = null;

        String selectRowQuery = null;

        String colName = null;

        String colValue = null;

        String text = null;

        try {

            Scanner in = new Scanner(System.in);

            stmt = con.createStatement();

            System.out.print("Ingrese el nombre de la tabla : ");

            tableName = in.nextLine();

            System.out.print("Ingrese el nombre de la columna : ");

            colName = in.nextLine();

            System.out.print("Ingrese el valor de la columna : ");

            colValue = in.nextLine();

            selectRowQuery = "SELECT * FROM  " + tableName + " WHERE " + colName + " = " + colValue + ";";

            ResultSet response = stmt.executeQuery(selectRowQuery);

            while (response.next()) {

                text = "";

                for (int i = 1; i <= response.getMetaData().getColumnCount(); i++) {

                    text = text + response.getString(i) + " | ";

                }

                System.out.println(text);

            }

            stmt.close();

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }

    }

}

