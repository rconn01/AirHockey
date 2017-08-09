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
    JButton Exit = new JButton("Exit");
    ImageIcon pic = new ImageIcon("redblue.png");
    JLabel imageLabel = new JLabel(pic);

    int menuWidth = 166;//size of each button
    int menuHeight = 60;//width of each button
    int menuY = 189;//y value of where button will be placed
    /*Dimensions for the frame*/
    int Width = 500;
    int Height = 300;

    /**
     * Creates the main menu with 2 buttons and displays main picture
     */
    public MainMenu() {
        //Menu Variables
        Menu.setResizable(false);
        Menu.setSize(Width, Height);
        //Menu.setLayout(null);
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Single Button
        Single.setSize(menuWidth, menuHeight);
        Single.setLocation(0,menuY);
        Single.setFont(new Font("Arial", Font.BOLD, 25));
        Menu.add(Single);
        Single.addActionListener(e -> {
                    new Thread(() -> new AirHockeyGame('S').start()).start();
                    Menu.setVisible(false);
                });

        //Double Button
        Double.setSize(menuWidth,menuHeight);
        Double.setLocation(171, menuY);
        Double.setFont(new Font("Arial", Font.BOLD, 25));
        //Double.setBackground(Color.RED);
        Menu.add(Double);
        Double.addActionListener(e -> {
            new Thread(()-> new AirHockeyGame('D').start()).start();
            Menu.setVisible(false);
        });

        //Exit Button Variables
        Exit.setSize(menuWidth,menuHeight);
        Exit.setLocation(342,menuY);
        Exit.setFont(new Font("Arial", Font.BOLD, 25));
        //Exit.setBackground(Color.BLUE);
        Menu.add(Exit);
        Exit.addActionListener(e -> System.exit(0));

        //Display Picture
        Menu.add(imageLabel);
        imageLabel.setBounds(0,0,320,166);
        imageLabel.setVisible(true);
        //Menu.setBackground(Color.BLACK);
        Menu.setVisible(true);

    }

    public static void main(String[] args) throws InterruptedException{
        new MainMenu();
    }

}
