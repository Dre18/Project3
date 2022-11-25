package Project_3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Commercial Bank’s Online Application System
 * 
 * GROUP Members...
 * André Cunningham...... 
 * Tianna-Lee Salmon.....
 * Narika Hall...........
 * Deallia Dunbar......
 */

public class Application extends JPanel {

    private JButton createAcc;
    private JButton Close;
    public JButton logIn;
    private JTable table;
    private Color panelColor;

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application newContentPane = new Application();
                    newContentPane.setOpaque(true);
                    JFrame frame = new JFrame("Montgomery Bank");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(newContentPane);
                    frame.pack();
                    frame.setVisible(true);

                    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = size.width;
                    int height = size.height;
                    frame.setSize(width, height);
                    frame.setLocationRelativeTo(null);

                } catch (Exception d) {
                    JOptionPane.showInputDialog("System Error");

                }

            }
        });
    }

    /**
     * 
     * The above code is creating a GUI for the main screen with displays the
     * companies logo .
     */
    public Application() {

        JPanel CommandPanel = new JPanel();
        JPanel displayPanel = new JPanel();
        JLabel bank;
        displayPanel.setPreferredSize(displayPanel.getToolkit().getScreenSize());
        displayPanel.setLayout(null);
        panelColor = new Color(123, 154, 239);
        displayPanel.setBackground(panelColor);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        logIn = new JButton("Admin Login");
        logIn.setBounds(300, 430, 200, 68);
        logIn.setBackground(Color.lightGray);

        createAcc = new JButton("Create Account");
        createAcc.setBounds(550, 430, 200, 68);
        createAcc.setBackground(Color.lightGray);

        Close = new JButton("Close");
        Close.setBounds(800, 430, 200, 68);
        Close.setBackground(Color.lightGray);

        Close.addActionListener(new CloseButtonListener());
        logIn.addActionListener(new LoginButtonListener());
        createAcc.addActionListener(new CreateAccButtonListener());

        try {
            BufferedImage montgomeryBank = ImageIO.read(new File("Image.png"));

            bank = new JLabel(new ImageIcon(montgomeryBank));

            bank.setBounds(400, 50, 500, 300);
            displayPanel.add(bank);
        } catch (IOException e) {
            e.getMessage();
        }

        JLabel welcomeMessage = new JLabel("Welcome to Montgomery Bank");
        welcomeMessage.setFont(new Font("Verdana", Font.BOLD, 15));
        welcomeMessage.setBounds(560, 350, 600, 68);

        displayPanel.add(welcomeMessage);
        displayPanel.add(createAcc);
        displayPanel.add(logIn);
        displayPanel.add(Close);

        add(CommandPanel);
        add(displayPanel);
        // getContentPane().add(BorderLayout.CENTER, panel);add(displaypanel);

    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    /**
     * It creates a new account from the data gathered from
     * the Createccount class.
     */
    private class CreateAccButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent f) {
            if (f.getSource() == createAcc) {
                try {
                    AccountListing newContentPane = new AccountListing();
                    newContentPane.setOpaque(true);
                    CreateAccount frame = new CreateAccount(newContentPane);
                    // newContentPane.setOpaque(true);
                    frame.setPreferredSize(new Dimension(500, 600));
                    frame.setResizable(true);
                    frame.pack();
                    frame.setVisible(true);

                } catch (Exception g) {

                    JOptionPane.showMessageDialog(createAcc, "System Error");
                }

            }
        }
    }

    /**
     * It's a class that creates a new frame when the user clicks the login button.
     * 
     */
    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logIn) {

                try {
                    Application newContentPane = new Application();
                    LoginScreen frame = new LoginScreen();
                    newContentPane.setOpaque(true);
                    frame.setTitle("Admin Login");
                    frame.setPreferredSize(new Dimension(500, 600));
                    frame.setResizable(true);
                    frame.pack();
                    frame.setVisible(true);

                } catch (Exception d) {

                }

            }

        }

    }

}