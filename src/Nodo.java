public class Nodo {
    /** Nodo de la lista doblemente enlazada de docentes */
    private Docente docente;
    Nodo siguiente;
    Nodo anteiror;

    public Nodo(Docente docente) {
        this.docente = docente;
        this.anteiror = this.siguiente = null;
    }

    public Docente getDocente() {
        return docente;
    }
}
