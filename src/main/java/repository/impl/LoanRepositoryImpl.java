package repository.impl;

import entity.Loan;
import entity.LumSumDetails;
import repository.LoanRepository;

import java.util.HashMap;
import java.util.Map;

public class LoanRepositoryImpl implements LoanRepository {

    Map<String, Map<String, Loan>> loanDB = new HashMap<>();

    @Override
    public Loan getLoanFromBankAndBorrowerId(String bank, String borrower) {
        if (!loanDB.containsKey(bank) || !loanDB.get(bank).containsKey(borrower)) return null;

        return loanDB.get(bank).get(borrower);
    }

    @Override
    public void addLumSumToLoan(String bank, String borrower, LumSumDetails lumSumDetails) {
        if (!loanDB.containsKey(bank) || !loanDB.get(bank).containsKey(borrower)) return;

        loanDB.get(bank).get(borrower).addToLumSumList(lumSumDetails);
    }

    @Override
    public void createNewLoan(Loan loan) {
        String bank = loan.getBankName();
        String borrower = loan.getCustomerName();

        if (!loanDB.containsKey(bank))
            loanDB.put(bank, new HashMap<>());

        if (!loanDB.get(bank).containsKey(borrower)) {
            loanDB.get(bank).put(borrower, loan);
        }
    }
}
