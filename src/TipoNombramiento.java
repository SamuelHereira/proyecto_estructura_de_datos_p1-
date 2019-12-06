public enum TipoNombramiento {

    AUXILIAR("auxiliar"),
    AGREGADO("agregado"),
    PRINCIPAL("principal");

    private String nombre;

    private TipoNombramiento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
