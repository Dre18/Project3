package Project_3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateAccount extends JFrame implements ItemListener {
    private JButton DoneButton;
    private JButton Close;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private CreateAccount thisFrame;
    private JTextField txtName;
    private JTextField DOB;
    private JTextField TRN;
    private JTextField TelephoneNO;
    private JTextField Addressline1;
    private JTextField Addressline2;
    private JTextField parish;
    private JTextField occupation;
    private JTextField country;
    private JTextField monthlySalary;
    private JLabel opt1;
    private JLabel opt2;
    private JLabel opt3;
    private JLabel opt4;
    private JComboBox Title;
    private JCheckBox Female;
    private JCheckBox Male;
    private JComboBox PhoneType;
    private JComboBox<BankBranch> Branch;
    private JComboBox<AccountType> accountType;
    private AccountListing aListing;

    /**
     * The below code is creating a GUI for the user to input data.
     */
    public CreateAccount(AccountListing aListing) {
        thisFrame = this;
        this.setResizable(false);
        this.aListing = aListing;
        setTitle("Montgomery Commercial Bank");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        Dimension pnlSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pnlSize.width;
        int height = pnlSize.height;
        pnlDisplay.setSize(width / 2, height / 2);

        JPanel A = new JPanel();
        A.setLayout(new FlowLayout());
        A.add(new JLabel("Title:"));
        String T[] = { "Mr", "Mrs", "Miss" };
        Title = new JComboBox<>(T);
        Title.setSelectedIndex(-1);
        Title.addItemListener(thisFrame);
        opt4 = new JLabel("none is selected");
        A.add(Title);
        A.add(opt4);
        pnlDisplay.add(A);

        JPanel B = new JPanel();
        B.setLayout(new FlowLayout());
        B.add(new JLabel("Name:"));
        txtName = new JTextField(20);
        B.add(txtName);
        B.add(new JLabel("First & Last name"));
        pnlDisplay.add(B);

        JPanel C = new JPanel();
        C.setLayout(new FlowLayout());
        C.add(new JLabel("Gender:"));
        C.add(new JLabel("Male"));
        Male = new JCheckBox();
        C.add(Male);
        C.add(new JLabel("Female"));
        Female = new JCheckBox();
        ButtonGroup cBoxGroup = new ButtonGroup();
        cBoxGroup.add(Male);
        cBoxGroup.add(Female);
        C.add(Female);
        pnlDisplay.add(C);

        JPanel D = new JPanel();
        D.setLayout(new FlowLayout());
        D.add(new JLabel("Account Type:"));
        accountType = new JComboBox<>();
        accountType.setModel(new DefaultComboBoxModel<>(AccountType.values()));
        accountType.setSelectedIndex(-1);
        accountType.addItemListener(thisFrame);
        opt1 = new JLabel("none is selected");
        D.add(accountType);
        D.add(opt1);
        pnlDisplay.add(D);

        JPanel E = new JPanel();
        E.setLayout(new FlowLayout());
        E.add(new JLabel("Bank Branch location:"));
        Branch = new JComboBox<>();
        Branch.setModel(new DefaultComboBoxModel<>(BankBranch.values()));
        Branch.setSelectedIndex(-1);
        Branch.addItemListener(thisFrame);
        opt2 = new JLabel("none is selected");
        E.add(Branch);
        E.add(opt2);
        pnlDisplay.add(E);

        JPanel F = new JPanel();
        F.setLayout(new FlowLayout());
        F.add(new JLabel("Occupation:"));
        occupation = new JTextField(20);
        F.add(occupation);
        pnlDisplay.add(F);

        JPanel G = new JPanel();
        G.setLayout(new FlowLayout());
        G.add(new JLabel("Monthly Salary:"));
        monthlySalary = new JTextField(20);
        G.add(monthlySalary);
        pnlDisplay.add(G);

        JPanel H = new JPanel();
        H.setLayout(new FlowLayout());
        H.add(new JLabel("DOB (dd/mm/yyyy):"));
        DOB = new JTextField(10);
        H.add(DOB);
        pnlDisplay.add(H);

        JPanel I = new JPanel();
        I.setLayout(new FlowLayout());
        I.add(new JLabel("TRN (NO SPACES):"));
        TRN = new JTextField(9);
        I.add(TRN);
        pnlDisplay.add(I);

        JPanel J = new JPanel();
        J.setLayout(new FlowLayout());
        J.add(new JLabel("Phone Type (NO SPACES):"));
        String P[] = { "Mobile", "Work", "Home" };
        PhoneType = new JComboBox<>(P);
        PhoneType.setSelectedIndex(-1);
        PhoneType.addItemListener(thisFrame);
        opt3 = new JLabel("none is selected");
        J.add(PhoneType);
        J.add(opt3);
        pnlDisplay.add(J);

        JPanel K = new JPanel();
        K.setLayout(new FlowLayout());
        K.add(new JLabel("Telephone (NO SPACES): (+1)"));
        TelephoneNO = new JTextField(10);
        K.add(TelephoneNO);
        pnlDisplay.add(K);

        JPanel L = new JPanel();
        L.setLayout(new FlowLayout());
        L.add(new JLabel("Addressline 1:"));
        Addressline1 = new JTextField(20);
        L.add(Addressline1);
        pnlDisplay.add(L);

        JPanel M = new JPanel();
        M.setLayout(new FlowLayout());
        M.add(new JLabel("Address line 2:"));
        Addressline2 = new JTextField(20);
        M.add(Addressline2);
        pnlDisplay.add(M);

        JPanel N = new JPanel();
        N.setLayout(new FlowLayout());
        N.add(new JLabel("Parish:"));
        parish = new JTextField(20);
        N.add(parish);
        pnlDisplay.add(N);

        JPanel O = new JPanel();
        O.setLayout(new FlowLayout());

        O.add(new JLabel("Country:"));
        country = new JTextField(20);
        O.add(country);
        pnlDisplay.add(O);

        pnlDisplay.setLayout(new BoxLayout(pnlDisplay, BoxLayout.Y_AXIS));

        DoneButton = new JButton("Done");
        DoneButton.setBackground(Color.lightGray);

        Close = new JButton("Close");
        Close.setBackground(Color.lightGray);

        ButtonListener buttonListener = new ButtonListener();
        Close.addActionListener(buttonListener);
        DoneButton.addActionListener(new DoneButtonListener());

        pnlCommand.add(DoneButton);
        pnlCommand.add(Close);

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        setVisible(true);

    }

    /**
     * It's a class that creates a GUI for a user to input information into a text
     * file after
     * form is filled out.
     * 
     */
    private class DoneButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent d) {
            if (d.getSource() == DoneButton) {
                try {
                    String name = txtName.getText();

                    String[] Ad1 = Addressline1.getText().trim().split(" ");
                    String[] Ad2 = Addressline2.getText().trim().split(" ");
                    String[] par = parish.getText().trim().split(" ");
                    String[] cntry = country.getText().trim().split(" ");
                    String Address = String.join("_", Ad1) + ", " + String.join("_", Ad2) + ", " + String.join("_", par)
                            + ", " + String.join("_", cntry);
                    String Occupation = occupation.getText().trim().replace(" ", "_");
                    String MS = monthlySalary.getText().trim().replace(",", "");
                    double MonthlySalary = Double.parseDouble(MS);
                    int trn = Integer.parseInt(TRN.getText());
                    String[] dob1 = DOB.getText().split("/");
                    AccountType accountType1 = (AccountType) accountType.getSelectedItem();
                    BankBranch branch1 = (BankBranch) Branch.getSelectedItem();
                    int[] Dob2 = new int[dob1.length];
                    for (int i = 0; i < dob1.length; i++) {
                        int s = Integer.parseInt(dob1[i]);
                        Dob2[i] = s;
                    }

                    Person p = new Person(name, Address, trn, Occupation, MonthlySalary, Dob2, accountType1, branch1);
                    aListing.addPerson(p);

                    String dob[] = new String[p.getDOB().length];
                    for (int i = 0; i < p.getDOB().length; i++) {
                        String s = "" + p.getDOB()[i];
                        dob[i] = s;
                    }
                    String DOB = dob[0] + "/" + dob[1] + "/" + dob[2];
                    FileWriter f = new FileWriter("Bankaccounts.dat", true);
                    BufferedWriter b = new BufferedWriter(f);
                    PrintWriter w = new PrintWriter(b);
                    w.println(p.getName() + " " + p.getAddress().replace(",", "") + " " + p.getTRN() + " "
                            + p.getOccupation() + " " + p.getMonthlySalary() + " " + DOB + " " + "" + p.getAccountType()
                            + " " + "" + p.getBankBranch());
                    w.close();

                    thisFrame.setVisible(false);

                    Receipt frame = new Receipt(trn, aListing);

                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(DoneButton, "No spacing for:\n TRN, Monthly Salary & Telephone");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(DoneButton, "Incomplete Fields");
                } catch (Exception l) {
                    JOptionPane.showMessageDialog(DoneButton,
                            "Please Close Application\nIf problem persists contact the application creator");
                }

            }
        }

    }

    public class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Close)

                thisFrame.setVisible(false);

        }
    }

    /**
     * @param e
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == accountType) {

            opt1.setText(accountType.getSelectedItem() + " selected");
        }
        if (e.getSource() == Branch) {
            opt2.setText(Branch.getSelectedItem() + " selected");
        }
        if (e.getSource() == PhoneType) {
            opt3.setText(PhoneType.getSelectedItem() + " selected");
        }
        if (e.getSource() == Title) {
            opt4.setText(Title.getSelectedItem() + " selected");
        }

    }

}
