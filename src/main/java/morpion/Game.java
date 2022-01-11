package morpion;

import java.util.*;

public class Game {

    private final static Scanner scanner = new Scanner(System.in);
    private final static ConfigRepository config = ConfigRepository.getInstance();
    private Joueur player1;
    private Joueur player2;
    private Joueur currentPlayer;
    private WinnerPossibilities winner;
    private final Map<Integer, Joueur> grid = new HashMap<>();
    private Etat etat;
    private final Etat etatConfiguration;
    private final Etat etatJouer;
    private final Etat etatTermine;

    public Game() {
        etatConfiguration = new EtatConfiguration(this);
        etatJouer = new EtatJouer(this);
        etatTermine = new EtatTermine(this);
        etat = etatConfiguration;
    }

    public void changerEtatConfiguration() {
        etat = etatConfiguration;
    }

    public void changerEtatJouer() {
        etat = etatJouer;
    }

    public void changerEtatTermine() {
        etat = etatTermine;
    }

    public void demarrerEtat() {
        etat.start();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ConfigRepository getConfig() {
        return config;
    }

    public Joueur getPlayer1() {
        return player1;
    }

    public void setPlayer1(Joueur player1) {
        this.player1 = player1;
    }

    public Joueur getPlayer2() {
        return player2;
    }

    public void setPlayer2(Joueur player2) {
        this.player2 = player2;
    }

    public Joueur getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Joueur currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Map<Integer, Joueur> getGrid() {
        return grid;
    }

    public WinnerPossibilities getWinner() {
        return winner;
    }

    public void setWinner(WinnerPossibilities winner) {
        this.winner = winner;
    }

}
