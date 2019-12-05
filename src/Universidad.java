public class Universidad {

    public static void pagarDocente(Docente docente) {
        if (!docente.getPagado()) {
            docente.setPagado(true);
            System.out.println("Pago realizado con éxito");
        }
        else System.out.println("El pago a este docente ya fue realizado");
    }
}
