import java.util.ArrayList;

public class Graf {
    private static ArrayList<Vertice> vertices;
    private static ArrayList<Vertice> interest;
    public static void init(){
        vertices = LeerDataset.grafs("dataset/graphS.paed");
    }

    public static ArrayList<Vertice> opcionA(int origen){
        ArrayList<Vertice> arrayV = null;
        Vertice nodo = null;
        interest = new ArrayList<>();
        int pos=Integer.MIN_VALUE;
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
            if (vertices.get(i).getId()==origen) {
                nodo = vertices.get(i);
                //pos = vertices.get(i).getPosicion();
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

            for(Vertice v:interest){
                System.out.println(v.getNombre() + " - " + v.getTipo());
            }
        }
        return arrayV;
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
}
