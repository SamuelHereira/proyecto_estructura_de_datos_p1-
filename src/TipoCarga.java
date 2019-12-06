public enum TipoCarga {

    TIEMPO_COMPLETO("tiempo completo"),
    MEDIO_TIEMPO("medio tiempo");

    private String nombre;

    private TipoCarga(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
