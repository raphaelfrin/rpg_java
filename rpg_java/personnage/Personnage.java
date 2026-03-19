package rpg_java.personnage;

public class Personnage {

    protected String nom;
    protected String classe;
    protected int pv;         // PV maximum
    protected int pvActuel;   // PV actuelle
    protected int atq;
    protected int def;
    protected int exp;
    protected int lv;

    // Constructeur générique
    public Personnage(String nom, String classe, int pv, int atq, int def) {
        this.nom = nom;
        this.classe = classe;
        this.pv = pv;
        this.pvActuel = pv; // Au départ, PV actuelle = PV max
        this.atq = atq;
        this.def = def;
        this.exp = 0;
        this.lv = 1;
    }

    // Constructeur vide pour héritage
    protected Personnage() {
        this.exp = 0;
        this.lv = 1;
    }

    // Getters
    public String getNom() { return nom; }
    public String getClasse() { return classe; }
    public int getPv() { return pv; }                 // PV max
    public int getPvActuel() { return pvActuel; }     // PV actuelle
    public int getAtq() { return atq; }
    public int getDef() { return def; }
    public int getExp() { return exp; }
    public int getLv() { return lv; }

    // Setters
    public void setPv(int pv) { this.pv = pv; }               // max
    public void setPvActuel(int pvActuel) {                  // actuelle
        this.pvActuel = Math.max(0, Math.min(pvActuel, this.pv)); // ne dépasse pas max et pas < 0
    }
    public void setAtq(int atq) { this.atq = atq; }
    public void setDef(int def) { this.def = def; }
    public void setExp(int exp) { this.exp = exp; }
    public void setLv(int lv) { this.lv = lv; }

    // Vérifie si le personnage est vivant
    public boolean estVivant() {
        return this.pvActuel > 0;
    }

    // Restaure les PV à max
    public void restaurerPv() {
        this.pvActuel = this.pv;
    }

    public void levelUp() {
        lv++; // on passe au niveau supérieur
        System.out.println("\nFélicitations ! " + nom + " passe au niveau " + lv + " !");

        // Augmentation des stats selon la classe
        switch (classe) {
            case "Guerrier":
                pv += 10;          // +20 PV max
                pvActuel = pv;     // restauration automatique
                atq += 2;          // +5 ATQ
                def += 2;          // +3 DEF
                break;
            case "Mage":
                pv += 5;          // +10 PV max
                pvActuel = pv;
                atq += 5;          // +7 ATQ
                def += 0;          // +1 DEF
                break;
            default:
                // classes futures
                break;
        }

        System.out.println("Nouvelles stats : PV=" + pv + ", ATQ=" + atq + ", DEF=" + def);
    }
}