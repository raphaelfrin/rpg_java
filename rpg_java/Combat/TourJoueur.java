package rpg_java.Combat;
import rpg_java.personnage.Personnage;
import rpg_java.pnj.Monstre;
import java.util.Scanner;

public class TourJoueur {
    protected static boolean avanceJoueur = false;

    protected static ResultatTour tourJoueur(Personnage perso, MonstreEnCombat monstre, Scanner scanner, int posJ, int posM) {
        boolean defenseActive = false;
        int Distance = 0;

        System.out.println("\nActions disponibles :");
        System.out.println("1 - Attaquer");
        System.out.println("2 - Se déplacer");
        System.out.println("3 - Défendre");
        System.out.print("Choix : ");

        String input = scanner.nextLine();
        int choix;

        try {
            choix = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : vous devez entrer un nombre.");
            return new ResultatTour(false, 0);
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
                    int degats = (int) (Math.max(1, perso.getAtq() * attaque.getBonusDegats() - monstre.getDef()));
                    monstre.setPv(monstre.getPv() - degats);
                    System.out.println("Vous infligez " + degats + " dégâts !");
                } else {
                    System.out.println("Le monstre est hors de portée !");
                }
                break;

            case 2:
                System.out.println("1 - avancer");
                System.out.println("2 - reculer");
                System.out.print("Votre choix : ");
                int deplacer;
                try {
                    deplacer = Integer.parseInt(scanner.nextLine());
                }  catch (NumberFormatException e) {
                    System.out.println("Déplacement non valide !");
                    break;
                }
                Deplacement[] deplacements = getDeplacementJoueur(perso);
                int deplacementChoix;
                if (deplacements.length > 1) {
                    System.out.println("Choisissez une methode de déplacement :");
                    for (int i = 0; i < deplacements.length; i++) {
                        System.out.println((i + 1) + " - " + deplacements[i].getNom()
                                + " (Distance: " + deplacements[i].getDistance() + ")");
                    }
                    System.out.print("Votre choix : ");
                    try {
                        deplacementChoix = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Le déplacement est invalide !");
                        break;
                    }
                } else {
                    deplacementChoix = 0;
                }
                if (deplacementChoix < 0 || deplacementChoix >= deplacements.length) {
                    System.out.println("Déplacement invalide !");
                    break;
                }

                Deplacement deplacement = deplacements[deplacementChoix];
                if (deplacer == 2) {
                    Distance = -deplacement.getDistance();
                } else Distance = deplacement.getDistance();
                break;

            case 3:
                defenseActive = true;
                System.out.println("Défense activée pour le prochain tour du monstre !");
                break;

            default:
                System.out.println("Choix invalide.");
        }

        return new ResultatTour(defenseActive, Distance);
    }


    private static Attaque[] getAttaquesJoueur(Personnage perso) {
        if (perso.getClasse().equals("Guerrier")) {
            return new Attaque[]{
                    new Attaque("Coup d'épée", 1, 1, 1.2),
                    new Attaque("Arc", 2, 4, 0.8)
            };
        } else if (perso.getClasse().equals("Mage")) {
            return new Attaque[]{
                    new Attaque("Projectile magique", 1, 2, 1.3),
                    new Attaque("Boule de feu", 2, 5, 1)
            };
        } else if (perso.getClasse().equals("Assassin")) {
            return new Attaque[]{
                    new Attaque("cout de couteau", 1, 1, 1.3),
                    new Attaque("lancer de couteau", 2, 3, 0.7)
            };
        } else {
            return new Attaque[0];
        }
    }

    private static Deplacement[] getDeplacementJoueur(Personnage perso) {
        if (perso.getClasse().equals("Assassin")) {
            return new Deplacement[]{
                    new Deplacement("marche", 1),
                    new Deplacement("dash", 3)
            };
        } else {
            return new Deplacement[]{
                    new Deplacement("avancer", 1)
            };
        }
    }
}
