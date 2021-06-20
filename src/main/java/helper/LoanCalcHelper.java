package helper;

import entity.LumSumDetails;

import java.util.List;

public class LoanCalcHelper {

    public static int calcEmi(int principle, int rate, int loanTerm) {
        float emi = calTotalLoanAmount(principle, rate, loanTerm) / (loanTerm * 12f);

        int intEmi = (int) Math.ceil(emi);
        return intEmi;
    }

    public static float calTotalLoanAmount(int principle, int rate, int loanTerm) {
        float totalAmount = principle + calcTotalInterest(principle, rate, loanTerm);
        return totalAmount;
    }

    public static float calcTotalInterest(int principle, int rate, int loanTerm) {
        return (principle * rate * loanTerm / 100f);
    }

    public static int calcTotalPaid(int emi, List<LumSumDetails> lumSumDetails, int uptoEmi) {
        int totalPaid = emi * uptoEmi + totalLumSumPaid(lumSumDetails, uptoEmi);
        return totalPaid;
    }

    public static int emisLeft(float totalAmount, int emi, List<LumSumDetails> lumSumDetails, int uptoEmi) {
        int totalPaid = calcTotalPaid(emi, lumSumDetails, uptoEmi);

        float amountLeft = totalAmount - totalPaid;
        int emisLeft = (int) Math.ceil(amountLeft / (emi * 1f));

        return emisLeft;
    }

    public static int totalLumSumPaid(List<LumSumDetails> lumSumDetails, int uptoEmi) {
        int total = 0;
        if (lumSumDetails != null)
            for (LumSumDetails lumSumDetail : lumSumDetails) {
                if (lumSumDetail.getWhen() <= uptoEmi) {
                    total += lumSumDetail.getAmount();
                }
            }
        return total;
    }
}
