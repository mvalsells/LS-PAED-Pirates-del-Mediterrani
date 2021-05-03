package paedS2.arbresR;

import java.util.ArrayList;

public class ArbolR {

    private TesoroR[] root = new TesoroR[3];
    private ArrayList<ArrayList> algo = new ArrayList<>();

    public ArbolR(TesoroR tesoroR) {
        root[0] = tesoroR;
    }

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
    }
}
