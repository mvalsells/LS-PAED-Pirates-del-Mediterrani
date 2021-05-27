package paedS2.arbresR;

import java.util.ArrayList;
import java.util.Arrays;

public class RectanguloR implements ElementoR{
    private ArrayList<ElementoR> elementos;
    private RectanguloR padre;
    private int orden;
    private boolean isHoja;
    private int altura;
    private int siguientePos;
    private float maxY;
    private float maxX;
    private float minY;
    private float minX;


    public RectanguloR(ElementoR elementoR, int orden, int altura, RectanguloR padre){
        elementos = new ArrayList<>();
        this.orden = orden;
        this.altura = altura;
        this.padre = padre;
        isHoja = true;
        siguientePos = 0;
        maxX=minX=elementoR.posicio().get(0);
        maxY=minY=elementoR.posicio().get(1);
        insert(elementoR);
    }

    @Override
    public float incremento(ElementoR elementoR) {
        return 0;
    }

    @Override
    public ArrayList<Float> posicio() {
        ArrayList<Float> pos = new ArrayList<>();
        pos.add(maxX);
        pos.add(maxY);
        pos.add(minX);
        pos.add(minY);
        return pos;
    }

    @Override
    public ArrayList<ElementoR> getHijos() {
        return elementos;
    }

    public void addHijo (RectanguloR nuevoHijo){
        elementos.add(nuevoHijo);
    }

    public RectanguloR getPadre() {
        return padre;
    }

    @Override
    public float area(ElementoR elementoR) {
        return 0;
    }

    private void updateLimits(float x, float y){
        if (y>maxY){
            maxY=y;
        } else if (y<minY){
            minY=y;
        }
        if (x>maxX){
            maxX=x;
        } else if (x<minX){
            minX=x;
        }
    }


    @Override
    public void insert(ElementoR elementoR) {
        ArrayList<Float> newPos = elementoR.posicio();
        updateLimits(newPos.get(0), newPos.get(1));
        if (isHoja) {
            elementos.add(elementoR);
            if (elementos.size() > orden) {
                if (this.padre == null || this.padre.getHijos().size() == 3) {
                    isHoja = false;
                    ElementoR[] maxTesoros = tesorosMasLejanos(elementos);
                    System.out.println("------------");

                    System.out.println(maxTesoros[0]);
                    System.out.println(maxTesoros[1]);


                    ArrayList<ElementoR> aux = new ArrayList<>();
                    int posTesoro1 = elementos.indexOf(maxTesoros[0]);
                    aux.add(new RectanguloR(elementos.remove(posTesoro1), orden, altura + 1, this));

                    int posTesoro2 = elementos.indexOf(maxTesoros[1]);
                    aux.add(new RectanguloR(elementos.remove(posTesoro2), orden, altura + 1, this));

                    float areaA = elementos.get(0).area(aux.get(0).getHijos().get(0));
                    float areaB = elementos.get(0).area(aux.get(1).getHijos().get(0));

                    if (areaA < areaB) {
                        aux.get(0).insert(elementos.remove(0));
                    } else {
                        aux.get(1).insert(elementos.remove(0));
                    }
                    aux.get(0).getHijos().get(0);

                    areaA = elementos.get(0).area(aux.get(0).getHijos().get(0));
                    areaB = elementos.get(0).area(aux.get(1).getHijos().get(0));

                    if (areaA < areaB) {
                        aux.get(0).insert(elementos.remove(0));
                    } else {
                        aux.get(1).insert(elementos.remove(0));
                    }

                    elementos = aux;

                    float[] masMin = actualizarPosicionRectangulo(elementos);

                    maxX = masMin[0];
                    maxY = masMin[1];
                    minX = masMin[2];
                    minY = masMin[3];



                    System.out.println(elementos.toString());
                    System.out.println(aux.toString());

                    System.out.println("------------");
                }else{
                    ElementoR masLejanos[] = tesorosMasLejanos(elementos);
                    float areaA = 0;
                    float areaB = 0;
                    for (int i = 0; i < elementos.size(); i++) {
                        if (!elementos.get(i).equals(masLejanos[0]) || !elementos.get(i).equals(masLejanos[1]) ){
                            areaA += elementos.get(i).area(masLejanos[0]);
                            areaB += elementos.get(i).area(masLejanos[1]);
                        }
                    }
                    ElementoR elem = null;
                    if (areaA < areaB){
                        elementos.remove(masLejanos[1]);
                        elem = masLejanos[1];
                    }else {
                        elementos.remove(masLejanos[0]);
                        elem = masLejanos[0];

                    }
                    ElementoR aux2[] = tesorosMasLejanos(elementos);
                    maxX = Math.max(aux2[0].posicio().get(0), aux2[1].posicio().get(0));
                    minX = Math.min(aux2[0].posicio().get(0), aux2[1].posicio().get(0));
                    maxY = Math.max(aux2[0].posicio().get(1), aux2[1].posicio().get(1));
                    minY = Math.min(aux2[0].posicio().get(1), aux2[1].posicio().get(1));
                    this.padre.addHijo(new RectanguloR(elem, orden, altura, this.padre));
                }

            }
        }else {

            float incermentoMin=Float.MAX_VALUE;
            ElementoR masCerca = null;
            for(ElementoR r : getHijos()){
                float incremento=elementoR.incremento(r);
                if ( incremento < incermentoMin){
                    incermentoMin=incremento;
                    masCerca=r;
                }
            }

            masCerca.insert(elementoR);

        }

    }

    private ElementoR[] tesorosMasLejanos(ArrayList<ElementoR> elementos){
        ElementoR[] maxTesoros = new TesoroR[2];
        float distanciaMax = 0;
        for (int i = 0; i < elementos.size(); i++) {
            for (int j = 0; j < elementos.size(); j++) {
                float distancia = elementos.get(i).incremento(elementos.get(j));
                if (distanciaMax < distancia){
                    maxTesoros[0] = elementos.get(i);
                    maxTesoros[1] = elementos.get(j);
                }
            }
        }
        return maxTesoros;
    }

    private float[] actualizarPosicionRectangulo(ArrayList<ElementoR> elementos){
        float[] pos = new float[4];
        pos[0] = Float.MIN_VALUE;
        pos[1] = Float.MIN_VALUE;
        pos[2] = Float.MAX_VALUE;
        pos[3] = Float.MAX_VALUE;
        for (int i = 0; i < elementos.size(); i++) {
            ArrayList<Float> posElemento = elementos.get(i).posicio();

            if (pos[0] < posElemento.get(0)) pos[0] = posElemento.get(0);
            if (pos[1] < posElemento.get(1)) pos[1] = posElemento.get(1);
            if (pos[2] > posElemento.get(2)) pos[2] = posElemento.get(2);
            if (pos[3] > posElemento.get(3)) pos[3] = posElemento.get(3);
        }
        return pos;
    }

    /*
    pos.add(maxX);
        pos.add(maxY);
        pos.add(minX);
        pos.add(minY);
     */

    @Override
    public String toString() {
        String text = "\n";
        for (int i = 0; i < altura; i++) {
            text = text + "\t";
        }

        return text+"RectanguloR{" +
                "minX=" + minX +
                " minY=" + minY +
                " maxX=" + maxX +
                " maxY=" + maxY +
                " num Tesoros=" + elementos.size() +
                " elementos=" + elementos +
                '}';
    }
}
