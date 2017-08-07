
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Puck {
    //diameter of the puck
    private static final int DIAMETER = 50;
    //x - initial x location of puck
    //y - initial y location of puck
    int x = 1, y = 1, z = 1;
    //speed and direction
    int Wx = 5, Hy = 5, Z = 5;
    //creates instance AirHockey game
    private AirHockeyGame game;
    Random rand = new Random();

    public Puck(AirHockeyGame game){
        this.game = game;
    }

    /**
     * controls the pucks movements as it collides with the walls and the rackets.
     * '- DIAMETER' because want the edge of puck to hit wall not center. Changes location
     * of the puck constantly
     */
    public void move(){
        //when it hits the left wall moves in positive direction (right)
        if(x + Wx < 0)
            Wx = game.speed;
        //when it hits the right wall moves in negative direction (left)
        if(x + Wx > game.getWidth() - DIAMETER)
            Wx = -game.speed;
        //when it hits the top the game ends
        if(y + Hy > game.getHeight() - DIAMETER)
            game.gameOver();
        //when it hits the bottom game ends
        if(y == 0)
            game.gameOver();

        //HONESTLY I HAVE NO IDEA WHAT THE z IS BUT IT WORKS SO IM NOT COMPLAINING!
        if(collisionP2()) {
            Hy = 1;
            //y = game.racketP1.getTopY() - DIAMETER;
            z = game.racketP2.getBottomY() - DIAMETER;
            //game.speed = rand.nextInt(5);
        }

        if (collisionP1()){
            Hy = -1;
            y = game.racketP1.getTopY() - DIAMETER;
            //z = game.racketP2.getTopY() - DIAMETER;
            //game.speed += rand.nextInt(5);

        }
        x = x + Wx;
        y = y + Hy;
        z = z + Z;
    }

    /**
     * returns true if there is a collision between the racketP1 and the puck
     */
    public boolean collisionP1(){
        if(game.puck.getBounds().intersects(game.racketP1.getBounds())){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * returns true if there is a collision between the racketP2 and the puck
     */
    public boolean collisionP2(){
        if(game.puck.getBounds().intersects(game.racketP2.getBounds())){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * returns the bounds of the puck based on where it is on board
     */
    private Rectangle getBounds(){
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    /**
     * colors the puck black
     * @param g2d
     */
    public void paint(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x, y, 50, 50);
    }
}
