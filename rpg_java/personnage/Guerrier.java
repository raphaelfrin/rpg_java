package rpg_java.personnage;

public class Guerrier extends Personnage {

    public Guerrier(String nom) {
        super(nom, "Guerrier",
                50,   // PV max
                5,   // ATQ
                5);   // DEF );
    }
}
