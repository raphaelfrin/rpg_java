package rpg_java.Combat;

import rpg_java.personnage.Personnage;
import rpg_java.pnj.Monstre;

import java.util.Scanner;

public class TourJoueur {
    protected static boolean avanceJoueur = false;
    protected static boolean jourJoueur(Personnage perso, MonstreEnCombat monstre, Scanner scanner, int posJ, int posM) {
        boolean defenseActive = false;
        avanceJoueur = false;

        System.out.println("\nActions disponibles :");
        System.out.println("1 - Attaquer");
        System.out.println("2 - Avancer");
        System.out.println("3 - Défendre");
        System.out.print("Choix : ");

        String input = scanner.nextLine();
        int choix;

        try {
            choix = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : vous devez entrer un nombre.");
            return false;
        }

        switch (choix) {
            case 1:
                Attaque[] attaques = getAttaquesJoueur(perso);
                System.out.println("Choisissez une attaque :");
                for (int i = 0; i < attaques.length; i++) {
                    System.out.println((i + 1) + " - " + attaques[i].getNom()
                            + " (Portée: " + attaques[i].getPorteeMin() + "-" + attaques[i].getPorteeMax()
                            + ", Bonus: " + attaques[i].getBonusDegats() + ")");
                }
                System.out.print("Votre choix : ");
                int attaqueChoix;
                try {
                    attaqueChoix = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Attaque invalide !");
                    break;
                }

                if (attaqueChoix < 0 || attaqueChoix >= attaques.length) {
                    System.out.println("Attaque invalide !");
                    break;
                }

                Attaque attaque = attaques[attaqueChoix];
                int distance = posM - posJ;

                if (distance >= attaque.getPorteeMin() && distance <= attaque.getPorteeMax()) {
                    int degats = (int) (Math.max(1, perso.getAtq() * attaque.getBonusDegats()-monstre.getDef()));
                    monstre.setPv(monstre.getPv() - degats);
                    System.out.println("Vous infligez " + degats + " dégâts !");
                } else {
                    System.out.println("Le monstre est hors de portée !");
                }
                break;

            case 2:
                if (posJ + 1 < posM) {
                    avanceJoueur = true;
                    System.out.println("Vous avancez d'une case.");
                } else {
                    System.out.println("Impossible d'avancer, le monstre est trop proche !");
                }
                break;

            case 3:
                defenseActive = true;
                System.out.println("Défense activée pour le prochain tour du monstre !");
                break;

            default:
                System.out.println("Choix invalide.");
        }

        return defenseActive;
    }

    private static Attaque[] getAttaquesJoueur(Personnage perso) {
        if (perso.getClasse().equals("Guerrier")) {
            return new Attaque[]{
                    new Attaque("Coup d'épée", 1, 1, 1.1),
                    new Attaque("Arc", 2, 3, 1)
            };
        } else if (perso.getClasse().equals("Mage")) {
            return new Attaque[]{
                    new Attaque("Projectile magique", 1, 2, 1.3),
                    new Attaque("Boule de feu", 2, 5, 1.1)
            };
        } else {
            return new Attaque[0];
        }
    }
}
