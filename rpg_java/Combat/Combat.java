package rpg_java.Combat;


import java.util.Scanner;
import rpg_java.personnage.Personnage;
import rpg_java.pnj.Monstre;
import rpg_java.pnj.UtilsMonstre;
import static rpg_java.Combat.Plateau.afficherPlateau;
import static rpg_java.Combat.TourJoueur.avanceJoueur;
import rpg_java.Combat.MonstreEnCombat;

public class Combat {

    public static void lancerCombat(Personnage perso) {
        Scanner scanner = new Scanner(System.in);

        try {
            MonstreEnCombat monstre = new MonstreEnCombat(
                    UtilsMonstre.tirerMonstre(perso.getLv())
            );
            int taillePlateau = 10;
            int posJ = 0;
            int posM = taillePlateau - 1;
            boolean joueurDefense = false;
            int Distance;

            System.out.println("Un monstre apparaît !");
            System.out.println(monstre);

            // Boucle de combat
            while (perso.estVivant() && monstre.estVivant()) {

                // 1️⃣ Affichage plateau + stats
                afficherPlateau(posJ, posM, taillePlateau);
                afficherEtat(perso, monstre);

                // 2️⃣ Tour du joueur
                ResultatTour resultat = TourJoueur.tourJoueur(perso, monstre, scanner, posJ, posM);
                joueurDefense = resultat.isDefenseActive();
                Distance = resultat.getDistance();

                if (posJ + Distance < posM && posJ + Distance >= 0) {
                    posJ += Distance;
                } else System.out.println("Déplacement impossible !");

                if (monstre.getPv() <= 0) {
                    System.out.println("\nLe monstre est vaincu !");
                    perso.setExp(perso.getExp() + 1);
                    if (perso.getExp()==5) {
                        perso.setExp(0);
                        perso.levelUp();
                    } else {
                        System.out.println("\nVous gagnez +1 XP, vous avez " + perso.getExp() + " point d'expérience.");
                    }
                    break;
                }

                // 3️⃣ Tour du monstre
                int distance = posM - posJ;
                if (distance <= 1) {
                    int defence;
                    if (joueurDefense) {
                        defence = perso.getDef()*2;
                        System.out.println("Défense active ! Dégâts réduits.");
                        joueurDefense = false;
                    }else {
                        defence = perso.getDef();
                    }

                    int degats = Math.max(1,monstre.getAtq()-defence);
                    perso.setPvActuel(perso.getPvActuel() - degats);
                    System.out.println(monstre.getNom() + " attaque et inflige " + degats + " dégâts !");
                } else {
                    // Déplacement monstre vers joueur
                    posM--;
                    System.out.println(monstre.getNom() + " avance d'une case.");
                }

                System.out.println();
            }

            if (!perso.estVivant()) {
                System.out.println(perso.getNom() + " est KO ! Défaite...");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void afficherEtat(Personnage perso, MonstreEnCombat monstre) {
        System.out.println(perso.getNom() + " PV: " + perso.getPvActuel() + "/" + perso.getPv());
        System.out.println(monstre.getNom() + " PV: " + monstre.getPv());
    }
}