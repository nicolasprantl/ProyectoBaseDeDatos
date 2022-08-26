import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
        // Probar la conexión a la base de datos
        PSQL_Connection connection = new PSQL_Connection();
        PSQL_Connection.getConnection();
        connection.CreateTable();
    }

    // // Insertar un registro en la tabla dados los siguientes datos como parámetros: nombre de la tabla, nombre de la columna, tipo de dato y tamaño de la columna
    // public static void insertRecord(String tableName, String columnName, String columnValue) throws Exception {
    //     Connection conn = dbConnection.getConnection();
    //     if (conn != null) {
    //         String sql = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (" + columnValue + ")";
    //         conn.createStatement().execute(sql);
    //         System.out.println("Registro insertado con éxito");
    //     } else {
    //         System.out.println("Error al insertar el registro");
    //     }
    // }

    // // listar los registros de la tabla dados los siguientes datos como parámetros: nombre de la tabla
    // public static void listRecords(String tableName) throws Exception {
    //     Connection conn = dbConnection.getConnection();
    //     if (conn != null) {
    //         String sql = "SELECT * FROM " + tableName;
    //         conn.createStatement().execute(sql);
    //         System.out.println("Registros listados con éxito");
    //     } else {
    //         System.out.println("Error al listar los registros");
    //     }
    // }

}   
