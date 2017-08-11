/**
 * Created by Sam Romeo and edited by Bryan Conn.
 * Started 8/5/17.
 * Creates a new player2 object and attributes required for it
 * to move, collide with the puck, and keep score.
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Player2 {
    /** Starting x location of the paddle */
    private int X = 250;

    /** The score of the player. */
    private int score = 0;

    /** Allows player location to be along y axis at 0.
     * Does not move up and down only along this axis */
    private static final int Y = 0;

    /** The width of the players paddle. */
    private static final int WIDTH = 100;

    /** The height of the players paddle */
    private static final int HEIGHT = 30;

    /** The game being played. */
    private AirHockeyGame game;

    /** The initial speed of the paddle. */
    int Wx = 0;

    /**
     * Sets the game to the game being played.
     *
     * @param game The game being played.
     */
    public Player2(AirHockeyGame game){
        this.game = game;
    }

    /**
     * Colors the paddle Blue.
     *
     * @param g The paddle being changed.
     */
    void paint(Graphics2D g){
        g.setColor(Color.BLUE);
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
        switch (e.getKeyCode()){
            case (KeyEvent.VK_A):
                Wx = -6;
                break;
            case (KeyEvent.VK_D):
                Wx = 6;
                break;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            game.frame.setVisible(false);
            game.frame.dispose();
            System.exit(0);
            new MainMenu();
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
     * The score at the moment. Needed to stop
     * double addition to score.
     *
     * @return The current score.
     */
    public int getScore(){
        if(game.getPuck().collideBottom())
            return score++;
        return score;
    }

    /**
     * Returns the score.
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
     * Returns the value of the location of the bottom of the paddle for player 2.
     *
     * @return The value of Y minus the height of the paddle.
     */
    public int getBottomY(){
        return Y - HEIGHT;
    }
}
