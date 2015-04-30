
/**
 * Write a description of class Option here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Option
    {
     IR("ir"),
     TERMINAR("terminar"),
     AYUDA ("ayuda"), 
     EXAMINAR ("examinar"),
     COMER ("comer"),
     VOLVER("volver"), 
     COGER("coger"),
     SOLTAR("soltar"), 
     OBJETOS("objetos"), 
     PESO("peso"),
     DESCONOCIDO("desconocido");
     
     
    private String command;
    private Option(String command)
    {this.command = command;}
    
    public String getCommand()
    {return command;}
        
        
    }

   

