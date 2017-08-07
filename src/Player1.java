/**
 * Created by srome on 8/5/2017.
 *
 * Edits by bconn.
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player1 {
    private int X = 250;
    private int score = 0;
    private static final int Y = 398;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private AirHockeyGame game;

    int Wx = 0;

    /**
     * Constructor that sets the game.
     *
     * @param game The game being used.
     */
    public Player1(AirHockeyGame game){
        this.game = game;
    }

    /**
     * Sets the color and size of the paddle.
     *
     * @param g The paddle that is being set.
     */
    void paint(Graphics2D g){
        g.setColor(Color.RED);
        g.fillRect(X, Y, WIDTH, HEIGHT);
    }

    /**
     * Moves the paddle.
     */
    public void move(){
        if(X + Wx > 0 && X + Wx < game.getWidth() - WIDTH)
            X = X + Wx;
    }

    /**
     * Moves the paddle based on the key pressed.
     *
     * @param e The key pressed.
     */
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            Wx = -game.speed;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            Wx = game.speed;
        }
    }

    /**
     * The key moving the paddle was released.
     *
     * @param e The key that was released.
     */
    public void keyReleased(KeyEvent e){
        Wx = 0;
    }

    /**
     * Adds to the score if there was a collision with the
     * players paddle or returns the same score if there
     * was no collision.
     *
     * @return The current score of the game.
     */
    public int getScore(){
        if(game.puck.collisionP1()){
            return score++;
        }
        else{
            return score;
        }
    }

    /**
     * Returns the bounds of the rectangle.
     *
     * @return A new rectangle with the specific size.
     */
    public Rectangle getBounds(){
        return new Rectangle(X, Y, WIDTH, HEIGHT);
    }

    /**
     * Returns the value Y.
     *
     * @return The value Y.
     */
    public int getTopY(){
        return Y;
    }
}
