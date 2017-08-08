/**
 * Created by Sam Romeo and edited by Bryan Conn.
 * Started 8/5/17.
 * Creates a new player2 object and attributes required for it
 * to move, collide with the puck, and keep score.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Player1 {
    /** You need to fill these out */
    private int X = 250;

    /** The score of the player. */
    private int score = 0;

    /** You need to fill these out */
    private static final int Y = 398;

    /** The width of the players paddle. */
    private static final int WIDTH = 100;

    /** The height of the players paddle */
    private static final int HEIGHT = 30;

    /** The game being played. */
    private AirHockeyGame game;

    /** The movement speed of the paddle at the start. */
    private int Wx = 0;

    /**
     * Sets the game to the game being played.
     *
     * @param game The game being played.
     */
    public Player1(AirHockeyGame game){
        this.game = game;
    }

    /**
     * Colors the paddle red.
     *
     * @param g The paddle being changed.
     */
    void paint(Graphics2D g){
        g.setColor(Color.RED);
        g.fillRect(X, Y, WIDTH, HEIGHT);
    }

    /**
     * Moves the paddle based the key pressed.
     */
    public void move(){
        if(X + Wx > 0 && X + Wx < game.getWidth() - WIDTH)
            X = X + Wx;
    }

    /**
     * Determines which key was pressed and set the direction
     * of the movement.
     *
     * @param e The key pressed.
     */
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            Wx = -4;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            Wx = 4;
        }
    }

    /**
     * When the key is released it sets the movement speed
     * back to 0.
     *
     * @param e The key being released.
     */
    public void keyReleased(KeyEvent e){
        Wx = 0;
    }

    /**
     * Determines the score at the moment.
     *
     * It adds to the score if the puck collided with the
     * top of the screen.
     *
     * @return The current score.
     */
    public int getScore(){
        if(game.puck.collideTop()){
            return score++;
        }
        else{
            return score;
        }
    }

    /**
     * The score at the moment. Needed to stop
     * double addition to score.
     *
     * @return The current score.
     */
    public int theScore(){
        return score;
    }

    /**
     * Returns the bounds of the rectangle.
     *
     * @return The bounds of the rectangle.
     */
    public Rectangle getBounds(){
        return new Rectangle(X, Y, WIDTH, HEIGHT);
    }

    /**
     * Returns the value of Y.
     *
     * @return The value of Y.
     */
    public int getTopY(){
        return Y;
    }
}
