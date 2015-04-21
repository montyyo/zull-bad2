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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player("Josu", 50.3F);
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, recepcion, salaDeReuniones, servicios, recursosHumanos, despachoDelDirector, salaDeProyecciones;

        entrada = new Room("en la entrada del edificio");
        entrada.addItem(new Item("Jarr�n", 2.5F, true));
        entrada.addItem(new Item("Jarr�n", 2.5F, true));
        entrada.addItem(new Item("Sof�", 50.3F, false));

        recepcion = new Room("en recepci�n");
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("telefono", 1.2F, true));
        recepcion.addItem(new Item("PC", 6.3F, true));
        recepcion.addItem(new Item("Impresora", 3.7F, true));
        recepcion.addItem(new Item("escritorio", 30F, false));

        salaDeReuniones = new Room("en la sala de reuniones");
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Mesa", 30.3F, false));
        salaDeReuniones.addItem(new Item("Microfono", 0.2F, true));

        servicios = new Room("en los servicios");
        servicios.addItem(new Item("Escobilla", 0.7F, true));
        servicios.addItem(new Item("Papel higienico", 0.2F, true));
        servicios.addItem(new Item("Ventana", 10.2F, false));
        servicios.addItem(new Item("Inhodoro", 25.6F, false));
        servicios.addItem(new Item("Lavabo", 10.6F, false));
        servicios.addItem(new Item("Espejo", 7.6F, true));

        recursosHumanos = new Room("en recursos humanos");
        recursosHumanos.addItem(new Item("Silla", 3.5F, true));
        recursosHumanos.addItem(new Item("PC", 3.5F, true));
        recursosHumanos.addItem(new Item("Impresora", 3.7F, true));
        recursosHumanos.addItem(new Item("Reclamaciones", 1.3F, true));
        recursosHumanos.addItem(new Item("Ficheros", 35.4F, false));

        despachoDelDirector = new Room("en el despacho del director");
        despachoDelDirector.addItem(new Item("telefono", 1.2F, true));
        despachoDelDirector.addItem(new Item("Plasma", 4.3F, true));
        despachoDelDirector.addItem(new Item("Puros", 0.2F, true));
        despachoDelDirector.addItem(new Item("Diploma", 2.5F, true));
        despachoDelDirector.addItem(new Item("Silla", 3.5F, true));
        despachoDelDirector.addItem(new Item("Foto familiar", 0.5F, true));
        despachoDelDirector.addItem(new Item("Portatil", 2.5F, true));
        despachoDelDirector.addItem(new Item("Sof�", 50.3F, false));

        salaDeProyecciones = new Room ("en la sala de proyecciones");
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("proyector", 4.1F, true));
        salaDeProyecciones.addItem(new Item("Pantalla para proyector", 8.2F, false));

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
    public void play() 
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
        System.out.println("Escribe 'help' si necesitas ayuda.");
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
        if(command.isUnknown()) {
            System.out.println("No se a que te refieres...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            System.out.println();
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);           
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            System.out.println();
            player.look();
        }
        else if (commandWord.equals("eat")) {
            System.out.println();
            player.eat();
        }
        else if (commandWord.equals("back"))
        {
            player.back();
            System.out.println();
            player.look();
        }
        else if(commandWord.equals("inventory"))
        {
            System.out.println();
            player.showCurrentInventory();
        }
        else if(commandWord.equals("weight"))
        {
            System.out.println();
            player.showCarryWeight();
        }
        else if (commandWord.equals("take"))
        {
            System.out.println();
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
                    System.out.println("Ese objeto no est� en la habitaci�n");
                }
                else
                {
                    player.showCurrentInventory();
                }
            }
            else
            {
                System.out.println("�Coger el qu�?");
            }
        }
        else if (commandWord.equals("drop"))
        {
            System.out.println();
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
                System.out.println("�Soltar el qu�?");
            }
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Est�s perdido, solo");
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
        player.look();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("�Salir qu�?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
