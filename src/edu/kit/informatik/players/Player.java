package edu.kit.informatik.players;

import java.util.ArrayList;
import java.util.HashMap;

import edu.kit.informatik.board.Board;
import edu.kit.informatik.main.InOutput;
import edu.kit.informatik.main.Main;
import edu.kit.informatik.main.Session;
import edu.kit.informatik.market.Market;
import edu.kit.informatik.tiles.Barn;
import edu.kit.informatik.tiles.Tile;
import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class of players' implementation. There will be declared all needed attributes
 * and methods for the game.
 * @author ubvaa
 * @version 1.1
 */
public class Player implements IPlayer {

    /**
     * Pool of all players
     */
    private static Player[] playersPool;
    /**
     * Pool of winners
     */
    private static ArrayList<Player> winnersPool = new ArrayList<>();
    /**
     * Winning gold
     */
    private static int winningGold;
    /**
     * Player's name
     */
    private String name;
    /**
     * Player's gold amount
     */
    private int gold;
    /**
     * Player's board
     */
    private Board board;
    /**
     * Player's moves amount in one round
     */
    private int movesAmount;
    /**
     * Player's updates (vegetables growed, barn spoiled) after the round
     */
    private HashMap<String, Integer> roundUpdates;

    /**
     * Constructor for a new player
     * @param name of the player
     */
    public Player(String name) {
        this.name = name;
        this.board = new Board();
        refreshTurn();
    }
    
    /**
     * Setter for winning gold
     * @param gold - amount of gold to be set as winning gold
     */
    public static void setWinningGold(int gold) {
        winningGold = gold;
    }
    
    /**
     * Method, that creates array, which length equals amount of players and that will be used as players' pool
     * @param playersAmount - amount of players in the game
     */
    public static void createPlayersPool(int playersAmount) {
        playersPool = new Player[playersAmount];
    }
    
    /**
     * Method, that adds new players to players' pool
     * @param number - "index number" of the player in the game, started with 1
     * @param name - name of the player
     */
    public static void addPlayer(int number, String name) {
        playersPool[number - 1] = new Player(name);
    }
    
    /**
     * Getter for players' pool
     * @return - array of players of the game
     */
    public static Player[] getPlayers() {
        return playersPool;
    }
    
    /**
     * Getter for winners' pool
     * @return - array of winners of the game
     */
    public static ArrayList<Player> getWinners() {
        return winnersPool;
    }
    
    @Override
    public HashMap<String, Integer> getRoundUpdates() {
        return this.roundUpdates;
    }
    
    @Override
    public void updateBoard() {
        Tile[][] board = this.board.getBoard();
        int spoiled = 0;
        int growed = 0;
        for (Tile[] line : board) {
            for (Tile tile : line) {
                if (tile == null) {
                    continue;
                }
                if (tile.getClass().equals(Barn.class) || !tile.isFree()) {
                    tile.decreaseCountdown();
                }
                
                if (tile.getCountdown() == 0) {
                    if (tile.getClass().equals(Barn.class)) {
                        spoiled = tile.getVegsAmount();
                        this.roundUpdates.put(Main.SPOILED, spoiled);
                        ((Barn) tile).cleanBarn();
                    } else {
                        growed = tile.getVegsAmount();
                        tile.increasePlantedVegs();
                        growed = tile.getVegsAmount() - growed;
                        this.roundUpdates.put(Main.GROWED, this.roundUpdates.get(Main.GROWED) + growed);       
                    }
                }
                
            }
        }
    }
    
    @Override
    public void refreshTurn() {
        this.movesAmount = 2;
        this.roundUpdates = new HashMap<>() {{
                put("growed", 0);
                put("spoiled", 0);
            }};
    }
    
    @Override
    public void doMove() {
        this.movesAmount = this.movesAmount - 1;
    }
    
    @Override
    public void endTurn() {
        this.movesAmount = 0;
    }
    
    @Override
    public int getMoves() {
        return this.movesAmount;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setGold(int gold) {
        this.gold = gold;
    }
    
    @Override
    public int getGold() {
        return this.gold;
    }

    @Override
    public void buyVegetable(VegetableType type) {
        this.gold = this.gold - Market.getPrice(type);
        this.getBarn().addVegetables(type, 1);
    }
    
    @Override
    public boolean allowedToBuy(int x, int y) {
        return this.board.allowedToBuy(x, y);
    }
    
    @Override
    public Tile[][] getBoard() {
        return this.board.getBoard();
    }
    
    @Override
    public int getBoardHeight() {
        return this.board.getHeight();
    }
    
    @Override
    public int getBoardWidth() {
        return this.board.getWidth();
    }
    
    @Override
    public String buyLand(int x, int y) {
        return this.board.buyLand(x, y);
    }
    
    @Override
    public int[] convertCoordinates(int x, int y) {
        return this.board.convertCoordinates(x, y);
    }
    
    @Override
    public int purchasePrice(int x, int y) {
        return this.board.purchasePrice(x, y);
    }
    
    @Override
    public String boardToString() {
        return this.board.boardToString();
    }
    
    @Override
    public Barn getBarn() {
        return this.board.getBarn();
    }
    
    @Override
    public String barnToString() {
        return this.board.barnToString(this);
    }
    
    /**
     * Method, that does early winners prove after the "quit"-command.
     * It ends a session and outputs score and winners in the end.
     */
    public static void earlyWinnersProve() {
        int winningGold = 0;
        
        for (Player player : playersPool) {
            int playerGold = player.getGold();
            if (playerGold > winningGold) {
                winningGold = playerGold;
                winnersPool = new ArrayList<>();
                winnersPool.add(player);
            }   
            else if (playerGold == winningGold) {
                winnersPool.add(player);    
            }
        }
        Session.end();
        InOutput.writeWinners();
    }
    
    /**
     * Method, that proves winners after each round. If there are at least
     * one winner, session will be ended, score and winner(s) will be outputed.
     */
    public static void winnersProve() {
        for (Player player : playersPool) {
            int playerGold = player.getGold();
            if (playerGold > winningGold) {
                winningGold = playerGold;
                winnersPool = new ArrayList<>();
                winnersPool.add(player);
            } else if (playerGold == winningGold) {
                winnersPool.add(player);
            }
        }
        if (winnersPool.size() > 0) {
            Session.end();
            InOutput.writeWinners();
        }
    }
    
}
