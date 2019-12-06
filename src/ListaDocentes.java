import javax.print.Doc;
import java.util.Scanner;

public class ListaDocentes {

    private Nodo inicio;

    public ListaDocentes() {
        this.inicio = null;
    }

    public void insertarDocente(Docente docente) {
        Nodo nuevo;
        nuevo = new Nodo(docente);
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
                System.out.println(aux.getDocente());
                aux = aux.siguiente;
            }
        } else {
            System.out.println("La lista está vacía");
        }
    }

    public Nodo buscarDocente(String cedula) throws NullPointerException {
        for (Nodo aux = inicio; aux != null ; aux = aux.siguiente) {
            if (cedula.equals(aux.getDocente().getCedula())) {
                return aux;
            }
        }
        return null;
    }

    public void eliminarDocente(String cedula) {
        Nodo actual = inicio;
        boolean encontrado = false;

        while ((actual != null) && (!encontrado)) {
            encontrado = (cedula.equals(actual.getDocente().getCedula()));
            if (!encontrado) actual = actual.siguiente;
        }

        if (actual != null) {
            if (actual == inicio) {
                inicio = actual.siguiente;
                if (actual.siguiente != null) actual.siguiente.anteiror = null;
            } else if (actual.siguiente != null) {
                actual.anteiror.siguiente = actual.siguiente;
                actual.siguiente.anteiror = actual.anteiror;
            } else {
                actual.anteiror.siguiente = null;
                actual = null;
            }
            System.out.println("Docente eliminado de los registros con éxito");
        } else {
            System.out.println("Docente no encontrado, no se puede eliminar");
        }
    }


}
