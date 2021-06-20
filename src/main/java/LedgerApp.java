import entity.Operation;
import service.LoanService;
import service.impl.LoanServiceImpl;

import java.io.*;

public class LedgerApp {
    private static final String BASE_RESOURCE_PATH = "src/main/resources/";
    private static final String SAMPLE_INPUT1 = "sampleInput1.txt";
    private static final String SAMPLE_INPUT2 = "sampleInput2.txt";
    private final LoanService loanService;

    public LedgerApp(LoanService loanService) {
        this.loanService = loanService;
    }

    public static void main(String[] args) throws IOException {

        String fileName = args[0];
        //System.out.printf("result for %s%n",fileName);
        processFile(fileName);

    }

    static void processFile(String fileName) throws IOException {
        File file = new File(fileName);
        final InputStream targetStream =
                new DataInputStream(new FileInputStream(file));

        BufferedReader br = new BufferedReader(new InputStreamReader(targetStream));
        String line;
        LedgerApp ledgerApp = new LedgerApp(new LoanServiceImpl());

        while ((line = br.readLine()) != null) {
            String[] data = line.split(" ");
            ledgerApp.performOperation(Operation.valueOf(data[0]), data);
        }
    }

    void performOperation(Operation operation, String[] data) {
        switch (operation) {
            case LOAN:
                loanService.createLoanInSystem(data);
                break;
            case PAYMENT:
                loanService.addPaymentToLoan(data);
                break;
            case BALANCE:
                System.out.println(loanService.getBalance(data));
                break;
        }
    }

}
