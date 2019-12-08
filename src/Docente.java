import java.util.Scanner;

public class Docente {

    /** Atributos */
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

    /** Constructor */
    public Docente() {}

    /** Getters & Setters */
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

    public void setHorasTrabajo(int dias) { // recibe la cantidad de días laborales que se establezca
        if (this.modalidad.getTipoCarga() == TipoCarga.MEDIO_TIEMPO) {
            this.horasTrabajo = ((dias * 8) / 2) - this.horasFaltas;
        } else {
            this.horasTrabajo = (dias * 8) - this.horasFaltas;
        }
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo() {
        // Dependiendo la modalidad
        if(this.modalidad instanceof Contrato) {
            if (this.modalidad.getTipoCarga() == TipoCarga.TIEMPO_COMPLETO) {
                this.sueldo = Modalidad.SUELDO_CONTRATO_COMPLETO;
            } else {
                this.sueldo = Modalidad.SUELDO_CONTRATO_MEDIO;
            }
        } else if(this.modalidad instanceof Nombramiento) {
            if(((Nombramiento) this.modalidad).getTipoNombramiento() == TipoNombramiento.AUXILIAR) {
                if (this.modalidad.getTipoCarga() == TipoCarga.TIEMPO_COMPLETO) this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_AUXILIAR_COMPLETO;
                else this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_AUXILIAR_MEDIO;
            } else if(((Nombramiento) this.modalidad).getTipoNombramiento() == TipoNombramiento.AGREGADO) {
                if (this.modalidad.getTipoCarga() == TipoCarga.TIEMPO_COMPLETO) this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_AGREGADO_COMPLETO;
                else this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_AGREGADO_MEDIO;
            } else if(((Nombramiento) this.modalidad).getTipoNombramiento() == TipoNombramiento.PRINCIPAL) {
                if (this.modalidad.getTipoCarga() == TipoCarga.TIEMPO_COMPLETO) this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_PRINCIPAL_COMPLETO;
                else this.sueldo = Modalidad.SUELDO_NOMBRAMIENTO_PRINCIPAL_MEDIO;
            }
        }
        // Descuento de horas faltadas
        float valPorHora;
        if (this.modalidad.getTipoCarga() == TipoCarga.TIEMPO_COMPLETO) valPorHora = (this.sueldo / 24) / 8;
        else valPorHora = (this.sueldo / 24) / 4;
        this.sueldo = this.sueldo - (valPorHora * horasFaltas);
    }

    public boolean getPagado() {
        return isPagado;
    }
    public void setPagado(boolean isPagado) {
        this.isPagado = isPagado;
    }

    /** Validación de patrón de cédula */
    private static boolean validacionCedula(String cedula) {

        int sumaimpar = 0, sumapar = 0, digito = 0, digitofinal = 0, resultado = 0, digitovalidacion = 0;

        for(int i = 0; i < 9; i++) {
            if(i % 2 == 0) {
                digito = Character.getNumericValue(cedula.charAt(i));
                digitofinal = digito * 2;
                if(digitofinal > 9) {
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

    /** Crear instancias de Docente correctamente validada */
    public static Docente crearDocente() {

        Scanner ingresar = new Scanner(System.in);

        Docente docente = new Docente();
        String nombre, apellido, direccion, correo, telefono, modoContrato, tipoNombramiento, tipoCarga, horasFalta;
        int modalidad = 0, nombramiento = 0, carga = 0, faltas = 0;

        System.out.println(Main.ANSI_GREEN + "INGRESE TODOS LOS DATOS DEL DOCENTE" + Main.ANSI_RESET);
        System.out.print(Main.ANSI_PURPLE + "Nombres: " + Main.ANSI_RESET);
        nombre = ingresar.nextLine();
        docente.setNombres(nombre); // nombres

        System.out.print(Main.ANSI_PURPLE + "Apellidos: " + Main.ANSI_RESET);
        apellido = ingresar.nextLine();
        docente.setApellidos(apellido); // apellidos

        docente.setCedula(pedirCedula()); // cédula validada

        System.out.print(Main.ANSI_PURPLE + "Dirección domiciliaria: " + Main.ANSI_RESET);
        direccion = ingresar.nextLine();
        docente.setDireccion(direccion); // dirección

        do {
            System.out.print(Main.ANSI_PURPLE + "Correo electrónico: " + Main.ANSI_RESET);
            correo = ingresar.nextLine();
            if ((!correo.contains("@")) && (!correo.contains("."))) {
                System.out.println(Main.ANSI_RED + "Ingrese un correo valido" + Main.ANSI_GREEN);
            }
        } while((!correo.contains("@")) || (!correo.contains(".")));
        docente.setCorreo(correo); // correo validado

        do {
            System.out.print(Main.ANSI_PURPLE + "Teléfono: " + Main.ANSI_RESET);
            telefono = ingresar.nextLine();
            if (telefono.length() != 10 || !telefono.matches("[0-9]+")) {
                System.out.println(Main.ANSI_RED + "Ingrese un teléfono válido" + Main.ANSI_GREEN);
            }
        } while(telefono.length() != 10 || !telefono.matches("[0-9]+"));
        docente.setTelefono(telefono); // teléfono validado


        do {
            System.out.print(Main.ANSI_PURPLE + "Modalidad de contrato del docente\n1. Contrato \t2. Nombramiento\nSeleccione una opción: " + Main.ANSI_RESET);
            modoContrato = ingresar.nextLine();
            if (!modoContrato.matches("[1-2]+")) System.out.println(Main.ANSI_RED + "Ingrese un opción válida" + Main.ANSI_RESET);
        } while(!modoContrato.matches("[1-2]+"));
        modalidad = Integer.parseInt(modoContrato); // opción validada

        if (modalidad == 1) {
            docente.setModalidad(new Contrato()); // modalidad contrato
        } else if (modalidad == 2) {
            docente.setModalidad(new Nombramiento()); // modalidad nombramiento
            do {
                System.out.print(Main.ANSI_PURPLE + "Tipo de nombramiento del docente \n1. Auxiliar \t2. Agregado \t3. Pricipal\nSeleccione una opción: " + Main.ANSI_RESET);
                tipoNombramiento = ingresar.nextLine();
                if (!tipoNombramiento.matches("[1-3]+")) System.out.println(Main.ANSI_RED + "Ingrese un opción válida" + Main.ANSI_RESET);
            } while(!tipoNombramiento.matches("[1-3]+"));
            nombramiento = Integer.parseInt(tipoNombramiento); // opción validada

            if (docente.getModalidad() instanceof Nombramiento) {
                if (nombramiento == 1) {
                    ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.AUXILIAR);
                } else if (nombramiento == 2) {
                    ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.AGREGADO);
                } else if (nombramiento == 3) {
                    ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.PRINCIPAL);
                }
            }
        }

        do {
            System.out.print(Main.ANSI_PURPLE + "Tipo de carga del docente \n1. Medio Tiempo \t2. Tiempo Completo\nSeleccione una opción: " + Main.ANSI_RESET);
            tipoCarga = ingresar.nextLine();
            if (!tipoCarga.matches("[1-2]+")) System.out.println(Main.ANSI_RED + "Ingrese un opción válida" + Main.ANSI_RESET);
        } while(!tipoCarga.matches("[1-2]+"));
        carga = Integer.parseInt(tipoCarga); // opción validada

        if (carga == 1) {
            docente.getModalidad().setTipoCarga(TipoCarga.MEDIO_TIEMPO); // carga medio tiempo
        } else if (carga == 2) {
            docente.getModalidad().setTipoCarga(TipoCarga.TIEMPO_COMPLETO); // carga tiempo completo
        }

        do {
            System.out.print(Main.ANSI_PURPLE + "Cantidad horas faltadas: " + Main.ANSI_RESET);
            horasFalta = ingresar.nextLine();
            if (!horasFalta.matches("[0-50]+")) System.out.println(Main.ANSI_RED + "Ingrese un opción válida" + Main.ANSI_RESET);
        } while(!horasFalta.matches("[0-50]+"));
        faltas = Integer.parseInt(horasFalta); // cantidad validada

        docente.setHorasFaltas(faltas); // horas faltadas
        docente.setHorasTrabajo(24); // horas trabajadas
        docente.setSueldo(); // sueldo total
        docente.setPagado(false);

        return docente; // devuelve la instancia de docente completamente validada
    }

    /** Pide y retorna números de cédula válidos */
    public static String pedirCedula() {
        Scanner ingresar = new Scanner(System.in);
        String cedula;
        do {
            do {
                System.out.print(Main.ANSI_PURPLE + "Cédula: " + Main.ANSI_RESET);
                cedula = ingresar.nextLine();
                if((cedula.length() != 10) || (!cedula.matches("[0-9]+"))) {
                    System.out.println(Main.ANSI_RED + "Ingrese una cédula válida" + Main.ANSI_RESET);
                }
            } while((cedula.length() != 10) || (!cedula.matches("[0-9]+")));
            if (!validacionCedula(cedula)) System.out.println(Main.ANSI_RED + "Ingrese una cédula válida" + Main.ANSI_RESET);
        } while (!validacionCedula(cedula));
        return cedula;
    }

    public String msjModalidad() {
        if (this.modalidad instanceof  Contrato) return "Contrato y carga " + this.modalidad.getTipoCarga().getNombre();
        else return "Nombramiento de tipo " + ((Nombramiento) this.modalidad).getTipoNombramiento().getNombre() + " y tipo de carga " + this.modalidad.getTipoCarga().getNombre();
    }

    public String msjPago() {
        if (this.isPagado) return  "El pago ya ha sido realizado con éxito";
        else return "El pago aún no ha sido realizado";
    }

    /** toString Datos del Docente */
    @Override
    public String toString() {
        return "-----------------------------------------------------------------" +
                "\nNombres: " + this.nombres + "\nApellidos: " + this.apellidos +
                "\nCédula: " + this.cedula + "\nDirección: " + this.direccion +
                "\nCorreo: " + this.correo + "\nTeléfono: " + this.telefono +
                "\nModalidad: " + msjModalidad() + "\nHoras trabajadas: " + this.horasTrabajo +
                "\nHoras faltadas: " + this.horasFaltas + "\nSueldo: " + this.sueldo +
                "\n" + msjPago() + "\n";
    }

    public void imprimirDatos() {
        System.out.println(Main.ANSI_BLUE + "-----------------------------------------------------------------" + Main.ANSI_RESET);
        System.out.println(Main.ANSI_YELLOW + "Nombres: " + Main.ANSI_RESET + this.nombres);
        System.out.println(Main.ANSI_YELLOW + "Apellidos: " + Main.ANSI_RESET + this.apellidos);
        System.out.println(Main.ANSI_YELLOW + "Cédula: " + Main.ANSI_RESET + this.cedula);
        System.out.println(Main.ANSI_YELLOW + "Dirección: " + Main.ANSI_RESET + this.direccion);
        System.out.println(Main.ANSI_YELLOW + "Correo: " + Main.ANSI_RESET + this.correo);
        System.out.println(Main.ANSI_YELLOW + "Teléfono: " + Main.ANSI_RESET + this.telefono);
        System.out.println(Main.ANSI_YELLOW + "Modalidad: " + Main.ANSI_RESET + msjModalidad());
        System.out.println(Main.ANSI_YELLOW + "Horas trabajadas: " + Main.ANSI_RESET + this.horasTrabajo);
        System.out.println(Main.ANSI_YELLOW + "Horas faltada: " + Main.ANSI_RESET + this.horasFaltas);
        System.out.println(Main.ANSI_YELLOW + "Sueldo: " + Main.ANSI_RESET + this.sueldo);
        System.out.println(msjPago());
    }
}