/**
 * Created by srome on 8/5/2017.
 *
 * Edits by bconn.
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Puck {
    private static final int DIAMETER = 50;
    int x = 1, y = 1, z = 1;
    int Wx = 5, Hy = 5, Z = 5;
    private AirHockeyGame game;

    public Puck(AirHockeyGame game){
        this.game = game;
    }

    public void move(){
        if(x + Wx < 0)
            Wx = game.speed;
        if(x + Wx > game.getWidth() - DIAMETER)
            Wx = -game.speed;
        if(y + Hy < 0)
            Hy = game.speed;
        if(y + Hy > game.getHeight() - DIAMETER)
            game.gameOver();
        if(y == 0)
            game.gameOver();

        if(collisionP2()) {
            Hy = 1;
            //y = game.racketP1.getTopY() - DIAMETER;
            z = game.racketP2.getBottomY() - DIAMETER;
            //game.speed++;
        }

        if (collisionP1()){
            Hy = -1;
            y = game.racketP1.getTopY() - DIAMETER;
            //z = game.racketP2.getTopY() - DIAMETER;
            //game.speed++;

        }
        x = x + Wx;
        y = y + Hy;
        z = z + Z;
    }

    public boolean collisionP1(){
        if(game.puck.getBounds().intersects(game.racketP1.getBounds())){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean collisionP2(){
        if(game.puck.getBounds().intersects(game.racketP2.getBounds())){
            return true;
        }
        else{
            return false;
        }
    }

    private Rectangle getBounds(){
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public void paint(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x, y, 50, 50);
    }
}
