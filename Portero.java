import java.util.ArrayList;
/**
 * Write a description of class Portero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portero
{
    
    private String nombre;
    private ArrayList<Item> item;
    private Item objeto;
    private Player jugador;
    /**
     * Constructor for objects of class Portero 
     * @param nombre , objeto deseado por el portero y array para implementaciones 
     */
    public Portero(String nombre,Item objeto)
    {
        this.nombre=nombre;
        this.objeto=objeto;
        item = new ArrayList<>();
     
    }

    /**
     * añade un objeto al array del guardia
     * @param objeto a añadir
     */
    public void addObjeto(Item obj)
    {
        item.add(obj);
    }
    
    /**
     * hace desaparecer al guardian anulando sus atributos
     */
    public void anularGuardian()
    {
        if(item.size() < 0)
        {
            nombre="";
            objeto=null;
        }
    }
    
   
    /**
     * @return nombre del guardia
     */
    public String getNombre()
    {
        
        return nombre;
    }
    
    
    
    /**
     * @return item requerido por el guardia
     */
   public Item objetoRequirido()
   {
    return objeto;
    
   }
    
   
   
    /**
     * sabemos la cantida de objetos que tiene el guardia por si realiamos impolementaciones usando su array
     */
    public void getNumberOfObjets()
    {
        if(item.isEmpty() == true)
        {
            System.out.println("el portero no tiene objetos");
        }
        else
        {
            System.out.println("el portero  tiene " + item.size() + " objetos");
        }
    }
    
    /**
     * nos muestra los datos del objeto que requiere le guardia
     */
    public void datosObjetoRequerido()
    {
        System.out.println( "\n el guardian solicita el " +objeto.itemToString());
    }
    
    /**
     * @return datos del guardia
     */
    public String toString()
    {
      
       
        return getNombre()+ " solicita el objeto"  + objeto.getDescription() + "  ID "+ objeto.getID();
    }
}
