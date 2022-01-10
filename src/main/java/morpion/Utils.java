package morpion;

import java.util.Scanner;

public class Utils {
    public static String demanderInteraction(Scanner scanner) {
        return scanner.nextLine();
    }

    public static String demanderString(String aDemander, Scanner scanner) {
        System.out.println(aDemander);
        return scanner.nextLine();
    }

    public static double demanderNombre(String aDemander, boolean isInt, Scanner scanner) {
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

    public static int genererNombreAleatoire(int min, int max) {
        return (int) (min + (Math.random() * ((max - min) + min)));
    }

}
