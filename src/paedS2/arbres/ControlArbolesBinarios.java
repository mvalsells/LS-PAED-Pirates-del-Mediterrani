package paedS2.arbres;

import paedS2.LeerDataset;

import java.util.ArrayList;
import java.util.Scanner;

public class ControlArbolesBinarios {

    String ruta = "dataset/treeXXS.paed";

    private ArbolTesoro arbolTesoro;

    public ControlArbolesBinarios() {
        arbolTesoro = LeerDataset.tesoro(ruta);
    }
    private void opcionA (){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEntra el nom del tresor a afegir: ");
        String nom = sc.nextLine();
        System.out.print("Entra el valor del tresor a afegir: ");
        Long valor = Long.parseLong(sc.nextLine());
        arbolTesoro.inserirNodo(new Tesoro(nom, valor));
        System.out.println("\nEl tresor s'ha afegit correctament al botí.");
    }

    private void opcionB (){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEntra el nom del tresor a eliminar: ");
        String nom = sc.nextLine();
        arbolTesoro.eliminarNodo(nom);
    }

    private void opcionC () {

        System.out.println("\n\tI. Preordre");
        System.out.println("\tII. Postordre");
        System.out.println("\tIII. Inordre");
        System.out.println("\tIV. Per nivells");

        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tQuin recorregut vols fer servir? ");
        String recorregut = sc.nextLine();
        switch (recorregut) {
            case "I":
                arbolTesoro.preOrdre();
                break;
            case "II":
                arbolTesoro.postOrdre();
                break;
            case "III":
                arbolTesoro.inOrdre();
                break;
            case "IV":
                arbolTesoro.nivells();
                break;

            default:
                System.out.println("Aquest cas no existeix");
        }
    }

    private void opcionD() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEntra el valor a cercar: ");
        long valor = sc.nextLong();
        System.out.println("\t" + arbolTesoro.cercaValorExacte(valor));
    }

    private void opcionE() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEntra el valor mínim a cercar: ");
        long valorMin = sc.nextLong();
        System.out.print("\tEntra el valor màxim a cercar: ");
        long valorMax = sc.nextLong();

        ArrayList<Tesoro> tesoros = arbolTesoro.cercaValorRang(valorMin, valorMax);
        System.out.println("\nS'han trobat "+ tesoros.size()+ " tresors en aquest rang: \n");
        for (int i = 0; i < tesoros.size(); i++) {
            System.out.println("\t"+tesoros.get(i));
        }

    }

    public void menuPrincipal() {

        Scanner scInt = new Scanner(System.in);
        int nodeOrigen;

        System.out.println("\n\tA. Afegir tresor");
        System.out.println("\tB. Eliminar tresor");
        System.out.println("\tC. Llistar botí");
        System.out.println("\tD. Cerca per valor (exacte)");
        System.out.println("\tE. Cerca valor (rang)\n");
        System.out.println("\tF. Tornar enrere");


        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEscull una opció:");
        String opc = sc.nextLine();

        switch (opc) {
            case "A":
                opcionA();
                return;

            case "B":
                opcionB();
                return;

            case "C":
                opcionC();
                return;

            case "D":
                opcionD();
                return;

            case "E":
                opcionE();
                return;

            case "F":
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }
}
