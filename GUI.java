import ecs100.*;
import java.util.HashMap;
/**
 * A GUI with buttons to traverse a list of user Cards
 *
 * @author Evie
 * @version 31/05/2022
 */
public class GUI
{
    // Instance variables
    private Card card; // Declare Card instance
    private Cards cards; // Declare Cards instance
    
    // Declaring the boundries of the image
    private double left;
    private double top;
    private double bottom;
    
    // X and Y for Image click area
    private double startX;
    private double startY;
    
    //Declare components of Card
    private String name;
    private int amount;
    private String imgFileName;

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // Initialise instance variables
        cards = new Cards();
        card = new Card(1, "Bulbasor", 123, "unknown.jpg");
        
        // Buttons for code
        UI.initialise();
        UI.addButton("Show all Cards", cards::printAll);
        UI.addButton("Add a Card", this::addCard);
        UI.addButton("Delete a Card", this::deleteCard);
        UI.addButton("Find a Card", this::findCard);
        UI.addButton("Amount of Cards", this::deckAmount);
        UI.addButton("Clear Field", this::clearField);
        UI.addButton("Quit", UI::quit);
        
        // Set up mouse listner
        UI.setMouseListener(this::doMouse);
    }

    /**
     * Clear all the text and the graphics from the page
     * Puts both clear method into one to make it easier to call
     */
    public void clearField() {
        UI.clearGraphics();
        UI.clearText();
    }
    
    
    /**
     * Add a Card to the users deck
     */
    public void addCard() {
        // Adds one on to every new Cards ID/key
        final int maxQuantity = 1;
        final int increment = 1;
        addNCard(); // Go add name
    }
    
    /**
     * Recive the name from the user then do checks
     * If everything is okay move to the method to recive value
     * This method is only for the name - reciving and checking
     */
    public void addNCard() {
        do{
            clearField();
            name = UI.askString("Name of the Pokémon: ");
            if (!name.matches("[a-zA-Z_]+")) {
                // Using sleep and clearField it makes the Gui more professional
                UI.sleep(500.0);
                clearField();
                UI.println("Invalid Input");
                UI.println("Please only enter letters");
                UI.sleep(2000.0); // Wait a little for reading then continue
                clearField();
            }
            else
            {
                break;
            }
        }while (true);
        addACard(); // Go add Value
    }
    
    /**
     * Recive the value from the user then do checks
     * This method is only for the value - reciving and checking
     * Accepts as string to check for null and anything but numbers
     * Turns to Int to check for boundries
     */
    private void addACard() {
        // Set Boundires for Value of card
        final int maxAQuantity = 900000;
        final int minAQuantity = 1;
        
        // Create str for checking purposes - changed into int later
        String str;
        
        do{
            clearField();
            str = UI.askString("Monetary Value of the Card: $");
            if (!str.matches("[0-9]+")) {
                // Using sleep and clearField it makes the Gui more professional
                UI.sleep(500.0);
                clearField();
                UI.println("Invalid input");
                UI.println("Please only enter numbers");
                UI.sleep(2000.0); // Wait a little for reading then continue
                clearField();
            }
            else
            {
                amount = Integer.parseInt(str); // Convert str to int called amount
                if (amount > maxAQuantity){
                    clearField();
                    // Boundery for largest amount allowed
                    UI.println("That is impossible as the most expensive Card sold for $900,000");
                    UI.println("Enter an amount that is less than that");
                    UI.sleep(2000.0); // Wait a little for reading then continue
                }
                else if (amount < minAQuantity) {
                    clearField();
                    // Boundery for smallest amount allowed
                    UI.println("I think it's unlikely that your card is that cheap");
                    UI.println("Enter a number that is more than $1");
                    UI.sleep(2000.0); // Wait a little for reading then continue
                }
                else
                {
                    break;
                }
            }
        }while (true);
        addICard(); // Go add image
    }
    
    /**
     * Recive the value from the user then do checks
     * Then move to show the details enetred including image
     */
    private void addICard(){
        clearField();
        // Add a Card image for the display in GUI
        imgFileName = UIFileChooser.open("Choose image file:");
        printCard(); // Go print out details entered
    }
    
    /**
     * Prints out all details enetred without adding to HashMap yet
     * moves onto detail checker
     */
    private void printCard(){
        clearField();
        
        // Show all details just entered
        UI.println("- Details entered -");
        UI.println("Name: " + name);
        UI.println("Monetary Value: $" + amount);
        
        // If image was entered print it else show the defultImage to check-
        // Image is correct
        if (imgFileName == null) {
            UI.drawImage("unknown.jpg", card.getX(), card.getY(), card.getWidth(), card.getHeight());
        }else{
            UI.drawImage(imgFileName, card.getX(), card.getY(), card.getWidth(), card.getHeight());
        }
        
        UI.println("");
        checkCard(); // Go check that details enetred are correct
    }
    
    /**
     * Checks all details are correct
     * If they are add card if not start the whole adding proccess again
     */
    private void checkCard(){
        String yn = UI.askString("Are all the details correct y/n?: ").toLowerCase();
            
        // Checks that all details entered are correct (cause users are idiots)
        if (yn.equals("y")) {
            // Increment the ID count and add to HashMap
            cards.addCardID();
            // Add the new Card to HashMap
            cards.addCard(name, amount, imgFileName);
            
            clearField();
            UI.println("New Card added");
        } else if (yn.equals("n")) {
            addNCard(); // Starts the whole proccess again
        }else if (!yn.matches("[0-9]+")) {
            UI.sleep(500.0);
            clearField();
            UI.println("Invalid input");
            UI.println("Answer doesn't make sense, please answer either 'y' or 'n'");
            UI.sleep(2000.0); // Wait a little for reading then continue
            clearField();
            printCard(); // Goes to print card again to print out details then re check
        }
    }
    
    
    /**
     * Finds Card based on Name and show details if found
     * Checks that there are some cards in deck first
     */
    public void findCard() {
        do {
            clearField();
            if (cards.getCardID() == 0) {
                // If currCard Id is 0 there are no cards in deck
                UI.println("You currently have no Cards in your deck");
                break;
            }else if (cards.getCardID() >= 1) {
                String name = UI.askString("What is the Name of the Pokemon?: ");
                clearField();
                if (cards.findCard(name.toLowerCase())) {
                    // Prints out details for card if found (including image)
                    UI.println("--Found Card--");
                    card = cards.getCard();
                    UI.println("Name: " + card.getName());
                    UI.println("Monetary Value: $ " + card.getAmount());
                    break;
                } else if (!name.matches("[a-zA-Z_]+")) {
                    // If answer enetred is anything but letters there is a mistake
                    UI.sleep(500.0);
                    UI.println("Invalid Input");
                    UI.println("Please only enter letters");
                    UI.sleep(2000.0); // Wait a little for reading then continue
                }else{
                    // If its passed through every check that card doesn't exist
                    UI.println("Card does not exist in your deck");
                    break;
                }
            }
        }while (true);
    }
    
    
    private void deleteCard() {
        do{
            clearField();
            if (cards.getCardID() == 0){
                // If currCard Id is 0 there are no cards in deck
                UI.println("You currently have no Cards in your deck");
                break;
            }else if (cards.getCardID() >= 1){
                // Asks for card wanting to delete
                String name = UI.askString("What is the Name of Card you wish to discard?: ");
                clearField();
                if (!name.matches("[a-zA-Z_]+")) {
                    UI.sleep(500.0);
                    UI.println("Invalid Input");
                    UI.println("Please only enter letters");
                    UI.sleep(2000.0); // Wait a little for reading then continue
                }
                else
                {
                    if (cards.findCard(name.toLowerCase())) {
                        // Prints out card if found
                        UI.println("--Found Card--");
                        card = cards.getCard();
                        UI.println("Name: " + card.getName());
                        UI.println("Monetary Value: $ " + card.getAmount());
                        checkDelete(); // Goes to check the card is correct
                        break;
                    } else {
                        // If its passed through every check that card doesn't exist
                        UI.println("That card does not exist in your deck");
                        break;
                    }
                }
            }
        } while (true);
    }
    
    /**
     * Method just for effect
     * If 'yn' in next method has an issue it goes to here
     * So it prints it out then checks again
     * Otherwise it skips over this method
     */
    private void printDelete(){
        // Prints out card found
        UI.println("--Found Card--");
        card = cards.getCard();
        UI.println("Name: " + card.getName());
        UI.println("Monetary Value: $ " + card.getAmount());
        checkDelete(); // Goes to check details again
    }
    
    private void checkDelete() {
         String yn = UI.askString("Is this the contact you wish to delete? y/n: ").toLowerCase();
        //Checks that all details entered are correct (cause users are idiots)
        if (yn.equals("y")) {
            // Delete card from HashMap
            cards.deleteCard(card.getID(), name, amount, imgFileName);
            // Take 1 off the current Card ID
            cards.minusCardID();
            
            clearField();
            UI.println("Contact deleted :)"); // Proccess ends
        }else if (yn.equals("n")) {
            deleteCard(); // Start proccess again
        }else if (!yn.matches("[0-9]+")) {
            // Checks for null and other things
            clearField();
            UI.println("Answer doesn't make sense, please answer either 'y' or 'n'");
            UI.sleep(2000.0); // Wait a little for reading then continue
            clearField();
            printDelete(); // Goes to print out details again
        }
        else{
            // In case anything skips through other checking
            clearField();
            UI.println("Answer doesn't make sense, please answer either 'y' or 'n'");
            UI.sleep(2000.0); // Wait a little for reading then continue
            clearField();
            printDelete(); // Goes to print out details again
        }
    }
    
    
    /**
     * Print out how many cards are in deck
     * Uses method from cards class but wipes screen first
     */
    private void deckAmount() {
        clearField();
        cards.deckAmount();
    }
    
    
    /**
     * When image are clicked clear everything
     */
    private void doMouse(String action, double x, double y) {
        // Makes area that does something when clicked on (Clears panes)
        if (action.equals("clicked")) {
            // Check location of the x and y against the location of the object
            if ((x >= 10) && (x <= card.getWidth()+10) &&
                (y >= 10) && (y <= card.getHeight()+10)) { clearField();
                    // Clears both panes
            }
        }
    }   
}
