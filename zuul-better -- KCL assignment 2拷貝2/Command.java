/**
 * This class is part of the "A stereotypical maze adventure" application.  
 *
 * This class holds information about a command that was issued by the user.
 * A command consists of two or three strings: a command word, a second
 * word, and an optional third word.
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word and the third word is null.
 * 
 * @author  Michael Kölling, David J. Barnes, and Chin Wan k21016106
 * @version 2021.12.03
 */

public class Command
{
    private String commandWord;     // The command word
    private String secondWord;      // The second word
    private String thirdWord;       // The optional third word

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     * @param thirdWord The optional third word of the command.
     */
    public Command(String firstWord, String secondWord, String thirdWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * @return The third word of this command. Returns null if there was no
     * third word
     */
    public String getThirdWord()
    {
        return thirdWord;
    }
    
    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
   
    /**
     * @return true if the command has a third word
     */
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
}

