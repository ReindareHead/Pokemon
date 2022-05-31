import ecs100.*;
/**
 * A GUI with buttons to traverse a list of user contacts
 *
 * @author (Evie M)
 * @version (23/05/2022)
 */
public class GUI
{
    // instance variables
    private Cards cards; // Declare Cards instance (class)
    private Card card; // Declare Card instace (class)
    
    // Declaring the boundries of the image
    private double left;
    private double top;
    private double bottom;
    
    // X and Y for Image click area
    private double startX;
    private double startY;
    
    //Declaring the Cards variables
    private String name;
    private int amount;
    private String imgFileName;
    
    /**
     * Constructor for objects of class GUI
     */
    public GUI() {
        // Initialise instance variables
        cards = new Cards();// Instantiate Methods object
        card = new Card(1, "Evie", 100,"unknown.jpeg"); // Instantiate Card object
        
        // Buttons for code
        UI.initialise();
        UI.addButton("Show all Cards", cards::printAll);
        UI.addButton("Add a Card", this::addCard);
        UI.addButton("Find a Card", this::findCard);
        UI.addButton("Clear Screen", this::clearField);
        UI.addButton("Quit", UI::quit);
        
        // Set up mouse listner
        UI.setMouseListener(this::doMouse);
    }
    
    /**
     * Clear all the text and the image from the page
     * Puts both things together into one method to make it easier to call
     */
    public void clearField() {
        UI.clearText();
        UI.clearGraphics();
    }
    
    /**
     * User add a Card to collection
     */
    public void addCard() {
        //Adds one on to every new Cards Id/Key
        final int maxQuantity = 1;
        final int increment = 1;
        addNCard();
    }
    
    public void addNCard(){
        do{
            clearField();
            name = UI.askString("Name of new Card: ");
            if (!name.matches("[a-zA-Z_]+")) {
                UI.sleep(500.0);
                UI.println("Invalid input");
                UI.println("Please only enter Letters");
                UI.println("");//Makes a white line inbetween lines
                UI.sleep(2000.0);
            }
            else
            {
                break;
            }
        } while (true);
        addACard();
    }
    
    private void addACard(){
        final int maxAQuantity = 900000;
        final int minAQuantity = 1;
        String str;
        do{
            clearField();
            str = UI.askString("Monetary Value of new Card: $");
            if (!str.matches("[0-9]+")) {
                UI.sleep(500.0);
                UI.println("Invalid input");
                UI.println("Please only enter numbers");
                UI.println("");//Makes a white line inbetween lines
                UI.sleep(2000.0);
            }
            else
            {
                amount = Integer.parseInt(str);//Convert to int to continue testing
                if
                (amount > maxAQuantity)
                {
                    clearField();
                    UI.println("That is impossible, the most expensive card sold for $900,000");
                    UI.println("Maybe you entered wrong? Try again");
                    UI.sleep(2000.0);
                }
                else if (amount < minAQuantity)
                {
                    clearField();
                    UI.println("That is impossible, you paid to little for that card then");
                    UI.println("Maybe you entered wrong? Try again");
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
        
        //Add a Card image for display in the GUI
        imgFileName = UIFileChooser.open("Choose image file");

        //Check if all the details are correct
        UI.println("- Details entered -");
        UI.println("Name: " + name);
        UI.println("Monetary Value: $" + amount);
        UI.println(" ");
    
        do{
            String yn = UI.askString("Are all the details correct y/n?: ")
                                     .toLowerCase();
            // Checks that all details entered are correct (cause users are idiots)
            if (yn.equals("y")) {
                // Increment the ID count and add to hashmap
                cards.setCardID();
                
                // Add the new Card to hashmap
                cards.addCard(name, amount, imgFileName);
                
                clearField();
                UI.println("New Card Added :)");
                UI.sleep(1000);
                break;
            }
            else if 
            (yn.equals("n")) 
            {
                addNCard();// Starts the proccess again
            }
            else if (!yn.matches("[a-zA-Z_]+"))
            {
                UI.println("Invalid input");
                UI.println("please answer either 'y' or 'n'");
                UI.sleep(2000.0);
            }
        }while (true);
    }
    
    /**
     * Finds Card based on Name and print out details if found
     */
    public void findCard() {
        clearField();
        String firstName = UI.askString("What is the Name of Card/Pokemon?: ");
        clearField();
        if (cards.findCard(firstName.toLowerCase())) {
            UI.println("--Found Card--");
            card = cards.getCard();
            UI.println("First Name: " + card.getName());
            UI.println("Monetary Value: $" + card.getAmount());
        }
        else
        {
            UI.println("That Card does not exist in your deck");
            clearField();
            findCard();
        }
    }
    
    /**
     * When image area clicked clear everything
     */
    private void doMouse(String action, double x, double y) {
        //Makes area that does something when clicked on (Clears panes)
        if (action.equals("clicked")) {
            //check location of the x and y against the location of the object
            if ((x >= 0) && (x <= 120) &&
                (y >= 0) && (y <= 130)) { clearField();
            }
        }
    }
}
