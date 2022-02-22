import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Class GamePlayer - The player of the game
 *
 * This class is part of the "A stereotypical maze adventure" application. 
 * 
 * The player is who plays the game, the player has its own properties, 
 * has its own inventory for storing items, and 
 * the player is able to walk around different rooms by 
 * inputing commands, pick up and drop items
 * desired, consume items to increase HP,
 * and equip weapon to fight different characters, including the final boss.
 * 
 * @author Chin Wan k21016106
 * @version 2021.12.03
 */
public class GamePlayer
{
    private int Weight;     // The weight the player is holding
    private int HP;         // The HP the player has
    private Weapon weapon;  // The weapon the player equips 
    private HashMap<String, Item> PlayerItems;  // The inventory of the player
    private int Weightlimit;    // The weightlimit of the weight the player can hold
    /**
     * Create the player with the player's properties and weight limit. 
     * At the beginning there is no weapon 
     * equipped and the inventory is empty.
     */
    public GamePlayer()
    {
        Weight = 0;
        Weightlimit = 1000;
        HP = 3000;
        PlayerItems = new HashMap<>();
        weapon = null;
    }
    /**
     * @return Return the HP the player has
     */
    public int returnHP()
    {
        return HP;
    }
    
    /** 
     * Increase the HP by the value
     * @param value The value increased on player's HP
     */
    public void increaseHP(int value)
    {
        HP += value;
    }
    
    /**
     * @return Return the current weight the player is holding
     */
    public int returnWeight()
    {
        return Weight;
    }
    
    /**
     * @return Return the weight limit the player can hold
     */
    public int returnWeightLimit()
    {
        return Weightlimit;
    }
    
    /**
     * Increase the weight that the player is holding 
     * by the item's weight
     * @param item The item that add its weight on the player
     */
    public void increaseWeight(Item item)
    {
        Weight += item.getWeights();
    }
    
    /**
     * Decrease the weight that the player is holding 
     * by the item's weight
     * @param item The item that is removed to decrease its weight from the player
     */
    public void decreaseWeight(Item item)
    {
        Weight -= item.getWeights();
    }
    
    /**
     * Pick up the item from the current room the player is in,
     * or from the opponent the player defeated, or from the riddler. Add the item picked up
     * to the inventory.
     * @param name Name of the item picked up
     * @param description Description of the item picked up
     * @param weight Weight of the item picked up
     * @param type Type of the item picked up
     * @param increasedHP The amount of HP that the item picked up can increase for the player
     * @param damageCaused The damage caused by the item picked up use during a fight
     */
    public void pickupPlayerItem(String name, String description, 
    int weight, String type, int increasedHP, int damageCaused)
    {
        if (type.equals("weapon")) {
            // Store as Weapon type is the item is a weapon
            Weapon weapon = new Weapon (name, description, weight, type, increasedHP, damageCaused);
            PlayerItems.put(name, weapon);
        }
        else {
            // Store as an item 
            Item item = new Item(name, description, weight, type, increasedHP, damageCaused);
            PlayerItems.put(name, item);
        }
    }
    
    /**
     * Remove the item desired from the inventory
     * @param name The name of the item desired to be removed
     * @return Return the item that is removed, or null if not found
     */
    public Item dropPlayerItem(String name)
    {
        List<String> alltheItems = hashmapToArraylist();
        
        for (String item : alltheItems){
            if (item.equals(name)){
                Item itemtodelete = PlayerItems.get(name);
                PlayerItems.remove(name);
                return itemtodelete;
            }
        }
        return null;
    }
    
