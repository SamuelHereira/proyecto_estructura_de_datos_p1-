import javax.print.Doc;
import java.util.Scanner;

public class Docente {

    //---------------------Atributos--------------------//
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
    private boolean isPagado;

    //---------------------Constructor vacio--------------------//
    public Docente() {}

    //---------------------Getters y Setters--------------------//
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
        this.horasTrabajo = dias * 12;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo() {
        /** Dependiendo de la modalidad */
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
        /** Dependiendo del tipo de carga */
        float valPorHora = this.sueldo / this.horasTrabajo;
        if(this.modalidad.getTipoCarga() == TipoCarga.MEDIO_TIEMPO) {
            this.sueldo = this.sueldo/2;
            this.horasTrabajo = this.horasTrabajo / 2;
        }

        this.sueldo = this.sueldo - (valPorHora * horasFaltas);
    }

    public boolean getPagado() {
        return isPagado;
    }

    public void setPagado(boolean isPagado) {
        this.isPagado = isPagado;
    }

    //---------------------Validaciones--------------------//

    private static boolean validacionCedula(String cedula) {

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

    //---------------------Crear Instancias de Docente--------------------//
    public static Docente crearDocente() {

        Scanner ingresar = new Scanner(System.in);

        Docente docente = new Docente();
        String nombre, apellido, cedula, direccion, correo, telefono;
        int modalidad, tipoCarga, horasFalta;

        System.out.println("-----------------------------------------------------------------");

        System.out.println("INGRESE LOS TODOS LOS DATOS DEL DOCENTE");
        System.out.print("Nombres: ");
        nombre = ingresar.nextLine();
        docente.setNombres(nombre); // nombres

        System.out.print("Apellidos: ");
        apellido = ingresar.nextLine();
        docente.setApellidos(apellido); // apellidos

        do {
            System.out.print("Cédula: ");
            cedula = ingresar.nextLine();
            if(!validacionCedula(cedula)) {
                System.out.println("Ingrese una cedula valida");
            }
        } while(!validacionCedula(cedula));
        docente.setCedula(cedula); // cédula validada

        System.out.print("Dirección domiciliaria: ");
        direccion = ingresar.nextLine();
        docente.setDireccion(direccion); // dirección

        do {
            System.out.print("Correo electrónico: ");
            correo = ingresar.nextLine();
            if(!correo.contains("@")) {
                System.out.println("Ingrese un correo valido");
            }
        } while(!correo.contains("@"));
        docente.setCorreo(correo); // correo validado

        do {
            System.out.print("Teléfono: ");
            telefono = ingresar.nextLine();
        } while(telefono.length() != 10 || !telefono.matches("[0-9]+"));
        docente.setTelefono(telefono); // teléfono validado

        do {
            System.out.print("Modalidad de contrato del docente\n1. Contrato \t2. Nombramiento\nSeleccione una opción: ");
            modalidad = ingresar.nextInt();
        } while(modalidad != 1 && modalidad != 2);
        if (modalidad == 1) {
            docente.setModalidad(new Contrato()); // modalidad contrato
        } else if (modalidad == 2) {
            int tipo;
            docente.setModalidad(new Nombramiento()); // modalidad nombramiento
            do {
                System.out.print("Tipo de nombramiento del docente \n1. Auxiliar \t2. Agregado \t3. Pricipal\nSeleccione una opción: ");
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
            System.out.print("Tipo de carga del docente \n1. Medio Tiempo \t2. Tiempo Completo\nSeleccione una opción: ");
            tipoCarga = ingresar.nextInt();
        } while(tipoCarga !=1 && tipoCarga != 2);
        if (tipoCarga == 1) {
            docente.getModalidad().setTipoCarga(TipoCarga.MEDIO_TIEMPO); // carga medio tiempo
        } else if (tipoCarga == 2) {
            docente.getModalidad().setTipoCarga(TipoCarga.TIEMPO_COMPLETO); // carga tiempo completo
        }

        do {
            System.out.print("Cantidad horas faltadas: ");
            horasFalta = ingresar.nextInt();
        } while(horasFalta < 0);
        docente.setHorasFaltas(horasFalta); // horas faltadas

        docente.setHorasTrabajo(30); // horas trabajadas
        docente.setSueldo(); // sueldo total
        docente.setPagado(false);

        return docente; // devuelve la instancia de docente completamente validada
    }

    //---------------------Datos del Docente--------------------//
    @Override
    public String toString() {
        String msjPago;
        if (this.isPagado) {
            msjPago = "El pago ya ha sido realizado con éxito";
        } else {
            msjPago = "El pago aún no ha sido realizado";
        }

        String msjModalidad;
        if (this.modalidad instanceof  Contrato) {
            msjModalidad = "Modalidad: Contrato y carga " + this.modalidad.getTipoCarga().getNombre();
        } else {
            msjModalidad = "Modalidad: Nombramiento de tipo " + ((Nombramiento) this.modalidad).getTipoNombramiento().getNombre() + " y tipo de carga " + this.modalidad.getTipoCarga().getNombre();
        }
        return "\n-----------------------------------------------------------------" +
                "\nNombres: " + this.nombres + "\nApellidos: " + this.apellidos +
                "\nCédula: " + this.cedula + "\nDirección: " + this.direccion +
                "\nCorreo: " + this.correo + "\nTeléfono: " + this.telefono +
                "\n" + msjModalidad + "\nHoras trabajas: " + this.horasTrabajo +
                "\nHoras faltadas: " + this.horasFaltas + "\nSueldo: " + this.sueldo +
                "\n" + msjPago;
    }
}
