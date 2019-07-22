package entity;

import java.util.List;
import java.util.Random;

public class Game {

    private List<Player> players;

    private Dice dice1;

    private Dice dice2;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Dice getDice1() {
        return dice1;
    }

    public void setDice1(Dice dice1) {
        this.dice1 = dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public void setDice2(Dice dice2) {
        this.dice2 = dice2;
    }

    public Player getPlayerFromName(String name) {
        final Player[] playerToReturn = new Player[1];
        this.players.forEach(player -> {
            if (player.getName().equals(name))
                playerToReturn[0] = player;
        });
        return playerToReturn[0];
    }

    public void movePlayer(String name) {
        this.dice1 = new Dice();
        dice1.setValue(new Random().nextInt(6) + 1);
        this.dice2 = new Dice();
        dice2.setValue(new Random().nextInt(6) + 1);
        this.players.forEach(player -> {
            if (player.getName().equals(name))
                player.setPosition(player.getPosition() + dice1.getValue() + dice2.getValue());
        });
    }

    public void movePlayer(String name, int x, int y) {
        this.players.forEach(player -> {
            if (player.getName().equals(name))
                player.setPosition(player.getPosition() + x + y);
        });
    }

    public boolean checkVictory() {
        final boolean[] victory = {false};
        this.getPlayers().forEach(player1 -> {
            if (player1.getPosition() == 63) {
                victory[0] = true;
            }
        });
        return victory[0];
    }

    public boolean checkGoose(int endingPosition) {
        switch (endingPosition) {
//            case 9:
            case 14:
            case 18:
            case 23: {
                return true;
            }
        }
        return false;
    }
}
