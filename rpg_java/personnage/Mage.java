package rpg_java.personnage;

public class Mage extends Personnage {

    public Mage(String nom) {
        super(nom, "Mage",
                30,   // PV max
                10,   // ATQ
                1);   // DEF
    }
}