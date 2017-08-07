import java.awt.*;
import java.awt.event.KeyEvent;


public class Player2 {
    private int X = 250;
    private int score = 0;
    private static final int Y = 0;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private AirHockeyGame game;

    int Wx = 0;

    public Player2(AirHockeyGame game){
        this.game = game;
    }
    void paint(Graphics2D g){
        g.setColor(Color.BLUE);
        g.fillRect(X, Y, WIDTH, HEIGHT);
    }

    public void move(){
        if(X + Wx > 0 && X + Wx < game.getWidth() - WIDTH)
            X = X + Wx;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_A){
            Wx = -4;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            Wx = 4;
        }
    }

    public void keyReleased(KeyEvent e){
        Wx = 0;
    }

    public int getScore(){
        if(game.puck.collisionP2()){
            return score++;
        }
        else{
            return score;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(X, Y, WIDTH, HEIGHT);
    }

    public int getBottomY(){
        return Y - HEIGHT;
    }
}
