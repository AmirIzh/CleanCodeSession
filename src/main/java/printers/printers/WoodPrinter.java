package printers.printers;

import printers.model.LowBudgetRetryFunctionalities;
import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

import java.util.List;
import java.util.Optional;

public class WoodPrinter extends BasePrinter implements Printer {

    public WoodPrinter(PrinterType printerType, int costPerSecond, int maxRetriesCount, LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        super(printerType, costPerSecond, maxRetriesCount, lowBudgetRetryFunctionalities);
    }

    @Override
    public Optional<String> isValidPrint(PrintCommand printCommand) {
        return Utils.isValidPrint(List.of(
                () -> Utils.isLargePrint(printCommand),
                () -> Utils.isColoredPrint(printCommand)));
    }
}
