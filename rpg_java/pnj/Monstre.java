package rpg_java.pnj;

public class Monstre {
    private String nom;
    private int niveauMin;
    private int niveauMax;
    private int pv;
    private int atq;
    private int def;

    public Monstre(String nom, int niveauMin, int niveauMax, int pv, int atq, int def) {
        this.nom = nom;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
        this.pv = pv;
        this.atq = atq;
        this.def = def;
    }

    // Getters
    public String getNom() { return nom; }
    public int getNiveauMin() { return niveauMin; }
    public int getNiveauMax() { return niveauMax; }
    public int getPv() { return pv; }
    public int getAtq() { return atq; }
    public int getDef() { return def; }

    public void setPv(int pv) {
        this.pv = Math.max(0, pv);
    }

    @Override
    public String toString() {
        return nom + " [LV " + niveauMin + "-" + niveauMax + "] PV:" + pv + " ATQ:" + atq + " DEF:" + def;
    }
}