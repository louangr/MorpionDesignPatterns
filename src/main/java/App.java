import java.util.List;
import java.util.Scanner;

public class App {
    private final static Scanner scanner = new Scanner(System.in);
    private final static ConfigRepository config = ConfigRepository.getInstance();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n\nBienvenue dans le MORPION");
            System.out.println("-------------------------\n");
            System.out.println("1 - Jouer");
            System.out.println("2 - Ajouter un joueur");
            System.out.println("3 - Lister les joueurs");
            System.out.println("4 - Tester Ajout Score Joueur");
            System.out.println("0 - Quitter\n");

            choice = (int) Utils.demanderNombre("Votre choix : ", true, scanner);
            System.out.println("\n");

            switch (choice) {
                case 1 -> jouer();
                case 2 -> ajouterUnJoueur();
                case 3 -> listerLesJoueurs();
                case 4 -> testAjoutScoreJoueur();
                case 0 -> scanner.close();
                default -> {
                    scanner.close();
                    choice = 0;
                }
            }
        } while(choice != 0);

        System.out.println("AU REVOIR\n\n");
    }

    private static void testAjoutScoreJoueur() {
        String pseudo = Utils.demanderString("Le pseudo (score +10):", scanner);
        Joueur joueur = config.getJoueur(pseudo);
        joueur.setScoreEnPlus(10);
    }

    private static void listerLesJoueurs() {
        List<Joueur> list = config.getJoueurs();

        if (list.size() > 0) {
            System.out.println("Voici les joueurs : \n");

            for (Joueur j : list) {
                System.out.println(j.toString());
            }
        }
        else {
            System.out.println("Aucun joueurs enregistrés");
        }

        Utils.demanderInteraction(scanner);
    }

    private static void ajouterUnJoueur() {
        String pseudo = Utils.demanderString("Votre pseudo :", scanner);
        String messageAjout = config.ajouterJoueur(pseudo)
                ? "Ajouté avec succès"
                : "Ajout impossible : le pseudo est déjà utilisé";
        System.out.println(messageAjout);
        Utils.demanderInteraction(scanner);
    }

    private static void jouer() {
        // TODO: vérifier qu'il y a dejà au moins deux joueurs présent dans la liste des joueurs sinon proposer d'aller en créer

        // TODO: créer le jeu dans une classe Game
        // qui demandera 2 joueurs par leur pseudo (via ConfigRepository.getJoueur(pseudo))
        // qui leur fera jouer une partie (=> en implémentant un DesignPattern)
        // qui felicitera le vainqueur et lui augmentera son score personnel (via Joueur.setScoreEnPlus(10))
        // + si jamais on a le temps => sauvegarder chaque Game dans une List<Game> dans le ConfigRepository pour garder un historique des parties (des deux joueurs de la partie et du vainqueur)
    }
}
