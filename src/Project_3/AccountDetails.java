package Project_3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

enum AccountType {
    Savings("Savings"), Chequing("Chequing");

    private AccountType(final String accountType) {
        this.accountType = accountType;
    }

    private String accountType;

    public String t() {
        return accountType;
    }
}

enum BankBranch {
    Portmore("Portmore, St Catherine"), NewKingston("New Kingston, Kingston"), OchoRios("Ocho Rios, St Ann"),
    MontegoBay(
            "Montego Bay, St James");

    private BankBranch(final String bankBranch) {
        this.bankBranch = bankBranch;
    }

    private String bankBranch;

    public String t() {
        return bankBranch;
    }

    public static BankBranch getname(String S) {
        BankBranch branch = null;
        if (S.equals("Portmore, St Catherine"))
            branch = BankBranch.Portmore;
        if (S.equals("New Kingston, Kingston"))
            branch = BankBranch.NewKingston;

        if (S.equals("Ocho Rios, St Ann"))
            branch = BankBranch.OchoRios;

        if (S.equals("Montego Bay, St James"))
            branch = BankBranch.MontegoBay;

        return branch;
    }
}

public class AccountDetails {

    private int TRN;
    private int accountNo;
    private AccountType accountType;
    private static int count = 100;
    private BankBranch bankBranch;
    private int i;
    private int f;

    public AccountDetails(int TRN, AccountType accountType, BankBranch bankBranch) {

        this.TRN = TRN;
        this.accountType = accountType;
        this.bankBranch = bankBranch;
        if (checkAcc("Bankaccounts.dat") == true) {

            accountNo += BranchCode(bankBranch) + count + i;
        } else
            accountNo = createAccNo() + f;
    }

    /**
     * @return int
     */
    public int getTRN() {
        return TRN;
    }

    /**
     * @return int
     */
    public int getAccountNO() {
        return accountNo;
    }

    /**
     * This function returns the account type of the account
     * 
     * @return The accountType is being returned.
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * This function returns the bank branch of the account
     * 
     * @return The bankBranch object.
     */
    public BankBranch getBankBranch() {
        return bankBranch;
    }

    /**
     * @param B
     * @return int
     */
    public int BranchCode(BankBranch B) {
        int code = 0;
        if (B.equals(BankBranch.Portmore)) {
            code = 124000000;

        }
        if (B.equals(BankBranch.NewKingston)) {
            code = 135000000;
        }
        if (B.equals(BankBranch.OchoRios)) {
            code = 168000000;
        }
        if (B.equals(BankBranch.MontegoBay)) {
            code = 179000000;
        }
        return code;
    }

    /**
     * @return int
     */
    public int createAccNo() {
        count++;
        int AccNo = BranchCode(bankBranch) + count;
        return AccNo;
    }

    /**
     * @param pfile
     * @return boolean
     */
    public boolean checkAcc(String pfile) {
        boolean b = false;
        Scanner pscan = null;
        int a = -1;
        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                a++;
                f = a;
                String[] nextLine = pscan.nextLine().split(" ");
                int trn = Integer.parseInt(nextLine[6]);
                if (trn == TRN) {
                    i = a;
                    b = true;
                }

            }
            pscan.close();
        } catch (IOException e) {
        }
        return b;
    }

}
