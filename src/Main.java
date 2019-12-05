import javax.print.Doc;

public class Main {

    public static void main(String[] args) {
        Docente d1 = new Docente("Chavez");
        Docente d2 = new Docente("Aviles");
        Docente d3 = new Docente("Valverde");
        Docente d4 = new Docente("Pita");

        ListaDocentes l1 = new ListaDocentes();

        l1.insertar(d1);
        l1.insertar(d2);
        l1.insertar(d3);
        l1.insertar(d4);

        l1.visualizar();
    }

}
