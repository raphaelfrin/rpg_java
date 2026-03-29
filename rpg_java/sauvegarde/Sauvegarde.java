package rpg_java.sauvegarde;

import java.io.*;
import rpg_java.personnage.*;

public class Sauvegarde {

    public static void sauvegarder(Personnage perso) {
        try {
            String nomFichier = perso.getNom().replaceAll("[^a-zA-Z0-9]", "_");

            File file = new File("src/rpg_java/save/" + nomFichier + ".txt");
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write(perso.getNom() + "\n");
            writer.write(perso.getClasse() + "\n");
            writer.write(perso.getPv() + "\n");
            writer.write(perso.getPvActuel() + "\n");
            writer.write(perso.getAtq() + "\n");
            writer.write(perso.getDef() + "\n");
            writer.write(perso.getExp() + "\n");
            writer.write(perso.getLv() + "\n");

            writer.close();

            System.out.println("Personnage sauvegardé dans : " + file.getName());

        } catch (IOException e) {
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }

    public static Personnage charger(String nom) {
        try {
            String nomFichier = nom.replaceAll("[^a-zA-Z0-9]", "_");

            BufferedReader reader = new BufferedReader(
                    new FileReader("src/rpg_java/save/" + nomFichier + ".txt")
            );

            String nomPerso = reader.readLine();
            String classe = reader.readLine();
            int pv = Integer.parseInt(reader.readLine());
            int pvActuel = Integer.parseInt(reader.readLine());
            int atq = Integer.parseInt(reader.readLine());
            int def = Integer.parseInt(reader.readLine());
            int exp = Integer.parseInt(reader.readLine());
            int lv = Integer.parseInt(reader.readLine());

            reader.close();

            Personnage perso = null;

            if (classe.equals("Guerrier")) {
                perso = new Guerrier(nomPerso);
            } else if (classe.equals("Mage")) {
                perso = new Mage(nomPerso);
            } else if (classe.equals("Assassin")) {
                perso = new Assassin(nomPerso);
            }
            perso.setPv(pv);
            perso.setPvActuel(pvActuel);
            perso.setAtq(atq);
            perso.setDef(def);
            perso.setExp(exp);
            perso.setLv(lv);

            System.out.println("Personnage chargé !");
            return perso;

        } catch (IOException e) {
            System.out.println("Aucune sauvegarde trouvée pour ce nom.");
            return null;
        }
    }
}