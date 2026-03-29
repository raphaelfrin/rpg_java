package rpg_java.menu;

import java.util.Scanner;

import rpg_java.personnage.Assassin;
import rpg_java.personnage.Personnage;
import rpg_java.personnage.Guerrier;
import rpg_java.personnage.Mage;

public class CreationPersonnage {

    public static Personnage creerPersonnage(Scanner scanner) {
        String nom = "";

        while (nom.trim().isEmpty()) { // trim() enlève les espaces avant/après
            System.out.print("Entrez le nom de votre personnage : ");
            nom = scanner.nextLine();

            if (nom.trim().isEmpty()) {
                System.out.println("Erreur : le nom ne peut pas être vide !");
            }
        }

        Personnage perso = null;

        while (true) {
            System.out.println("Choisissez la classe :");
            System.out.println("1 - Guerrier");
            System.out.println("2 - Mage");
            System.out.println("3 - Assassin");
            System.out.println("Tapez 'quit' pour annuler");
            System.out.print("Choix : ");

            String inputClasse = scanner.nextLine();

            if (inputClasse.equalsIgnoreCase("quit")) {
                System.out.println("Création du personnage annulée.");
                break;
            }

            try {
                int classeChoix = Integer.parseInt(inputClasse);

                switch (classeChoix) {
                    case 1: perso = new Guerrier(nom); break;
                    case 2: perso = new Mage(nom); break;
                    case 3: perso = new Assassin(nom); break;
                    default: System.out.println("Classe invalide."); continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Erreur : veuillez entrer 1, 2 ou 'quit'.");
            }
        }

        if (perso != null) {
            System.out.println("Personnage créé !");
            System.out.println("Nom : " + perso.getNom() + ", Classe : " + perso.getClasse());
            System.out.println("PV : " + perso.getPv() + ", ATQ : " + perso.getAtq() + ", DEF : " + perso.getDef());
        }

        return perso;
    }
}