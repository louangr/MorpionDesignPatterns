import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class Joueur {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private String pseudo;
    private int score;

    public Joueur() {
        super();
    }

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }

    public void setScoreEnPlus(int score) {
        int oldValue = this.score;
        this.score += score;
        this.pcs.firePropertyChange("score", oldValue, score);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return " - " + pseudo +"\t(score: " + score + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(pseudo, joueur.pseudo);
    }
}
