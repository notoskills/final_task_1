package edu.kit.informatik.board;

import edu.kit.informatik.players.Player;
import edu.kit.informatik.tiles.Barn;
import edu.kit.informatik.tiles.Tile;

/**
 * Interface for the game's class Board
 * @author ubvaa
 * @version 1.1
 */
public interface IBoard {
    /**
     * Getter for board's height
     * @return - board's height
     */
    int getHeight();
    
    /**
     * Getter for board's width
     * @return - board's width
     */
    int getWidth();
    
    /**
     * Getter for board as a 2D-Array of tiles
     * @return - board
     */
    Tile[][] getBoard();
    
    /**
     * Method, that gets barn as a String
     * @param player - player, whose barn is returned
     * @return - barn as a String
     */
    String barnToString(Player player);
    
    /**
     * Method, that converts input coordinates to "2D-Array" coordinates
     * @param x - first input coordinate
     * @param y - second input coordinate
     * @return - Array of two elements, that are equal new coordinates
     */
    int[] convertCoordinates(int x, int y);
    
    /**
     * Method, that adds a bought tile to the board 
     * @param x - first tile's coordinate
     * @param y - second tile's coordinate
     * @return - name of the bought tile
     */
    String buyLand(int x, int y);
    
    /**
     * Method, that proves all possible conditions, if player is allowed to buy
     * a new tile with coordinates (x, y).
     * @param x - first tile's coordinate
     * @param y - second tile's coordinate
     * @return - true, if allowed; else - false
     */
    boolean allowedToBuy(int x, int y);
    //void extendRight();
    //void extendDown();
    //void extendUp();
    //void extendLeft();
    
    /**
     * Method, that calculates price of the tile with coordinates (x, y)
     * @param x - first tile's coordinate
     * @param y - second tile's coordinate
     * @return - price of the tile
     */
    int purchasePrice(int x, int y);
    
    /**
     * Getter for a barn on the board
     * @return - barn
     */
    Barn getBarn();
    
    /**
     * Method, that transforms a board to a String
     * @return - board as a string
     */
    String boardToString();
}
