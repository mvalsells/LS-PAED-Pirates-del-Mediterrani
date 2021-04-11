package paedS2.arbres;

public class Tesoro {
    private String name;
    private long valor;
    private Tesoro padre;
    private Tesoro hijoMenor;
    private Tesoro hijoMayor;

    public Tesoro(String name, long valor){
        this.name  = name;
        this.valor = valor;
        this.hijoMenor = null;
        this.hijoMayor = null;
    }

    public Tesoro(String name, long valor, Tesoro padre) {
        this.name = name;
        this.valor = valor;
        this.padre = padre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Tesoro getPadre() {
        return padre;
    }

    public void setPadre(Tesoro padre) {
        this.padre = padre;
    }

    public Tesoro getHijoMenor() {
        return hijoMenor;
    }

    public void setHijoMenor(Tesoro hijoMenor) {
        this.hijoMenor = hijoMenor;
    }

    public Tesoro getHijoMayor() {
        return hijoMayor;
    }

    public void setHijoMayor(Tesoro hijoMayor) {
        this.hijoMayor = hijoMayor;
    }

    @Override
    public String toString() {
        return name + " - " + valor + " doblons";
    }
}
