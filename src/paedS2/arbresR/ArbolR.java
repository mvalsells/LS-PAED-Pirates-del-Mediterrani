package paedS2.arbresR;

import java.util.ArrayList;

public class ArbolR {

    private RectanguloR root;
    private int orden;

    public ArbolR(TesoroR tesoroR,int orden ) {
        root = new RectanguloR(tesoroR, orden);
        this.orden=orden;
    }

    public void inserir(ElementoR elementoR){
        root.insert(elementoR);
    }

/*
    public void insertTesoro(TesoroR tesoroR){
        insertTesoro(tesoroR, root);
    }

    private void insertTesoro(TesoroR tesoroR, TesoroR[] rectangulo){
        if (!isFull(rectangulo)){
            for (int i = 0; i < 2; i++) {
                if (rectangulo[i] == null){
                    //rectangulo[i] = new TesoroR[3];
                }
            }
        } else {

        }
    }


    public boolean isFull(TesoroR[] rectangulo){
        if (rectangulo[2] == null){
            return true;
        }
        return false;
    }*/
}
