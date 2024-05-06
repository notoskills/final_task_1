package edu.kit.informatik.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.kit.informatik.players.Player;

/**
 * This is a class with all in- and outputs in the program
 * @author ubvaa
 * @version 1.1
 */
public final class InOutput {

    /**
     * New scanner
     */
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Private InOutput constructor
     */
    private InOutput() { }
    
    /**
     * Method, that outputs @object in the console
     * @param object
     */
    public static void writeLine(Object object) {
        System.out.println(object);
    }
    
    /**
     * Method, that reads players' input in the console
     * @return players' input as a String
     * @throws IOException
     */
    public static String readLine() throws IOException {
        return scanner.nextLine();
    }
    
    /**
     * Method, that outputs errors in the console
     * @param errorText text of the error
     */
    public static void writeError(String errorText) {
        writeLine("Error: " + errorText);
    }
    
    /**
     * Method, that outputs players' scores in the end of the game
     */
    public static void writeScore() {
        String score = "";
        for (int i = 1; i <= Player.getPlayers().length; i++) {
            Player player = Player.getPlayers()[i - 1];
            score += "Player " + String.valueOf(i) + " (" + player.getName() + "): " 
                  + player.getGold() + Main.LINE_SEPARATOR; 
        }
        
        score = score.substring(0, score.length() - 1);
        InOutput.writeLine(score);
    }
    
    /**
     * Method, that outputs winners in the console
     */
    public static void writeWinners() {
        writeScore();
        int winnersAmount = Player.getWinners().size();
        ArrayList<Player> winners = new ArrayList<>(Player.getWinners());
        switch (winnersAmount) {
            case 1:
                System.out.println(winners.get(0).getName() + " has won!");
                break;
            case 2:
                System.out.println(winners.get(0).getName() + " and " + winners.get(1).getName() + " have won!");
                break;
            default:
                String output = "";
                
                for (int i = 0; i < winnersAmount - 1; i++) {
                    output += winners.get(i).getName() + ", ";
                }
                output = output.substring(0, output.length() - ", ".length());
                output += " and " + winners.get(winnersAmount - 1).getName();
                output += " have won!";
                System.out.println(output);
                break;
        }
    }
    
}
