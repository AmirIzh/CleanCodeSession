package printers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class LowBudgetRetryFunctionalities {
    private static final UnaryOperator<PrintCommand> DEFAULT_FUNCTIONALITY = printCommand -> printCommand;
    private final List<UnaryOperator<PrintCommand>> functionalities;

    public LowBudgetRetryFunctionalities() {
        functionalities = new ArrayList<>();
        setLowBudgetRetryFunctionality(0, DEFAULT_FUNCTIONALITY);
    }

    public void setLowBudgetRetryFunctionality(int retryNumber, UnaryOperator<PrintCommand> retryFunctionality) {
        while (functionalities.size() < retryNumber + 1) {
            functionalities.add(DEFAULT_FUNCTIONALITY);
        }

        functionalities.set(retryNumber, retryFunctionality);
    }

    public UnaryOperator<PrintCommand> getLowBudgetRetryFunctionality(int retryNumber) {
        return functionalities.get(retryNumber);
    }
}
