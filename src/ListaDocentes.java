import javax.print.Doc;

public class ListaDocentes {

    private Nodo inicio;

    public ListaDocentes() {
        this.inicio = null;
    }

    public void insertar(Docente docente) {
        Nodo nuevo;
        nuevo = new Nodo(docente);

        if (inicio == null) {
            inicio = nuevo;
        } else if (docente.getApellidos().compareTo(inicio.getDocente().getApellidos()) < 0) {
            nuevo.siguiente = inicio;
            inicio.anteiror = nuevo;
            inicio = nuevo;
        } else {
            Nodo aux;
            aux = inicio;
            while ((aux.siguiente != null) && (docente.getApellidos().compareTo(aux.getDocente().getApellidos()) > 0)) {
                aux = aux.siguiente;
            }

            if (docente.getApellidos().compareTo(aux.getDocente().getApellidos()) > 0) {
                aux.siguiente = nuevo;
                nuevo.anteiror = aux;
            } else {
                nuevo.siguiente = aux;
                nuevo.anteiror = aux.anteiror;
                aux.anteiror.siguiente = nuevo;
                aux.anteiror = nuevo;
            }

        }
    }

    public void visualizar() {
        Nodo aux;
        aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDocente().getApellidos() + " ");
            aux = aux.siguiente;
        }
    }
}
