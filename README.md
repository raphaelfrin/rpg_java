# RPG Java 🎮

![Java](https://img.shields.io/badge/Java-25-orange)
## Description

Ce projet est un **jeu de rôle textuel en Java** où le joueur peut créer ou charger un personnage, combattre des monstres et progresser en niveau.  
Le jeu se déroule sur un plateau linéaire de 10 cases, où le joueur et le monstre s’affrontent à chaque tour.

Le projet inclut :
- Création et gestion de personnages (Guerrier et Mage)
- Gestion d’un bestiaire de monstres à partir d’un fichier CSV
- Système de combat au tour par tour avec attaques, déplacement et défense
- Système d’expérience et montée de niveau
- Sauvegarde et chargement des personnages via fichiers texte

## Installation

1. Cloner le dépôt :
```bash
git clone https://github.com/raphaelfrin/rpg_java.git
```
## Structure du projet
`````
src/
│
├─ rpg_java/
│ ├─ Main.java
│ ├─ menu/
│ │ ├─ MenuStart.java
│ │ ├─ CreationPersonnage.java
│ │ └─ ChargerPersonnage.java
│ ├─ personnage/
│ │ ├─ Personnage.java
│ │ ├─ Guerrier.java
│ │ ├─ Mage.java
│ │ └─ Assassin.java
│ ├─ pnj/
│ │ ├─ Monstre.java
│ │ ├─ UtilsMonstre.java
│ │ └─ monstres.csv
│ ├─ Combat/
│ │ ├─ Attaque.java
│ │ ├─ Combat.java
│ │ ├─ Deplacement.java
│ │ ├─ MonstreEnCombat.java
│ │ ├─ Plateau.java
│ │ ├─ ResultatTour.java
│ │ └─ TourJoueur.java
│ ├─ sauvegarde/
│ │ └─ Sauvegarde.java
│ ├─ save/
│ │ └─ NomPersonnage.txt
│ └─ affichage/
│ ├─ AfficherStats.java
│ └─ AfficherBestiaire.java
`````

---

## Fonctionnalités

### Menu Principal
- Créer un personnage (Guerrier, Mage ou Assassin)
- Charger un personnage existant
- Quitter le jeu

### Combat
- Plateau linéaire de 10 cases
- Le joueur commence en case 0, le monstre en case 9
- Actions du joueur :
    - **Attaquer** : plusieurs attaques selon la classe, avec portée et bonus de dégâts
    - **Se déplacer** : déplacer d’une case vers le monstre
    - **Défendre** : augmenter temporairement la défense pour le prochain tour
- Tour du monstre :
    - S'il est à portée, attaque le joueur
    - Sinon, avance d'une case vers le joueur
- Fin du combat lorsque le joueur ou le monstre tombe à 0 PV

### Progression
- Gain d'expérience (+1 XP par monstre)
- Level-up après 5 XP
- Augmentation des statistiques à chaque niveau

### Sauvegarde / Chargement
- Les personnages sont sauvegardés dans des fichiers texte (`NomPersonnage.txt`) dans rpg_java/save/NomPersonnage.txt
- Lecture rapide pour afficher le bestiaire ou la liste des personnages sans tout charger
- Chargement complet d’un personnage sélectionné

---

## Structure du projet

---

## Comment jouer
1. Exécutez `Main.java`
2. Suivez les instructions du menu
3. Créez ou chargez un personnage
4. Commencez un combat ou gérez votre progression
5. Quittez le jeu via le menu

---

## Technologies

- Java 17+
- IntelliJ IDEA
- CSV pour la liste des monstres et txt pour la save