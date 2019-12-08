import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void cls() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception e) {

        }
    }

    public static void pause() {
        try {
            new ProcessBuilder("cmd","/c","pause").inheritIO().start().waitFor();
        } catch (Exception e) {

        }
    }

    public static String menuPrincipal() {
        return "PAGO DE DOCENTES" +
        "\n1. Insertar Docente" +
        "\n2. Realizar pago a docente" +
        "\n3. Consular docente" +
        "\n4. Listar docentes" +
        "\n5. Eliminar docente" +
        "\n6. Exportar registro de docentes" +
        "\n7. Salir" +
        "\nSeleccione una opción: ";
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        ListaDocentes l1 = new ListaDocentes();
        String cedula;
        int opc;
        do {
            Main.cls();
            opc = Integer.parseInt(JOptionPane.showInputDialog(Main.menuPrincipal()));
            switch(opc) {
                case 1:
                    l1.insertarDocente(Docente.crearDocente());
                    System.out.println("Datos del docente guardados con éxito");
                    Main.pause();
                    Main.cls();
                    break;
                case 2:
                    try {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("REALIZAR PAGO A DOCENTE");
                        System.out.print("Cédula: ");
                        cedula = entrada.nextLine();
                        Administracion.pagarDocente(l1.buscarDocente(cedula).getDocente());
                    } catch (NullPointerException e) { }
                    Main.pause();
                    Main.cls();
                    break;
                case 3:
                    try {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("CONSULTAR DOCENTE");
                        System.out.print("Cédula: ");
                        cedula = entrada.nextLine();
                        System.out.println(l1.buscarDocente(cedula).getDocente());
                    } catch (NullPointerException e) { }
                    Main.pause();
                    Main.cls();
                    break;
                case 4:
                    System.out.println("-----------------------------------------------------------------");
                    System.out.print("LISTA DE TODOS LOS DOCENTES");
                    l1.visualizar();
                    Main.pause();
                    Main.cls();
                    break;
                case 5:
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("ELIMINAR DOCENTE");
                    System.out.print("Cédula: ");
                    cedula = entrada.nextLine();
                    l1.eliminarDocente(cedula);
                    Main.pause();
                    Main.cls();
                    break;
                case 6:
                    System.out.println("-----------------------------------------------------------------");
                    l1.exportarListaTxt();
                    Main.pause();
                    Main.cls();
                    break;
                case 7:
                    System.out.println("-----------------------------------------------------------------");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
                    Main.pause();
                    Main.cls();
            }

        } while(opc != 7);


    }
}