package paedS2;

import paedS2.arbres.AVLTree;
import paedS2.arbres.ArbolTesoro;
import paedS2.arbres.Tesoro;
import paedS2.arbresR.ArbolR;
import paedS2.arbresR.TesoroR;
import paedS2.grafs.Vertice;
import paedS2.taules.Pirata;
import paedS2.taules.Taula;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerDataset {
    public static ArrayList<Vertice> grafs (String ruta) {
        ArrayList<Vertice> arrayV = new ArrayList<>();
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            int numVert = Integer.parseInt(br.readLine());

            for (int i =0; i<numVert; i++){
                String linia = br.readLine();
                String[] split = linia.split(",");
                int id = Integer.parseInt(split[0]);
                Vertice v = new Vertice(id, split[1],split[2]);
                arrayV.add(v);
            }

            Vertice.initRelaciones(numVert);
            int numRel = Integer.parseInt(br.readLine());

            for (int i = 0; i < numRel; i++) {
                String linia = br.readLine();
                String[] split = linia.split(",");
                int idOrigen = Integer.parseInt(split[0]);
                int idDestinacion = Integer.parseInt(split[1]);
                float distancia = Float.parseFloat(split[2]);

                int posOrigen=0;
                int posDestination=0;
                boolean origen=false;
                boolean destination=false;
                for (Vertice v: arrayV){
                    if (v.getId() == idOrigen) {
                        posOrigen=v.getPosicion();
                        origen = true;
                    } else if (v.getId() == idDestinacion){
                        posDestination=v.getPosicion();
                        destination=true;
                    } else if ( destination && origen){
                        break;
                    }
                }

                Vertice.anadirRelacion(posOrigen,posDestination,distancia);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayV;
    }

    public static ArbolTesoro tesoro (String ruta) {

        ArbolTesoro arbol = null;

        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            int numTesoros = Integer.parseInt(br.readLine());

            String linia = br.readLine();
            String[] split = linia.split(",");
            long valor = Long.parseLong(split[1]);
            arbol = new ArbolTesoro(new Tesoro(split[0], valor));

            for (int i =1; i<numTesoros; i++){
                linia = br.readLine();
                split = linia.split(",");
                valor = Long.parseLong(split[1]);
                arbol.inserirNodo(new Tesoro(split[0], valor));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arbol;
    }

    public static AVLTree tesoroAVL (String ruta) {

        AVLTree arbol = null;

        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            int numTesoros = Integer.parseInt(br.readLine());

            String linia = br.readLine();
            String[] split = linia.split(",");
            long valor = Long.parseLong(split[1]);
            Tesoro root = new Tesoro(split[0], valor);
            arbol = new AVLTree(root);

            for (int i =1; i<numTesoros; i++){
                linia = br.readLine();
                split = linia.split(",");
                valor = Long.parseLong(split[1]);
                arbol.insert(valor, split[0]);

            }
            System.out.println("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arbol;
    }

    public static ArbolR tesoroR (String ruta){
        ArbolR arbol = null;

        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            int numTesoros = Integer.parseInt(br.readLine());

            String linia = br.readLine();
            System.out.println("Linia: " + linia);
            String[] split = linia.split(",");
            float x = Float.parseFloat(split[1]);
            float y = Float.parseFloat(split[2]);
            arbol = new ArbolR(new TesoroR(split[0], x, y) , 3);

            for (int i =1; i<numTesoros; i++){
                linia = br.readLine();
                split = linia.split(",");
                x = Float.parseFloat(split[1]);
                y = Float.parseFloat(split[2]);
                arbol.inserir(new TesoroR(split[0], x, y));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arbol;
    }

    public static Taula taula (String ruta){
        Taula taula = null;

        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            int numPirates = Integer.parseInt(br.readLine());
            taula = new Taula(numPirates);
            for (int i = 0; i<numPirates; i++){
                String linia = br.readLine();
                String[] split = linia.split(",");
                taula.afegirPirata(new Pirata(split[0],Integer.parseInt(split[1]),split[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taula;
    }
}
