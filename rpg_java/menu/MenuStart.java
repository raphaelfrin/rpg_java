package rpg_java.menu;

import java.io.File;
import java.util.Scanner;
import rpg_java.personnage.Personnage;
import rpg_java.sauvegarde.Sauvegarde;

public class MenuStart {

    public static Personnage afficherMenu(Scanner scanner) {
        boolean running = true;
        Personnage perso = null;

        while (running) {
            System.out.println("\nBienvenue dans le jeu !");
            System.out.println("1 - Créer un personnage");
            System.out.println("2 - Charger un personnage");
            System.out.println("Tapez 'quit' pour quitter");
            System.out.print("Choix : ");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Fermeture du jeu...");
                break;
            }

            int choix;
            try {
                choix = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : vous devez entrer un nombre ou 'quit'.");
                continue;
            }

            switch (choix) {
                case 1:
                    perso = CreationPersonnage.creerPersonnage(scanner);
                    if (perso != null) running = false;
                    break;
                case 2:
                    perso = ChargerPersonnage.chargerPersonnage(scanner);

                    if (perso != null) running = false;

                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }

        return perso;
    }
}