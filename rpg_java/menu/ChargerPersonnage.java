package rpg_java.menu;

import java.io.*;
import java.util.Scanner;

import rpg_java.personnage.Personnage;
import rpg_java.sauvegarde.Sauvegarde;

public class ChargerPersonnage {

    public static Personnage chargerPersonnage(Scanner scanner) {

        File dossier = new File("src/rpg_java/save/");

        File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".txt"));

        if (fichiers == null || fichiers.length == 0) {
            System.out.println("Aucune sauvegarde disponible.");
            return null;
        }

        System.out.println("\n=== SAUVEGARDES DISPONIBLES ===");

        for (int i = 0; i < fichiers.length; i++) {

            try (BufferedReader reader = new BufferedReader(new FileReader(fichiers[i]))) {

                String nom = reader.readLine();      // ligne 1
                String classe = reader.readLine();   // ligne 2

                reader.readLine(); // pv
                reader.readLine(); // pvActuel
                reader.readLine(); // atq
                reader.readLine(); // def
                reader.readLine(); // exp

                int lv = Integer.parseInt(reader.readLine()); // ligne 8

                System.out.println((i + 1) + " - " + nom + " (" + classe + " LV " + lv + ")");

            } catch (Exception e) {
                System.out.println((i + 1) + " - [Fichier corrompu]");
            }
        }

        System.out.print("Choix : ");
        String input = scanner.nextLine();

        int choix;
        try {
            choix = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide.");
            return null;
        }

        if (choix < 1 || choix > fichiers.length) {
            System.out.println("Choix invalide.");
            return null;
        }

        String nomFichier = fichiers[choix - 1].getName().replace(".txt", "");

        Personnage perso = Sauvegarde.charger(nomFichier);

        if (perso == null) {
            System.out.println("Chargement échoué.");
        }

        return perso;
    }
}