
/**
 * Class Boss - The final boss in the game
 * 
 * This class is part of the "A stereotypical maze adventure" application. 
 * 
 * The Boss class is the subclass of the class Character. The character Boss
 * in the boss class has the same properties as a normal character, only with
 * stronger damage, higher HP, and a special attack. It also stays in a different 
 * room that only can be reached if certain commands are inputed.
 *
 * @author Chin Wan k21016106
 * @version 2021.12.03
 */
public class Boss extends Character
{
    private Weapon DeathNote;     // The special attack of the Boss
    /**
     * Create a boss with its properties and its locations
     * @param name The name of the boss
     * @param NPCHP The HP the boss has
     * @param NPCDamage The damage the boss cause per attack
     * @param currentRoom The room the noss is currently in
     */
    public Boss(String name, int HP, int Damage, Room currentRoom)
    {
        super(name, HP, Damage, currentRoom);
        
        // Create the special weapon for special attack
        DeathNote = new Weapon("DeathNote", "THE DeathNote", 200, 
        "spell", 0, 100000000);
    }

    /**
     * @return Return the final weapon of the boss, which is the Death Note
     */

    public Item returnFinalWeapon()
    {
        return DeathNote;
    }
    
    /**
     * @return Return the damage caused by the special attack of the boss
     */
    public int returnFinalWeaponDamage()
    {
        return DeathNote.getDamageCaused();
    }
    
    /**
     * Reset the damage the death note causes to 0, 
     * this is called when the player is able to disable the Death Note
     */
    public void resetBossDamage()
    {
        DeathNote.resetDamage();
    }
    
    /**
     * Use Death Note on the player (by writing the player's name on it),
     * let player take damage from death note
     * @param player The player that takes damage from the usage of Death Note
     */
    public void useDeathNote(GamePlayer player)
    {
        player.takeDamage(returnFinalWeaponDamage());
    }
}
