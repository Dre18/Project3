package Project_3;

public class Person extends AccountDetails {
    private String name;
    private String address;
    private int TRN;
    private String occupation;
    private double monthlysalary;
    private int[] DOB;

    // A constructor that takes in the parameters and sets them to the variables.
    public Person(String name, String address, int TRN, String occupation, double monthlysalary, int[] DOB,
            AccountType accountType, BankBranch bankBranch) {
        super(TRN, accountType, bankBranch);

        this.name = name;
        this.address = address;

        this.TRN = TRN;
        this.occupation = occupation;
        this.monthlysalary = monthlysalary;
        this.DOB = DOB;

    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return name.split(" ")[0];
    }

    public String getLastName() {
        return name.split(" ")[1];
    }

    public String getAddress() {
        return address;
    }

    public int getTRN() {
        return TRN;
    }

    public String getOccupation() {
        return occupation;
    }

    public double getMonthlySalary() {
        return monthlysalary;
    }

    public int[] getDOB() {
        return DOB;
    }

    public String toString() {
        String str = "";
        return str;
    }

    public boolean appStat() {
        boolean status = false;

        if (DOB[2] < 2004) {
            status = true;
        } else {
            if (DOB[2] == 2004) {
                if (DOB[1] <= 4) {
                    status = true;
                } else
                    status = false;
            }
        }
        return status;

    }

    public String getStatus() {
        String Status;
        if (appStat() == true) {
            Status = "Approved";

        } else {
            Status = "Denied";
        }
        return Status;
    }
}
