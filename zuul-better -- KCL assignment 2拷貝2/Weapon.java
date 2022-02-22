
/**
 * Class Weapon - A weapon in the game
 * 
 * This class is part of the "A stereotypical maze adventure" application. 
 * 
 * This is a subclass of the type item, a weapon is specifically used for 
 * attacking the opponent during a fight, causing subtraction of the HP of the opponent.
 * It needs to be equipped by the player to be able to use. An weapon also inherit the same
 * function as an item.
 *
 * @author Chin Wan k21016106
 * @version 2021.12.03
 */
public class Weapon extends Item
{
    /**
     * Create a weapon with all the properties it has
     * @param name The name of the weapon
     * @param description The description of the weapon
     * @param weight The weight of the weapon
     * @param type The type of the weapon
     * @param increasedHP The HP increased by the weapon when used, normally it is 0 for all weapons
     * @param damageCaused The damage caused by the weapon during an attack in a fight
     */
    public Weapon(String name, String description, int weight, String type,
    int increasedHP, int damageCaused)
    {
        super(name, description, weight, type, increasedHP, damageCaused);
        
    }

    /**
     * @return Return the damage caused by the weapon
     */
    public int getDamage(){
        return super.damageCaused;
    }
}
