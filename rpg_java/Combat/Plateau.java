package rpg_java.Combat;

public class Plateau {
    protected static void afficherPlateau(int posJ, int posM, int taille) {
        StringBuilder plateau = new StringBuilder();
        for (int i = 0; i < taille; i++) {
            if (i == posJ && i == posM) plateau.append("X");
            else if (i == posJ) plateau.append("J");
            else if (i == posM) plateau.append("M");
            else plateau.append(".");
        }
        System.out.println(plateau.toString());
    }
}
