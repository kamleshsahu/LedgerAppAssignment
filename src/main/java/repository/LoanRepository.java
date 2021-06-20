package repository;

import entity.Loan;
import entity.LumSumDetails;

public interface LoanRepository {

    Loan getLoanFromBankAndBorrowerId(String bank, String borrower);

    void addLumSumToLoan(String bank, String borrower, LumSumDetails lumSumDetails);

    void createNewLoan(Loan loan);
}
