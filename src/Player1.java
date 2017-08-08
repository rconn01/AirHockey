
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Player1 {
    private int X = 250;
    private int score = 0;
    private static final int Y = 398;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private AirHockeyGame game;

    int Wx = 0;

    public Player1(AirHockeyGame game){
        this.game = game;
    }
    void paint(Graphics2D g){
        g.setColor(Color.RED);
        g.fillRect(X, Y, WIDTH, HEIGHT);
    }

    public void move(){
        if(X + Wx > 0 && X + Wx < game.getWidth() - WIDTH)
            X = X + Wx;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            Wx = -4;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            Wx = 4;
        }
    }

    public void keyReleased(KeyEvent e){
        Wx = 0;
    }

    public int getScore(){
        if(game.puck.collideTop()){
            return score++;
        }
        else{
            return score;
        }
    }

    public int theScore(){
        return score;
    }

    public Rectangle getBounds(){
        return new Rectangle(X, Y, WIDTH, HEIGHT);
    }

    public int getTopY(){
        return Y;
    }
}
