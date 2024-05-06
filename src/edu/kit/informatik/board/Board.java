package edu.kit.informatik.board;

import edu.kit.informatik.main.Main;
import edu.kit.informatik.players.Player;
import edu.kit.informatik.tiles.Barn;
import edu.kit.informatik.tiles.Field;
import edu.kit.informatik.tiles.Garden;
import edu.kit.informatik.tiles.Tile;

/**
 * A class, that implements Board of each player in the game.
 * @author ubvaa
 * @version 1.1
 */
public class Board implements IBoard {
    /**
     * Starting board for each player
     */
    private final Tile[][] startingBoard = {{null, new Field(), null}, {new Garden(), new Barn(), new Garden()}};
    /**
     * Factor by price purchasing
     */
    private final int factor = 10;
    
    /**
     * Height of the board
     */
    private int height;
    /**
     * Width of the board
     */
    private int width;
    /**
     * The first coordinate of barn on the board
     */
    private int barnX;
    /**
     * The second coordinate of barn on the board
     */
    private int barnY;
    /**
     * Board as a 2D-Array of tiles
     */
    private Tile[][] board;
    
    /**
     * Constructor of a new board
     */
    public Board() {
        this.board = startingBoard;
        this.barnX = 1;
        this.barnY = 1;
        this.height = 2;
        this.width = 3;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public Tile[][] getBoard() {
        return this.board;
    }
    
    @Override
    public String barnToString(Player player) {
        Tile barn = this.board[this.barnX][this.barnY];
        return ((Barn) barn).toString(player);
    }
    
    @Override
    public int[] convertCoordinates(int x, int y) {
        int[] newCoordinates = new int[2];
        newCoordinates[0] = barnX - y;
        newCoordinates[1] = barnY + x;
        return newCoordinates;
    }
    
    @Override
    public String buyLand(int x, int y) {
        Tile tile = Tile.getTilesPool().get(0);
        Tile.getTilesPool().remove(0);

        if (x < 0) {
            this.board[x + 1][y] = tile;
        }
        else if (y < 0) {
            this.board[x][y + 1] = tile;
        } 
        else {
            this.board[x][y] = tile;
        }
        String name = tile.getClass().getSimpleName();
        if (name.contains("Large")) {
            name = name.substring(0, 5) + Main.BLANK_SYMBOL + name.substring(5);
        }
        return name;
    }
    
    @Override
    public boolean allowedToBuy(int x1, int y1) {
        int x = x1;
        int y = y1;
        Tile[][] board = this.board;
        int height = this.getHeight();
        int width = this.getWidth();
        if (x < -1 || y < -1 || x > height || y > width || x + y - height - width >= 0) {
            return false;
        } else if (x == height) {
            if (board[x - 1][y] == null) {
                return false;
            }
            this.extendDown();
            height++;
        } else if (x == -1) {
            if (board[x + 1][y] == null) {
                return false;
            }
            this.extendUp();
            x = 0;
            height++;
        } else if (y == width) {
            if (board[x][y - 1] == null) {
                return false;
            }
            this.extendRight();
            width++;
        } else if (y == -1) {
            if (board[x][y + 1] == null) {
                return false;
            }
            this.extendLeft();
            y = 0;
            width++;
        }
        
        if (x == 0 && y == 0) {
            return board[x + 1][y] != null || board[x][y + 1] != null;
        }
        else if (x == 0 && y == width - 1) {
            return board[x][y - 1] != null || board[x + 1][y] != null;
        }
        else if (x == height - 1 && y == 0) {
            return board[x - 1][y] != null || board[x][y + 1] != null;
        }
        else if (x == height - 1 && y == width - 1) {
            return board[x][y - 1] != null || board[x - 1][y] != null;
        }
        else if (x == height - 1 && y > 0 && y < width - 1) {
            return board[x - 1][y] != null || board[x][y + 1] != null || board[x][y - 1] != null;
        }
        else if (x == 0 && y > 0 && y < width - 1) {
            return board[x + 1][y] != null || board[x][y + 1] != null || board[x][y - 1] != null;
        }
        else if (x > 0 && x < height - 1 && y == width - 1) {
            return board[x][y - 1] != null || board[x + 1][y] != null || board[x - 1][y] != null;
        }
        else if (x > 0 && x < height - 1 && y == 0) {
            return board[x][y + 1] != null || board[x + 1][y] != null || board[x - 1][y] != null;
        }
        else if (x > 0 && x < height - 1 && y > 0 && y < width - 1) {
            return board[x + 1][y] != null || board[x - 1][y] != null || board[x][y + 1] != null 
                    || board[x][y - 1] != null;
        }
        else {
            return false;
        }
    }
    
    /**
     * Helping method to extend board to the right, when player wants to buy a tile, 
     * that is located on the new "column"
     */
    private void extendRight() {
        int newHeight = this.height;
        int newWidth = this.width + 1;
        Tile[][] newBoard = new Tile[newHeight][newWidth];
        for (int i = 0; i < this.height; i++) {
            System.arraycopy(this.board[i], 0, newBoard[i], 0, this.width);
        }

        this.board = newBoard;
        this.height = newHeight;
        this.width = newWidth;
    }
    
    /**
     * Helping method to extend board downwards, when player wants to buy a tile, 
     * that is located on the new "line"
     */
    private void extendDown() {
        int newHeight = this.height + 1;
        int newWidth = this.width;
        Tile[][] newBoard = new Tile[newHeight][newWidth];
        for (int i = 0; i < this.height; i++) {
            System.arraycopy(this.board[i], 0, newBoard[i], 0, this.width);
        }

        this.board = newBoard;
        this.height = newHeight;
        this.width = newWidth;
    }
    
    /**
     * Helping method to extend board upwards, when player wants to buy a tile, 
     * that is located on the new "line"
     */
    private void extendUp() {
        int newHeight = this.height + 1;
        int newWidth = this.width;
        Tile[][] newBoard = new Tile[newHeight][newWidth];
        for (int i = 0; i < this.height; i++) {
            System.arraycopy(this.board[i], 0, newBoard[i + 1], 0, this.width);
        }

        this.board = newBoard;
        this.height = newHeight;
        this.width = newWidth;
        this.barnX = this.barnX + 1;
    }
    
    /**
     * Helping method to extend board to the left, when player wants to buy a tile, 
     * that is located on the new "column"
     */
    private void extendLeft() {
        int newHeight = this.height;
        int newWidth = this.width + 1;
        Tile[][] newBoard = new Tile[newHeight][newWidth];
        for (int i = 0; i < this.height; i++) {
            System.arraycopy(this.board[i], 0, newBoard[i], 1, this.width);
        }

        this.board = newBoard;
        this.height = newHeight;
        this.width = newWidth;
        this.barnY = this.barnY + 1;
    }
    
    @Override
    public int purchasePrice(int x, int y) {
        return factor * ((Math.abs(y - this.barnY) + Math.abs(x - this.barnX)) - 1);
    }
    
    @Override
    public Barn getBarn() {
        return (Barn) this.board[this.barnX][this.barnY];
    }
    
    @Override
    public String boardToString() {
        String output = "";
        boolean lastIsTile;
        for (int i = 0; i < this.height; i++) {
            String firstLine = "";
            String secondLine = "";
            String thirdLine = "";
            lastIsTile = false;
            for (int j = 0; j < this.width; j++) {
                Tile tile = this.board[i][j];
                if (tile == null) {
                    String blankLine = Main.BLANK_SYMBOL.repeat(5);
                    if (!lastIsTile) {                        
                        blankLine += Main.BLANK_SYMBOL;
                    }
                    firstLine += blankLine;
                    secondLine += blankLine;
                    thirdLine += blankLine;
                    lastIsTile = false;
                    continue;
                } 
                if (!lastIsTile) {
                    firstLine += Main.SEPARATOR_SYMBOL;
                    secondLine += Main.SEPARATOR_SYMBOL;
                    thirdLine += Main.SEPARATOR_SYMBOL;
                }
                if (!tile.getClass().equals(Barn.class)) {
                    String abbreviation = tile.getAbbreviation();
                    String countdown;
                    String type;
                    int capacity = tile.getCapacity();
                    if (tile.isFree()) {
                        countdown = "*";
                        type = Main.BLANK_SYMBOL;
                    } else {
                        type = String.valueOf(this.board[i][j].getTypeToString().charAt(0));
                        countdown = capacity == tile.getVegsAmount() ? "*" : String.valueOf(tile.getCountdown());
                    }
                    switch (abbreviation.length()) {
                        case 1:
                            firstLine += Main.BLANK_SYMBOL + abbreviation + Main.BLANK_SYMBOL 
                                       + countdown + Main.BLANK_SYMBOL;
                            break;
                        case 2:
                            firstLine += Main.BLANK_SYMBOL + abbreviation + Main.BLANK_SYMBOL + countdown;
                            break;
                        case 3:
                            firstLine += abbreviation + Main.BLANK_SYMBOL + countdown;
                            break;
                        default:
                            break;
                    }
                    secondLine += Main.BLANK_SYMBOL.repeat(2) + type + Main.BLANK_SYMBOL.repeat(2);
                    thirdLine += Main.BLANK_SYMBOL + tile.getVegsAmount() + "/" 
                               + capacity + Main.BLANK_SYMBOL;
                } else {
                    String blankLine = Main.BLANK_SYMBOL.repeat(5);
                    String countdown = tile.getVegsAmount() != 0 ? String.valueOf(tile.getCountdown()) : "*";
                    firstLine += blankLine;
                    secondLine += Main.BLANK_SYMBOL + tile.getAbbreviation() 
                                + Main.BLANK_SYMBOL + countdown + Main.BLANK_SYMBOL;
                    thirdLine += blankLine;
                }                     
                lastIsTile = true;
                firstLine += Main.SEPARATOR_SYMBOL;
                secondLine += Main.SEPARATOR_SYMBOL;
                thirdLine += Main.SEPARATOR_SYMBOL;
            }
            if (!lastIsTile) {
                firstLine += Main.BLANK_SYMBOL;
                secondLine += Main.BLANK_SYMBOL;
                thirdLine += Main.BLANK_SYMBOL;
            }
            output += firstLine + Main.LINE_SEPARATOR + secondLine 
                   + Main.LINE_SEPARATOR + thirdLine + Main.LINE_SEPARATOR;
        }
        output = output.substring(0, output.length() - 1);
        return output;
    }
    

    
}
