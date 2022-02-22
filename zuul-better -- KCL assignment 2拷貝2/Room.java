import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "A stereotypical maze adventure" application. 
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * Each room contains its own stationary items, and characters that will 
 * move to different rooms automatically.
 * 
 * @author  Michael Kölling,David J. Barnes, and Chin Wan k21016106
 * @version 2021.12.03
 */

public class Room 
{
    private String description;                 // description of the room
    private HashMap<String, Room> exits;        // stores exits of this room
    private HashMap<String, Item> RoomItems;    // stores items of this room
    private HashMap<String, Character> ListofCharacters; //stores characters of different rooms
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        RoomItems = new HashMap<>();
        ListofCharacters = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Set the items in the room in the HashMap,
     * if the type is Weapon, store it as a weapon
     * instead of an item
     * @param name The item or weapon's name
     * @param description The item or weapon's description
     * @param weight The item or weapon's weight
     * @param type The item or weapon's type
     * @param increasedHP The amount of HP that the item or weapon can increase for the player
     * @param damageCaused The amount of damage that the item or weapon cause during a fight
     */
    public void setRoomItems(String name, String description, 
    int weight, String type, int increasedHP, int damageCaused)
    {
        if (type.equals("weapon")){
            Weapon weapon = new Weapon(name, description, weight, type, increasedHP,
            damageCaused);
            RoomItems.put(name, weapon);
        }
        else {
            Item item = new Item(name, description, weight, type, increasedHP,
            damageCaused);
            RoomItems.put(name, item);
        }
    }
    
    /**
     * Remove the item from the room due to the player picked it up
     * @param name The name of the item that is removed
     * @return The item that is deleted, or null if item is not found
     */
    public Item deleteRoomItems(String name)
    {
        List<String> alltheItems = RoomItems.keySet()
                                           .stream()
                                           .collect(Collectors.toList());
        for (String item : alltheItems){
            if (item.equals(name)){
                Item itemtodelete = RoomItems.get(name);
                RoomItems.remove(name);
                return itemtodelete;
            }
        }
        return null;
    }
    
    /**
     * Get a specific item from a specific room
     * @param itemName The name of the item to get
     * @return Return the item desired
     */
    public Item getRoomItem(String itemName)
    {
        return RoomItems.get(itemName);
    }
    
    /**
     * Print the description of all the items in the specific room
     */
    public void ItemDescription()
    {
        if (RoomItems.values() != null) {
            for (Item item: RoomItems.values())
            {
                System.out.println("Items in this room are: " + item.getName() + ", "+ item.getDescription());
            }   
        }
        else {
            System.out.println("There is no item in this room.");
        }
    }
    // Setting the characters in the room
    
    /**
     * Create the character that have specific properties, with the room 
     * they are in, if the character is the boss, store it as the boss type
     * @param name The name of the character
     * @param HP The HP that the character has
     * @param Damage The damage that the character cause during a fight
     * @param currentRoom The room that the character is in
     */
    public void setNPCs(String name, int HP, int Damage, Room currentRoom)
    {
        if (name.equals("Boss")){
            Boss boss = new Boss(name, HP, Damage, currentRoom);
        }
        else {
            Character NPC = new Character(name, HP, Damage, currentRoom);
            ListofCharacters.put(name, NPC);
        }
    }
    
    /**
     * Print the description of the characters in a specific room
     * @param room The room that has the characters 
     * @param ListOfCharacters A HashMap with the list of characters 
     */
    public void NPCDescription(Room room, HashMap<String, Character> ListOfCharacters)
    {
        //Check if the character exist in the HashMap
        if (ListOfCharacters.values() != null) {
            for (Character character: ListOfCharacters.values()){
                // Check if character is in the room
                if (character.returnCurrentRoom() == room){
                    System.out.println("Characters in this room are: " + character.returnName());
                }
            }
        }
    }
}

