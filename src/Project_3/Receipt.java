package Project_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Receipt {
    private JPanel panel;
    private JButton enterButton;
    private JButton close;
    private JPanel Rpanel;
    private JTextField trnNum;
    private Receipt thisReceipt;
    private JFrame frame;
    private JTextArea receipt;
    private int trn;
    AccountListing accountlisting;
    private Color panelColor;

    // The below code is creating a receipt for a user.
    public Receipt(int TRN, AccountListing Alisting) {
        thisReceipt = this;
        trn = TRN;
        accountlisting = Alisting;
        frame = new JFrame("User Receipt");
        frame.setSize(frame.getToolkit().getScreenSize());

        frame.setVisible(true);
        receipt = new JTextArea("", 36, 5);
        Rpanel = new JPanel();
        Rpanel.setSize(Rpanel.getToolkit().getScreenSize());
        close = new JButton("Close");
        close.addActionListener(new CloseButtonListener());
        Rpanel.add(close, BorderLayout.PAGE_END);

        if (trn == 0) {
            panel = new JPanel();
            JLabel label = new JLabel("Enter TRN to view Receipt:");
            trnNum = new JTextField(8);

            panel.add(label);
            panel.add(trnNum);
            panelColor = new Color(123, 154, 239);
            panel.setBackground(panelColor);

            enterButton = new JButton("Enter");
            enterButton.addActionListener(new Enterbutton());
            enterButton.setBackground(Color.lightGray);
            panel.add(enterButton);

            frame.getContentPane().add(BorderLayout.PAGE_START, panel);
        } else {

            receipt.setEditable(false);
            receipt.setBounds(10, 70, 290, 80);
            receipt.setText(showReceipt());

            Rpanel.add(receipt);
            frame.getContentPane().add(BorderLayout.CENTER, Rpanel);
            try {
                AccountListing n = new AccountListing();
                File myObj = new File("Receipts\\" + accountlisting.getPerson(trn).getAccountNO() + ".txt");
                if (myObj.createNewFile()) {
                    FileWriter f = new FileWriter("Receipts\\" + accountlisting.getPerson(trn).getAccountNO() + ".txt",
                            true);
                    BufferedWriter b = new BufferedWriter(f);
                    PrintWriter w = new PrintWriter(b);
                    w.println(receipt.getText());
                    w.close();
                }
            } catch (IOException o) {

            }
        }
    }

    /**
     * @return JTextArea
     */
    public JTextArea getReceipt() {
        return receipt;
    }

    /**
     * @return String
     */
    public String showReceipt() {

        String str = "";
        str = "===================================================\n";
        if (accountlisting.getPerson(trn).getTRN() != trn) {
            str += "           PERSON WAS NOT FOUND                    \n ";
            str += "===================================================\n";
        } else if (accountlisting.getPerson(trn).appStat() == true) {
            str += "     ==Your New Account Was Successfully Created==\n";
            str += "===================================================\n";
            str += "     ========= Montgomery Commercial Bank ========\n";
            str += "Branch:                      " + accountlisting.getPerson(trn).getBankBranch() + "\n";
            str += "Account Holder:        " + accountlisting.getPerson(trn).getName() + "\n";
            str += "Account#:                    " + accountlisting.getPerson(trn).getAccountNO() + "\n";
            str += "Account type:                " + accountlisting.getPerson(trn).getAccountType() + "\n";
            str += "===================================================\n";
        } else {
            str += "            ========= Montgomery Commercial Bank ========\n";
            str += "==Your Application for a " + accountlisting.getPerson(trn).getAccountType()
                    + " account could not be approved==\n";
            str += "===================================================\n";
            str += "           Please contact our customer service line via 888-333-5555\n";
            str += "                                                       OR \n";
            str += "           email us at Montgomery.CustomerService@gmail.com\n";
            str += "                                             to rectify this issue\n";
            str += "===================================================\n";
        }
        return str;
    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
        }

    }

    /**
     * It's a class that implements ActionListener and has a method that takes the
     * text from a
     * textfield and sets it to a variable.
     * 
     * 
     * 
     * 
     */
    private class Enterbutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == enterButton) {
                int T = Integer.parseInt(trnNum.getText());
                trn = T;

                receipt = getReceipt();
                receipt.setEditable(false);
                receipt.setBounds(10, 70, 290, 80);
                receipt.setText(showReceipt());

                Rpanel.add(receipt);
                frame.getContentPane().add(BorderLayout.CENTER, Rpanel);

            }
        }
    }
}
