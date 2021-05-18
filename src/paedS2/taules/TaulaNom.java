package paedS2.taules;

import java.util.ArrayList;
import java.util.Arrays;

public class TaulaNom{
    private int mida;
    private int[] edats = new int[38];
    ArrayList<Pirata>[] taula;
    public TaulaNom(int mida){
        this.mida=mida;
        taula = new ArrayList[mida];
        for (int i=0; i<taula.length; i++){
            taula[i] = new ArrayList<>();
        }
        Arrays.fill(edats,0);
    }

    public void afegirPirata(Pirata pirata){
        int hash = hash(pirata.getNom());
        edats[pirata.getEdat()-13]++;
        taula[hash].add(pirata);
    }
    public void eliminarPirata(String nom){
        int hash = hash(nom);
        ArrayList<Pirata> hashData = taula[hash];
        for (Pirata p:hashData){
            if (p.getNom().equals(nom)){
                edats[p.getEdat()-13]--;
                hashData.remove(p);
                break;
            }
        }
    }

    public Pirata consultarPirata(String nom){
        int hash = hash(nom);
        ArrayList<Pirata> hashData = taula[hash];
        for (Pirata p:hashData){
            if (p.getNom().equals(nom)){
                return p;
            }
        }
        return null;
    }

    public int[] getEdats() {
        return edats;
    }

    private int hash(String str){
        char[] charArray = str.toCharArray();
        int pos = 0;
        for (int i=0; i< charArray.length; i++){
            pos += Math.pow(2,i)*charArray[i];
        }
        return pos%mida;
    }
}
