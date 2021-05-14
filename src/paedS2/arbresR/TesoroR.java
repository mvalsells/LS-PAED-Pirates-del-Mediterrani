package paedS2.arbresR;

import java.util.ArrayList;

public class TesoroR implements ElementoR{
    private String name;
    private float x;
    private float y;

    public TesoroR(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public float incremento(ElementoR elementoR) {
        if (elementoR.posicio().size()==2){
            //És un punt
            float c1 = Math.abs(x-elementoR.posicio().get(0));
            float c2 = Math.abs(y-elementoR.posicio().get(1));
            return (float) Math.hypot(c1,c2);
        } else if (elementoR.posicio().size()==4){
            //És rectangle
            float[] punt1 = {elementoR.posicio().get(0),elementoR.posicio().get(1)};
            float[] punt2 = {elementoR.posicio().get(2),elementoR.posicio().get(3)};
            float areaActual = Math.abs(punt1[0]-punt2[0])*Math.abs(punt1[1]-punt2[1]);

            //Nova area
            float costatX = Math.max(Math.abs(punt1[0]-x),Math.abs(punt2[0]-x));
            float costatY = Math.max(Math.abs(punt1[1]-y),Math.abs(punt2[1]-y));
            float novaArea = costatX*costatY;
            return novaArea-areaActual;
        }
        return Float.MIN_VALUE;
    }

    @Override
    public ArrayList<Float> posicio() {
        ArrayList<Float> pos = new ArrayList<>();
        pos.add(x);
        pos.add(y);
        return pos;
    }

    @Override
    public ArrayList<ElementoR> getHijos() {
        return null;
    }

    @Override
    public float area(ElementoR elementoR) {
        float base = Math.abs(x-elementoR.posicio().get(0));
        float altura = Math.abs(y-elementoR.posicio().get(1));


        return base * altura;
    }

    @Override
    public void insert(ElementoR elementoR) {

    }

    @Override
    public String toString() {
        return "TesoroR{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
