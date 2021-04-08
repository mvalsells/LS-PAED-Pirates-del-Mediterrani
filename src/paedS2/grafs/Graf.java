package paedS2.grafs;

import paedS2.LeerDataset;

import java.util.*;

public class Graf {
    private static ArrayList<Vertice> vertices;
    private static ArrayList<Vertice> interest;
    private static ArrayList<Vertice> danger;
    public static void init(){
        vertices = LeerDataset.grafs("dataset/graphXXS.paed");
    }

    public static void opcionA(int origen){
        Vertice nodo = null;
        interest = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
            if (vertices.get(i).getId()==origen) {
                nodo = vertices.get(i);
            }
        }

        if (nodo==null){
            //TODO Mirar al hacer el menu
            System.out.println("Nodo no existente");
        } else {
            dfs(nodo);

            for (Vertice v:vertices){
                if (!v.isVisitado()){
                    dfs(v);
                }
            }

            System.out.println("\nAmb DFS s'han trobat els següents llocs d'interès: \n");

            for(Vertice v:interest){
                System.out.println("\t" + v.getNombre() + " - " + v.getTipo());
            }
        }
    }

    private static void dfs(Vertice nodo){
        nodo.setVisitado(true);
        float[][] relaciones = Vertice.getRelaciones();
        if (nodo.getTipo().equals("INTEREST")){
            interest.add(nodo);
        }
        for (int i = 0; i < vertices.size(); i++) {
            Vertice adj = vertices.get(i);
            float dist = relaciones[nodo.getPosicion()][i];
            if (dist!=0.0){
                if (!adj.isVisitado()){
                    dfs(adj);
                }
            }
        }
    }

    public static void opcionB(int origen){
        Vertice nodo = null;
        danger = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
            if (vertices.get(i).getId()==origen) {
                nodo = vertices.get(i);
            }
        }
        if (nodo==null){
            //TODO Mirar al hacer el menu
            System.out.println("Nodo no existente");
        } else {
            bfs(nodo);

            for (Vertice v:vertices){
                if (!v.isVisitado()){
                    bfs(v);
                }
            }

            System.out.println("\nAmb BFS s'han trobat els següents llocs perillosos: \n");
            for(Vertice v:danger){
                System.out.println("\t" + v.getNombre() + " - " + v.getTipo());
            }
        }
    }

    private static void bfs(Vertice nodo) {
        Queue<Vertice> cua = new LinkedList<>();
       // PriorityQueue<paedS2.grafs.Vertice> cua = new PriorityQueue<>();
        cua.offer(nodo);
        nodo.setVisitado(true);
        while (!cua.isEmpty()){
            Vertice siguiente = cua.poll();
            //Logica
            if (siguiente.getTipo().equals("DANGER")){
                danger.add(siguiente);
            }
            float[][] relaciones = Vertice.getRelaciones();
            for (int i = 0; i < vertices.size(); i++) {
                Vertice adj = vertices.get(i);
                float dist = relaciones[siguiente.getPosicion()][i];
                if (dist!=0.0){
                    if (!adj.isVisitado()){
                        cua.offer(adj);
                        adj.setVisitado(true);
                    }
                }
            }
        }
    }

    private static void opcionC () {
        System.out.println("Obtenint el MST...");
        mstPrim();
    }

    /*private static void mst() {
        paedS2.grafs.Vertice parents[] = new paedS2.grafs.Vertice[vertices.size()];
        float key[] = new float[vertices.size()];
        float relaciones[][] = paedS2.grafs.Vertice.getRelaciones();

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
            key[i] = Float.MAX_VALUE;
        }

        key[0] = 0.0f;
        parents[0] = null;

        for (int i = 0; i < vertices.size() - 1; i++) {
            float minDist = Float.MAX_VALUE;
            paedS2.grafs.Vertice vertMin = null;

            for (int j = 0; j < vertices.size(); j++) {
                if(!vertices.get(j).isVisitado() && key[j] < minDist) {
                    minDist = key[j];
                    vertMin = vertices.get(j);

                }
            }

            vertMin.setVisitado(true);

            for (int j = 0; j < vertices.size(); j++) {
                float rel = relaciones[vertMin.getPosicion()][vertices.get(j).getPosicion()];
                if(rel != 0 && !vertices.get(j).isVisitado() && rel < key[j]) {
                    parents[j] = vertMin;
                    key[j] = rel;
                }
            }

        }

        for (int i = 1; i < vertices.size(); i++) {
            System.out.println(parents[i].toString() + "-----" + relaciones[i][parents[i].getPosicion()]);
        }


    }*/

    private static void mstPrim() {
        Vertice nodos[] = new Vertice[vertices.size()];
        int j = 0, k = 0;

        float rels[][] = new float[vertices.size()][vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
        }

        vertices.get(0).setVisitado(true);
        nodos[0] = vertices.get(0);

        for (int i = 1; i < vertices.size(); i++) {
            float peso = Float.MAX_VALUE;
            int position = -1;
            int jv2 = -1;
            for (j = 0; j < vertices.size(); j++) {
                if(vertices.get(j).isVisitado()) {
                    for (k = 0; k < vertices.size(); k++) {
                        if(Vertice.getRelaciones()[j][k] < peso && !vertices.get(k).isVisitado() && Vertice.getRelaciones()[j][k] != 0) {
                            peso = Vertice.getRelaciones()[j][k];
                            position = k;
                            jv2 = j;
                        }
                    }
                }
            }
            rels[jv2][position] = peso;
            rels[position][jv2] = peso;
            vertices.get(position).setVisitado(true);
            nodos[i] = vertices.get(position);
        }

        for (int i = 0; i < rels.length; i++) {
            System.out.println();
            for (int l = 0; l < rels.length; l++) {
                System.out.print(rels[i][l] + "\t");
            }
        }
    }

    private static void opcionD() {
        Scanner sc = new Scanner(System.in);
        int nodoOrigen, nodoDestino;

        System.out.print("Entra identificador nodo origen: ");
        nodoOrigen = sc.nextInt();

        System.out.print("Entra identificador nodo destino: ");
        nodoDestino = sc.nextInt();

        Vertice vertOrigen = null, vertDestino = null;
        for (Vertice v : vertices){
            if (v.getId() == nodoOrigen) {
                vertOrigen = v;
            } else if (v.getId() == nodoDestino){
                vertDestino = v;
            }
        }

        if (vertOrigen != null && vertDestino != null){
            dijkstra(vertOrigen,vertDestino);
        }


    }

    private static void dijkstra(Vertice nodoOrigen, Vertice nodoDestino) {
        int caminos[][] = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < caminos.length; i++) {
            Arrays.fill(caminos[i], -1);
        }
        float dist[] = new float[vertices.size()];
        float graf[][] = Vertice.getRelaciones();
        for (int i = 0; i < vertices.size(); i++) {
            dist[i] = Float.MAX_VALUE;
            vertices.get(i).setVisitado(false);
        }
        dist[nodoOrigen.getPosicion()] = 0;
        nodoOrigen.setVisitado(true);
        Vertice actual = nodoOrigen;
        caminos[actual.getPosicion()][0] = actual.getId();
        while (!nodoDestino.isVisitado()) {
            for (Vertice adj : vertices){
                float peso = graf[actual.getPosicion()][adj.getPosicion()];
                if (peso != 0 && !adj.isVisitado()){
                    float nova = dist[actual.getPosicion()] + peso;
                    if (dist[adj.getPosicion()] > nova){
                        dist[adj.getPosicion()] = nova;
                        int i=0;
                        for (i = 0; i < caminos.length; i++) {
                            if (caminos[actual.getPosicion()][i]!=-1){
                                caminos[adj.getPosicion()][i] = caminos[actual.getPosicion()][i];
                            } else {
                                break;
                            }
                        }
                        caminos[adj.getPosicion()][i] = adj.getId();
                    }
                }
            }
            actual.setVisitado(true);


            float minDist = Float.MAX_VALUE;
            Vertice vertMin = null;

            for (int j = 0; j < vertices.size(); j++) {
                if(!vertices.get(j).isVisitado() && dist[j] < minDist) {
                    minDist = dist[j];
                    vertMin = vertices.get(j);
                }
            }
            actual = vertMin;


        }

        System.out.println("Distancia total: " + dist[nodoDestino.getPosicion()]);
        System.out.print("Passem pels nodes: ");
        for (int i = 0; i < caminos.length; i++){
            if (caminos[nodoDestino.getPosicion()][i] != -1 ) {
                System.out.print(caminos[nodoDestino.getPosicion()][i] + ", ");
            } else {
                break;
            }
        }
        System.out.println();
    }

    public static void menuPrincipal() {

        Scanner scInt = new Scanner(System.in);
        int nodeOrigen;

        System.out.println("\n\tA. Cercar llocs d'interès (DFS)");
        System.out.println("\tB. Cercar llocs perillosos (BFS)");
        System.out.println("\tC. Mostrar la Carta Nàutica Universal (MST)");
        System.out.println("\tD. Trobar la ruta òptima (Dijkstra) \n");
        System.out.println("\tE. Tornar enrere");


        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEscull una opció:");
        String opc = sc.nextLine();

        switch (opc) {
            case "A":

                System.out.print("\nEntra l'identificador del node origen: ");
                nodeOrigen = scInt.nextInt();

                opcionA(nodeOrigen);

                return;

            case "B":

                System.out.print("\nEntra l'identificador del node origen: ");
                nodeOrigen = scInt.nextInt();

                opcionB(nodeOrigen);

                return;

            case "C":

                opcionC();
                return;

            case "D":
                opcionD();
                return;

            case "E":
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }
}
