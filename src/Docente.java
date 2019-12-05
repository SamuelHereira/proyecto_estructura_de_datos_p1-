public class Docente {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String correo;
    private String telefono;
    private Modalidad modalidad;
    private int horasFaltas;
    private int horasTrabajo;
    private float sueldo;

    public Docente(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public int getHorasFaltas() {
        return horasFaltas;
    }

    public void setHorasFaltas(int horasFaltas) {
        this.horasFaltas = horasFaltas;
    }

    public int getHorasTrabajo() {
        return horasTrabajo;
    }

    public void setHorasTrabajo(int dias) {
        this.horasTrabajo = dias*12;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo() {
        /**Dependiendo de la modalidad**/
        if(this.modalidad instanceof Contrato) {
            this.sueldo = Modalidad.SUELDO_CONTRATO_COMPLETO;
        } else if(this.modalidad instanceof Nombramiento) {

            if(((Nombramiento) this.modalidad).getTipoNombramiento() == TipoNombramiento.AUXILIAR) {
                this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_AUXILIAR_COMPLETO;
            } else if(((Nombramiento) this.modalidad).getTipoNombramiento() == TipoNombramiento.AGREGADO) {
                this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_AGREGADO_COMPLETO;
            } else if(((Nombramiento) this.modalidad).getTipoNombramiento() == TipoNombramiento.PRINCIPAL) {
                this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_PRINCIPAL_COMPLETO;
            }
        }
        /** Dependiendo del tipo de carga **/
        if(this.modalidad.getTipoCarga() == TipoCarga.MEDIO_TIEMPO) {
            this.sueldo = this.sueldo/2;
        }

        this.sueldo = this.sueldo - ((this.sueldo/horasTrabajo)*horasFaltas);
    }
}
