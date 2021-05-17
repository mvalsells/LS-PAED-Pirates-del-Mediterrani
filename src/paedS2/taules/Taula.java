package paedS2.taules;

import java.util.ArrayList;

public class Taula {
    int mida;
    ArrayList<Pirata>[] taula;
    public Taula(int mida){
        this.mida=mida;
        taula = new ArrayList[mida];
        for (int i=0; i<taula.length; i++){
            taula[i] = new ArrayList<>();
        }
    }

    public void afegirPirata(Pirata pirata){
        int hash = hash(pirata.getNom());
        taula[hash].add(pirata);
    }
    public void eliminarPirata(String nom){

    }

    public Pirata consultarPirata(String nom){
        return null;
    }

    public int hash(String str){
        char[] charArray = str.toCharArray();
        int pos = 0;
        for (int i=0; i< charArray.length; i++){
            pos += Math.pow(2,i)*charArray[i];
        }
        return pos%mida;
    }
}
