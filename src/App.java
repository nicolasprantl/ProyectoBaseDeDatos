

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
    public static void main(String[] args) throws Exception {
        // Probar la conexión a la base de datos UCU_DB
        PSQL_Connection connection = new PSQL_Connection();
        PSQL_Connection.getConnection();

        Scanner in = new Scanner(System.in);
        int option = 0;
        while (option != 7) {
            System.out.println("1. Crear tabla");
            System.out.println("2. Consultar tabla");
            System.out.println("3. Actualizar linea");
            System.out.println("4. Eliminar linea");
            System.out.println("5. Insertar linea");
            System.out.println("6. Consultar tabla por columna");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opción: ");
            option = in.nextInt();
            switch (option) {
                case 1:
                    connection.createTable();
                    break;
                case 2:
                    connection.selectAll();
                    break;
                case 3:
                    connection.updateRow();
                    break;
                case 4:
                    connection.deleteRow();
                    break;
                case 5:
                    connection.insertRow();
                    break;
                case 6:
                    connection.selectByPrimaryKey();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    connection.closeConnection();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}
