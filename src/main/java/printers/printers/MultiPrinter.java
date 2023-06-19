package printers.printers;

import printers.model.LowBudgetRetryFunctionalities;
import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

import java.util.List;
import java.util.Optional;

public class MultiPrinter extends BasePrinter implements Printer {

    public MultiPrinter(PrinterType printerType, int costPerSecond, int maxRetriesCount, LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        super(printerType, costPerSecond, maxRetriesCount, lowBudgetRetryFunctionalities);
    }

    @Override
    public Optional<String> isValidPrint(PrintCommand printCommand) {
        return Utils.isValidPrint(List.of());
    }
}
