import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class ConfigRepository implements PropertyChangeListener {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String nomConfig = "config.json";
    private static ConfigRepository instance;
    private static List<Joueur> joueurs;

    private ConfigRepository() {
        joueurs = new ArrayList<>();
        chargerJoueurs();
    }

    public static ConfigRepository getInstance() {
        if (instance == null) {
            instance = new ConfigRepository();
        }
        return instance;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public Joueur getJoueur(String pseudo) {
        return joueurs.stream().filter(j -> j.getPseudo().equals(pseudo)).findFirst().orElse(null);
    }

    public Boolean ajouterJoueur(String pseudo) {
        Joueur nouveauJoueur = new Joueur(pseudo);
        if (!joueurs.contains(nouveauJoueur)) {
            nouveauJoueur.addPropertyChangeListener(this);
            joueurs.add(nouveauJoueur);
            sauvegarderJoueurs();
            return true;
        }
        return false;
    }

    public Boolean sauvegarderJoueurs() {
        try {
            objectMapper.writeValue(Paths.get(nomConfig).toFile(), joueurs);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        sauvegarderJoueurs();
    }

    private void chargerJoueurs() {
        try {
            List<Joueur> joueursTemp = objectMapper.readValue(Paths.get(nomConfig).toFile(), new TypeReference<List<Joueur>>() {});

            for (Joueur j: joueursTemp) {
                Joueur nouveauJoueur = new Joueur(j.getPseudo());
                nouveauJoueur.setScoreEnPlus(j.getScore());
                nouveauJoueur.addPropertyChangeListener(this);
                joueurs.add(nouveauJoueur);
            }
        } catch (Exception e) {
        }
    }
}