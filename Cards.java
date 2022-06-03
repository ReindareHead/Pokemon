import ecs100.*;
import java.util.HashMap;
/**
 * Support class for GUI class
 * Contains the hashmap of the users contacts
 *
 * @author Evie
 * @version 31/05/2022
 */
public class Cards
{
    // Instance variables
    private HashMap<Integer, Card> deck; // Creating the HashMap
    private int currCardID; // Store the curr ID of the Card being added
    private Card currCard; // Store the instance of the current card
    
    /**
     * Constructor for objects of class Cards
     */
    public Cards()
    {
        // Initialise instance variables
        deck = new HashMap<Integer, Card>(); // Initialise HashMap
        
        this.currCardID = 0; // Store the current Card ID
    }

    /**
     * Add Card to the map
     * Adds a new card with an Id, name, amount, and image
     */
    public void addCard(String name, int amount, String image)
    {
        deck.put(currCardID, new Card(currCardID, name, amount, image));
    }
    
    /**
     * Delete Card from the map
     * Delete card based of the id number
     */
    public void deleteCard(int id, String name, int amount, String image)
    {
        // Removes cards based of ID found from search
        // If deleted with currCardID it will delete last card added
        deck.remove(id);
    }
    
    /**
     * Find a Card based on the name
     * Sets the current Card instance if found
     * Return a boolean which is false is not found
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
                // Prints out each card in this format
                UI.println("    -----");
                UI.println("ID: " + cardID);
                UI.println("Pokémon Name: " + deck.get(cardID).getName());
                UI.println("Monetary Value: " + deck.get(cardID)
                            .getAmount());
            }
        }
    }
    
    /**
     * Print out size of HashMap
     * Checks how may things are in HashMap then tells user
     */
    public void deckAmount() {
        // Puts number into sentance
        UI.println("You currently have " + deck.size() + " cards in your deck");
    }
    
    /**
     * Set Card Id
     * int amount to increment Card id by 1
     * This is for when a card is added
     */
    public void addCardID() {
        //Card ID plus one
        this.currCardID = this.currCardID + 1;
    }
    
    /**
     * Set Card Id
     * int amount to increment Card id by -1
     * This is for when a card is deleted
     */
    public void minusCardID() {
        // Card is minus one
        this.currCardID = this.currCardID - 1;
    }
    
    /**
     * Getter for the current Card instance
     * @return card
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
