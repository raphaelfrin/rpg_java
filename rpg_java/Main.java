package rpg_java;

import java.util.Scanner;

import rpg_java.Combat.Combat;
import rpg_java.affichage.AfficherBestiaire;
import rpg_java.affichage.AfficherStats;
import rpg_java.menu.MenuStart;
import rpg_java.personnage.Personnage;
import rpg_java.sauvegarde.Sauvegarde;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Personnage perso = MenuStart.afficherMenu(scanner);

        if (perso == null) {
            System.out.println("Aucun personnage créé ou chargé. Fin du jeu.");
            scanner.close();
            return;
        }

        System.out.println("\nLe jeu peut maintenant continuer avec : "
                + perso.getNom() + " (" + perso.getClass().getSimpleName() + ")");

        boolean jeuActif = true;

        while (jeuActif && perso.estVivant()) {

            System.out.println("\n=== MENU DU JEU ===");
            System.out.println("1 - Commencer un nouveau combat");
            System.out.println("2 - Sauvegarder la progression");
            System.out.println("3 - Afficher les statistiques du personnage");
            System.out.println("4 - Afficher le bestiaire");
            System.out.println("5 - Quitter le jeu sans sauvegarder");
            System.out.print("Choix : ");

            String input = scanner.nextLine();
            int choix;

            try {
                choix = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : vous devez entrer un nombre.");
                continue;
            }

            switch (choix) {

                case 1:
                    Combat.lancerCombat(perso);
                    break;

                case 2:
                    Sauvegarde.sauvegarder(perso);
                    break;

                case 3:
                    System.out.println("\n=== STATISTIQUES DU PERSONNAGE ===");
                    AfficherStats.afficherStatsPerso(perso);
                    break;

                case 4:
                    System.out.println("\n=== BESTIAIRE ===");
                    AfficherBestiaire.afficherBestiaire();
                    break;

                case 5:
                    System.out.println("Fermeture du jeu...");
                    jeuActif = false;
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }

        if (!perso.estVivant()) {
            System.out.println("\nVotre personnage est mort. Fin du jeu.");
        }

        scanner.close();
    }
}