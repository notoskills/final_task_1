package edu.kit.informatik.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import edu.kit.informatik.market.Market;
import edu.kit.informatik.players.Player;
import edu.kit.informatik.tiles.Barn;
import edu.kit.informatik.tiles.Tile;
import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that handle all players' commands and calls 
 * appropriate methods, that implements each command.
 * @author ubvaa
 * @version 1.1
 */
public final class Commands {

    /**
     * Private Commands constructor
     */
    private Commands() { }
    
    /**
     * Method, that handles player's input line and pass handled line 
     * to handleCommand(Player player, ArrayList<String> command)-Method
     * @param player - player, whose input is handled
     * @param line - input line
     */
    public static void handleLine(Player player, String line) {
        if (line.length() == 0 || line.substring(line.length() - 1).equals(Main.BLANK_SYMBOL)) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        ArrayList<String> command = new ArrayList<>(Arrays.asList(line.split(Main.BLANK_SYMBOL)));
        handleCommand(player, command);
    }
    
    /**
     * Method, that handle command and executes it, if it is correct
     * @param player - player, whose command is handled
     * @param command - command, that is handled
     */
    private static void handleCommand(Player player, ArrayList<String> command) {
        String commandName = command.get(0);
        command.remove(0);
        if (commandName.equals(Main.COMMAND_BUY)) {
            buy(player, command);
        } else if (commandName.equals(Main.COMMAND_PLANT)) {
            plant(player, command);
        } else if (commandName.equals(Main.COMMAND_SHOW)) {
            show(player, command);
        } else if (commandName.equals(Main.COMMAND_HARVEST)) {
            harvest(player, command);
        } else if (commandName.equals(Main.COMMAND_END)) {
            endTurn(player, command);
        } else if (commandName.equals(Main.COMMAND_SELL)) {
            sell(player, command);
        } else if (commandName.equals(Main.COMMAND_QUIT)) {
            quit(command);
        } else {
            InOutput.writeError(Main.INVALID_COMMAND);
        }
    }
    
    /**
     * Implementation of the game's command "buy"
     * @param player - current player, that has given the command
     * @param arguments - arguments of the command
     */
    private static void buy(Player player, ArrayList<String> arguments) {
        if (arguments.size() <= 1) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        switch (arguments.get(0)) {
            case Main.VEGETABLE:
                if (arguments.size() != 2) {
                    InOutput.writeError(Main.INVALID_COMMAND);
                    return;
                }       
                VegetableType type = VegetableType.stringToType(arguments.get(1));
                if (type == null) {
                    InOutput.writeError("unknown vegetable type.");
                } else if (player.getGold() < Market.getPrice(type)) {
                    InOutput.writeError(Main.NOT_ENOUGH_GOLD);
                } else {  
                    player.buyVegetable(type);
                    InOutput.writeLine("You have bought a " + type.getNameSingular() 
                                        + " for " + Market.getPrice(type) + " gold.");
                    player.doMove();
                }
                break;
            case Main.LAND:
                if (arguments.size() != 3) {
                    InOutput.writeError(Main.INVALID_COMMAND);
                    return;
                } else if (Tile.getTilesPool().size() == 0) {
                    InOutput.writeError(Main.NO_TILES);
                    return;
                }
                int x = 0;
                int y = 0;
                try {
                    x = Integer.valueOf(arguments.get(1));
                    y = Integer.valueOf(arguments.get(2));
                } catch (NumberFormatException e) {
                    InOutput.writeError(Main.INVALID_ARGS);
                    return;
                }
                int[] newCoordinates = player.convertCoordinates(x, y);
                x = newCoordinates[0];
                y = newCoordinates[1];
                Tile[][] board = player.getBoard();
                boolean isFree;
                if (x >= player.getBoardHeight() || y >= player.getBoardWidth() 
                        || x < 0 || y < 0 || board[x][y] == null) {
                    isFree = true;
                } else {
                    isFree = false;
                }
                
                if (!isFree) {
                    InOutput.writeError("you already have tile there.");
                    return;
                }
                int price = player.purchasePrice(x, y);
                int playerGold = player.getGold();
                if (price > playerGold) {
                    InOutput.writeError(Main.NOT_ENOUGH_GOLD);
                    return;
                }
                boolean allowedToBuy = player.allowedToBuy(x, y);
                if (allowedToBuy) {
                    String name = player.buyLand(x, y);
                    player.setGold(playerGold - price);
                    player.doMove();
                    InOutput.writeLine("You have bought a " + name + " for " + price + " gold.");
                } else {
                    InOutput.writeError(Main.TILE_TOO_FAR);
                }
                break;
            default:
                InOutput.writeError(Main.INVALID_COMMAND);
                break;
        }
    }
    

