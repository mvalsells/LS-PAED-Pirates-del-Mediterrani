public class Tesoro {
    private String name;
    private long valor;

    public Tesoro(String name, long valor){
        this.name  = name;
        this.valor = valor;
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
}
