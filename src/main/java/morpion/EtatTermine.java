package morpion;

import static morpion.WinnerPossibilities.*;

public class EtatTermine extends Etat {
    public EtatTermine(Game game) {
        super(game);
    }

    @Override
    public void start(){
        if(game.getWinner() == AUCUN) {
            System.out.println("Partie nulle !");
        } else {
            Joueur winner = game.getWinner() == JOUEUR1 ? game.getPlayer1() : game.getPlayer2();
            game.getConfig().getJoueur(winner.getPseudo()).setScoreEnPlus(10);
            System.out.println(winner.getPseudo() + " (" + winner.getIcone() + ")" + " gagne la partie !");
            System.out.println("Nouveau score : " + winner);
        }
        Utils.demanderInteraction(game.getScanner());
        game.changerEtatConfiguration();
    }

}
