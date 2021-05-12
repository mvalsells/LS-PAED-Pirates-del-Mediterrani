package paedS2.taules;

import paedS2.LeerDataset;

import java.util.Scanner;

public class ControlTaules {

    private Taula taula;
    private String ruta = "dataset/tablesXXS.paed";
    Scanner sc;

    public ControlTaules(){
        taula = LeerDataset.taula(ruta);
        sc  = new Scanner(System.in);
    }


    private void opcionA(){
        System.out.print("Entra el nom del pirata a afegir: ");
        String nom = sc.nextLine();
        System.out.print("Entra l'edat del pirata a afegir: ");
        int edat = Integer.parseInt(sc.nextLine());
        System.out.print("Entra el rol del pirata a afegir: ");
        String rol = sc.nextLine();

        taula.afegirPirata(new Pirata(nom, edat,rol));
        System.out.println("\nEl pirata s'ha afegit correctament a la tripulació.");
    }

    private void opcionB(){
        System.out.print("Entra el nom del pirata a eliminar: ");
        String nom = sc.nextLine();
        taula.eliminarPirata(nom);
        System.out.println("\nEl pirata s'ha eliminat correctament de la tripulació. F.");
    }
    private void opcionC(){
        System.out.print("Entra el nom del pirata a consultar: ");
        String nom = sc.nextLine();
        Pirata p = taula.consultarPirata(nom);
        StringBuilder sb = new StringBuilder();
        sb.append("\nNom: ");
        sb.append(p.getNom());
        sb.append("\nEdat: ");
        sb.append(p.getEdat());
        sb.append("\nRol: ");
        sb.append(p.getRol());
        System.out.println(sb);
    }
    private void opcionD(){
        System.out.println("\nGenerant histograma...");
    }

    public void menuPrincipal() {


        System.out.println("\n\tA. Afegir pirata");
        System.out.println("\tB. Eliminar pirata");
        System.out.println("\tC. Consultar pirata");
        System.out.println("\tD. Histograma per edats\n");
        System.out.println("\tE. Tornar enrere");


        System.out.print("\n\tEscull una opció: ");
        String opc = sc.nextLine();

        switch (opc) {
            case "A":
                opcionA();
                break;

            case "B":
                opcionB();
                break;

            case "C":
                opcionC();
                break;

            case "D":
                opcionD();
                break;

            case "E":
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }
}
