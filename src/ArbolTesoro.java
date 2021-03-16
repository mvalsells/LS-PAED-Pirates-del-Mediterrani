import java.util.ArrayList;

public class ArbolTesoro {

    private Tesoro tesoroOrigen;

    public ArbolTesoro(Tesoro tesoroOrigen) {
        this.tesoroOrigen = tesoroOrigen;
    }

    public void inserirNodo(Tesoro tesoro) {
        inserirNodo(tesoro, tesoroOrigen);
    }

    private void inserirNodo(Tesoro tesoro, Tesoro padre) {
        if (padre.getValor() < tesoro.getValor()) {
            if(padre.getHijoMayor() == null) {
                padre.setHijoMayor(tesoro);
            } else {
                inserirNodo(tesoro, padre.getHijoMayor());
            }
        } else if (padre.getValor() > tesoro.getValor()) {
            if(padre.getHijoMenor() == null) {
                padre.setHijoMenor(tesoro);
            } else {
                inserirNodo(tesoro, padre.getHijoMenor());
            }
        }
    }

}
