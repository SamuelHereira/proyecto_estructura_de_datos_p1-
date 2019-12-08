public class Administracion {

    public static void pagarDocente(Docente docente) {
        if (!docente.getPagado()) {
            docente.setPagado(true);
            System.out.println(Main.ANSI_CYAN + "Efectuando pago --> $" + docente.getSueldo() + Main.ANSI_RESET);
            System.out.println(Main.ANSI_YELLOW + "A nombre de: " + Main.ANSI_RESET + docente.getNombres() + " " + docente.getApellidos());
            System.out.println("Pago realizado con éxito");
        }
        else System.out.println("El pago a este docente ya fue realizado");
    }

}
