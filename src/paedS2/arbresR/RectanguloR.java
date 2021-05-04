package paedS2.arbresR;

import java.util.ArrayList;

public class RectanguloR implements ElementoR{
    private ArrayList<ElementoR> elementos;
    private RectanguloR padre;
    private int orden;
    private int siguientePos;


    public RectanguloR(ElementoR elementoR, int orden){
        elementos = new ArrayList<>();
        this.orden = orden;
        siguientePos = 0;
        insert(elementoR);
    }

    @Override
    public float incremento(ElementoR elementoR) {
        return 0;
    }

    @Override
    public void insert(ElementoR elementoR) {
        if (siguientePos<orden){
            elementos.add(elementoR);
        } else {

        }

    }
}
