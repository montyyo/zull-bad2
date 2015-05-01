import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Portero portero;
  
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player("Marco", 50.3F);
        createRooms();
       
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, recepcion, salaDeReuniones, servicios, recursosHumanos, despachoDelDirector, salaDeProyecciones;

        entrada = new Room("en la entrada del edificio","guardian entrada", null,false);
        entrada.addItem(new Item("Jarrón", 2.5F, true));
        entrada.addItem(new Item("Jarrón", 2.5F, true));
        entrada.addItem(new Item("Sofá", 50.3F, false));
         

        recepcion = new Room("en recepción","guardian recepcion", entrada.getItem(0),true);
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("telefono", 1.2F, true));
        recepcion.addItem(new Item("PC", 6.3F, true));
        recepcion.addItem(new Item("Impresora", 3.7F, true));
        recepcion.addItem(new Item("escritorio", 30F, false));
        

        salaDeReuniones = new Room("en la sala de reuniones", "guardian sala de reuniones", null,false  );
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Mesa", 30.3F, false));
        salaDeReuniones.addItem(new Item("Microfono", 0.2F, true));

        servicios = new Room("en los servicios", "guardian servicios", null,false );
        servicios.addItem(new Item("Escobilla", 0.7F, true));
        servicios.addItem(new Item("Papel higienico", 0.2F, true));
        servicios.addItem(new Item("Ventana", 10.2F, false));
        servicios.addItem(new Item("Inhodoro", 25.6F, false));
        servicios.addItem(new Item("Lavabo", 10.6F, false));
        servicios.addItem(new Item("Espejo", 7.6F, true));

        recursosHumanos = new Room("en recursos humanos", "guardian recursos humanos", salaDeReuniones.getItem(2),true);
        recursosHumanos.addItem(new Item("Silla", 3.5F, true));
        recursosHumanos.addItem(new Item("PC", 3.5F, true));
        recursosHumanos.addItem(new Item("Impresora", 3.7F, true));
        recursosHumanos.addItem(new Item("Reclamaciones", 1.3F, true));
        recursosHumanos.addItem(new Item("Ficheros", 35.4F, false));

        despachoDelDirector = new Room("en el despacho del director", "guardian despacho del director", null,false);
        despachoDelDirector.addItem(new Item("telefono", 1.2F, true));
        despachoDelDirector.addItem(new Item("Plasma", 4.3F, true));
        despachoDelDirector.addItem(new Item("Puros", 0.2F, true));
        despachoDelDirector.addItem(new Item("Diploma", 2.5F, true));
        despachoDelDirector.addItem(new Item("Silla", 3.5F, true));
        despachoDelDirector.addItem(new Item("Foto familiar", 0.5F, true));
        despachoDelDirector.addItem(new Item("Portatil", 2.5F, true));
        despachoDelDirector.addItem(new Item("Sofá", 50.3F, false));

        salaDeProyecciones = new Room ("en la sala de proyecciones", "guardian sala de proyecciones", null,true );
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("proyector", 4.1F, true));
        salaDeProyecciones.addItem(new Item("Pantalla para proyector", 8.2F,false));

        entrada.setExit("north", recepcion);

        recepcion.setExit("east", salaDeReuniones);
        recepcion.setExit("south", entrada);
        recepcion.setExit("west", recursosHumanos);

        salaDeReuniones.setExit("north", servicios);
        salaDeReuniones.setExit("west", recepcion);
        salaDeReuniones.setExit("northWest", salaDeProyecciones);

        servicios.setExit("south", salaDeReuniones);

        recursosHumanos.setExit("north", despachoDelDirector);
        recursosHumanos.setExit("east", recepcion);

        despachoDelDirector.setExit("east", salaDeProyecciones);
        despachoDelDirector.setExit("south", recursosHumanos);

        salaDeProyecciones.setExit("southEast", salaDeReuniones);
        salaDeProyecciones.setExit("west", despachoDelDirector);

        player.setCurrentRoom(entrada);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public  void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println();
        System.out.println("Gracias por jugar. Hasta la vista.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido a The Office!");
        System.out.println("Escribe '" + Option.AYUDA.getCommand() +"' para ver la ayuda");
        System.out.println();        
        player.look();
        player.showCurrentInventory();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        Option commandWord = command.getCommandWord();
        
        switch(commandWord){
            case AYUDA:
            System.out.println();
            printHelp();
            break;
            
            case IR:
            goRoom(command);
            give( command);
            player.look();
            
            
            System.out.println();
          
            break;
            
            case TERMINAR:
            wantToQuit = quit(command);
            break;
            
            case EXAMINAR :
              System.out.println();
            player.look();
            break;
            
             case COMER:
            System.out.println();
            player.eat();
            break;
            
             case VOLVER:
             player.back();
              player.look();
            System.out.println();
            
            break;
            
             case OBJETOS:
            System.out.println();
            player.showCurrentInventory();
            break;
            
             case PESO:
            System.out.println();
            player.showCarryWeight();
            break;
            
            case COGER:
             System.out.println();
             take(command);
            break;
            
            case SOLTAR :
             System.out.println();
            drop(command);
            break;
            
            
            
            case DESCONOCIDO:
            System.out.println("No entiendo las instrucciones");
        }
       
       
       
        return wantToQuit;
    }

    private void give(Command command)
    {
       
        Item guardianObjeto=player.getCurrentRoom().itemPortero();
        boolean mark=true; 
          
        if(player.guardianEnHab()== true )
        {
            System.out.println("El guardian te pide mo objeto"+ 
                                "\nsi no lo has recogido e intentas avanzar  volveras a la habitacion anterior");
            int i = 0;
            boolean find=false;
            while (i < player.getNumberOfInventoryItems() && !find)
                {
                    if (player.getItem(i) ==  guardianObjeto)
                    {
                        find = true;
                        mark = false;
                        System.out.println(" ");
                        System.out.println(" Increible Tienes ese objeto,damelo y podras continuar");
                       
                        player.getCurrentRoom().newObjArrayGuard(player.getItem(i));
                        player.getCurrentRoom().addItem(player.drop(player.getItem(i)));
                        player.getCurrentRoom().removeItem(guardianObjeto.getID());
                        
                       player.getCurrentRoom().eliminarGuardian();
                       goRoom(command);
                    }
                    i++;
                    }
        }
         else if(player.guardianEnHab()== false)
        {
              
               System.out.println("No hay guardian es esta sala");
               
                                
               
        }
        
        
        if( mark == true && player.guardianEnHab()== true )
        {
            System.out.println(" No Tienes el objeto requerido ,encuentralo o no podras continuar");
            
            System.out.println("");
            
            
            System.out.println("");
            System.out.println(" El guardian no te permite avanzar de habitacion " );
            System.out.println("busca el objeto o no podras continuar");
            
            player.back();
        
        }
        
       
       
       
            
        
        
    }
    
    private void drop(Command command)
    {
        if(command.hasSecondWord()) 
            {
                int i = 0;
                boolean match = false;     
                while (i < player.getNumberOfInventoryItems() && !match)
                {
                    if (player.getItem(i).getID() == Integer.parseInt(command.getSecondWord()))
                    {
                        match = true;
                        player.getCurrentRoom().addItem(player.drop(player.getItem(i)));
                    }
                    i++;
                }
                if (!match)
                {
                    System.out.println("No tienes ese objeto");
                }
                else
                {
                    player.showCurrentInventory();
                }
            }
            else
            {
                System.out.println("¿Soltar el qué?");
            }          
    }
    
    private void take(Command command)
    {
        if(command.hasSecondWord()) 
            {
                int i = 0;
                boolean match = false;   
                while (i < player.getCurrentRoom().getNumberOfRoomItems() && !match)
                {
                    if (player.getCurrentRoom().getItem(i).getID() == Integer.parseInt(command.getSecondWord()))
                    {
                        match = true;
                        player.take(player.getCurrentRoom().getItem(i));
                    }
                    i++;
                }
                if (!match)
                {
                    System.out.println("Ese objeto no está en la habitación");
                }
                else
                {
                    player.showCurrentInventory();
                }
            }
            else
            {
                System.out.println("¿Coger el qué?");
            }
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Estás perdido, solo");
        System.out.println("en la oficina");
        System.out.println();
        parser.showAllCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir a donde?");
            return;
        }
        
        player.goRoom(command.getSecondWord());
        System.out.println();
        
       }
     

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("¿Salir qué?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
        }
}