    /**
     * Implementation of the game's command "plant"
     * @param player - current player, that has given the command
     * @param arguments - arguments of the command
     */
    private static void plant(Player player, ArrayList<String> arguments) {
        if (arguments.size() != 3) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        
        int x = 0;
        int y = 0;
        
        try {
            x = Integer.valueOf(arguments.get(0));
            y = Integer.valueOf(arguments.get(1));
        } catch (NumberFormatException e) {
            InOutput.writeError(Main.INVALID_ARGS);
            return;
        }
        
        VegetableType type = VegetableType.stringToType(arguments.get(2));
        int[] newCoordinates = player.convertCoordinates(x, y);
        x = newCoordinates[0];
        y = newCoordinates[1];
        
        if (x >= player.getBoardHeight() || y >= player.getBoardWidth() || x < 0 || y < 0) {
            InOutput.writeError(Main.NO_SUCH_TILE);
            return;
        }
        
        Tile tile = player.getBoard()[x][y];
        
        if (tile == null) {
            InOutput.writeError(Main.NO_SUCH_TILE);
            return;
        }
        
        if (tile.allowedToPlant(type) && player.getBarn().getInventory().get(type) > 0) {
            player.getBarn().removeVegetables(type, 1);
            tile.plant(type);
            player.doMove();
        } else if (!tile.allowedToPlant(type)) {
            InOutput.writeError("not allowed vegetable/tile type or the tile is already planted.");
        } else {
            InOutput.writeError("you don't have this vegetable in your barn.");
        }
    }
    
    /**
     * Implementation of the game's command "harvest"
     * @param player - current player, that has given the command
     * @param arguments - arguments of the command
     */
    private static void harvest(Player player, ArrayList<String> arguments) {
        if (arguments.size() != 3) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        int x = 0;
        int y = 0;
        int amount = 0;
        
        try {
            x = Integer.valueOf(arguments.get(0));
            y = Integer.valueOf(arguments.get(1));
            amount = Integer.valueOf(arguments.get(2));
        } catch (NumberFormatException e) {
            InOutput.writeError(Main.INVALID_ARGS);
            return;
        }
        
        int[] newCoordinates = player.convertCoordinates(x, y);
        x = newCoordinates[0];
        y = newCoordinates[1];
        
        if (x >= player.getBoardHeight() || y >= player.getBoardWidth() || x < 0 || y < 0) {
            InOutput.writeError(Main.NO_SUCH_TILE);
            return;
        }
        
        Tile tile = player.getBoard()[x][y];
        
        if (tile == null) {
            InOutput.writeError(Main.NO_SUCH_TILE);
            return;
        }
        
        String output = "You have harvested ";
        
        if (!(tile.getClass().equals(Barn.class)) && tile.getVegsAmount() >= amount && amount >= 0 && !tile.isFree()) {
            tile.harvestVegetables(amount);
            VegetableType type = tile.getType();
            player.getBarn().addVegetables(type, amount);
            output += String.valueOf(amount) + " ";
            output += amount == 1 ? type.getNameSingular() : type.getNamePlural();
            output += ".";
            player.doMove();
        } else if (tile.getVegsAmount() < amount) {
            InOutput.writeError("there is not enough planted vegetables on the tile.");
            return;
        } else if (amount < 0) {
            InOutput.writeError("amount of vegetables can't be negative.");
            return;
        } else if (tile.getClass().equals(Barn.class)) {
            InOutput.writeError("you can't harvest barn.");
            return;
        } else {
            InOutput.writeError("you can't harvest empty tile.");
            return;
        }
        
        InOutput.writeLine(output);
    }


