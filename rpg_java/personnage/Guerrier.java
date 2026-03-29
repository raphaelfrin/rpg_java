package rpg_java.personnage;

public class Guerrier extends Personnage {

    public Guerrier(String nom) {
        super(nom, "Guerrier",
                15,   // PV max
                5,   // ATQ
                2);   // DEF );
    }
}
