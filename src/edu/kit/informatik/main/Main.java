package edu.kit.informatik.main;

/**
 * This is Main class, the entry point of the program.
 * Here are initialized all in- and output constants of program.
 * @author ubvaa
 * @version 1.1
 */
public final class Main {

    /**
     * Line separator
     */
    public static final String LINE_SEPARATOR = System.lineSeparator();
    /**
     * Starting picture of the game
     */
    public static final String STARTING_PICTURE = "                           _.-^-._    .--.    " + LINE_SEPARATOR
            + "                        .-'   _   '-. |__|    " + LINE_SEPARATOR
            + "                       /     |_|     \\|  |    " + LINE_SEPARATOR
            + "                      /               \\  |    " + LINE_SEPARATOR
            + "                     /|     _____     |\\ |    " + LINE_SEPARATOR
            + "                      |    |==|==|    |  |    " + LINE_SEPARATOR
            + "  |---|---|---|---|---|    |--|--|    |  |    " + LINE_SEPARATOR
            + "  |---|---|---|---|---|    |==|==|    |  |    " + LINE_SEPARATOR
            + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + LINE_SEPARATOR
            + "^^^^^^^^^^^^^^^ QUEENS FARMING ^^^^^^^^^^^^^^^" + LINE_SEPARATOR
            + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    /**
     * First question for initialization of the game
     */
    public static final String PLAYERS_AMOUNT_DECLARATION = "How many players?";
    /**
     * Second question for initialization of the game
     */
    public static final String PLAYER_DECLARATION = "Enter the name of player ";
    /**
     * Third question for initialization of the game
     */
    public static final String STARTING_GOLD_DECLARATION = "With how much gold should each player start?";
    /**
     * Fourth question for initialization of the game
     */
    public static final String WINNING_GOLD_DECLARATION = "With how much gold should a player win?";
    /**
     * Fifth question for initialization of the game
     */
    public static final String SEEDS_DECLARATION = "Please enter the seed used to shuffle the tiles:";

    
    /**
     * Invalid command error text
     */
    public static final String INVALID_COMMAND = "invalid command.";
    /**
     * Invalid arguments error text
     */
    public static final String INVALID_ARGS = "invalid arguments.";
    /**
     * Invalid seed error text
     */
    public static final String INVALID_SEED = "seed must be an integer in range from −2147483648 to 2147483648.";
    /**
     * Invalid winning gold amount error text
     */
    public static final String INVALID_WINNING_AMOUNT = "winning amount of gold must be greater than or equal to 1.";
    /**
     * Invalid starting gold amount error text
     */
    public static final String INVALID_STARTING_AMOUNT = "starting amount of gold must be greater than or equal to 0.";
    /**
     * Command line arguments error text
     */
    public static final String NO_ARGS = "no command arguments are allowed.";
    /**
     * Invalid amount error text
     */
    public static final String INVALID_AMOUNT = "amount must be an integer.";
    /**
     * Not enough gold error text
     */
    public static final String NOT_ENOUGH_GOLD = "you don't have enough gold.";
    /**
     * No more tiles error text
     */
    public static final String NO_TILES = "there is no more tiles to buy.";
    /**
     * Tile is too far to buy error text
     */
    public static final String TILE_TOO_FAR = "this tile is too far from you.";
    /**
     * No such tile on player's board error text
     */
    public static final String NO_SUCH_TILE = "there is no such tile on your board.";
    /**
     * Invalid player's name error text
     */
    public static final String INVALID_PLAYER_NAME = "player's name must correspond to regular expression [A-Za-z]+.";
    /**
     * Invalid players' amount error text
     */
    public static final String PLAYERS_AMOUNT = "there must be at least one player.";
    

    /**
     * Command show
     */
    public static final String COMMAND_SHOW = "show";
    /**
     * Command buy
     */
    public static final String COMMAND_BUY = "buy";
    /**
     * Command sell
     */
    public static final String COMMAND_SELL = "sell";
    /**
     * Command plant
     */
    public static final String COMMAND_PLANT = "plant";
    /**
     * Command harvest
     */
    public static final String COMMAND_HARVEST = "harvest";
    /**
     * Command end
     */
    public static final String COMMAND_END = "end";
    /**
     * Command quit
     */
    public static final String COMMAND_QUIT = "quit";
    
    /**
     * Barn is spoiled text
     */
    public static final String BARN_SPOILED = "The vegetables in your barn are spoiled.";
    /**
     * Vegetables have grown text
     */
    public static final String GROWN_PLURAL = " vegetables have grown since your last turn.";
    /**
     * Vegetables has grown text
     */
    public static final String GROWN_SINGULAR = " vegetable has grown since your last turn.";
    /**
     * "vegetable" text
     */
    public static final String VEGETABLE = "vegetable";
    /**
     * "land" text
     */
    public static final String LAND = "land";
    /**
     * "all" text
     */
    public static final String ALL = "all";
    /**
     * "growed" text
     */
    public static final String GROWED = "growed";
    /**
     * "spoiled" text
     */
    public static final String SPOILED = "spoiled";
    /**
     * Player's name pattern
     */
    public static final String PLAYER_PATTERN = "[A-Za-z]+";
    /**
     * "tomato" text
     */
    public static final String TOMATO = "tomato";
    /**
     * "salad" text
     */
    public static final String SALAD = "salad";
    /**
     * "mushroom" text
     */
    public static final String MUSHROOM = "mushroom";
    /**
     * "carrot" text
     */
    public static final String CARROT = "carrot";
    /**
     * Blank symbol
     */
    public static final String BLANK_SYMBOL = " ";
    /**
     * Separator symbol for showing board
     */
    public static final String SEPARATOR_SYMBOL = "|";
    /**
     * Separator for showing barn
     */
    public static final String BARN_SEPARATOR = "-";
    

    /**
     * Private Main constructor
     */
    private Main() { }
    
    /**
     * Entry point of the program.
     * @param args arguments, that will be checked in @Communication class
     */
    public static void main(String[] args) {
        Session communication = new Session();
        communication.begin(args);
    }
}
