package rpg_java.affichage;

import java.util.List;

import rpg_java.pnj.Monstre;
import rpg_java.pnj.UtilsMonstre;

public class AfficherBestiaire {

    public static void afficherBestiaire() {

        try {
            List<Monstre> monstres = UtilsMonstre.getMonstres();

            System.out.println("=========== BESTIAIRE ===========");

            System.out.printf("%-12s %-8s %-6s %-6s %-6s%n",
                    "Nom", "Niveau", "PV", "ATQ", "DEF");

            for (Monstre m : monstres) {

                String niveau = m.getNiveauMin() + "-" + m.getNiveauMax();

                System.out.printf("%-12s %-8s %-6d %-6d %-6d%n",
                        m.getNom(),
                        niveau,
                        m.getPv(),
                        m.getAtq(),
                        m.getDef());
            }

            System.out.println("=================================");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}