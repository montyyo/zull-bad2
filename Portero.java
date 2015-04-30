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
     */
    public Portero(String nombre,Item objeto)
    {
        this.nombre=nombre;
        this.objeto=objeto;
        item = new ArrayList<>();
     
    }

    public void addObjeto(Item obj)
    {
        item.add(obj);
    }
    
    public void anularGuardian()
    {
        if(item.size() < 0)
        {
            nombre="";
            objeto=null;
        }
    }
    
   
    
    public String getNombre()
    {
        
        return nombre;
    }
    
   public Item objetoRequirido()
   {
    return objeto;
    
   }
    
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
    
    public void datosObjetoRequerido()
    {
        System.out.println( "\n el guardian solicita el " +objeto.itemToString());
    }
    
    public String toString()
    {
      
       
        return getNombre()+ " solicita el objeto"  + objeto.getDescription() + "  ID "+ objeto.getID();
    }
}
