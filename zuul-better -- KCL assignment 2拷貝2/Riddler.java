import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

/**
 * CLass Riddler - a special character in the adventure game.
 * 
 * This class is the main class of the "A stereotypical maze adventure" application.
 * The riddler represents a character in this text-based game. It has a list of riddles
 * which he can use to ask the player if the player talk to him. 
 * When the answer matches what the riddler has, a prize from the riddler will be given
 * to the player.
 *
 * @author Chin Wan k21016106
 * @version 2021.12.03
 */
public class Riddler
{
    private ArrayList<String> questions;            // Riddler's list of riddles
    private HashMap<String, String> questionss;     // A HashMap of riddles and answers
    private String questionChosen;                  // Question chosen by the riddler
    /**
     * Create a riddler with the list of riddles and answers
     * the riddler owns
     */
    public Riddler()
    {
        questions = new ArrayList<>();
        questionss = new HashMap<>();
        createQuestion();
        createAnswer();
        questionChosen = "";
    }

    /**
     * Create different riddles and store it in an ArrayList
     */
    public void createQuestion()
    {
        String question1, question2, question3, question4, question5;
       
        // Create the questions
        question1 = "I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?";
        question2 = "You measure my life in hours and I serve you by expiring. I’m quick when I’m thin and slow when I’m fat. The wind is my enemy.";
        question3 = "I have cities, but no houses. I have mountains, but no trees. I have water, but no fish. What am I?";
        question4 = "I'm clean when I'm black, Dirty when white; Get too close, And you might sneeze. What am I?";
        question5 = "What word contains all of the twenty six letters?";
        
        questions = new ArrayList<String>(Arrays.asList(question1, question2, question3, question4, question5));
    }
    
    /**
     * Create different answers and store the question 
     * and answer as key and value in a HashMap
     */
    public void createAnswer()
    {
        String answer1, answer2, answer3, answer4, answer5;
        
        // Create the answers
        answer1 = "echo";
        answer2 = "candle";
        answer3 = "map";
        answer4 = "chalkboard";
        answer5 = "alphabet";
        
        questionss.put(questions.get(0), answer1);
        questionss.put(questions.get(1), answer2);
        questionss.put(questions.get(2), answer3);
        questionss.put(questions.get(3), answer4);
        questionss.put(questions.get(4), answer5);
    }
    
    /**
     * Get a random riddle from the ArrayList to ask
     * the player
     */
    public void askQuestion()
    {
        Random rand = new Random();
        int index = rand.nextInt(questions.size()-1); 
        questionChosen = questions.get(index);
        System.out.println("Please answer the following riddle by using the command [answer + riddleanswer]");
        System.out.println(questionChosen); 
    }
   
    /**
     * Check whether the answer inputed by the player 
     * is correct. If it is correct, let the player get a prize
     * @param answer The answer inputed by the player
     * @param player The player who inputs the answer
     */
    public void checkAnswer(String answer, GamePlayer player)
    {
        if (questionss.get(questionChosen).equals(answer)){
            System.out.println("That is correct! Now go claim your prize!");
            // Player claim the prize
            player.pickupPlayerItem("DestroytheDeathNote", "It is very useful for winning this game", 0, "spell", 0, 0);
            return;
        }
        else {
            System.out.println("That is incorrect!");
        }
    }
}