    /**
     * Implementation of the game's command "show"
     * @param player - current player, that has given the command
     * @param arguments - arguments of the command
     */
    private static void show(Player player, ArrayList<String> arguments) {
        if (arguments.size() != 1) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        
        switch (arguments.get(0)) {
            case "barn":
                InOutput.writeLine(player.barnToString());
                break;
            case "board":
                InOutput.writeLine(player.boardToString());
                break;
            case "market":
                InOutput.writeLine(Market.marketToString());
                break;
            default:
                InOutput.writeError(Main.INVALID_COMMAND);
                break;
        }
    }
    
    /**
     * Implementation of the game's command "sell"
     * @param player - current player, that has given the command
     * @param arguments - arguments of the command
     */
    private static void sell(Player player, ArrayList<String> arguments) {
        int soldAmount = 0;
        int earnings = player.getGold();
        HashMap<VegetableType, Integer> vegsToSell = new HashMap<VegetableType, Integer>() {{
                for (VegetableType type : VegetableType.values()) {
                    put(type, 0);
                }
            }};
        
        if (arguments.size() == 0) {
            earnings = 0;
        }
        else if (arguments.get(0).equals(Main.ALL) && arguments.size() == 1) {
            HashMap<VegetableType, Integer> barn = player.getBarn().getInventory();
            Set<VegetableType> keys = player.getBarn().getInventory().keySet();

            for (VegetableType type : keys) {
                int amount = barn.get(type);
                player.getBarn().removeVegetables(type, amount);
                vegsToSell.put(type, amount);
                soldAmount += amount;
                player.setGold(player.getGold() + amount * Market.getPrice(type));
                
            }
            earnings = player.getGold() - earnings;
        }
        else {
            for (String vegetable : arguments) {
                VegetableType type = VegetableType.stringToType(vegetable);
                if (type != null) {
                    vegsToSell.put(type, vegsToSell.get(type) + 1);
                } else {
                    InOutput.writeError(Main.INVALID_COMMAND);
                    return;
                }
            }
            
            Set<VegetableType> vegetables = vegsToSell.keySet();
            for (VegetableType type : vegetables) {
                if (vegsToSell.get(type) > player.getBarn().getInventory().get(type)) {
                    InOutput.writeError("you don't have these vegetables in your barn.");
                    return;
                }
            }
            
            for (VegetableType type : vegetables) {
                HashMap<VegetableType, Integer> inventory = player.getBarn().getInventory();
                inventory.put(type, inventory.get(type) - vegsToSell.get(type));
                soldAmount += vegsToSell.get(type);
                player.setGold(player.getGold() + vegsToSell.get(type) * Market.getPrice(type));
            }
            
            earnings = player.getGold() - earnings;
        }
        player.doMove();
        Market.pricesUpdate(vegsToSell);
        String output = "You have sold " + String.valueOf(soldAmount);
        output += soldAmount == 1 ? " vegetable " : " vegetables ";
        output += "for " + String.valueOf(earnings) + " gold.";
        InOutput.writeLine(output);

    }
    
    /**
     * Implementation of the game's command "end turn"
     * @param player - current player, that has given the command
     * @param arguments - arguments of the command
     */
    private static void endTurn(Player player, ArrayList<String> arguments) {
        if (arguments.size() != 1) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        
        String command = arguments.get(0);
        if (!command.equals("turn")) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        
        player.endTurn();
    }
    
    /**
     * Implementation of the game's command "quit"
     * @param arguments - arguments of the command
     */
    private static void quit(ArrayList<String> arguments) {
        if (arguments.size() != 0) {
            InOutput.writeError(Main.INVALID_COMMAND);
            return;
        }
        Session.quitGame();
    }
    
}
