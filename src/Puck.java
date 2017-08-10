/**
 * Created by Sam Romeo and edited by Bryan Conn.
 * Started 8/5/17.
 * Creates a new puck object and attributes required for it
 * to move and collide with different objects.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Puck {
    /** DIAMETER is the diameter of the puck */
    private static final int DIAMETER = 50;

    /** x - initial x location of puck */
    /** y - initial y location of puck */
    /** z - used to let the puck go back down after hitting the top paddle */
    int x = 1, y = 1, z = 1;

    /** Changes the location of the puck every time it moves
     * based on if it has a collision or not.
     */
    int Wx = 5, Hy = 5, Z = 5;

    /** WINNER is the number of goals required to win */
    private final int WINNER = 7;

    /** The speed of the puck for the x and y directions */
    private int speed = 5;

    /** The max speed possible. */
    private final int SPEED_MAX = 10;

    /** player1 is the first player */
    private Player1 player1;

    /** player2 is the second player */
    private Player2 player2;

    /** Creates instance AirHockey game */
    private AirHockeyGame game;

    /** Creates a new random variable */
    private Random rand = new Random();

    /**
     * Creates a new Puck object.
     *
     * @param game The game being played.
     */
    public Puck(AirHockeyGame game){
        this.game = game;
        this.player1 = game.getPlayer1();
        if(game.getType() == 'D'){
            this.player2 = game.getPlayer2();
        }
    }

    /**
     * Controls the pucks movements as it collides with the walls and the rackets.
     * '- DIAMETER' because want the edge of puck to hit wall not center. Changes location
     * of the puck constantly
     */
    public void move(){
        //when it hits the left wall moves in positive direction (right)
        if(collideLeft())
            Wx *= -1;
        //when it hits the right wall moves in negative direction (left)
        else if(collideRight())
            Wx *= -1;

        switch (game.getType()){
            case 'S':
                if(collideTop()) {
                    Hy = rand.nextInt(SPEED_MAX) + 1;
                }
                //when it hits the bottom the game ends
                else if(collideBottom()) {
                    game.gameOver();
                }
                break;
            //when it hits the top the game resets and player 1 gets a point
            case 'D':
                if(collideTop()) {
                    if(player1.theScore() == WINNER)
                        game.gameOver();
                    game.restart(game);
                }
                //when it hits the bottom the game resets and player 2 gets a point
                else if(collideBottom()) {
                    if (player2.theScore() == WINNER)
                        game.gameOver();
                    game.restart(game);
                }
                //Sends the puck away from the paddle after collision changes speed randomly
                //adds or subtracts.
                if (collideP2()) {
                    Hy = rand.nextInt(SPEED_MAX) + 1;
                    z = player2.getBottomY() - DIAMETER;
                    Wx += rand.nextInt(SPEED_MAX) + 1;
                    speed = Wx;
                }
                break;
        }
        //Rebound the puck if it collides with the first paddle.
        if (collideP1()) {
            Hy = -rand.nextInt(SPEED_MAX) - 1;
            y = player1.getTopY() - DIAMETER;
            Wx -= rand.nextInt(SPEED_MAX) + 1;
            speed = Wx;
        }
        //changes the location of the puck
        x += Wx;
        y += Hy;
        z += Z;
    }

    /**
     * Returns true if there is a collision between the racketP1 and the puck.
     *
     * @return If the puck collides with the first players paddle.
     */
    public boolean collideP1(){
        return game.getPuck().getBounds().intersects(player1.getBounds());
    }

    /**
     * Returns true if there is a collision between the racketP2 and the puck.
     *
     * @return If the puck collides with the second players paddle.
     */
    public boolean collideP2(){
        return game.getPuck().getBounds().intersects(player2.getBounds());
    }

    /**
     * Tests to see if the puck has hit the top of the
     * screen.
     *
     * @return A boolean of if it has hit the top or not.
     */
    public boolean collideTop(){
        return y <= 0;
    }

    /**
     * Tests to see if the puck has hit the left side of the
     * screen.
     *
     * @return A boolean of if it has hit the left side or not.
     */
    public boolean collideLeft(){
        return x + Wx <= 0;
    }

    /**
     * Tests to see if the puck has hit the right side of the
     * screen.
     *
     * @return A boolean of if it has hit the right side or not.
     */
    public boolean collideRight(){
        return x + Wx >= game.getWidth() - DIAMETER;
    }

    /**
     * Tests to see if the puck has hit the bottom of the
     * screen.
     *
     * @return A boolean of if it hit the bottom or not.
     */
    public boolean collideBottom(){
        return y + Hy >= game.getHeight() - DIAMETER;
    }

    /**
     * Returns the bounds of the puck based on where it is on board.
     *
     * @return A new Rectangle with dimensions x and y.
     */
    private Rectangle getBounds(){
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    /**
     * Colors the puck black.
     *
     * @param g2d The puck.
     */
    public void paint(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x, y, 50, 50);
    }
}
