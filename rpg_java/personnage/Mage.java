package rpg_java.personnage;

public class Mage extends Personnage {

    public Mage(String nom) {
        super(nom, "Mage",
                10,   // PV max
                5,   // ATQ
                0);   // DEF
    }
}