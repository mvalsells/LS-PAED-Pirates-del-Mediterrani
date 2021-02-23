import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vertice> arrayV = LeerDataset.grafs("dataset/graphXXL.paed");
        for (int i=0; i< arrayV.size(); i++) {
            System.out.println(arrayV.get(i).toString());
        }
        /*float[][] rel = Vertice.getRelaciones();
        for (int i = 0; i < rel.length ; i++) {
            for (int j = 0; j < rel.length; j++) {
                System.out.print(rel[i][j]+"\t |");
            }
            System.out.println("");
        }*/
    }
}
