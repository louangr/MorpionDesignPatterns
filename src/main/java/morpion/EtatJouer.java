package morpion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static morpion.WinnerPossibilities.*;

public class EtatJouer extends Etat {
    public EtatJouer(Game game) {
        super(game);
    }

    @Override
    public void start(){
        jouer();
        game.changerEtatTermine();
        game.demarrerEtat();
    }

    private void jouer() {
        int position;
        boolean continueGame;

        do {
            afficherGrille();
            boolean continueInput = true;
            do {
                position = (int) Utils.demanderNombre(game.getCurrentPlayer().getPseudo() + " (" + game.getCurrentPlayer().getIcone() + ")" + " - Position à jouer : ",true, game.getScanner());
                if((position < 1 || position > 9) || (game.getGrid().get(position - 1) != null)) {
                    System.err.println("Veuillez saisir un emplacement entre 1 et 9 qui n'est pas déjà occupé");
                } else {
                    continueInput = false;
                    game.getGrid().put(position - 1, game.getCurrentPlayer());
                    game.setCurrentPlayer(game.getCurrentPlayer().equals(game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1());
                }

            }while(continueInput);

            continueGame = verifierGagnant();

        }while(!continueGame);

    }

    private boolean verifierGagnant() {

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
                players.add(game.getGrid().get(value));
            }
            if ((Collections.frequency(players, players.get(0))) == players.size()) {
                if (players.get(0) != null) {
                    afficherGrille();
                    game.setWinner(players.get(0).equals(game.getPlayer1()) ? JOUEUR1 : JOUEUR2);
                    return true;
                }
            }
        }

        for(Map.Entry<Integer, Joueur> entry : game.getGrid().entrySet()) {
            equality = entry.getValue() == null || equality;
        }

        if(!equality) {
            afficherGrille();
            game.setWinner(AUCUN);
            return true;
        }

        return false;
    }

    public void afficherGrille() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if(game.getGrid().get(i * 3 + j) == null) {
                    sb.append(" " + ((i * 3 + j) + 1) + " ");
                } else {
                    sb.append(" " + game.getGrid().get(i * 3 + j).getIcone() + " ");
                }
                sb.append(j == 2 ? "" : "|");
            }
            sb.append("\n");
            sb.append(i != 2 ? ("-----------") : "").append(i != 2 ? "\n" : "");
        }
        System.out.println(sb);

    }

}
