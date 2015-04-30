import java.util.*;
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
        
    private ArrayList<Option> options;
    private  Option commands;
    
    
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.options=new ArrayList<>();
        options.add(Option.IR);
        options.add( Option.TERMINAR);
        options.add( Option.AYUDA);
        options.add( Option.EXAMINAR);
        options.add( Option.COMER);
        options.add( Option.VOLVER);
        options.add( Option.COGER);
        options.add( Option.SOLTAR);
        options.add( Option.OBJETOS);
        options.add( Option.PESO);
        options.add( Option.DESCONOCIDO);
        
        //for(Option opt : Option.values()){options.add(opt);}
    }

    
    
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean esComando = false;
      int index = 0;
       while(index < options.size() && !esComando)
       {
           if(options.get(index).getCommand().equals(aString))            {
                esComando = true;
           }
            index++;
       }
        return esComando;
    }
    
    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
             String validCommands = "Los comandos son: \n";
      for(Option command : options)
         {
           validCommands += command.getCommand() + " ";
         }
         
         
         System.out.println(validCommands);
        
    }
    
    /**
   * Return the object Option associated with a word.
   * @param commandWord The word to look up (as a string).
   * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
   *         if it is not a valid command word
   */
  public  Option getCommandWord(String commandWord)
  { 
    Option var = Option.DESCONOCIDO;
    
    boolean encontrado = false;
       int index = 0;
       while(index < options.size() && !encontrado)
       {
        if(options.get(index).getCommand().equals(commandWord))
            {
                 encontrado = true;
                var = options.get(index);
               
            }
            index++;
        }
       
    return var;
  }
    
}
