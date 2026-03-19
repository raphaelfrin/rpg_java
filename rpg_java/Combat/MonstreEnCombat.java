package rpg_java.Combat;

import rpg_java.pnj.Monstre;

public class MonstreEnCombat {

    private String nom;
    private int pv;
    private int atq;
    private int def;

    public MonstreEnCombat(Monstre monstre) {
        this.nom = monstre.getNom();
        this.pv = monstre.getPv();
        this.atq = monstre.getAtq();
        this.def = monstre.getDef();
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = Math.max(0, pv);
    }

    public int getAtq() {
        return atq;
    }

    public int getDef() {
        return def;
    }

    public boolean estVivant() {
        return pv > 0;
    }

    @Override
    public String toString() {
        return nom + " PV:" + pv + " ATQ:" + atq + " DEF:" + def;
    }
}