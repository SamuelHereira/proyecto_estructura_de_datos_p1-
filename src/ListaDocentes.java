import javax.print.Doc;
import java.util.Scanner;

public class ListaDocentes {

    private Nodo inicio;

    public ListaDocentes() {
        this.inicio = null;
    }

    private Nodo crearDocente() {
        Docente docente = new Docente();
        docente.ingresarDatos(docente);
        Nodo nuevoNodo = new Nodo(docente);
        return nuevoNodo;
    }

    public void insertarDocente() {
        Nodo nuevo;
        nuevo = crearDocente();

        if (inicio == null) {
            inicio = nuevo;
        } else if (nuevo.getDocente().getApellidos().compareTo(inicio.getDocente().getApellidos()) < 0) {
            nuevo.siguiente = inicio;
            inicio.anteiror = nuevo;
            inicio = nuevo;
        } else {
            Nodo aux;
            aux = inicio;
            while ((aux.siguiente != null) && (nuevo.getDocente().getApellidos().compareTo(aux.getDocente().getApellidos()) > 0)) {
                aux = aux.siguiente;
            }

            if (nuevo.getDocente().getApellidos().compareTo(aux.getDocente().getApellidos()) > 0) {
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
        if(inicio != null) {
            while (aux != null) {
                System.out.println(aux.getDocente().getApellidos() + " ");
                aux = aux.siguiente;
            }
        } else {
            System.out.println("LISTA VACIA");
        }
    }


}
