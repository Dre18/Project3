package Project_3;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;

public class AccountListing extends JPanel {
    private JButton sortByAcc;
    private JButton close;
    private JButton update;
    private JButton sortLastName;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private ArrayList<Person> Alist;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField Name;
    private JTextField Address;

    private JTextField Occupation;
    private JTextField MonthlySalary;
    private JTextField DOB;
    private JTextField AccountTYpe;

    private JButton receipt;
    private JButton delete;

    /**
     * The below code is creating a GUI to show the list of persons in the bank
     * for the bank administrator to interact with.
     */
    public AccountListing() {
        super(new GridLayout(2, 1));
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        // pnlDisplay.setBounds(150, 300, 15, 30);
        pnlDisplay.setBackground(Color.LIGHT_GRAY);
        Alist = loadPersons("Bankaccounts.dat");
        String[] columnNames = { "Account Number", "Name", "Address", "TRN", "Occupation", "MonthlySalary", "DOB",
                "AccountType", "Branch", "Application Status" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(Alist);

        table.setPreferredScrollableViewportSize(new Dimension(500, Alist.size() * 15 + 50));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        add(scrollPane);

        Name = new JTextField(10);
        Address = new JTextField(10);
        Occupation = new JTextField(10);
        MonthlySalary = new JTextField(10);
        DOB = new JTextField(10);
        AccountTYpe = new JTextField(10);
        pnlDisplay.setLayout(new GridLayout(4, 0));

        pnlDisplay.add(new JLabel("New Name:"));
        pnlDisplay.add(Name);
        pnlDisplay.add(new JLabel("New Address:"));
        pnlDisplay.add(Address);
        pnlDisplay.add(new JLabel("New Occupation:"));
        pnlDisplay.add(Occupation);
        pnlDisplay.add(new JLabel("New MonthlySalary:"));
        pnlDisplay.add(MonthlySalary);
        pnlDisplay.add(new JLabel("New DOB:"));
        pnlDisplay.add(DOB);
        pnlDisplay.add(new JLabel("New Account type:"));
        pnlDisplay.add(AccountTYpe);

        receipt = new JButton("Get Receipt");
        delete = new JButton("Delete");
        sortByAcc = new JButton("Sort by Acccount Number");
        close = new JButton("Close");
        sortLastName = new JButton("Sort by Last Name");
        update = new JButton("Update");

        receipt.addActionListener(new ViewReceipt());
        receipt.setBackground(Color.lightGray);
        close.addActionListener(new CloseButtonListener());
        close.setBackground(Color.lightGray);
        sortLastName.addActionListener(new AddSortName());
        sortLastName.setBackground(Color.lightGray);
        sortByAcc.addActionListener(new AddSortAcc());
        sortByAcc.setBackground(Color.lightGray);
        update.addActionListener(new update());
        update.setBackground(Color.green);
        delete.addActionListener(new removePerson());
        delete.setBackground(Color.red);

        pnlCommand.add(sortByAcc, BorderLayout.CENTER);
        pnlCommand.add(sortLastName, BorderLayout.CENTER);
        pnlCommand.add(receipt, BorderLayout.CENTER);
        pnlCommand.add(delete, BorderLayout.CENTER);

        pnlCommand.add(update, BorderLayout.CENTER);

        pnlCommand.add(pnlDisplay, BorderLayout.PAGE_START);
        add(pnlCommand);

    }

    /**
     * @param Trn
     * @return Person
     */
    public Person getPerson(int Trn) {
        Person person = null;
        for (Person A : Alist) {
            if (Trn == A.getTRN()) {
                person = A;
            }
        }
        return person;
    }

    /**
     * @param p
     */
    private void addToBank(Person p) {
        String dob[] = new String[p.getDOB().length];
        for (int i = 0; i < p.getDOB().length; i++) {
            String s = "" + p.getDOB()[i];
            dob[i] = s;
        }
        String DOB = dob[0] + "/" + dob[1] + "/" + dob[2];
        String[] item = { "" + p.getAccountNO(), p.getName(), p.getAddress(), "" + p.getTRN(), p.getOccupation(),
                "" + p.getMonthlySalary(), DOB, "" + p.getAccountType().toString(), "" + p.getBankBranch().t(),
                p.getStatus() };
        model.addRow(item);
    }

    void createAndShowGUI() {
        JFrame frame = new JFrame("Montgomery Commercial Bank");
        AccountListing newContentPane = new AccountListing();
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * @param p
     */
    public void addPerson(Person p) {
        Alist.add(p);
        addToBank(p);

    }

    /**
     * @param pfile
     * @return ArrayList<Person>
     */
    private ArrayList<Person> loadPersons(String pfile) {
        Scanner pscan = null;
        ArrayList<Person> Alist = new ArrayList<Person>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0] + " " + nextLine[1];
                String Address = nextLine[2].replace("_", " ") + ", " + nextLine[3].replace("_", " ") + ", "
                        + nextLine[4].replace("_", " ") + ", " + nextLine[5].replace("_", " ");
                int trn = Integer.parseInt(nextLine[6]);
                String Occupation = nextLine[7].replace("_", " ");
                double MonthlySalary = Double.parseDouble(nextLine[8]);
                String[] dob1 = nextLine[9].split("/");
                int[] DOB = new int[dob1.length];
                for (int i = 0; i < dob1.length; i++) {
                    int s = Integer.parseInt(dob1[i]);
                    DOB[i] = s;
                }
                Person p = new Person(name, Address, trn, Occupation, MonthlySalary, DOB,
                        AccountType.valueOf(nextLine[10]), BankBranch.valueOf(nextLine[11]));
                Alist.add(p);

            }

            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "System Error");
        }
        return Alist;
    }

    /**
     * @param Alist
     */
    private void showTable(ArrayList<Person> Alist) {
        if (Alist.size() > 0) {
            for (Person i : Alist) {
                {
                    addToBank(i);
                }
            }
        }

    }

    /**
     * The CloseButtonListener class implements the ActionListener interface, which
     * means that it must
     * have an actionPerformed method.
     * 
     * The actionPerformed method is called when the user clicks the close button.
     * 
     * The actionPerformed method simply exits the program.
     */
    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    private class removePerson implements ActionListener {
        public void actionPerformed(ActionEvent k) {
            if (k.getSource() == delete) {
                int i = table.getSelectedRow();
                for (Person p : Alist) {
                    if (p.getTRN() == Integer.parseInt(table.getValueAt(i, 3).toString()))
                        Alist.remove(p);
                    model.setRowCount(0);
                    showTable(Alist);
                }
                String value = table.getModel().getValueAt(i, 4).toString();

                try {
                    FileReader fr = new FileReader("Bankaccount.dat");
                    BufferedReader br = new BufferedReader(fr);

                    Scanner pscan = new Scanner(new File("Bankaccount.dat"));
                    while (pscan.hasNext()) {
                        String[] nextLine = pscan.nextLine().split(" ");
                        if (nextLine[6].equals(value)) {

                        }
                        br.close();
                    }
                }

                catch (IOException e) {
                }
            }

        }
    }

    /**
     * It's a GUI that allows the user to update a record in a table.
     * </code>
     */
    private class update implements ActionListener {
        public void actionPerformed(ActionEvent n) {
            if (n.getSource() == update) {
                int i = table.getSelectedRow();
                if (Name.getText().isEmpty() == false) {
                    for (Person p : Alist) {
                        if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
                            Person U = new Person(Name.getText(), p.getAddress(), p.getTRN(), p.getOccupation(),
                                    p.getMonthlySalary(), p.getDOB(), p.getAccountType(), p.getBankBranch());
                            Alist.remove(p);
                            Alist.add(U);
                            model.setRowCount(0);
                            showTable(Alist);
                        }
                    }
                }

                if (Address.getText().isEmpty() == false) {
                    for (Person p : Alist) {
                        if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
                            Person U = new Person(p.getName(), Address.getText(), p.getTRN(), p.getOccupation(),
                                    p.getMonthlySalary(), p.getDOB(), p.getAccountType(), p.getBankBranch());
                            Alist.remove(p);
                            Alist.add(U);
                            model.setRowCount(0);
                            showTable(Alist);
                        }

                    }
                }

                if (Occupation.getText().isEmpty() == false) {
                    for (Person p : Alist) {
                        if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
                            Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), Occupation.getText(),
                                    p.getMonthlySalary(), p.getDOB(), p.getAccountType(), p.getBankBranch());
                            Alist.remove(p);
                            Alist.add(U);
                            model.setRowCount(0);
                            showTable(Alist);
                        }

                    }
                }
                if (MonthlySalary.getText().isEmpty() == false) {
                    for (Person p : Alist) {
                        if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
                            Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), p.getOccupation(),
                                    Double.parseDouble(MonthlySalary.getText()), p.getDOB(), p.getAccountType(),
                                    p.getBankBranch());
                            Alist.remove(p);
                            Alist.add(U);
                            model.setRowCount(0);
                            showTable(Alist);
                        }

                    }
                }
                if (DOB.getText().isEmpty() == false) {
                    for (Person p : Alist) {
                        if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
                            String[] DOB1 = DOB.getText().split("/");
                            int[] dob = new int[DOB1.length];
                            for (int k = 0; k < DOB1.length; k++) {
                                int s = Integer.parseInt(DOB1[k]);
                                dob[k] = s;
                            }
                            Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), p.getOccupation(),
                                    p.getMonthlySalary(), dob, p.getAccountType(), p.getBankBranch());
                            Alist.remove(p);
                            Alist.add(U);
                            model.setRowCount(0);
                            showTable(Alist);
                        }

                    }
                }
                if (AccountTYpe.getText().isEmpty() == false) {
                    for (Person p : Alist) {
                        if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
                            Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), p.getOccupation(),
                                    p.getMonthlySalary(), p.getDOB(), AccountType.valueOf(AccountTYpe.getText()),
                                    p.getBankBranch());
                            Alist.remove(p);
                            Alist.add(U);
                            model.setRowCount(0);
                            showTable(Alist);
                        }

                    }
                }

            }

        }
    }

    /**
     * It's a class that implements an ActionListener and Comparator.
     * It sorts the members of the bank in order of last name.
     */
    private class AddSortName implements ActionListener, Comparator<Person> {

        public void actionPerformed(ActionEvent m) {
            if (m.getSource() == sortLastName) {
                // Collections.reverse(Alist);
                model.setRowCount(0);
                Collections.sort(Alist, new AddSortName());
                showTable(Alist);

            }
        }

        public int compare(Person p1, Person p2) {
            return p1.getLastName().compareTo(p2.getLastName());
        }

    }

    /**
     * It's a class that implements an ActionListener and Comparator.
     * It sorts the members of the bank in account number order.
     */
    public class AddSortAcc implements ActionListener, Comparator<AccountDetails> {

        public void actionPerformed(ActionEvent n) {
            if (n.getSource() == sortByAcc) {
                model.setRowCount(0);
                Collections.sort(Alist, new AddSortAcc());
                showTable(Alist);

            }
        }

        public int compare(AccountDetails a1, AccountDetails a2) {
            return a1.getAccountNO() - a2.getAccountNO();
        }
    }

    /**
     * It's a class that implements ActionListener and has a method that creates a
     * new Receipt object
     * when the receipt button is clicked.
     */
    private class ViewReceipt implements ActionListener {
        public void actionPerformed(ActionEvent R) {
            if (R.getSource() == receipt) {

                AccountListing AL = new AccountListing();
                Receipt x = new Receipt(0, AL);
            }
        }

    }
}
