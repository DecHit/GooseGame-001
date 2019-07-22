package game;

import entity.Game;
import entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainGame {

    private static void executeMovement(Game game, String[] input) {
        boolean[] gooseMovementDone = {false};

        if (input.length == 4) {
            if (game.getPlayerFromName(input[1]) == null)
                System.out.println("Player to move not found");
            else {
                int initialPosition = game.getPlayerFromName(input[1]).getPosition();
                game.movePlayer(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                int endingPosition = game.getPlayerFromName(input[1]).getPosition();
                int bouncePosition = 0;

                if (endingPosition > 63)
                    bouncePosition = 63 - (endingPosition - 63);

                switch (endingPosition) {
                    case 6: {
                        System.out.println(input[1] + " rolls " + input[2] + ", " + input[3] + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to The Bridge. " + input[1] + "jumps to 12.");
                        game.getPlayers().forEach(player1 -> {
                            if (player1.getName().equals(input[1])) {
                                player1.setPosition(12);
                            }
                        });
                    }
                    case 5:
                    case 9:
                    case 14:
                    case 18:
                    case 23:
                    case 27: {
                        if (game.checkGoose(endingPosition + Integer.parseInt(input[2]) + Integer.parseInt(input[3]))) {
                            gooseMovementDone[0] = true;
                            System.out.println(input[1] + " rolls " + input[2] + ", " + input[3] + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ", The Goose. " + input[1] + " moves again and goes to " + (endingPosition + Integer.parseInt(input[2]) + Integer.parseInt(input[3])) + ", The Goose. " + input[1] + " moves again and goes to " + (endingPosition + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) + Integer.parseInt(input[2]) + Integer.parseInt(input[3])));
                            game.getPlayers().forEach(player1 -> {
                                if (player1.getName().equals(input[1])) {
                                    player1.setPosition(endingPosition + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) + Integer.parseInt(input[2]) + Integer.parseInt(input[3]));
                                }
                            });
                        } else {
                            gooseMovementDone[0] = true;
                            System.out.println(input[1] + " rolls " + input[2] + ", " + input[3] + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ", The Goose. " + input[1] + " moves again and goes to " + (endingPosition + Integer.parseInt(input[2]) + Integer.parseInt(input[3])));
                            game.getPlayers().forEach(player1 -> {
                                if (player1.getName().equals(input[1])) {
                                    player1.setPosition(endingPosition + Integer.parseInt(input[2]) + Integer.parseInt(input[3]));
                                }
                            });
                        }
                    }
                    default: {
                        if (game.checkVictory())
                            System.out.println(input[1] + " rolls " + input[2] + ", " + input[3] + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ". " + input[1] + " Wins!");
                        else if (!game.checkVictory() && endingPosition > 63) {
                            System.out.println(input[1] + " rolls " + input[2] + ", " + input[3] + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to 63. " + input[1] + " bounces! " + input[1] + " returns to " + bouncePosition);
                            int finalBouncePosition = bouncePosition;
                            game.getPlayers().forEach(player1 -> {
                                if (player1.getName().equals(input[1])) {
                                    player1.setPosition(finalBouncePosition);
                                }
                            });
                        } else if (!game.checkVictory() && endingPosition < 63 && !gooseMovementDone[0])
                            System.out.println(input[1] + " rolls " + input[2] + ", " + input[3] + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ".");
                    }
                }
            }
        } else if (input.length == 2) {
            if (game.getPlayerFromName(input[1]) == null)
                System.out.println("Player to move not found");
            else {
                int initialPosition = game.getPlayerFromName(input[1]).getPosition();
                game.movePlayer(input[1]);
                int endingPosition = game.getPlayerFromName(input[1]).getPosition();
                int bouncePosition = 0;

                if (endingPosition > 63)
                    bouncePosition = 63 - (endingPosition - 63);

                switch (endingPosition) {
                    case 6: {
                        System.out.println(input[1] + " rolls " + game.getDice1().getValue() + ", " + game.getDice2().getValue() + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to The Bridge. " + input[1] + "jumps to 12.");
                        game.getPlayers().forEach(player1 -> {
                            if (player1.getName().equals(input[1])) {
                                player1.setPosition(12);
                            }
                        });
                    }
                    case 5:
                    case 9:
                    case 14:
                    case 18:
                    case 23:
                    case 27: {
                        if (game.checkGoose(endingPosition + game.getDice1().getValue() + game.getDice2().getValue())) {
                            gooseMovementDone[0] = true;
                            System.out.println(input[1] + " rolls " + game.getDice1().getValue() + ", " + game.getDice2().getValue() + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ", The Goose. " + input[1] + " moves again and goes to " + (endingPosition + game.getDice1().getValue() + game.getDice2().getValue()) + ", The Goose. " + input[1] + " moves again and goes to " + (endingPosition + game.getDice1().getValue() + game.getDice2().getValue() + game.getDice1().getValue() + game.getDice2().getValue()));
                            game.getPlayers().forEach(player1 -> {
                                if (player1.getName().equals(input[1])) {
                                    player1.setPosition(endingPosition + game.getDice1().getValue() + game.getDice2().getValue() + game.getDice1().getValue() + game.getDice2().getValue());
                                }
                            });
                        } else {
                            gooseMovementDone[0] = true;
                            System.out.println(input[1] + " rolls " + game.getDice1().getValue() + ", " + game.getDice2().getValue() + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ", The Goose. " + input[1] + " moves again and goes to " + (endingPosition + game.getDice1().getValue() + game.getDice2().getValue()));
                            game.getPlayers().forEach(player1 -> {
                                if (player1.getName().equals(input[1])) {
                                    player1.setPosition(endingPosition + game.getDice1().getValue() + game.getDice2().getValue());
                                }
                            });
                        }
                    }
                    default: {
                        if (game.checkVictory())
                            System.out.println(input[1] + " rolls " + game.getDice1().getValue() + ", " + game.getDice2().getValue() + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ". " + input[1] + " Wins!");
                        else if (!game.checkVictory() && endingPosition > 63) {
                            System.out.println(input[1] + " rolls " + game.getDice1().getValue() + ", " + game.getDice2().getValue() + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to 63. " + input[1] + " bounces! " + input[1] + " returns to " + bouncePosition);
                            int finalBouncePosition = bouncePosition;
                            game.getPlayers().forEach(player1 -> {
                                if (player1.getName().equals(input[1])) {
                                    player1.setPosition(finalBouncePosition);
                                }
                            });
                        } else if (!game.checkVictory() && endingPosition < 63 && !gooseMovementDone[0])
                            System.out.println(input[1] + " rolls " + game.getDice1().getValue() + ", " + game.getDice2().getValue() + ". " + input[1] + " moves from " + (initialPosition == 0 ? "Start" : initialPosition) + " to " + endingPosition + ".");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome player! Do you wanna play? Type 'add player <name>' to enter the game");
        String[] inputString = new String[1];

        Player player;
        List<Player> players = new ArrayList<>();
        game.setPlayers(players);
        boolean gameInitialization = true;
        boolean[] gameIsRunning = {false};

        while (gameInitialization) {
            inputString[0] = scanner.nextLine();
            if (inputString[0].equalsIgnoreCase("closeInit")) {
                gameInitialization = false;
                gameIsRunning[0] = true;
            }
            if (gameInitialization && inputString[0].contains("add player ")) {
                inputString[0] = inputString[0].substring(11);

                final String[] console = {"Players: "};

                if (game.getPlayers().stream().noneMatch(player1 -> inputString[0].equals(player1.getName()))) {
                    player = new Player(inputString[0]);
                    players.add(player);
                    players.forEach(item -> console[0] = console[0] + item.getName() + ", ");
                } else {
                    System.out.println(inputString[0] + ": already existing player");
                }
                System.out.println(console[0].substring(0, console[0].length() - 2));
            }
        }

        game.setPlayers(players);

        while (gameIsRunning[0]) {
            inputString[0] = scanner.nextLine();
            if (inputString[0].contains("move ")) {
                inputString[0] = inputString[0].replaceAll(", ", " ");
                inputString[0] = inputString[0].replaceAll(",", " ");

                String[] result = inputString[0].split("\\s");

                executeMovement(game, result);

            }
            game.getPlayers().forEach(player1 -> {
                if (player1.getPosition() == 63) {
                    gameIsRunning[0] = false;
                }
            });

        }
        scanner.close();
    }
}
