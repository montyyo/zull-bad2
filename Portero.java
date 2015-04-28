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
    /**
     * Constructor for objects of class Portero
     */
    public Portero(String nombre,Item objeto)
    {
        this.nombre=nombre;
        this.objeto=objeto;
        item = new ArrayList<>();
        
    }

    public String getNombre()
    {
        return nombre;
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
    
    public void objetoRequerido()
    {
        System.out.println(objeto.itemToString());
    }
}