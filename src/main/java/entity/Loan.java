package entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class Loan {

    String bankName;
    String customerName;
    int rateOfInterest;
    int principle;
    int emi ;
    float totalAmount;
    int loanTerm;
    List<LumSumDetails> lumSumDetailsList;

    public void addToLumSumList(LumSumDetails lumSumDetails){
        if(lumSumDetailsList == null)
            lumSumDetailsList = new ArrayList<LumSumDetails>();
        lumSumDetailsList.add(lumSumDetails);
    }
}
