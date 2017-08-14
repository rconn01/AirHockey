import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by bryanconn on 8/14/17.
 */
public class LeaderBoard {

    JFrame LBoard = new JFrame("Leader Board");
    int LBWidth = 333;//width of each button
    int LBHeight = 120;//height of each button

    /*Dimensions for the frame*/
    int Width = 1000;
    int Height = 600;

    ArrayList<Integer> scoreList = new ArrayList<>();

    /**
     * Creates the main menu with 2 buttons and displays main rules
     */
    public LeaderBoard(int score) throws FileNotFoundException{
        LBoard.setResizable(false);
        LBoard.setSize(Width, Height);
        LBoard.setLocationRelativeTo(null);
        LBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try{
            BufferedWriter scoreList = new BufferedWriter(new FileWriter("scores.txt"));
            scoreList.write(score);
            scoreList.newLine();
        }
        catch (IOException ex){}


        BufferedReader scoreListType = new BufferedReader(new FileReader("scores.txt"));

        Box box = new Box(BoxLayout.Y_AXIS);
        LBoard.add(box, JLabel.CENTER);
        for (int i = 1; i <= 10; i++) {
            try{score = new Integer(scoreListType.readLine());}
            catch (IOException ex){}
            box.add(new Label(i + ". Score = " + score));
        }

        LBoard.setVisible(true);
    }
}
