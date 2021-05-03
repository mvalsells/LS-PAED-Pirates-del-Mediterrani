package paedS2;

import paedS2.arbres.AVLTree;
import paedS2.arbres.ControlArbolesBinarios;
import paedS2.grafs.Graf;

import java.util.Scanner;

public class Main {
    private static ControlArbolesBinarios controlArbolesBinarios = new ControlArbolesBinarios();;
    public static void main(String[] args) {
        Graf.init();

        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("\n-= Pirates del Mediterrani =- \n");
        System.out.println("1. Rutes (Grafs)");
        System.out.println("2. Inventari (Arbres binaris)");
        System.out.println("3. Coberta (Arbres R)");
        System.out.println("4. Tripulació (Taules) \n");
        System.out.println("5. Sortir");

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEscull una opció: ");
        int opc = sc.nextInt();

        switch (opc) {
            case 1:

                Graf.menuPrincipal();
                break;

            case 2:
                controlArbolesBinarios.menuPrincipal();
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                System.out.println("A reveure camarada!");
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }/*
   public static void main(String[] args) {
       AVLTree avl = LeerDataset.tesoroAVL("dataset/treeXS.paed");
       avl.nivells();
   }*/

}
