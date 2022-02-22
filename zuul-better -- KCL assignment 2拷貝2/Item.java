import java.util.ArrayList;

/**
 * Class item - An item in the game
 * 
 * This class is part of the "A stereotypical maze adventure" application. 
 * 
 * An item has different properties, and it exists either in specific rooms
 * or it is held by the player or the characters. An item can be checked, picked up
 * , put down, or even used by the player. The item can also be given from the characters
 * to the player. 
 * This is also the superclass of class Weapon.
 * 
 * @author Chin Wan k21016106
 * @version 2021.12.03
 */
public class Item 
{
    private String itemName;      // Name of the item
    private String itemDescription;  // Description of the item
    private int itemWeight;     // Weight of the item
    private String itemType;    // Type of the item
    private int increasedHP;    // The amount of HP that the item can increase for the player
    protected int damageCaused; // The damage caused by the item use during a fight
    /**
     * Create an item with all the properties it has
     * @param itemName Name of the item
     * @param itemDescription Description of the item
     * @param itemWeight Weight of the item
     * @param itemType Type of the item
     * @param increasedHP The amount of HP that the item can increase for the player
     * @param damageCaused The damage caused by the item use during a fight
     */
    public Item(String itemName, String itemDescription, 
    int itemWeight, String itemType, int increasedHP, int damageCaused)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        this.itemType = itemType;
        this.increasedHP = increasedHP;
        this.damageCaused = damageCaused; 
    }
    
    /**
     * @return Return the name of the item
     */
    public String getName()
    {
        return itemName;
    }
    
    /**
     * @return Return the description of the item
     */
    public String getDescription()
    {
        return itemDescription;
    }
    
    /**
     * @return Return the weight of the item
     */
    public int getWeights()
    {
        return itemWeight;
    }
    
    /**
     * @return Return the item type
     */
    public String getTypes()
    {
        return itemType;
    }
    
    /**
     * @return Return the HP increased by the item when used
     */
    public int getIncreasedHP()
    {
        return increasedHP;
    }
    
    /** 
     * @return Return the damage caused by the item use during a fight
     */
    public int getDamageCaused()
    {
        return damageCaused;
    }
    
    /**
     * Reset the damage to 0. Used for the final boss fight
     * to let the damage of the death note caused becomes 0
     */
    public void resetDamage()
    {
        damageCaused = 0;
    }
    
    /**
     * Match the property searched for a specific item 
     * to the properties with type String, if matches, return 
     * the property ; if not, return an empty string
     * @param property The property that is searched by the player
     * @item The item that is searched by the player for a specific property
     * @return Return the property with type String, or empty string if property is not found
     */
   
    public String matchPropertiesString(String property, Item item)
    {
        String propertyString = "";
        if (property.equals("name")){
            propertyString = item.getName();
        }
        else if (property.equals("description")){
            propertyString = item.getDescription();
        }
        else if (property.equals("type")){
            propertyString = item.getTypes();
        }
        return propertyString;
    }
    
    /**
     * Match the property searched for a specific item 
     * to the properties with type int, if matches, return 
     * the property ; if not, return the value -1
     * @param property The property that is searched by the player
     * @item The item that is searched by the player for a specific property
     * @return Return the property with type int, or value -1 if property is not found
     */
    public int matchPropertiesInt(String property, Item item)
    {
        int propertyint = -1;
        if (property.equals("weight")){
            propertyint = item.getWeights();
        }
        else if (property.equals("increasehp")){
            propertyint = item.getIncreasedHP();
        }
        else if (property.equals("damagecaused")){
            propertyint = item.getDamageCaused();
        }
        return propertyint;
    }
}