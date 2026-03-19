package rpg_java.pnj;

import java.io.*;
import java.util.*;

public class UtilsMonstre {

    // Liste statique pour cache
    private static List<Monstre> monstresCache = null;

    /**
     * Charge la liste des monstres depuis le CSV (une seule fois)
     */
    public static List<Monstre> getMonstres() throws Exception {

        // Si déjà chargé, on retourne la liste
        if (monstresCache != null) {
            return monstresCache;
        }

        List<Monstre> monstres = new ArrayList<>();

        InputStream is = UtilsMonstre.class.getResourceAsStream("monstres.csv");
        if (is == null) {
            throw new Exception("Fichier monstres.csv introuvable dans le package rpg_java.pnj");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String ligne;

        while ((ligne = br.readLine()) != null) {
            if (ligne.trim().isEmpty()) continue;

            String[] tokens = ligne.split(",");

            String nom = tokens[0];
            int niveauMin = Integer.parseInt(tokens[1]);
            int niveauMax = Integer.parseInt(tokens[2]);
            int pv = Integer.parseInt(tokens[3]);
            int atq = Integer.parseInt(tokens[4]);
            int def = Integer.parseInt(tokens[5]);

            monstres.add(new Monstre(nom, niveauMin, niveauMax, pv, atq, def));
        }

        br.close();

        if (monstres.isEmpty()) {
            throw new Exception("Aucun monstre chargé.");
        }

        // Stockage dans le cache pour réutilisation
        monstresCache = monstres;

        return monstresCache;
    }

    /**
     * Tirage aléatoire d'un monstre compatible avec le niveau du joueur
     */
    public static Monstre tirerMonstre(int lvJoueur) throws Exception {

        List<Monstre> monstres = getMonstres();
        List<Monstre> candidats = new ArrayList<>();

        for (Monstre m : monstres) {
            if (m.getNiveauMin() <= lvJoueur && lvJoueur <= m.getNiveauMax()) {
                candidats.add(m);
            }
        }

        if (candidats.isEmpty()) {
            throw new Exception("Aucun monstre disponible pour le niveau " + lvJoueur);
        }

        Random random = new Random();
        int index = random.nextInt(candidats.size());

        return candidats.get(index);
    }
}