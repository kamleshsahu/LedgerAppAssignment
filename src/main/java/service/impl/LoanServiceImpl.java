package service.impl;

import repository.LoanRepository;
import repository.impl.LoanRepositoryImpl;
import service.LoanService;
import entity.Loan;
import entity.LumSumDetails;
import helper.LoanCalcHelper;

import static helper.LoanCalcHelper.*;


public class LoanServiceImpl implements LoanService {

    LoanRepository loanRepository;

    public LoanServiceImpl() {
        loanRepository = new LoanRepositoryImpl();
    }


    @Override
    public void addPaymentToLoan(String[] data) {
        String bank = data[1];
        String customer = data[2];

        loanRepository.addLumSumToLoan(bank, customer, getLumSumObject(data));
    }

    @Override
    public String getBalance(String[] data) {
        String bank = data[1];
        String customer = data[2];
        int emiNo = Integer.parseInt(data[3]);

        Loan loan = loanRepository.getLoanFromBankAndBorrowerId(bank, customer);

        int amountPaid = LoanCalcHelper.calcTotalPaid(loan.getEmi(), loan.getLumSumDetailsList(), emiNo);
        amountPaid = (int) Math.min(amountPaid, loan.getTotalAmount());
        int emisLeft = LoanCalcHelper.emisLeft(loan.getTotalAmount(), loan.getEmi(), loan.getLumSumDetailsList(), emiNo);
        emisLeft = Math.max(0, emisLeft);

        return String.format("%s %s %s %s", bank, customer, amountPaid, emisLeft);
    }

    @Override
    public void createLoanInSystem(String[] data) {
        loanRepository.createNewLoan(getLoanObject(data));
    }

    private LumSumDetails getLumSumObject(String[] data) {
        int lumSumAmount = Integer.parseInt(data[3]);
        int emiNo = Integer.parseInt(data[4]);

        return LumSumDetails.builder().amount(lumSumAmount).when(emiNo).build();
    }

    private Loan getLoanObject(String[] data) {
        String bankName = data[1];
        String customerName = data[2];
        int rateOfInterest = Integer.parseInt(data[5]);
        int principle = Integer.parseInt(data[3]);
        int loanTerm = Integer.parseInt(data[4]);
        int emi = calcEmi(principle, rateOfInterest, loanTerm);
        float totalAmount = calTotalLoanAmount(principle, rateOfInterest, loanTerm);

        return Loan.builder()
                .bankName(bankName)
                .customerName(customerName)
                .rateOfInterest(rateOfInterest)
                .principle(principle)
                .loanTerm(loanTerm)
                .emi(emi)
                .totalAmount(totalAmount)
                .build();
    }
}
