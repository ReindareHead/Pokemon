import ecs100.*;
/**
 * Support class for Cards class
 * Contains the fields for a card
 * 
 *
 * @author (Evie M)
 * @version (23/05/2022)
 */
public class Card
{
    // Instance variables
    private int id;
    private String name;
    private int amount;
    private String image;
    static final String defultImage = "unknown.jpg";
    
    // Boundries for image
    int locX = 10;
    int locY = 10;
    final double width = 200;
    final double height = 250;
    /**
     * Constructor for objects of class Card
     * @param key is id
     * @param fnm is Name
     * @param pn is Monetary Value
     * @param img is Card Image
     */
    public Card(int key, String nm, int amt, String img)
    {
        // Initialise instance variables
        id = key;
        name = nm;
        amount = amt;
        if (img == null) {
            // Add defult image if no image added by user
            this.image = defultImage;
        }
        else
        {
            this.image = img;
        }
    }
    
    /**
     * Constructor overloading
     * Set defult image to object
     * @param key is ID
     * @param nm is Name
     * @param amt is Monetary Value
     */
    public Card(int key, String nm, int amt) {
        this(key, nm, amt, defultImage);
    }
    
    /**
     * Display image on GUI
     */
    public void displayCard() {
        UI.drawImage(this.image, locX, locY, width, height);
    }
    
    /**
     * Getter for ID
     * @return ID
     */
    public int getID() {
        return this.id;
    }
    
    /**
     * Getter for Name
     * @return name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter for Amount
     * @return amount
     */
    public int getAmount() {
        return this.amount;
    }
    
    /**
     * Getter for X
     * @return x
     */
    public int getX() {
        return this.locX;
    }
    
    /**
     * Getter for Y
     * @return y
     */
    public int getY() {
        return this.locY;
    }
    
    /**
     * Getter for Width
     * @return width
     */
    public double getWidth() {
        return this.width;
    }
    
    /**
     * Getter for Height Number
     * @return height
     */
    public double getHeight() {
        return this.height;
    }
}
