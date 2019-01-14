package objects;

public class Loan {
    private String LoanAmount;
    private String APR;
    private String LoanTerm;
    private String MonthlyPayment;

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getAPR() {
        return APR;
    }

    public void setAPR(String APR) {
        this.APR = APR;
    }

    public String getLoanTerm() {
        return LoanTerm;
    }

    public void setLoanTerm(String loanTerm) {
        LoanTerm = loanTerm;
    }

    public String getMonthlyPayment() {
        return MonthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        MonthlyPayment = monthlyPayment;
    }
}
