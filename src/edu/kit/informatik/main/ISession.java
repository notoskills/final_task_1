package edu.kit.informatik.main;

import edu.kit.informatik.players.Player;

/**
 * Interface for a session's class 
 * @author ubvaa
 * @version 1.1
 */
public interface ISession {

    /**
     * Method for the begin of program, where @param args will be checked. If there is no
     * arguments, then outputs starting picture and question about amount of players.
     * Else there will be an error with output, after which the program will stop.  
     * @param args - command line arguments
     */
    void begin(String[] args);
    
    /**
     * Method for declaration of amount of players in the game. It reads player's input
     * and handle it. If something is wrong, it will output the problem and wait for
     * the next input, until input will be correct. When input is correct, tiles' pool to
     * shuffle and players' pool will be created.
     * @return amount of players in the game
     */
    int playersAmountDeclaration();
    
    
    /**
     * Method for declaration of players' names. It reads player's input
     * and handle it. If something is wrong, it will output the problem and wait for
     * the next input, until input will be correct. If input is correct, each player
     * will be created. Then startingGoldDeclaration() will be started.
     */
    void playersDeclaration();
    
    
    /**
     * Method for declaration of players' starting amount of gold. It reads player's 
     * input and handle it. If something is wrong, it will output the problem and wait 
     * for the next input, until input will be correct. If input is correct, each player 
     * will be assigned an amount of gold. Then winningGoldDeclaration() will be started.
     */
    void startingGoldDeclaration();
    
    
    /**
     * Method for declaration of winning amount of gold. It reads player's input
     * and handle it. If something is wrong, it will output the problem and wait for
     * the next input, until input will be correct. If input is correct, winning gold
     * will be set. Then seedShuffle() will be started.
     */
    void winningGoldDeclaration();
    
    
    /**
     * Method for declaration of seed to shuffle the created tiles. It reads player's 
     * input and handle it. If something is wrong, it will output the problem and wait
     * for the next input, until input will be correct. If input is correct, tiles will
     * be shuffled. Then nextRound() will be started.
     */
    void seedShuffle();
    
    
    /**
     * Method, that implements every round and every player's turn, until the game will stop.
     * Firstly, it outputs all information, that is prescribed by the task.
     * Then listen(player) will be started.
     */
    void nextRound();
    
    
    /**
     * Method, that implements end of the round. All players' boards will be updated,
     * amount of players' turns will be reseted. Then will be checked, if someone has won.
     */
    void endRound();

    
    /**
     * Method, that reads input of players and pass each input to the Commands.handleLine() Method.
     * @param player - player, that has written command.
     */
    void listen(Player player);
    
}
