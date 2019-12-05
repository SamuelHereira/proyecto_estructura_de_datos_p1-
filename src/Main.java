import java.util.Scanner;

public class Main {
    /** Para VALIDAR CEDULA **/


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opc = 0;

        ListaDocentes l1 = new ListaDocentes();

        do {
            System.out.println("PAGO DE DOCENTES");
            System.out.println("1. Insertar Docente");
            System.out.println("2. Lista de docentes");
            System.out.println("3. Salir");

            opc = scanner.nextInt();

            switch( opc ) {
                case 1: l1.insertarDocente();
                        break;
                case 2: l1.visualizar();
                        break;
                case 3: System.exit(1);
                        break;
                default:
                        System.out.println("Ingrese una opcion valida");
            }

        } while(opc != 3);


    }

}
