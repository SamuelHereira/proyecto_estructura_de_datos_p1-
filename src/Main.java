import java.util.Scanner;

public class Main {

    public static void ingresarDatosDocente(Docente docente) {
        String nombre, apellido, cedula, direccion, correo, telefono;
        int modalidad, tipoCarga, horasFalta;
        Scanner ingresar = new Scanner(System.in);

        System.out.println("Ingrese los nombres del docente");
        nombre = ingresar.nextLine();
        docente.setNombres(nombre);

        System.out.println("Ingrese los apellidos del docente");
        apellido = ingresar.nextLine();
        docente.setApellidos(apellido);

        System.out.println("Ingrese la cedula del docente");
        cedula = ingresar.nextLine(); // VALIDAR CEDULA
        docente.setCedula(cedula);

        System.out.println("Ingrese la direccion del docente");
        direccion = ingresar.nextLine();
        docente.setDireccion(direccion);

        System.out.println("Ingrese el correo del docente");
        correo = ingresar.nextLine(); // VALIDAR QUE CONTENGA @
        docente.setCorreo(correo);

        System.out.println("Ingrese el numero de telefono del docente");
        telefono = ingresar.nextLine(); // VALIDAR QUE SEA DE 10 DIGITOS
        docente.setTelefono(telefono);

        System.out.println("Modalidad de contrato del docente: \n1. Contrato \t 2. Nombramiento");
        modalidad = ingresar.nextInt();
        if(modalidad == 1) {
            docente.setModalidad(new Contrato());
        } else if (modalidad == 2) {
            int tipo;
            docente.setModalidad(new Nombramiento());
            System.out.println("Tipo de nombramiento del docente \n1. Auxiliar \t 2. Agregado \t 3. Pricipal");
            tipo = ingresar.nextInt();
            if(docente.getModalidad() instanceof Nombramiento) {
                if (tipo == 1) {
                    ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.AUXILIAR);
                } else if (tipo == 2) {
                    ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.AGREGADO);
                } else if (tipo == 3) {
                    ((Nombramiento) docente.getModalidad()).setTipoNombramiento(TipoNombramiento.PRINCIPAL);
                }

            }
        } else {
            /*** VALIDAR **/
        }

        System.out.println("Tipo de carga del docente \n1. Medio Tiempo \t 2. Tiempo Completo");
        tipoCarga = ingresar.nextInt();
        if(tipoCarga == 1) {
            docente.getModalidad().setTipoCarga(TipoCarga.MEDIO_TIEMPO);
        } else if(tipoCarga == 2) {
            docente.getModalidad().setTipoCarga(TipoCarga.TIEMPO_COMPLETO);
        } else {
            /** VALIDAR **/
        }

        System.out.println("Ingrese el numero de horas faltadas");
        horasFalta = ingresar.nextInt();
        docente.setHorasFaltas(horasFalta);

        docente.setHorasTrabajo(30);
        docente.setSueldo();


    }

    public static void main(String[] args) {
        Docente docente = new Docente();
        ingresarDatosDocente(docente);
        System.out.println(docente.getSueldo());
    }

}
