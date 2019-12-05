import java.util.Scanner;

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

    private boolean validacion(String cedula) {

        int sumaimpar = 0, sumapar = 0, digito = 0, digitofinal = 0, resultado = 0, digitovalidacion = 0;

        for(int i = 0; i < 9; i++) {
            if(i % 2 == 0) {
                digito = Character.getNumericValue(cedula.charAt(i));
                digitofinal = digito*2;
                if(digitofinal>9) {
                    digitofinal -= 9;
                }
                sumaimpar += digitofinal;
            }
            else {
                sumapar += Character.getNumericValue(cedula.charAt(i));
            }

        }
        resultado = sumaimpar + sumapar;

        digitovalidacion = resultado % 10;
        return (digitovalidacion + Character.getNumericValue(cedula.charAt(9)) == 10);
    }

    /**Para INGRESAR DATOS DEL DOCENTE **/
    public void ingresarDatos(Docente docente) {
        String nombre, apellido, cedula, direccion, correo, telefono;
        int modalidad, tipoCarga, horasFalta;
        Scanner ingresar = new Scanner(System.in);

        System.out.println("Ingrese los nombres del docente");
        nombre = ingresar.nextLine();
        docente.setNombres(nombre);

        System.out.println("Ingrese los apellidos del docente");
        apellido = ingresar.nextLine();
        docente.setApellidos(apellido);

        do {
            System.out.println("Ingrese la cedula del docente");
            cedula = ingresar.nextLine(); // VALIDAR CEDULA
            if(!validacion(cedula)) {
                System.out.println("Ingrese una cedula valida");
            }
        } while(!validacion(cedula));
        docente.setCedula(cedula);

        System.out.println("Ingrese la direccion del docente");
        direccion = ingresar.nextLine();
        docente.setDireccion(direccion);

        do {
            System.out.println("Ingrese el correo del docente");
            correo = ingresar.nextLine(); // VALIDAR QUE CONTENGA @
            if(!correo.contains("@")) {
                System.out.println("Ingrese un correo valido");
            }
        } while(!correo.contains("@"));
        docente.setCorreo(correo);

        do {
            System.out.println("Ingrese el numero de telefono del docente");
            telefono = ingresar.nextLine();
        } while(telefono.length() != 10 || !telefono.matches("[0-9]+")); // VALIDAR QUE SEA DE 10 DIGITOS
        docente.setTelefono(telefono);

        do {
            System.out.println("Modalidad de contrato del docente: \n1. Contrato \t 2. Nombramiento");
            modalidad = ingresar.nextInt();
        } while(modalidad != 1 && modalidad != 2);
        if (modalidad == 1) {
            docente.setModalidad(new Contrato());
        } else if (modalidad == 2) {
            int tipo;
            docente.setModalidad(new Nombramiento());
            do {
                System.out.println("Tipo de nombramiento del docente \n1. Auxiliar \t 2. Agregado \t 3. Pricipal");
                tipo = ingresar.nextInt();
                if (docente.getModalidad() instanceof Nombramiento) {
                    if (tipo == 1) {
                        ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.AUXILIAR);
                    } else if (tipo == 2) {
                        ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.AGREGADO);
                    } else if (tipo == 3) {
                        ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.PRINCIPAL);
                    }

                }
            } while(tipo != 1 && tipo != 2 && tipo != 3);
        }

        do {
            System.out.println("Tipo de carga del docente \n1. Medio Tiempo \t 2. Tiempo Completo");
            tipoCarga = ingresar.nextInt();
        } while(tipoCarga !=1 && tipoCarga != 2);
        if (tipoCarga == 1) {
            docente.getModalidad().setTipoCarga(TipoCarga.MEDIO_TIEMPO);
        } else if (tipoCarga == 2) {
            docente.getModalidad().setTipoCarga(TipoCarga.TIEMPO_COMPLETO);
        } else {
            /** VALIDAR **/
        }


        do {
            System.out.println("Ingrese el numero de horas faltadas");
            horasFalta = ingresar.nextInt();
        } while(horasFalta < 0);
        docente.setHorasFaltas(horasFalta);

        docente.setHorasTrabajo(30);
        docente.setSueldo();


    }
}
