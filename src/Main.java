import javax.swing.*;
import java.util.Scanner;

public class Main {

    /** Constantes ANSI */
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    /** Limpiar pantalla de consola MS-DOS */
    public static void cls() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception e) { }
    }

    /** Pausar la ejecución en consola MS-DOS */
    public static void pause() {
        try {
            new ProcessBuilder("cmd","/c","pause").inheritIO().start().waitFor();
        } catch (Exception e) { }
    }

    /** Retorna el menú principal como cadena */
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
        int opc;
        do {
            Main.cls();
            try {
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
                            System.out.println(Main.ANSI_GREEN + "REALIZAR PAGO A DOCENTE" + Main.ANSI_RESET);
                            Administracion.pagarDocente(l1.buscarDocente(Docente.pedirCedula()).getDocente());
                        } catch (NullPointerException e) { }
                        Main.pause();
                        Main.cls();
                        break;
                    case 3:
                        try {
                            System.out.println(Main.ANSI_GREEN + "CONSULTAR DOCENTE" + Main.ANSI_RESET);
                            l1.buscarDocente(Docente.pedirCedula()).getDocente().imprimirDatos();
                        } catch (NullPointerException e) { }
                        Main.pause();
                        Main.cls();
                        break;
                    case 4:
                        System.out.println(Main.ANSI_GREEN + "LISTA DE TODOS LOS DOCENTES" + Main.ANSI_RESET);
                        l1.visualizar();
                        Main.pause();
                        Main.cls();
                        break;
                    case 5:
                        System.out.println(Main.ANSI_GREEN + "ELIMINAR DOCENTE" + Main.ANSI_RESET);
                        l1.eliminarDocente(Docente.pedirCedula());
                        Main.pause();
                        Main.cls();
                        break;
                    case 6:
                        l1.exportarListaTxt();
                        Main.pause();
                        Main.cls();
                        break;
                    case 7:
                        System.exit(1);
                        break;
                    default:
                        System.out.println(Main.ANSI_RED + "Ingrese una opción valida" + Main.ANSI_RESET);
                        Main.pause();
                        Main.cls();
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida");
                Main.pause();
            } finally {
                opc = 8;
            }
        } while(opc != 7);

    }
}