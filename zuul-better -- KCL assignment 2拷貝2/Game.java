import java.util.ArrayList;
import java.util.Stack;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

/**
 *  This class is the main class of the "A stereotypical maze adventure" application. 
 *   
 *  Users are trapped in a castle, the only way to get out is to defeat the boss in the 
 *  boss room! Get your weapons and gather items to defeat creatures and dragons on the 
 *  way!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling, David J. Barnes, and Chin Wan k21016106
 * @version 2021.12.03
 */

public class Game 
{
    private Parser parser;      // Parser for reading commands 
    private Room currentRoom;   // The current room the player is in
    private Room bossRoom;      // The room that the boss is in
    private Room magicRoom;     // The magic room
    private Room transportRoom; // The room that send player to a random room
    private Room riddlerRoom;   // The room that the riddler is in
    private ArrayList<Room> Rooms;   // The arraylist of the rooms 
    private Stack<Room> stack;  // The stack of the rooms
    private GamePlayer player;  // The game player     
    private Riddler riddler;    // The riddler
    private Boss boss;          // The final boss of the game
    private HashMap<String, Character> listOfCharacters; // A HashMap of characters
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        stack = new Stack<Room>();
        player = new GamePlayer();
        riddler = new Riddler();
        Rooms = new ArrayList<>();
        riddler = new Riddler();
        listOfCharacters = new HashMap<>();
        createRooms();
    }
    
    /**
     * Create all the rooms and link their exits together.
     * 
     */
    private void createRooms()
    {
        Room lobby, magicroom, masterbedroom, gallery, torturechamber;
        Room warehouse, transportroom, laboratory, riddlerroom;
        
        // create the rooms
        lobby = new Room ("in the lobby");
        masterbedroom = new Room ("in the master bedroom");
        magicroom = new Room ("in the room full of magic!");
        gallery = new Room ("in the gallery");
        torturechamber = new Room ("in the torture chamber");
        warehouse = new Room ("in the warehouse");
        laboratory = new Room ("in the laboratory");
        riddlerroom = new Room ("in the riddlerroom now!");
        transportroom = new Room ("in the transport room! Now you will be send to a random room!");
        bossRoom = new Room ("Welcome to the boss room! Here marks your grave!");
        Rooms = new ArrayList<Room>(Arrays.asList(lobby, masterbedroom, magicroom, gallery, torturechamber,
        warehouse, laboratory, riddlerroom, transportroom));
        
        // initialise the items
        masterbedroom.setRoomItems("Goldfruit", "A fruit for increase significant HP", 100, 
        "IncreaseHP", 1500, 0);
        masterbedroom.setRoomItems("Bed","Just a bed.", 20000, "object", 0, 0);
        
        magicroom.setRoomItems("Magichat", "A magic hat that helps increase HP",
        100, "IncreaseHP", 100, 0);
        
        gallery.setRoomItems("Painting", "Famous 18th century painting", 500, 
        "object", 0, 0);
        gallery.setRoomItems("Beef", "Just a beef", 100, "IncreaseHP", 200, 0);
        
        torturechamber.setRoomItems("Tortureequipment", "For getting information from prisoners",
        2000, "object", 0, 0);
        torturechamber.setRoomItems("ArthurSword", "A famous sword that causes significant damages to the opponent",
        1000, "weapon", 0, 1300);
        
        warehouse.setRoomItems("Banana", "Just a banana.", 100, "IncreaseHP", 
        200, 0);
        warehouse.setRoomItems("Apple", "Just an apple.", 50, "IncreaseHP",
        100, 0);
        warehouse.setRoomItems("Bread", "Just a bread.", 100, "IncreaseHP",
        200, 0);
        warehouse.setRoomItems("Chainsaw", "infamous chainsaw from Jason.", 1000,
        "weapon", 0, 800);
        
        laboratory.setRoomItems("Smallknife", "Just a small knife.", 100,
        "weapon", 0, 600);
        laboratory.setRoomItems("Dihydrogenmonoxide", "A very mysterious liquid.", 250,
        "object", 0, 0);
        laboratory.setRoomItems("Medicine", "Just a medicine", 100, 
        "increaseHP", 1000, 0);
        
         // initialise room exits
        lobby.setExit("east", magicroom);
        lobby.setExit("west", masterbedroom);
        lobby.setExit("south", gallery);
        
        masterbedroom.setExit("east", lobby);
        
        magicroom.setExit("west", lobby);
        magicroom.setExit("south", torturechamber);
        
        gallery.setExit("north", lobby);
        gallery.setExit("east", torturechamber);
        gallery.setExit("south", laboratory);
        
        torturechamber.setExit("north", magicroom);
        torturechamber.setExit("west", gallery);
        torturechamber.setExit("south", warehouse);
        
        warehouse.setExit("north", torturechamber);
        warehouse.setExit("west", laboratory);
        warehouse.setExit("south", transportroom);
        
        laboratory.setExit("north", gallery);
        laboratory.setExit("east", warehouse);
        laboratory.setExit("west", riddlerroom);
        
        riddlerroom.setExit("east", laboratory);

        
        Character Monster1, Monster2, Monster3, Monster4, Monster5;
        Character Monster6, Monster7, Monster8, Riddler;
        
        // Create the characters
        Monster1 = new Character("Goblin", 200, 300, magicroom);
        Monster2 = new Character("Goblin2", 300, 400, magicroom);
        Monster3 = new Character("Goblin3", 100, 200, torturechamber);
        Monster4 = new Character("Gobiln4", 500, 300, warehouse);
        Monster5 = new Character("RedDragon", 6000, 600, laboratory);
        Monster6 = new Character("Kobold", 150, 20, masterbedroom);
        Monster7 = new Character("ChaosDragon", 50000, 200, warehouse);
        Monster8 = new Character("GoldDragon", 6500, 600, laboratory);
        boss = new Boss("Boss", 10000, 800, bossRoom);
        
        listOfCharacters.put("Goblin", Monster1);
        listOfCharacters.put("Goblin2", Monster2);
        listOfCharacters.put("Goblin3", Monster3);
        listOfCharacters.put("Goblin4", Monster4);
        listOfCharacters.put("RedDragon", Monster5);
        listOfCharacters.put("Kobold", Monster6);
        listOfCharacters.put("ChaosDragon", Monster7);
        listOfCharacters.put("GoldDragon", Monster8);
        listOfCharacters.put("Boss", boss);
        
        // Set characters' items
        Monster1.setCharacterItems("Chicken", "Just a chicken", 100, "IncreaseHP",
        600, 0);
        Monster2.setCharacterItems("NecroSword", "Sword that destroys everything it faces",
        1500, "weapon", 0, 300);
        Monster3.setCharacterItems("Pork", "Delicious cooked pork", 200, 
        "IncreaseHP", 150, 0);
        Monster4.setCharacterItems("Worm", "A disgusting worm", 50, 
        "IncreaseHP", 500, 0);
        Monster5.setCharacterItems("Submachine gun", "Just a normal machine gun.",
        500, "weapon", 0, 200);
        Monster6.setCharacterItems("Useless rock", "A rock that is useless.",
        120, "IncreaseHP", 0, 0);
        Monster7.setCharacterItems("gamma-ray bursts", "Just a small explosion", 10,
        "weapon", 0, 100000000);
        Monster8.setCharacterItems("Gundam!", "The Gundam!", 400, "weapon", 0, 1500);

        
        currentRoom = lobby;  // start game outside
        transportRoom = transportroom; 
        riddlerRoom = riddlerroom;
        magicRoom = magicroom;
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
        while (! finished && player.returnHP() >0 && boss.returnHP()>0) {
            // Print characters of the room the player is in
            if (currentRoom != bossRoom){
            currentRoom.NPCDescription(currentRoom, listOfCharacters);
        }
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("You have been captured to this castle!");
        System.out.println("This castle has monsters and a final boss to beat");
        System.out.println("Your goal is to defeat all the monsters");
        System.out.print("before you are able to fight the final boss");
        System.out.println("There are items on the ground where you can pick up,");
        System.out.print("and weapons/foods to get when defeating a monster");
        System.out.println("Type help if you need help, it consists of all the commands you need");
        System.out.println("When using 3-word commands, please type properties first, then item/NPC name");
        System.out.println("The item properties are: " + "name, description, type, weight, increasehp, damagecaused");
        System.out.println(currentRoom.getLongDescription());
        // Print the player's empty inventory
        System.out.println(player.getItemList());
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
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {
            previousRoom();
        }
        else if (commandWord.equals("drop")) {
            droptheItem(command);
        }
        else if (commandWord.equals("pick")) {
            pickupItem(command);
        }
        else if (commandWord.equals("check")) {
            // Can either check all properties of an item, or a particular property
            if (!command.hasThirdWord()){
                checkdesireItem(command);
            }
            else {
                checkItemPropertiesinfo(command);
            }
        }
        else if (commandWord.equals("fight")) {
            fight(command);
        }
        else if (commandWord.equals("consume")) {
            consume(command);
        }
        else if (commandWord.equals("equip")){
            equipTheWeapon(command);
        }
        else if (commandWord.equals("talk")){
            theRiddler(command);
        }
        else if (commandWord.equals("yes")) {
            yesGoFightBoss();
        }
        else if (commandWord.equals("no")) {
            // Send to a random room after rejecting going to the boss room
            System.out.println("Then you will be transport to a random room.");
            currentRoom = transportRoom;
            TransporterRoom();
        }
        else if (commandWord.equals("answer")) {
            riddler.checkAnswer(command.getSecondWord(), player);
            System.out.println(player.getItemList());
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print how to use important commands, 
     * and the list of command words to use
     */
    private void printHelp() 
    {
        System.out.println("It's okay, don't cry, ");
        System.out.println("let me help you find your command words");
        System.out.println();
        System.out.println("Your command words are: ");
        parser.showCommands();
        System.out.println("Item properties are: "+"name, description, type, weight, increasehp, damagecaused");
        System.out.println("If want to talk to the riddler, please use [talk riddler] command");
        System.out.println("If want to fight the boss, please go to bossroom using command [go bossRoom]"+
        "then fight the boss using command [fight Boss]");
        System.out.println("If want to leave the fight, please use the command [run]");
    }

    /** 
     * Inputing one direction If there is an exit, enter the new
     * room and print description of the room and anything in it, 
     * otherwise print an error message
     * If next room has special function, it will be implemented
     * @param command the direction command to be read to know which room to go next
     */
    private void goRoom(Command command) 
    {
        if (command.getSecondWord().equals("bossRoom")){
            System.out.println("Are you ready to go to the bossroom?");
            System.out.println("Also boss has a final weapon that you should worry about...");
        }
        else {
            if(!command.hasSecondWord()) {
                // We need second word to know where to go
                System.out.println("Go where?");
                return;
            }
            String direction = command.getSecondWord();
    
            // Try to leave current room
            Room nextRoom = currentRoom.getExit(direction);
    
            if (nextRoom == null) {
                System.out.println("There is no door!");
            }
            
            else {
                stack.add(currentRoom);
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                System.out.println(player.getItemList());
                System.out.println("Your current HP: "+player.returnHP());
                System.out.println("Your current Weight holding: " +player.returnWeight());
                currentRoom.ItemDescription();
                magicRoom();
                if (currentRoom == riddlerRoom) {
                    System.out.println ("The riddler is here too!");
                }
            }
            TransporterRoom();
            NPCmovement();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Enter the previous room the player was in
     */
    private void previousRoom()
    {
       if (stack.isEmpty() == true) {
            System.out.println("You cannot go back further!");
       }
       else{
            // get the room at the top of the stack then delete the room
            currentRoom = stack.pop();
            System.out.println(currentRoom.getLongDescription());
       }
       NPCmovement();
    }
    
    /**
     * Drop the item the player desire to drop, 
     * return to the room the player is in. If item not found, 
     * print an error message
     * @param command the item that the player wants to drop
     */
    private void droptheItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // We need the second word to know which item to drop
            System.out.println("Drop which item?");
            return;
        }
        String ItemtoDrop = command.getSecondWord();
        
        // Drop the item
        Item tItem = player.dropPlayerItem(ItemtoDrop);  
        if (player.returnWeight() == 0) {
            System.out.println("Cannot drop! As you don't have any items");
            return;
        }
        if (tItem != null){
            currentRoom.setRoomItems(tItem.getName(), tItem.getDescription(),
            tItem.getWeights(), tItem.getTypes(), tItem.getIncreasedHP(),
            tItem.getDamageCaused());
            // decrease total weight of the player
            player.decreaseWeight(tItem);  
            System.out.println(player.getItemList());
        }
        else{
            System.out.println("Item cannot be found from player's itemlist!");
        }
    }
    
    /**
     * Pick up the item the player desire from the room 
     * the player is in, add to player's inventory. If item not
     * found, print an error message
     * @param command the item that the player wants to pick up
     */
    private void pickupItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // We need a second word to know which item to pick up
            System.out.println("Pick up which item?");
            return;
        }
        String ItemtoPick = command.getSecondWord();
        
        // Get the item from the room
        Item tItem = currentRoom.getRoomItem(ItemtoPick);
        if (tItem != null){
            // Set the unmovable objects
            if (tItem.getTypes().equals("object")){
                System.out.println("Item cannot be taken!");
            }
            else{
                player.increaseWeight(tItem);
                if (player.returnWeight()>player.returnWeightLimit()){
                    System.out.println("Over the weight limit!");
                    // Decrease the weight that was just added
                    player.decreaseWeight(tItem);
                    System.out.println(player.getItemList());
                }
                else{
                    currentRoom.deleteRoomItems(ItemtoPick);
                    player.pickupPlayerItem(tItem.getName(), tItem.getDescription(),
                    tItem.getWeights(), tItem.getTypes(), tItem.getIncreasedHP(),
                    tItem.getDamageCaused());
                    System.out.println(player.getItemList());
                }
            }
        }
        else{
            System.out.println("Item cannot be found!");
        }
    }
    
    /**
     * Check all the properties of the desire item, 
     * need to pick up the item first. If not in the inventory,
     * print error messages
     * @param command the item to be checked
     */
    private void checkdesireItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // We need the second word to know which item to check
            System.out.println("which item to check?");
            return;
        }
        String DesireItem = command.getSecondWord();
        
        // Check if the item is in player's inventory
        if (player.getSpecificItem(DesireItem) != null) {
            player.getItemInfo(DesireItem);
        }
        else {
            System.out.println("You do not have this item in your inventory.");
            System.out.println("If wanting to check the item in the room, please pick up then check.");
        }
    }
    
    /** 
     * Check a specific property of the item desired,
     * if the property is not found, print an error message
     * @command the property and the item to be checked
     */
    private void checkItemPropertiesinfo(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Check what?");
            return;
        }
        if (!command.hasThirdWord()) {
            System.out.println("Which item?");
        }
        // Get the specific property
        String itemProperty = command.getSecondWord();
        itemProperty.toLowerCase();
        
        String itemName = command.getThirdWord();
        // Get the item desired
        Item desiredItem = player.getSpecificItem(itemName);
        
        if (desiredItem != null) {
            System.out.println(itemProperty + ": ");
            // Check if the item returns int or String, and whether it returns a value
            if (desiredItem.matchPropertiesInt(itemProperty, desiredItem) != -1){
                System.out.println(desiredItem.matchPropertiesInt(itemProperty, desiredItem));
            }
            if (!desiredItem.matchPropertiesString(itemProperty, desiredItem).equals("")){
                System.out.println(desiredItem.matchPropertiesString(itemProperty, desiredItem));
            }
        }
    }
    
    /**
     * Transport the player to a random room
     */
    private void TransporterRoom()
    {
        if (currentRoom == transportRoom){
            Random rand = new Random();
            int index = rand.nextInt(Rooms.size()-1);
            // Get a random room from the ArrayList
            currentRoom = Rooms.get(index);
            System.out.println(currentRoom.getLongDescription());
        }
        NPCmovement();
    }
    
    /**
     * Fight the character in the room
     * @param command the character to be fought
     */
    private void fight(Command command)
    {
        if(!command.hasSecondWord()) {
            // We need a second word to know who to fight
            System.out.println("Fight who?");
            return;
        }
        new Fight(listOfCharacters, currentRoom, player, boss).fightSomeone(command);
        NPCmovement();
    }
    
    /**
     * To increase HP by consuming the item that can 
     * increase HP. If item cannot be found, print an
     * error message
     * @param command the item to be consumed
     */
    private void consume(Command command)
    {
        if(!command.hasSecondWord()) {
            // We need to know the second word to know which item to consume
            System.out.println("which food to consume?");
            return;
        }
        String desireItem = command.getSecondWord();
        Item item = player.getSpecificItem(desireItem);
        
        // Consume the item
        player.consume(desireItem);
        if (desireItem != null) {
            player.dropPlayerItem(desireItem);
            player.decreaseWeight(item);
            currentRoom.ItemDescription();
        }
        else {
            System.out.println("Cannot consume this item as it is not in your inventory.");
            currentRoom.ItemDescription();
        }
    }
    
    /**
     * Let the player equip the weapon taken from
     * its inventory. If item is not found,
     * print an error message
     * @param command the weapon to be equipped 
     */
    private void equipTheWeapon(Command command)
    {
        if(!command.hasSecondWord()) {
            // We need the second word to know which item to equip
            System.out.println("which item to equip?");
            return;
        } 
        String DesireWeapon = command.getSecondWord();
        
        // Equip the weapon
        Item theWeapon = player.equipWeapon(DesireWeapon);
        if (theWeapon != null){
            System.out.println("The weapon equipped is: " + player.returnWeaponEquipped().getName());
        }
        else {
            System.out.println("We can't find this weapon, or this is not a weapon.");
        }
    }
    
    /**
     * To let the riddler ask a riddle when talk to him
     * if talk to the wrong person or the player is not 
     * in the riddler room, print an error message
     * @param command the person to talk to, which is the riddler
     */
    private void theRiddler(Command command){
        boolean answered = false;
        if (!command.hasSecondWord()) {
            // We need a second word to know who to talk to
            System.out.println("Talk to who?");
            return;
        }
        if (!command.getSecondWord().equals("riddler")){
            System.out.println("You can only talk to the riddler");
        }
        if (currentRoom == riddlerRoom){
            // Riddler ask the question
            riddler.askQuestion();
        }
        else {
            System.out.println("You can only talk to riddler when in the riddler room!");
        }
    }
    
    /**
     * To let the HP increase by 100 every time 
     * the player passes the magic room
     */
    private void magicRoom()
    {
         if (currentRoom == magicRoom){
             player.increaseHP(100);
         }
    }
    
    /**
     * To agree to go fight boss, and print important
     * information about the player and the boss
     */
    private void yesGoFightBoss()
    {
        currentRoom = bossRoom;
        System.out.println(currentRoom.getShortDescription());
        System.out.println(player.getItemList());
        System.out.println("Your current HP: "+player.returnHP());
        System.out.println("Your current Weight holding: " +player.returnWeight());
        System.out.println("Boss's HP: " + boss.returnHP());
        System.out.println("Boss's damage per attack: " + boss.returnDamage());
    }
    
    // Implementation of NPC commands
    
    /**
     * Let all characters to move to a random room 
     */
    private void NPCmovement()
    {
        randomRoomNPC(listOfCharacters.get("Goblin"));
        randomRoomNPC(listOfCharacters.get("Goblin2"));
        randomRoomNPC(listOfCharacters.get("Goblin3"));
        randomRoomNPC(listOfCharacters.get("Goblin4"));
        randomRoomNPC(listOfCharacters.get("RedDragon"));
        randomRoomNPC(listOfCharacters.get("Kobold"));
        randomRoomNPC(listOfCharacters.get("GoldDragon"));
        randomRoomNPC(listOfCharacters.get("ChaosDragon"));
    }
    
    /**
     * To let a single character to move a random room
     * @param NPC the character that is going to move to a random room
     */
    private void randomRoomNPC(Character NPC)
    {
        Random rand = new Random();
        int index = rand.nextInt(Rooms.size()-1);
        //Get a random room from the ArrayList
        Room NPCroom = Rooms.get(index);
        NPC.setCurrentRoom(NPCroom);
    }
}
