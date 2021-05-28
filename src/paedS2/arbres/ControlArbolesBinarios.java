package paedS2.arbres;

import paedS2.LeerDataset;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlArbolesBinarios {

    String ruta = "dataset/treeM.paed";

    private AVLTree arbolTesoro;
    private static long initialReadTime;

    public ControlArbolesBinarios() {

        initialReadTime = System.currentTimeMillis();
        arbolTesoro = LeerDataset.tesoroAVL(ruta);
        initialReadTime = System.currentTimeMillis() - initialReadTime;

    }
    private void opcionA (){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEntra el nom del tresor a afegir: ");
        String nom = sc.nextLine();
        System.out.print("Entra el valor del tresor a afegir: ");
        Long valor = Long.parseLong(sc.nextLine());

        initialReadTime = System.currentTimeMillis();
        arbolTesoro.insert(arbolTesoro.getRoot(), valor, nom);
        initialReadTime = System.currentTimeMillis() - initialReadTime;
        System.out.println("\n\tHe taradat " + initialReadTime + " millis a Afegir tresor a Arboles Binarios");
        System.out.println("\nEl tresor s'ha afegit correctament al botí.");
    }

    private void opcionB (){
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEntra el nom del tresor a eliminar: ");
        String nom = sc.nextLine();
        initialReadTime = System.currentTimeMillis();
        arbolTesoro.eliminarTesoro(nom);
        initialReadTime = System.currentTimeMillis() - initialReadTime;
        System.out.println("\n\tHe taradat " + initialReadTime + " millis a Eliminar Tesoro tresor a Arboles Binarios");
    }

    private void opcionC () {

        arbolTesoro.balanceigArbol(arbolTesoro.getRoot());

        System.out.println("\n\tI. Preordre");
        System.out.println("\tII. Postordre");
        System.out.println("\tIII. Inordre");
        System.out.println("\tIV. Per nivells");

        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tQuin recorregut vols fer servir? ");
        String recorregut = sc.nextLine();
        switch (recorregut) {
            case "I":
                initialReadTime = System.currentTimeMillis();
                arbolTesoro.preOrdre();
                initialReadTime = System.currentTimeMillis() - initialReadTime;
                System.out.println("\n\tHe taradat " + initialReadTime + " millis");
                break;
            case "II":
                initialReadTime = System.currentTimeMillis();

                arbolTesoro.postOrdre();
                initialReadTime = System.currentTimeMillis() - initialReadTime;
                System.out.println("\n\tHe taradat " + initialReadTime + " millis ");
                break;
            case "III":
                initialReadTime = System.currentTimeMillis();

                arbolTesoro.inOrdre();
                initialReadTime = System.currentTimeMillis() - initialReadTime;
                System.out.println("\n\tHe taradat " + initialReadTime + " millis ");
                break;
            case "IV":
                initialReadTime = System.currentTimeMillis();

                arbolTesoro.nivells();
                initialReadTime = System.currentTimeMillis() - initialReadTime;
                System.out.println("\n\tHe taradat " + initialReadTime + " millis ");
                break;
            case "X":
                arbolTesoro.dibujarArbol();
                break;
            default:
                System.out.println("Aquest cas no existeix");
        }
    }

    private void opcionD() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEntra el valor a cercar: ");
        long valor = sc.nextLong();
        initialReadTime = System.currentTimeMillis();
        System.out.println("\t" + arbolTesoro.cercaValorExacte(valor));
        initialReadTime = System.currentTimeMillis() - initialReadTime;
        System.out.println("\n\tHe taradat " + initialReadTime + " millis a valor a cercar a Arboles Binarios");
    }

    private void opcionE() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEntra el valor mínim a cercar: ");
        long valorMin = sc.nextLong();
        System.out.print("\tEntra el valor màxim a cercar: ");
        long valorMax = sc.nextLong();

        initialReadTime = System.currentTimeMillis();
        ArrayList<Tesoro> tesoros = arbolTesoro.cercaValorRang(valorMin, valorMax);
        initialReadTime = System.currentTimeMillis() - initialReadTime;
        System.out.println("\nS'han trobat "+ tesoros.size()+ " tresors en aquest rang: \n");
        for (int i = 0; i < tesoros.size(); i++) {
            System.out.println("\t"+tesoros.get(i));
        }
        System.out.println("\n\tHe taradat " + initialReadTime + " millis a valor a cercar a Arboles Binarios");



    }

    public void menuPrincipal() {

        Scanner scInt = new Scanner(System.in);
        int nodeOrigen;
        System.out.println("\n\tHe taradat " + initialReadTime + " millis a llegir el dataset Arboles Binarios");
        System.out.println("\n\tA. Afegir tresor");
        System.out.println("\tB. Eliminar tresor");
        System.out.println("\tC. Llistar botí");
        System.out.println("\tD. Cerca per valor (exacte)");
        System.out.println("\tE. Cerca valor (rang)\n");
        System.out.println("\tF. Tornar enrere");


        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEscull una opció: ");
        String opc = sc.nextLine();

        switch (opc) {
            case "A":
                opcionA();
                System.out.println("\n\tHe taradat " + initialReadTime + " millis a Afegir tresor a Arboles Binarios");

                return;

            case "B":
                opcionB();
                break;
                //return;

            case "C":
                opcionC();
                //return;
                break;

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
