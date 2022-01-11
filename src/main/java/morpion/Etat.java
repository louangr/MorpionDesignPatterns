package morpion;

public abstract class Etat {
    protected Game game;

    public Etat(Game game) {
        this.game = game;
    }

    public void start() {}

}
