package morpion;

import java.util.*;

import static morpion.WinnerPossibilities.*;

public class Game {

    private final static Scanner scanner = new Scanner(System.in);
    private final static ConfigRepository config = ConfigRepository.getInstance();
    private Joueur player1;
    private Joueur player2;
    private Joueur currentPlayer;
    private WinnerPossibilities winner;
    private final Map<Integer, Joueur> grid = new HashMap<>();

    public Game() {}

    public void ajouterJoueurs() {

        for(int i = 1; i <= 2; i++) {
            String pseudo;
            boolean continueInput = true;
            do {
                pseudo = Utils.demanderString("Pseudo joueur " + i + " :", scanner);
                if(config.getJoueur(pseudo) == null) {
                    System.err.println("Le pseudo du joueur " + i + " n'est pas présent dans la liste des joueurs !");
                }
                else if(player1 != null && player1.equals(config.getJoueur(pseudo))) {
                    System.err.println("Les deux joueurs doivent être différents !");
                }
                else {
                    continueInput = false;
                }
            }while(continueInput);

            player1 = i == 1 ? config.getJoueur(pseudo) : player1;
            player2 = i == 2 ? config.getJoueur(pseudo) : player2;
        }

    }

    public void initialisation() {

        int randomNumber;

        System.out.println("Génération aléatoire des icônes...");
        randomNumber = Utils.genererNombreAleatoire(1, 2);
        player1.setIcone(randomNumber == 1 ? "O" : "X");
        player2.setIcone(randomNumber == 1 ? "X" : "O");
        System.out.println(player1.getPseudo() + " : " + player1.getIcone() + "\n" + player2.getPseudo() + " : " + player2.getIcone());
        Utils.demanderInteraction(scanner);

        System.out.println("Génération aléatoire du joueur qui débute la partie...");
        randomNumber = Utils.genererNombreAleatoire(1, 2);
        currentPlayer = randomNumber == 1 ? player1 : player2;
        System.out.println("Le joueur qui débute la partie est : " + currentPlayer.getPseudo());
        Utils.demanderInteraction(scanner);

        for(int i = 0; i<=8; i++){
            grid.put(i, null);
        }


    }

    public void demarrer() {
        int position;
        boolean continueGame;

        do {
            afficherGrille();
            boolean continueInput = true;
            do {
                position = (int) Utils.demanderNombre(currentPlayer.getPseudo() + " (" + currentPlayer.getIcone() + ")" + " - Position à jouer : ",true, scanner);
                if((position < 1 || position > 9) || (grid.get(position - 1) != null)) {
                   System.err.println("Veuillez saisir un emplacement entre 1 et 9 qui n'est pas déjà occupé");
                } else {
                    continueInput = false;
                    grid.put(position - 1, currentPlayer);
                    currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
                }

            }while(continueInput);

            continueGame = verifierGagnant();

        }while(!continueGame);

        Utils.demanderInteraction(scanner);

    }

    public void afficherGrille() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if(grid.get(i * 3 + j) == null) {
                    sb.append(" " + ((i * 3 + j) + 1) + " ");
                } else {
                    sb.append(" " + grid.get(i * 3 + j).getIcone() + " ");
                }
                sb.append(j == 2 ? "" : "|");
            }
            sb.append("\n");
            sb.append(i != 2 ? ("-----------") : "").append(i != 2 ? "\n" : "");
        }
        System.out.println(sb);

    }

    public boolean verifierGagnant() {

        boolean equality = false;
        List<Joueur> players = new ArrayList<>();
        Integer[][] winCombinations = new Integer[][]{
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //  horizontal
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //  vertical
                {0, 4, 8}, {2, 4, 6}             //  diagonale
        };

        for (Integer[] combinations : winCombinations) {
            players.clear();
            for (Integer value : combinations) {
                players.add(grid.get(value));
            }
            if ((Collections.frequency(players, players.get(0))) == players.size()) {
                if (players.get(0) != null) {
                    afficherGrille();
                    config.getJoueur(players.get(0).getPseudo()).setScoreEnPlus(10);
                    System.out.println(players.get(0).getPseudo() + " (" + players.get(0).getIcone() + ")" + " gagne la partie !");
                    System.out.println("Nouveau score : " + players.get(0));
                    winner = players.get(0).equals(player1) ? JOUEUR1 : JOUEUR2;
                    return true;
                }
            }
        }

        for(Map.Entry<Integer, Joueur> entry : grid.entrySet()) {
            equality = entry.getValue() == null || equality;
        }

        if(!equality) {
            afficherGrille();
            System.out.println("Partie nulle !");
            winner = AUCUN;
            return true;
        }

        return false;
    }


}
