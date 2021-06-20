package service;

public interface LoanService {

    void addPaymentToLoan(String[] data);

    String getBalance(String[] data);

    void createLoanInSystem(String[] data);
}
