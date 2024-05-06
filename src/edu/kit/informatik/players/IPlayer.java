package edu.kit.informatik.players;

import java.util.HashMap;

import edu.kit.informatik.tiles.Barn;
import edu.kit.informatik.tiles.Tile;
import edu.kit.informatik.vegetables.VegetableType;

/**
 * Interface for the game's Player's class 
 * @author ubvaa
 * @version 1.1
 */
public interface IPlayer {
    /**
     * Getter for player's name.
     * @return - name of the player as a String
     */
    String getName();
    
    /**
     * Setter for player's gold.
     * @param gold - new amount of the gold
     */
    void setGold(int gold);
    
    /**
     * Getter for player's gold.
     * @return - amount of the player's gold
     */
    int getGold();
    
    /**
     * Getter for round updates after each round
     * @return - round updates for each player
     */
    HashMap<String, Integer> getRoundUpdates();
    
    /**
     * Method, that updates player's board after each round: decreases countdowns, increases 
     * vegetables amount, when they are growed, proves, if barn must be spoiled and so on.
     * At the end it refreshes player's round updates.
     */
    void updateBoard();
    
    /**
     * Method, that refreshes player's amount of moves in each round
     * and resets round updates.
     */
    void refreshTurn();
    
    /**
     * Method, that decreases player's amount of moves during the round.
     */
    void doMove();
    
    /**
     * Method, that nullifies player's amount of moves during the round.
     */
    void endTurn();
    
    /**
     * Getter for player's amount of moves.
     * @return - amount of moves in the round
     */
    int getMoves();
    
    /**
     * Method, that adds a vegetable, that was bought at the market, to the barn
     * and deducts gold for its purchase.
     * @param type - type of the vegetable, that was bought
     */
    void buyVegetable(VegetableType type);
    
    /**
     * Method, that proves, if player allowed to buy a tile with coordinates (x, y).
     * @param x - first coordinate of the tile
     * @param y - second coordinate of the tile
     * @return - true, if allowed; false, if not allowed
     */
    boolean allowedToBuy(int x, int y);
    
    /**
     * Getter for player's board.
     * @return - player's board as a 2D-Array
     */
    Tile[][] getBoard();
    
    /**
     * Getter for player's board's height.
     * @return - player's board's height
     */
    int getBoardHeight();
    
    /**
     * Getter for player's board's width.
     * @return - player's board's width
     */
    int getBoardWidth();
    
    /**
     * Method, that places the bought tile on the board, removes it form tiles pool 
     * and gets name of the bought tile.
     * @param x - first coordinate of the tile
     * @param y - second coordinate of the tile
     * @return - name of the bought tile as a String
     */
    String buyLand(int x, int y);
    
    /**
     * Method, that converts "gaming" coordinates to "board/2D-Array" coordinates.
     * @param x - first inputed coordinate of the tile
     * @param y - second inputed coordinate of the tile
     * @return - array of 2 integers, that are equal "new" coordinates
     */
    int[] convertCoordinates(int x, int y);
    
    /**
     * Method, that purchases price for the tile.
     * @param x - first coordinate of the tile
     * @param y - second coordinate of the tile
     * @return - price of the tile
     */
    int purchasePrice(int x, int y);
    
    /**
     * Method, that gets player's board as a String.
     * @return - player's board as a String
     */
    String boardToString();
    
    /**
     * Getter for player's barn.
     * @return - player's barn
     */
    Barn getBarn();
    
    /**
     * Method, that gets player's barn as a String.
     * @return - player's barn as a String.
     */
    String barnToString();
}
