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

                    System.out.println(elementos.toString());
                    System.out.println(aux.toString());

                    System.out.println("------------");
                }else{
                    elementos.remove(elementoR);
                    this.padre.addHijo(new RectanguloR(elementoR, orden, altura, this.padre));
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

            /*
            //if (isInSideArea((TesoroR) elementoR)) return;

            float areaMin = Float.MAX_VALUE;
            int posElemento = -1;
            for (int i = 0; i < elementos.size(); i++) {

                ArrayList<ElementoR> hijos =  (ArrayList<ElementoR>)elementos.get(i).getHijos().clone();

                if (hijos.get(0).getClass().equals(TesoroR.class)){
                   //Los Elementos son Tesoros,es decir Hoja
                    hijos.add(elementoR);

                    ElementoR[] tesorosMax = tesorosMasLejanos(hijos);
                    float area = tesorosMax[0].area(tesorosMax[1]);

                    if (area < areaMin){
                        posElemento = i;
                    }
                }
            }

            elementos.get(posElemento).insert(elementoR);*/
        }

    }

    private boolean isInSideArea(TesoroR tesoroR){
        for (int i = 0; i < elementos.size(); i++) {
            if (betweenTwo(elementos.get(i).getHijos().get(0).posicio().get(0), tesoroR.posicio().get(0), elementos.get(i).getHijos().get(1).posicio().get(0))){
                if(betweenTwo(elementos.get(i).getHijos().get(0).posicio().get(1), tesoroR.posicio().get(1), elementos.get(i).getHijos().get(1).posicio().get(1))){
                    //Esta dentro del rectangulo

                    //elementos.get(i).insert(tesoroR);
                    return true;

                }
            }


        }

        return false;
    }

    private boolean betweenTwo(float num, float num1, float num2){
        if (num1 < num && num < num2){
            return true;
        }else if(num1 > num && num > num2){
            return true;
        }
        return false;
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
