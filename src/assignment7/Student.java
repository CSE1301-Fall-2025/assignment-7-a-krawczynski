package assignment7;

public class Student {
    private String firstName;
    private String lastName;
    private int ID;
    private int attemptedCredits;
    private int passingCredits;
    private double totalGradeQualityPoints;
    private double bearBucksBalance;
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return ID;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public int getPassingCredits() {
        return passingCredits;
    }

    public double getTotalGradeQualityPoints() {
        return totalGradeQualityPoints;
    }

    public double getBearBucksBalance() {
        return bearBucksBalance;
    }
    
    public Student(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = id;
    }

    public String getClassStanding() {
        if (passingCredits < 30) {
            return "First Year";
        }
        if (passingCredits >= 30 && passingCredits < 60) {
            return "Sophomore";
        }
        if (passingCredits >= 60 && passingCredits < 90) {
            return "Junior";
        }
        return "Senior";
    }

    public Boolean isEligibleForPhiBetaKappa() {
        if (passingCredits >= 98 && calculateGradePointAverage() >= 3.6) {
            return true;
        }
        if (passingCredits >= 75 && calculateGradePointAverage() >= 3.8) {
            return true;
        }
        return false;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public double deductBearBucks(double balance) {
        bearBucksBalance = bearBucksBalance - balance;
        return bearBucksBalance;
    }

    public double depositBearBucks(double balance) {
        bearBucksBalance = bearBucksBalance + balance;
        return bearBucksBalance;
    }

    public double cashOutBearBucks() {
        double payout;
        if (bearBucksBalance <= 10.0) {
            payout = 0.0;
        } else {
            payout = bearBucksBalance - 10.0;
        }
        
        bearBucksBalance = 0.0;  // reset balance after cashing out
        return payout;
    }

    public void submitGrade(double grade, int credits) {
        this.attemptedCredits += credits;
        this.totalGradeQualityPoints += grade*credits;
        
        if (grade > 1.7) {
            this.passingCredits += credits;
        }
    }

    public double calculateGradePointAverage() {
        if (attemptedCredits == 0) return Double.NaN;
        return totalGradeQualityPoints/attemptedCredits;
    }

    public Student createLegacy(String firstName, Student otherParent, boolean isHyphenated, int id) {
    // Determine last name
        String legacyLastName;
            if (isHyphenated) {
                legacyLastName = this.getLastName() + "-" + otherParent.getLastName();
        } else {
            legacyLastName = this.getLastName();
        }

    // Create the child
        Student child = new Student(firstName, legacyLastName, id);

    // Cash out parents and give child the combined total
        double amountThis = this.cashOutBearBucks();
        double amountOther = otherParent.cashOutBearBucks();
        double combined = amountThis + amountOther;

        child.depositBearBucks(combined);

        return child;
}

@Override
public String toString() {
    return getFullName() + " (ID: " + this.ID + ")";
}

}
