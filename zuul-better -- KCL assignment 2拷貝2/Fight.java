import java.util.HashMap;

/**
 * Class Fight - Where the fight interaction is written
 * 
 * This class is part of the "A stereotypical maze adventure" application. 
 * 
 * The fight class is designed to have the methods for the player and 
 * the characters to go against each other, attempting to reduce each other's HP 
 * until one reaches 0. 
 * The fight is turn-based, and the only way to attack is to implement
 * the "Attack" command, hoping the opponent's HP will run out first.
 *
 * @Chin Wan k21016106
 * @2021.12.03
 */
public class Fight
{
    private GamePlayer player;   // The player of the game       
    private HashMap<String, Character> ListOfCharacters;    // List of the characters 
    private Room currentRoom;   // The room the player is currently in
    private Character riddler;  // The riddler as a character
    private Character opponent; // The opponent of the fight
    private Parser parser; // Parser for reading commands
    private Boss boss;  // The boss of the game
    private boolean run;    // Whether the player wants to run
    /**
     * Create the fight and initialise the player and the character to fight against, also the
     * current room the player is in, and whether the player wants to run away
     * @param listOfCharacters The list of characters that may be involved in a fight
     * @param currentRoom The room the player is currently in
     * @param player The player of the game
     * @param boss The boss of the game
     */
    public Fight(HashMap<String, Character> listOfCharacters, Room currentRoom, GamePlayer player, Boss boss)
    {
        parser = new Parser();
        this.ListOfCharacters = listOfCharacters;
        this.currentRoom = currentRoom;
        this.player = player;
        this.boss = boss;
        run = false;
    }
    /**
     * Given a command, process the command that may be used during a fight
     * @param command The command to be processed
     * @return 1 if fight doesn't end, 2, if fight ended
     */
    public int processFightCommand(Command command)
    {
        int endAttack = 1;
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            // Use 0 represents unknown commands;
            return 0;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("Attack")) {
            if (!opponent.returnName().equals("boss")){
                Attack(command);
            }
            else {
                attackBoss(command);
            }
            endAttack = 2;
        }
        else if (commandWord.equals("run")){
            run = true;
        }
        return endAttack;
    }
        
    /**
     * The main method of the fight. A turn-based fight
     * to fight the opponent by utilizing command "Attack", and take
     * damage from the opponent and reduce HP.
     * When the player wins, receive a reward from the opponent
     *
     * @param command The name of the opponent to fight against
     */
    public void fightSomeone(Command command)
    {
        String characterToFight = command.getSecondWord();
        if (characterToFight.equals("Boss")) {
            opponent = ListOfCharacters.get(characterToFight);
            Boss theBoss = (Boss) opponent;
            boss = theBoss;
            // Fight the boss in a different condition
            fightBoss();
        }
        else{
            opponent = ListOfCharacters.get(characterToFight);
            System.out.println("You will be fighting: "+ opponent.returnName());
            System.out.println("Attack the opponent using the fight command [Attack] + [OpponentName]");
            if (opponent == riddler){
                System.out.println("You cannot fight the riddler!");
            }
            else {
                if (opponent.returnCurrentRoom() == currentRoom){
                    if(player.returnWeaponEquipped() != null){
                        // 1 means it's not opponent's turn, 2 means it is
                        int opponentTurn = 1;
                        while (opponent.returnHP()>0 || player.returnHP()>0)
                        {
                            Command followupCommand = parser.getCommand();
                            // Loop the processing of the commands
                            opponentTurn = processFightCommand(followupCommand);
                            if (opponentTurn == 0) {
                                opponentTurn = 1;
                            }
                            else if (run == true){
                                System.out.println("You cannot run away from a fight!");
                                opponentTurn = 2;
                            }
                            else {
                                if (opponent.returnHP()>0){
                                    System.out.println("This attack has caused " + player.returnWeaponPower()+ " on the opponent!");
                                    System.out.println("The HP that opponent still has is: " + opponent.returnHP());
                                    opponentTurn = 2;
                                }
                                else {
                                    System.out.println("This attack has caused " + player.returnWeaponPower());
                                    System.out.println("You have defeated " + characterToFight);
                                    // Get the reward from the opponent
                                    Getreward();
                                    return;
                                }
                                if (opponentTurn == 2) {
                                    // Opponent's turn to attack
                                    TakeDamage();
                                    if (player.returnHP()>0) {
                                        System.out.println("The opponent has attacked!");
                                        System.out.println("The damage you have taken from this attack is: " + opponent.returnDamage());
                                        System.out.println("The HP you have left is:"+player.returnHP());
                                        opponentTurn = 1;
                                    }
                                    else{
                                        System.out.println("You have died");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    else {
                    System.out.println("You have not equipped any weapon!");
                    return;
                    }
                }
                else {
                System.out.println("You are not in the same room as the" + opponent.returnName());
                return;
                }
            }
        }
    }
    
    /**
     * This shows what happens when fighting a boss. Attack boss 
     * with the "Attack" command, but the boss has a special weapon when his
     * HP are below 1000, and require special item "DestroytheDeathNote" 
     * to dodge the attack
     */
    public void fightBoss()
    {
        System.out.println("You will be fighting: the Boss!");
        System.out.println("Attack boss using command [Attack Boss]");
        if (boss.returnCurrentRoom() == currentRoom){
            if(player.returnWeaponEquipped() != null){
                // 1 means it's not boss's turn, 2 means it is
                int bossTurn = 1;
                while (boss.returnHP()>0 || player.returnHP()>0)
                {
                    Command followupCommand = parser.getCommand();
                    bossTurn = processFightCommand(followupCommand);
                    if (bossTurn == 0) {
                            bossTurn = 1;
                    }
                    else if (run == true){
                        System.out.println("You cannot run away from the boss!");
                        bossTurn = 1;
                    }
                    else {
                        if (boss.returnHP()>100) {
                            System.out.println("This attack has caused " + player.returnWeaponPower()+ " on the boss!");
                            System.out.println("Boss still have " + boss.returnHP() +" left");
                            bossTurn = 2; 
                        }
                        if (1000 > boss.returnHP() && boss.returnHP()>0) {
                            System.out.println("This attack has caused " + player.returnWeaponPower()+ " on the boss!");
                            System.out.println("Boss have equipped the final weapon: " + boss.returnFinalWeapon().getName());
                            System.out.println("If you don't have the special spell, the boss will write your name on the book");
                            // Check whether the player has the special item to avoid the final weapon
                            player.checkSpell(boss);
                            bossTurn = 2;
                        }
                        if (boss.returnHP()<0) {
                            System.out.println("This attack has caused " + player.returnWeaponPower()+ " on the boss!");
                            // The only game winning condition
                            System.out.println("You have won the game! Congratulations!");
                            return;
                        }
                        if (bossTurn == 2){
                            if (player.returnHP()>0 && boss.returnHP() >1000) {
                                takeBossDamage();
                                System.out.println("The opponent has attacked!");
                                System.out.println("The damage you have taken from this attack is: " + boss.returnDamage());
                                System.out.println("The HP you have left is:"+ player.returnHP());
                                bossTurn = 1;
                            }
                            else if (player.returnHP()>0 && boss.returnHP()<1000 && boss.returnHP()>0){
                                System.out.println ("The opponent has used death note, and attack you!");
                                // Boss attacks with the final weapon
                                takeBossDeathNote();
                                System.out.println("The HP you have left is:" + player.returnHP());
                            }
                            else if (player.returnHP()<0){
                                System.out.println("The opponent has attacked!");
                                if (1000 >boss.returnHP() && boss.returnHP()>0){
                                    System.out.println("The boss wrote your name on the death note, you have died.");
                                    return;
                                }
                                else{
                                    System.out.println("The damage you have taken from this attack is: " + boss.returnDamage());
                                    System.out.println("You have died");
                                    return;
                                }
                            } 
                        }
                    }
                }
            }
            else {
                    System.out.println("You have not equipped any weapon!");
                    return;
            }
        }
        else {
            System.out.println("You are not in the same room as the" + opponent.returnName());
            return;
        }
    }
    
    /**
     * Obtain the item from the opponent the player defeated, 
     * and prints out the reward obtained
     */
    private void Getreward()
    {
        Item tItem = opponent.returnCharacterItems();
        opponent.itemTaken(opponent);
        System.out.println("The item you received is: " + tItem.getName() + " , It is used for " + tItem.getTypes());
        player.increaseWeight(tItem); 
        if (player.returnWeight() > player.returnWeightLimit() )
        {
            System.out.println("Over weight limit! You cannot get this reward!");
            player.decreaseWeight(tItem);
            currentRoom.setRoomItems(tItem.getName(), tItem.getDescription(),
            tItem.getWeights(), tItem.getTypes(), tItem.getIncreasedHP(),tItem.getDamageCaused());
        }
        else {
            player.pickupPlayerItem(tItem.getName(), tItem.getDescription(),
            tItem.getWeights(), tItem.getTypes(), tItem.getIncreasedHP(),tItem.getDamageCaused());
        }
    }
    
    /**
     * Normal Attack to go against the opponent
     * @param command The character to be attacked
     */    
    public void Attack(Command command)
    {
        if (!command.hasSecondWord()){
            System.out.println("Attack who?");
        }
        String characterToFight = command.getSecondWord();
        
        // Get the opponent to be attacked
        Character theOpponent = ListOfCharacters.get(characterToFight);
        if (theOpponent != null) {
            if (theOpponent != opponent){
                System.out.println("You are not currently fighting "+ theOpponent.returnName());
            }
            else {
                opponent.takeDamage(player.returnWeaponPower());
            }
        }
    }
    
    /**
     * Take damage from the attack of the opponent
     */
    private void TakeDamage()
    {
        player.takeDamage(opponent.returnDamage());
    }
    
    // Methods about fighting the final boss
    /**
     * Attack the final boss 
     */
    private void attackBoss(Command command)
    {
        String opponentName = command.getSecondWord();
        if (opponentName.equals("Boss")) {
            boss.takeDamage(player.returnWeaponPower());
        }
    }
    /**
     * Take damage from the attack of the boss
     */
    private void takeBossDamage()
    {
        player.takeDamage(boss.returnDamage());
    }
    /**
     * Take damage from the usage of Death Note from the boss
     */
    private void takeBossDeathNote()
    {
        boss.useDeathNote(player);
    }
}
