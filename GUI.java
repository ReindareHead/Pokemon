import ecs100.*;
import java.util.HashMap;
/**
 * A GUI with buttons to traverse a list of user contacts
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
        UI.addButton("Show all cards", cards::printAll);
        UI.addButton("Add a Card", this::addCard);
        UI.addButton("Find a Card", this::findCard);
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
        UI.clearText();
        UI.clearGraphics();
    }
    
    /**
     * Add a Card to the users deck
     */
    public void addCard() {
        // Adds one on to every new Cards ID/key
        final int maxQuantity = 1;
        final int increment = 1;
        addNCard();
    }
    
    /**
     * Recive the name from the user then do checks
     * If everything is okay move to the method to recive value
     */
    public void addNCard() {
        do{
            clearField();
            name = UI.askString("Name of the Pokemon: ");
            if (!name.matches("[a-zA-Z_]+")) {
                UI.sleep(500.0);
                UI.println("Invalid Input");
                UI.println("Please only enter letters");
                UI.sleep(2000.0);;
            }
            else
            {
                break;
            }
        }while (true);
        addACard();
    }
    
    private void addACard() {
        final int maxAQuantity = 900000;
        final int minAQuantity = 1;
        String str;
        do{
            clearField();
            str = UI.askString("Monetary Value of the Card: $");
            if (!str.matches("[0-9]+")) {
                UI.sleep(500.0);
                UI.println("Invalid input");
                UI.println("Please only enter numbers");
                UI.sleep(2000.0);
            }
            else
            {
                amount = Integer.parseInt(str); // Convert str to int called amount
                if (amount > maxAQuantity){
                    clearField();
                    UI.println("That is impossible as the most expensive Card sold for $900,000");
                    UI.println("enter an amount that is less than that");
                    UI.sleep(2000.0);
                }
                else if (amount < minAQuantity) {
                    clearField();
                    UI.println("I think it's unlikely that your card is that cheap");
                    UI.println("Enter a number that is more than $1");
                    UI.sleep(2000.0);
                }
                else
                {
                    break;
                }
            }
        }while (true);
        checkCard();
    }
    
    private void checkCard(){
        clearField();
    
        // Add a Card image for the display in GUI
        imgFileName = UIFileChooser.open("Choose image file:");
    
        // Show all details just entered
        UI.println("- Details entered -");
        UI.println("Name: " + name);
        UI.println("Monetary Value: $" + amount);
        UI.println("");
        
        do{
            String yn = UI.askString("Are all the details correct y/n?: ").toLowerCase();
            
            // Checks that all details entered are correct (cause users are idiots)
            if (yn.equals("y")) {
                // Increment the ID count and add to HashMap
                cards.setCardID();
                
                // Add the new Card to HashMap
                cards.addCard(name, amount, imgFileName);
                
                clearField();
                UI.println("New Card added");
                UI.sleep(1000.0);
                break;
            } else if (yn.equals("n")) {
                addNCard(); // Starts the proccess again
            } else if (!yn.matches("[a-zA-Z_]+")) {
                UI.println("Invalid Input");
                UI.println("Please enter eitehr 'y' or 'n'");
                UI.sleep(2000.0);
            }
        }while (true);
    }
    
    /**
     * Finds Card based on Name and show details if found
     */
    public void findCard() {
        clearField();
        String name = UI.askString("What is the Name of the Pokemon?: ");
        clearField();
        if (cards.findCard(name.toLowerCase())) {
            UI.println("--Found Card--");
            card = cards.getCard();
            UI.println("Name: " + card.getName());
            UI.println("Monetary Value: $ " + card.getAmount());
        } else {
            UI.println("Card does not exist in your deck");
            clearField();
            findCard();
        }
    }
    
    /**
     * When image are clicked clear everything
     */
    private void doMouse(String action, double x, double y) {
        // Makes area that does something when clicked on (Clears panes)
        if (action.equals("clicked")) {
            // Check location of the x and y against the location of the object
            if ((x >= 0) && (x <= 220) &&
                (y >= 0) && (y <= 270)) { clearField();
            }
        }
    }   
}
