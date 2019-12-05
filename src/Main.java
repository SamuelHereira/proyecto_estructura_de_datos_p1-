import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        ListaDocentes l1 = new ListaDocentes();
        String cedula;

        int opc = 0;

        do {
            System.out.println("PAGO DE DOCENTES");
            System.out.println("1. Insertar Docente");
            System.out.println("2. Realizar pago a docente");
            System.out.println("3. Consular docente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opc = entrada.nextInt();

            switch(opc) {
                case 1:
                    l1.insertarDocente(Docente.crearDocente());
                    break;
                case 2:
                    try {
                        entrada.nextLine();
                        System.out.print("Cédula: ");
                        cedula = entrada.nextLine();
                        Universidad.pagarDocente(l1.buscarDocente(cedula).getDocente());
                    } catch (NullPointerException e) {
                        System.out.println("Docente no registrado, no se pudo realizar el pago");
                    }
                    break;
                case 3:
                    l1.visualizar();
                case 4:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }

        } while(opc != 4);


    }
}