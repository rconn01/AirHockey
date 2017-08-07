import javax.swing.*;
import java.awt.*;

public class MainMenu {

    JFrame Menu = new JFrame("AirHockey");
    JButton Start = new JButton("Play");
    JButton Exit = new JButton("Exit");
    ImageIcon pic = new ImageIcon("redblue.png");
    JLabel imageLabel = new JLabel(pic);

    int menuWidth = 250;//size of each button
    int menuHeight = 60;//width of each button
    int menuY = 189;//y value of where button will be placed
    int Width = 500;
    int Height = 300;

    public MainMenu(){
        //Menu Variables
        Menu.setResizable(false);
        Menu.setSize(Width, Height);
        Menu.setLayout(null);
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Play Button
        Start.setSize(menuWidth,menuHeight);
        Start.setLocation(0, menuY);
        Start.setFont(new Font("Arial", Font.BOLD, 40));
        Start.setBackground(Color.RED);
        Menu.add(Start);
        Start.addActionListener(e -> new AirHockeyGame().start());

        //Exit Button Variables
        Exit.setSize(menuWidth,menuHeight);
        Exit.setLocation(255,menuY);
        Exit.setFont(new Font("Arial", Font.BOLD, 40));
        Exit.setBackground(Color.BLUE);
        Menu.add(Exit);
        Exit.addActionListener(e -> System.exit(0));

        //Display Picture
        imageLabel.setBounds(0,40,40,20);
        imageLabel.setVisible(true);
        Menu.add(imageLabel);
        Menu.setVisible(true);

    }

    public static void main(String[] args) {
        new MainMenu();
    }

}
