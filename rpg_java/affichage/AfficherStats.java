package rpg_java.affichage;

import rpg_java.personnage.Personnage;

public class AfficherStats {

    public static void afficherStatsPerso(Personnage perso) {

        System.out.println("===== STATISTIQUES DU PERSONNAGE =====");

        System.out.printf("%-12s : %s%n", "Nom", perso.getNom());
        System.out.printf("%-12s : %s%n", "Classe", perso.getClasse());
        System.out.printf("%-12s : %d%n", "Niveau", perso.getLv());
        System.out.printf("%-12s : %d%n", "EXP", perso.getExp());
        System.out.printf("%-12s : %d / %d%n", "PV", perso.getPvActuel(), perso.getPv());
        System.out.printf("%-12s : %d%n", "ATQ", perso.getAtq());
        System.out.printf("%-12s : %d%n", "DEF", perso.getDef());

        System.out.println("======================================");
    }
}