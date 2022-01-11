package morpion;

public class EtatConfiguration extends Etat {
    public EtatConfiguration(Game game) {
        super(game);
    }

    @Override
    public void start() {
        ajouterJoueurs();
        initialisation();
        game.changerEtatJouer();
        game.demarrerEtat();
    }

    private void ajouterJoueurs() {

        for(int i = 1; i <= 2; i++) {
            String pseudo;
            boolean continueInput = true;
            do {
                pseudo = Utils.demanderString("Pseudo joueur " + i + " :", game.getScanner());
                if(game.getConfig().getJoueur(pseudo) == null) {
                    System.err.println("Le pseudo du joueur " + i + " n'est pas présent dans la liste des joueurs !");
                }
                else if(game.getPlayer1() != null && game.getPlayer1().equals(game.getConfig().getJoueur(pseudo))) {
                    System.err.println("Les deux joueurs doivent être différents !");
                }
                else {
                    continueInput = false;
                }
            }while(continueInput);

            game.setPlayer1(i == 1 ? game.getConfig().getJoueur(pseudo) : game.getPlayer1());
            game.setPlayer2(i == 2 ? game.getConfig().getJoueur(pseudo) : game.getPlayer2());
        }

    }

    private void initialisation() {

        int randomNumber;

        System.out.println("Génération aléatoire des icônes...");
        randomNumber = Utils.genererNombreAleatoire(1, 2);
        game.getPlayer1().setIcone(randomNumber == 1 ? "O" : "X");
        game.getPlayer2().setIcone(randomNumber == 1 ? "X" : "O");
        System.out.println(game.getPlayer1().getPseudo() + " : " + game.getPlayer1().getIcone() + "\n" + game.getPlayer2().getPseudo() + " : " + game.getPlayer2().getIcone());
        Utils.demanderInteraction(game.getScanner());

        System.out.println("Génération aléatoire du joueur qui débute la partie...");
        randomNumber = Utils.genererNombreAleatoire(1, 2);
        game.setCurrentPlayer(randomNumber == 1 ? game.getPlayer1() : game.getPlayer2());
        System.out.println("Le joueur qui débute la partie est : " + game.getCurrentPlayer().getPseudo());
        Utils.demanderInteraction(game.getScanner());

        for(int i = 0; i<=8; i++){
            game.getGrid().put(i, null);
        }

    }


}
