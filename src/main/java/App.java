import java.util.Scanner;

public class App {
    private final static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n\nBienvenue dans le MORPION");
            System.out.println("-------------------------\n");
            System.out.println("1 - Jouer");
            System.out.println("2 - Ajouter un joueur");
            System.out.println("3 - Lister les joueurs");
            System.out.println("0 - Quitter\n");

            choice = (int)demanderNombre("Votre choix : ", true);
            System.out.println("\n");

            switch (choice) {
                case 1 -> jouer();
                case 2 -> ajouterUnJoueur();
                case 3 -> listerLesJoueurs();
                case 0 -> scanner.close();
                default -> {
                    scanner.close();
                    choice = 0;
                }
            }
        } while(choice != 0);

        System.out.println("AU REVOIR\n\n");
    }

    private static void listerLesJoueurs() {
    }

    private static void ajouterUnJoueur() {
    }

    private static void jouer() {
    }

    private static double demanderNombre(String aDemander, boolean isInt) {
        boolean continueInput = true;

        do {
            try{
                System.out.println(aDemander);
                var i = isInt ? scanner.nextInt() : scanner.nextDouble();
                continueInput = false;
                scanner.nextLine();
                return i;
            }
            catch (Exception e) {
                System.err.println(isInt ? "Veuillez saisir un entier" : "Veuillez saisir un nombre (/!\\ '.' => ',')");
                scanner.nextLine();
            }
        }
        while (continueInput);

        return -1;
    }
}
