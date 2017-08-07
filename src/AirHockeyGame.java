import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class AirHockeyGame extends JPanel implements KeyListener{
    Puck puck = new Puck(this);
    Player1 racketP1 = new Player1(this);
    Player2 racketP2 = new Player2(this);
    int speed = 1;

    private int getScore(){
        return speed - 1;
    }

    /**
     * method for making sure when the designated button is pressed an action occurs
     * @param e event that occurs
     */
    public void keyPressed(KeyEvent e){
        racketP1.keyPressed(e);
        racketP2.keyPressed(e);
    }

    /**
     * method
     * @param e
     */
    public void keyReleased(KeyEvent e){
        racketP1.keyReleased(e);
        racketP2.keyReleased(e);
    }

    public AirHockeyGame(){
        addKeyListener(this);
        setFocusable(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        puck.paint(g2d);
        racketP1.paint(g2d);
        racketP2.paint(g2d);
        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font("Arial", Font.BOLD, 25));
        g2d.drawString(String.valueOf(racketP2.getScore()), 10, 30);
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 25));
        g2d.drawString(String.valueOf(racketP1.getScore()), 10, 415);
    }

    public void gameMoves(){
        racketP1.move();
        racketP2.move();
        puck.move();
    }

    public void gameOver(){
        JOptionPane.showMessageDialog(this, "Player1 score is: " + racketP1.getScore() + "\nPlayer2 score is: " + racketP2.getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException{
        JFrame frame = new JFrame("Air Hockey Game");
        frame.setSize(500, 500);
        AirHockeyGame myGame = new AirHockeyGame();
        frame.add(myGame);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            myGame.gameMoves();
            myGame.repaint();
            Thread.sleep(10);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}


