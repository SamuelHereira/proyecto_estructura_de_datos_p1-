public class Modalidad {

    public static final int SUELDO_CONTRATO_COMPLETO = 2100;
    public static final int SUELDO_NOMBRAMIENTO_AUXILIAR_COMPLETO = 2100;
    public static final int SUELDO_NOMBRAMIENTO_AGREGADO_COMPLETO = 3400;
    public static final int SUELDO_NOMBRAMIENTO_PRINCIPAL_COMPLETO = 5000;

    private TipoCarga tipoCarga;

    public TipoCarga getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
    }
}
