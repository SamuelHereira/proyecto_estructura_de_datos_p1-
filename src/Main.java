import java.util.Scanner;

public class Main {

    public static void menuPrincipal() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("PAGO DE DOCENTES");
        System.out.println("1. Insertar Docente");
        System.out.println("2. Realizar pago a docente");
        System.out.println("3. Consular docente");
        System.out.println("4. Listar docentes");
        System.out.println("5. Eliminar docente");
        System.out.println("6. Exportar registro de docentes");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        ListaDocentes l1 = new ListaDocentes();
        String cedula;

        int opc = 0;

        do {
            Main.menuPrincipal();
            opc = entrada.nextInt();

            switch(opc) {
                case 1:
                    l1.insertarDocente(Docente.crearDocente());
                    System.out.println("Datos del docente guardados con éxito");
                    break;
                case 2:
                    try {
                        entrada.nextLine();
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("REALIZAR PAGO A DOCENTE");
                        System.out.print("Cédula: ");
                        cedula = entrada.nextLine();
                        Administracion.pagarDocente(l1.buscarDocente(cedula).getDocente());
                    } catch (NullPointerException e) { }
                    break;
                case 3:
                    try {
                        entrada.nextLine();
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("CONSULTAR DOCENTE");
                        System.out.print("Cédula: ");
                        cedula = entrada.nextLine();
                        System.out.println(l1.buscarDocente(cedula).getDocente());
                    } catch (NullPointerException e) { }
                    break;
                case 4:
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("LISTA DE TODOS LOS DOCENTES");
                    l1.visualizar();
                    break;
                case 5:
                    entrada.nextLine();
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("ELIMINAR DOCENTE");
                    System.out.print("Cédula: ");
                    cedula = entrada.nextLine();
                    l1.eliminarDocente(cedula);
                    break;
                case 6:
                    l1.exportarListaTxt();
                    break;
                case 7:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
            }

        } while(opc != 7);


    }
}