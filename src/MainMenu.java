import javax.swing.*;
import java.awt.*;
/**
 * Created on 8/5/17
 * Creates a main menu page before the game is actually played. Allows player to exit or play the game.
 */
public class MainMenu {

    JFrame Menu = new JFrame("AirHockey");
    JButton Single = new JButton("Single");
    JButton Double = new JButton("Double");
    JButton Rules = new JButton("Rules");
    JLabel label = new JLabel("Air Hockey", JLabel.CENTER);

    int menuWidth = 333;//width of each button
    int menuHeight = 120;//height of each button
    int menuY = 430;//y value of where button will be placed
    /*Dimensions for the frame*/
    int Width = 1000;
    int Height = 600;

    /**
     * Creates the main menu with 2 buttons and displays main rules
     */
    public MainMenu() {

        //Menu Variables
        Menu.setResizable(false);
        Menu.setSize(Width, Height);
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Single Button
        Single.setSize(menuWidth, menuHeight);
        Single.setLocation(0,menuY);
        Single.setFont(new Font("Stencil", Font.PLAIN, 45));
        Menu.add(Single);
        Single.addActionListener(e -> {
                    new Thread(() -> new AirHockeyGame('S').start()).start();
                    Menu.setVisible(false);
                });

        //Double Button
        Double.setSize(menuWidth, menuHeight);
        Double.setLocation(338, menuY);
        Double.setFont(new Font("Stencil", Font.PLAIN, 45));
        Menu.add(Double);
        Double.addActionListener(e -> {
            new Thread(()-> new AirHockeyGame('D').start()).start();
            Menu.setVisible(false);
        });

        //Rules Button Variables
        Rules.setSize(menuWidth, menuHeight);
        Rules.setLocation(676,menuY);
        Rules.setFont(new Font("Stencil", Font.PLAIN, 45));
        Menu.add(Rules);
        Rules.addActionListener(e -> {
            JFrame rulesMenu = new JFrame("Rules");
            JButton ok = new JButton("OK");
            rulesMenu.setSize(Width, Height);
            JTextArea text = new JTextArea("Double: Try to be the first to 7 points by having the puck hit\n              the other players side. \n\nSingle: Try to score as many points by preventing the puck from\n             hitting the bottom.");
            text.setFont(new Font("Arial", Font.PLAIN, 30));
            ok.setFont(new Font("Stencil", Font.PLAIN, 45));
            ok.setSize(menuWidth, menuHeight);
            ok.setLocation(330, 380);
            ok.addActionListener(a -> rulesMenu.setVisible(false));
            rulesMenu.add(ok);
            rulesMenu.setLocationRelativeTo(null);
            rulesMenu.add(text);
            rulesMenu.setVisible(true);
        });

        //Display label
        label.setSize(menuWidth, menuHeight);
        label.setLocation(5,5);
        label.setFont(new Font("Stencil", Font.ITALIC, 150));
        Menu.add(label);

        //Displays the menu
        Menu.setVisible(true);

    }

    public static void main(String[] args) throws InterruptedException{
        new MainMenu();
    }

}
