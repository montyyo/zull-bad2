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
    private boolean exist;
    /**
     * Constructor for objects of class Portero
     */
    public Portero(String nombre,Item objeto,boolean exist)
    {
        this.nombre=nombre;
        this.objeto=objeto;
        item = new ArrayList<>();
        this.exist=exist;
    }

    public String getNombre()
    {
        
        return nombre;
    }
    
    public boolean getExist()
    {
        return exist;
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
        System.out.println( "\n el guardian solicita el " +objeto.itemToString());
    }
    
    public String toString()
    {
        String datos=null;
        if(exist == false)
        {
            datos = "no hay guardian en esta sala";
        }
        else
        {
            datos = getNombre()+ " solicita el objeto"  + objeto.getDescription() + "  ID "+ objeto.getID();
        }
        return datos;
    }
}
