package associations.agregation;

import java.util.ArrayList;
import java.util.List;

public class FootballTeam {
    public List<FootballPlayer> footballPlayers = new ArrayList<>();

    public FootballTeam() {}

    public List<FootballPlayer> getFootballPlayers() {
        return footballPlayers;
    }

    public void setFootballPlayers(List<FootballPlayer> footballPlayers) {
        this.footballPlayers = footballPlayers;
    }
}
