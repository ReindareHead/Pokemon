import java.util.HashMap;
import ecs100.*;
/**
 * Support class for GUI class
 * Contains the hashmap of the users contacts
 *
 * @author (Evie M)
 * @version (23/05/2022)
 */
public class Cards
{
    // Instance variables
    private HashMap<Integer, Card> deck; // Creating/declaring the hashmap
    private int currCardID; // Store the current ID of card being added
    private Card currCard; // Store the instance of the current card


    /**
     * Constructor for methods
     */
    public Cards()
    {
        // Initialise instance variables
        deck = new HashMap<Integer, Card>(); // Initialise hashmap
        
        this.currCardID = 0; // Store the current Cards ID
    }
    
    /**
     * Add Card to the map
     * @param firstName is fnm
     * @param phoneNumber is pn
     * @param image is img
     */
    public void addCard(String name, int amount, String image) {
        deck.put(currCardID, new Card(currCardID, name, amount, image));
    }
    
    /**
     * Find a Card based on name
     * Sets the current Card instance if found
     * return a boolean which is false if not found
     * @param name is nm
     * @return false/true
     */
    public boolean findCard(String name) {
        // Search for Card
        for
        (int cardID : deck.keySet())
        {
            if (deck.get(cardID).getName().toLowerCase()
            .equals(name.toLowerCase())) {
                currCard = deck.get(cardID);
                // Display Card on canvas
                deck.get(cardID).displayCard();
                return true;
            }
        }
        return false;
    }
    
    /**
     * print details of all Cards
     */
    public void printAll() {
        // Traverse map
        UI.clearText();
        UI.clearGraphics();
        if (currCardID == 0) {
            UI.println("You currently have no Cards");
        }
        else
        {
            for
            (int cardID : deck.keySet()) {
                UI.println("    -----");
                UI.println("ID: " + cardID);
                UI.println("First Name: " + deck.get(cardID).getName());
                UI.println("Monetary Value: " + deck.get(cardID)
                            .getAmount());
            }
        }
    }
    
    /**
     * Set Card Id
     * int amount to increment Card id by
     */
    public void setCardID() {
        this.currCardID += 1;
    }
    
    /**
     * Getter for the current Card instance
     * @return Card
     */
    public Card getCard() {
        return this.currCard;
    }
    
    /**
     * Getter for the current Card instance
     * @return Card id
     */
    public int getCardID() {
        return this.currCardID;
    }
}
