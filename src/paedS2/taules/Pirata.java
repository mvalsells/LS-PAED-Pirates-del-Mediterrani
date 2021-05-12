package paedS2.taules;

public class Pirata {
    private String nom;
    private int edat;
    private String rol;

    public Pirata(String nom, int edat, String posicio) {
        this.nom = nom;
        this.edat = edat;
        this.rol = posicio;
    }

    public String getNom() {
        return nom;
    }

    public int getEdat() {
        return edat;
    }

    public String getRol() {
        return rol;
    }
}
