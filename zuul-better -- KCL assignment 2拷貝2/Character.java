import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Class Character - A character in the game
 *
 * This class is part of the "A stereotypical maze adventure" application. 
 * 
 * A character has different properties, and it is able to move around different rooms
 * by itself. Each character holds an item which can be given
 * to the player. The character is able to fight against the player by reducing 
 * the player's HP.
 * 
 * @author Chin Wan k21016106
 * @version 2021.12.03
 */
public class Character
{
    private int NPCHP;      // The HP the character has
    private int NPCDamage;  // The damage the character cause per attack
    private String name;    // The name of the character
    private Room currentRoom;   // The current room the character is in
    private HashMap<String, Item> NPCItems; // The list of items for each character
    /**
     * Create a character with its properties and its location
     * @param name The name of the character
     * @param NPCHP The HP the character has
     * @param NPCDamage The damage the character cause per attack
     * @param currentRoom The room the character is currently in
     */
    public Character(String name, int NPCHP, int NPCDamage, Room currentRoom)
    {
        this.NPCHP = NPCHP;
        this.name = name;
        this.NPCDamage = NPCDamage;
        this.currentRoom = currentRoom;
        NPCItems = new HashMap<>();
    }
    
    /**
     * @return Return the HP the character has
     */
    public int returnHP()
    {
        return NPCHP;
    }
    
    /** 
     * @return Return the name of the character
     */
    public String returnName()
    {
        return name;
    }
    
    /**
     * @return Return the damage caused by the character per attack
     */
    public int returnDamage()
    {
        return NPCDamage;
    }
    
    /**
     * @return Return the current room the character is in
     */
    public Room returnCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Set the room desired to the current room the character is in
     * @param room The room to be set to be the current room the character is in
     */
    public void setCurrentRoom(Room room)
    {
        this.currentRoom = room;
    }
    
    /**
     * Create the item for each character with the item's properties, 
     * if the type is equal to weapon, then store the item as type Weapon
     * @param name Name of the item
     * @param description Description of the item
     * @param weight Weight of the item
     * @param type Type of the item
     * @param increasedHP The amount of HP that the item can increase for the player
     * @param damageCaused The damage caused by the item use during a fight
     */
    public void setCharacterItems(String name, String description, 
    int weight, String type, int increasedHP, int damageCaused)
    {
        if (type.equals("weapon")){
            // Store it as a weapon with type Weapon 
            Weapon weapon = new Weapon(name, description, weight, type, increasedHP,
            damageCaused);
            NPCItems.put(name, weapon);
        }
        else {
            // Store it as an item
            Item item = new Item(name, description, weight, type, increasedHP,
            damageCaused);
            NPCItems.put(name, item);
        }
    }

    /**
     * @return Return all the items of a character, return null if there is none
     */
    public Item returnCharacterItems()
    {
        for (Item item: NPCItems.values())
        {
            return item;
        }
        return null;
    }
    
    // Methods that are used in a fight
    
    /** 
     * Take damage from the attack, reducing HP of the character
     * @param Damage Amount of damage taken by the character
     */
    public void takeDamage(int Damage)
    {
        NPCHP -= Damage;
    }
    
    /**
     * Letting the item of a character to be taken, usually happens 
     * after a fight against the player and lost
     * @param characterName The name of the character that has its item taken
     */
    public void itemTaken(Character characterName)
    {
        Item itemtodelete = characterName.returnCharacterItems();
        NPCItems.remove(characterName);
    }
}