    /**
     * Get the information of a specific item desired from the inventory,
     * prints out all the necessary informations. 
     * @param itemName The name of the item desired for checking its information
     */
    public void getItemInfo(String itemName)
    {
        Item itemDesired = PlayerItems.get(itemName);
        
        if (itemDesired != null){
            System.out.println( "Description: " + itemDesired.getDescription());
            System.out.println( "Item types: " + itemDesired.getTypes());
            System.out.println( "Weight: " + itemDesired.getWeights());
            // prints useful information if the item is a weapon
            if (itemDesired.getTypes().equals("weapon")) {
                System.out.println("Damage per attack: " + itemDesired.getDamageCaused());
            }
            // prints the HP that can be increased if the item has the property
            else if (itemDesired.getTypes().equals("IncreaseHP")) {
                System.out.println("HP that can be increased: " + itemDesired.getDamageCaused());
            }
        }
    }
    
    /**
     * Get the itemDesired by the player and return it.
     * @param itemName The name of the item desired
     * @return Return the itemDesired, or null if not found in the inventory
     */
    public Item getSpecificItem(String itemName)
    {
        Item itemDesired = PlayerItems.get(itemName);
        
        if (itemDesired != null){
            return itemDesired;
        }
        return null;
    }
    
    /**
     * @return Return the list of all the items in the player's inventory
     */
    public String getItemList()
    {
        String itemList = "Your current Item list: ";
        
        List<String> alltheItems = hashmapToArraylist();
        for (String item : alltheItems){
             itemList = itemList + " " + item;
        }
        return itemList;
    }
    
    /**
     * Consume the desired item from the inventory, increase the HP 
     * of the player by the item's property "increasedHP",
     * if the item is not found then print an error message
     * @param itemName The item desired to consume
     */
    public void consume(String itemName)
    {
        Item itemDesired = PlayerItems.get(itemName);
        if (!PlayerItems.get(itemName).getTypes().equals("IncreaseHP")) {
            System.out.println("Well you can't eat this...");
        }
        int HPIncrease = itemDesired.getIncreasedHP();
        
        // Increase the HP of the player
        HP = HP + HPIncrease;
        System.out.println("HP: " + HP);
    }
    
    /**
     * @return Return the keys of the HashMap of the inventory to ArrayList
     */ 
    public List<String> hashmapToArraylist()
    {
        List<String> alltheItems = PlayerItems.keySet()
                                           .stream()
                                           .collect(Collectors.toList());
        return alltheItems;
    }
    
    // Methods that are used in a fight
    
    /**
     * @return Return the weapon currently equipped for the player
     */
    public Item returnWeaponEquipped()
    {
        return weapon;
    }
    
    /**
     * @return Return the damage caused by the weapon equipped for each attack
     */
    public int returnWeaponPower()
    {
        return weapon.getDamageCaused();
    }
    
    /**
     * Take damage during a fight, HP decreases
     * @param damage The damage taken by the player
     */
    public void takeDamage(int damage)
    {
        HP -= damage;
    }
    
    /**
     * Equip the weapon desired from the player's inventory, add to the weapon
     * field of the GamePlayer class. If weapon not found, return null
     * @param itemName The name of the weapon desired to equip
     * @return Return the weapon equipped, or null if the weapon desired is not found 
     */
    public Item equipWeapon(String itemName)
    {
        Item itemDesired = PlayerItems.get(itemName);
        if (PlayerItems.get(itemName).getTypes().equals("weapon")) {
            Weapon newWeapon = (Weapon) itemDesired;
            weapon = newWeapon;
            return newWeapon;
        }
        return null;
    }
    
    /**
     * Check whether there is an item "DestroytheDeathNote" in the player's
     * inventory, if yes, reset the damage of the Death Note from the boss to 0.
     * If not, the value of the damage the Death Note causes remains
     * @param boss The boss that holds the Death Note
     */
    public void checkSpell(Boss boss)
    {
        List<String> alltheItems = hashmapToArraylist();
        boolean found = false;
        for (String item : alltheItems){
            if (item.equals("DestroytheDeathNote")){
                System.out.println("Spell has been found, final weapon has been disabled");
                boss.resetBossDamage();
                found = true;
            }
        }
        if (found = false){
            System.out.println("No spell has been found. Final boss will launch the attack");
        }
    }
} 
