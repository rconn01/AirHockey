/**
 * Created by Sam Romeo and edited by Bryan Conn.
 * Started 8/5/17.
 * Creates a new Air Hockey Game that has to be played
 * by two players.
 */
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
    /** The puck being used to play. */
    public Puck puck;

    private char type;

    /** The first player. */
    private Player1 racketP1 = new Player1(this);

    /** The second player. */
    private Player2 racketP2 = new Player2(this);

    /**
     * Allows for access to the games first player outside of the file.
     */
    public Player1 getPlayer1(){
        return racketP1;
    }

    /**
     * Allows for access to the games second player outside of the file.
     */
    public Player2 getPlayer2(){
        return racketP2;
    }

    /**
     * Allows for access to the games puck outside of the file.
     */
    public Puck getPuck(){
        return puck;
    }

    public char getType(){
        return type;
    }

    /**
     * Determines which key is pressed and then acts on the
     * action required.
     *
     * @param e The key that was pressed.
     */
    public void keyPressed(KeyEvent e){
        racketP1.keyPressed(e);
        if(getType() == 'D'){
            racketP2.keyPressed(e);
        }
    }

    /**
     * Determines what key is released.
     *
     * @param e Determines what key is released.
     */
    public void keyReleased(KeyEvent e){
        racketP1.keyReleased(e);
        if(getType() == 'D'){
            racketP2.keyReleased(e);
        }
    }

    /**
     * Creates the game and sets the listeners and the focuses.
     */
    public AirHockeyGame(char c){
        addKeyListener(this);
        setFocusable(true);
        this.type = c;
    }

    /**
     * Sets the coloring of the puck, and both paddles.
     * Sets the coloring and fonts of both scores.
     *
     * @param g The graphics access.
     */
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        puck.paint(g2d);
        racketP1.paint(g2d);
        if(getType() == 'D'){
            racketP2.paint(g2d);
            g2d.setColor(Color.BLUE);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            g2d.drawString(String.valueOf(racketP2.getScore()), 10, 30);
        }
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 25));
        g2d.drawString(String.valueOf(racketP1.getScore()), 10, 415);
    }

    /**
     * Starts both paddles and the puck moving.
     */
    public void gameMoves(){
        racketP1.move();
        if(getType() == 'D'){
            racketP2.move();
        }
        puck.move();
    }

    /**
     * Reports that the game has ended in a popup window, with the score
     * of both players and then exits the screen.
     */
    public void gameOver(){
        if(getType() == 'D')
            JOptionPane.showMessageDialog(this, "Player1 score is: " + racketP1.getScore() + "\nPlayer2 score is: " + racketP2.getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
        else if(getType() == 'S')
            JOptionPane.showMessageDialog(this, "Player1 score is: " + racketP1.getScore() , "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    /* This could use your better explanation in comments through it. */
    /**
     * Creates a screen than plays the game out on it.
     //@param args
     * @throws InterruptedException
     */
    //public static void main(String[] args) throws InterruptedException{
    public void start() {
        JFrame frame = new JFrame("Air Hockey Game");
        frame.setSize(500, 500);
        this.puck = new Puck(this);
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            this.gameMoves();
            this.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    /**
     * Restarts the puck, used to let the game run
     * until a score is met.
     *
     * @param game The game being played.
     */
    public void restart(AirHockeyGame game){
        puck = new Puck(game);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}


