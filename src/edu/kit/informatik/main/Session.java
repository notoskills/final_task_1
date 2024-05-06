package edu.kit.informatik.main;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Pattern;

import edu.kit.informatik.players.Player;
import edu.kit.informatik.tiles.Tile;

/**
 * A class, that implements current session. There will be declared all starting inputs
 * and will be read every players' input.
 * @author ubvaa
 * @version 1.1
 */
public class Session implements ISession {

    /**
     * Status of current session
     */
    private static boolean isRunning = true;
    
    @Override
    public void begin(String[] args) {
        if (args.length != 0) {
            InOutput.writeError(Main.NO_ARGS);
        } else {
            InOutput.writeLine(Main.STARTING_PICTURE);
            InOutput.writeLine(Main.PLAYERS_AMOUNT_DECLARATION);
            playersDeclaration();
        }
    }
    
    @Override
    public int playersAmountDeclaration() {
        Integer playersAmount = 0;
        
        while (true) {
            try {
                String input = InOutput.readLine();
                if (input.equals(Main.COMMAND_QUIT)) {
                    isRunning = false;
                    break;
                }
                
                playersAmount = Integer.valueOf(input);
                
                if (playersAmount <= 0) {
                    InOutput.writeError(Main.PLAYERS_AMOUNT);
                }
                else {
                    break;
                }
            } catch (NumberFormatException | IOException e) {
                InOutput.writeError(Main.INVALID_AMOUNT);    
            } 
        }
        
        if (isRunning) {
            Tile.tilesPoolCreating(playersAmount);
        }
        return playersAmount;
    }
    

    @Override
    public void playersDeclaration() {
        int playersAmount = playersAmountDeclaration();
        Player.createPlayersPool(playersAmount);
        String playerName;
        for (int i = 1; i <= playersAmount; i++) {
            try {
                InOutput.writeLine(Main.PLAYER_DECLARATION + String.valueOf(i) + ":");
                String input = InOutput.readLine();
                if (input.equals(Main.COMMAND_QUIT)) {
                    isRunning = false;
                    break;
                }
                playerName = new String(input);
                
                while (!Pattern.matches(Main.PLAYER_PATTERN, playerName)) {
                    InOutput.writeError(Main.INVALID_PLAYER_NAME);
                    playerName = InOutput.readLine();
                    if (playerName.equals(Main.COMMAND_QUIT)) {
                        isRunning = false;
                        break;
                    }
                }
                if (!isRunning) {
                    break;
                }
                
                Player.addPlayer(i, playerName);
            } catch (IOException e) {
                InOutput.writeError(Main.INVALID_COMMAND);
            }
 
        }
        
        if (isRunning) {
            InOutput.writeLine(Main.STARTING_GOLD_DECLARATION);
            startingGoldDeclaration();
        }
    }


    @Override
    public void startingGoldDeclaration() {
        Integer startingGold;
        
        while (true) {
            try {
                String input = InOutput.readLine();
                if (input.equals(Main.COMMAND_QUIT)) {
                    isRunning = false;
                    break;
                }
                
                startingGold = Integer.valueOf(input);
                if (startingGold < 0) {
                    InOutput.writeError(Main.INVALID_STARTING_AMOUNT);
                }
                else {
                    for (Player player : Player.getPlayers()) {
                        player.setGold(startingGold);
                    }
                    break;
                }
                
            } catch (NumberFormatException | IOException e) {
                InOutput.writeError(Main.INVALID_AMOUNT);
            }
        }
        if (isRunning) {
            InOutput.writeLine(Main.WINNING_GOLD_DECLARATION);
            winningGoldDeclaration();
        }
    }
    
    @Override
    public void winningGoldDeclaration() {
        Integer gold;
        while (true) {
            try {
                String input = InOutput.readLine();
                if (input.equals(Main.COMMAND_QUIT)) {
                    isRunning = false;
                    break;
                }
                
                gold = Integer.valueOf(input);
                if (gold <= 0) {
                    InOutput.writeError(Main.INVALID_WINNING_AMOUNT);
                }
                else {
                    Player.setWinningGold(gold);
                    break;
                }
            } catch (NumberFormatException | IOException e) {
                InOutput.writeError(Main.INVALID_AMOUNT);
            }
        }
        
        if (isRunning) {
            InOutput.writeLine(Main.SEEDS_DECLARATION);
            seedShuffle();
        }
    }
    
    @Override
    public void seedShuffle() {
        int seed;
        while (true) {
            try {
                String input = InOutput.readLine();
                if (input.equals(Main.COMMAND_QUIT)) {
                    isRunning = false;
                    break;
                }
                
                seed = Integer.valueOf(input);
                Random random = new Random(seed);
                Collections.shuffle(Tile.getTilesPool(), random);
                break;
            } catch (NumberFormatException | IOException e) {
                InOutput.writeError(Main.INVALID_SEED);
            }
        }
        
        if (isRunning) {
            nextRound();
        }
    }

    @Override
    public void nextRound() {
        while (isRunning) {
            for (Player player : Player.getPlayers()) {
                String output = "";
                int growed = player.getRoundUpdates().get(Main.GROWED);
                int spoiled = player.getRoundUpdates().get(Main.SPOILED);
                output += Main.LINE_SEPARATOR + "It is " + player.getName() + "'s turn!" + Main.LINE_SEPARATOR;
                if (growed == 1) {
                    output += String.valueOf(growed) + Main.GROWN_SINGULAR
                            + Main.LINE_SEPARATOR;
                } else if (growed > 1) {
                    output += String.valueOf(growed) + Main.GROWN_PLURAL
                            + Main.LINE_SEPARATOR;
                }
                
                if (spoiled != 0) {
                    output += Main.BARN_SPOILED + Main.LINE_SEPARATOR;
                }
                
                output = output.substring(0, output.length() - 1);
                InOutput.writeLine(output);
                
                while (player.getMoves() != 0 && isRunning) {
                    listen(player);
                }
                if (!isRunning) {
                    break;
                }
            }
            
            if (isRunning) {
                endRound();
            }
        }
    }
    
    @Override
    public void endRound() {
        for (Player player : Player.getPlayers()) {
            player.refreshTurn();
            player.updateBoard();
        }
        Player.winnersProve();
    }
    
    @Override
    public void listen(Player player) {
        try {
            String input = InOutput.readLine();
            Commands.handleLine(player, input);
        } catch (IOException e) {
            InOutput.writeError(Main.INVALID_COMMAND);
        }
    }
    
    /**
     * Method, that will be called after "quit"-command.
     */
    public static void quitGame() {
        Player.earlyWinnersProve();
        isRunning = false;
    }
    
    /**
     * Method, that ends the game by changing isRunning to false.
     */
    public static void end() {
        isRunning = false;
    }
}
