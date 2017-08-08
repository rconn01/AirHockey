
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

    //changes the location of the puck every time it moves
    //based on if it has a collision or not.
    int Wx = 5, Hy = 5, Z = 5;

    private final int WINNER = 7;
    private final int SPEED = 20;

    private Player1 player1;
    private Player2 player2;

    //creates instance AirHockey game
    private AirHockeyGame game;
    Random rand = new Random();

    public Puck(AirHockeyGame game){
        this.game = game;
        this.player1 = game.getPlayer1();
        this.player2 = game.getPlayer2();
    }


    /**
     * controls the pucks movements as it collides with the walls and the rackets.
     * '- DIAMETER' because want the edge of puck to hit wall not center. Changes location
     * of the puck constantly
     */
    public void move(){
        //when it hits the left wall moves in positive direction (right)
        if(x + Wx < 0)
            Wx = rand.nextInt(SPEED);
        //when it hits the right wall moves in negative direction (left)
        else if(x + Wx > game.getWidth() - DIAMETER)
            Wx = -rand.nextInt(SPEED);


        //when it hits the top the game ends
        if(collideTop()) {
            if(player1.theScore() == WINNER)
                game.gameOver();
            game.restart(game);
        }
        //when it hits the bottom game ends
        else if(collideBottom()) {
            if(player2.theScore() == WINNER)
                game.gameOver();
            game.restart(game);
        }


        //HONESTLY I HAVE NO IDEA WHAT THE z IS BUT IT WORKS SO IM NOT COMPLAINING!
        if(collisionP2()) {
            Hy = 1;
            z = game.racketP2.getBottomY() - DIAMETER;
            Wx = rand.nextInt(SPEED);
        }

        else if (collisionP1()){
            Hy = -1;
            y = game.racketP1.getTopY() - DIAMETER;
            Wx = rand.nextInt(SPEED);
        }


        //changes the location of the puck
        x += Wx;
        y += Hy;
        z += Z;
    }

    /**
     * returns true if there is a collision between the racketP1 and the puck
     */
    public boolean collisionP1(){
        return game.puck.getBounds().intersects(game.racketP1.getBounds());
    }

    /**
     * returns true if there is a collision between the racketP2 and the puck
     */
    public boolean collisionP2(){
        return game.puck.getBounds().intersects(game.racketP2.getBounds());
    }

    /**
     * returns the bounds of the puck based on where it is on board
     */
    private Rectangle getBounds(){
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public boolean collideBottom(){
        return y + Hy > game.getHeight() - DIAMETER;
    }

    public boolean collideTop(){
        return y == 0;
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
