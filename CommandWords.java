import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    //     private static final String[] validCommands = {
        //         "go", "quit", "help" , "look", "eat", "back", "take", "drop", "inventory", "weight"
        //     };
        
    private HashMap<String,Option> options;
    private Option commands;
    
    
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.options=new HashMap<>();
        options.put("go", Option.GO);
        options.put("quit", Option.QUIT);
        options.put("help", Option.HELP);
        options.put("look", Option.LOOK);
        options.put("eat", Option.EAT);
        options.put("back", Option.BACK);
        options.put("take", Option.TAKE);
        options.put("drop", Option.DROP);
        options.put("inventory", Option.INVENTORY);
        options.put("weight", Option.WEIGHT);
        options.put("unknown", Option.UNKNOWN);
    }

    
    
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {


       return options.containsKey(aString);
    }
    
    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String availableCommands = "commands\n ";
        
        for (String command : options.keySet())
        {
            System.out.println(availableCommands+ " " );
        }
        
    }
    
    /**
   * Return the object Option associated with a word.
   * @param commandWord The word to look up (as a string).
   * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
   *         if it is not a valid command word
   */
  public Option getCommandWord(String commandWord)
  { 
    Option var = null;
    if(options.get(commandWord) == null)
    {
       var=Option.UNKNOWN;
    }
    else
    {
        var= options.get(commandWord);
    }
    return var;
  }
    
}
