package printers.printers;

import org.springframework.beans.factory.annotation.Autowired;
import printers.errorhandling.exceptions.PrintTooExpensiveException;
import printers.errorhandling.exceptions.PrintTooSlowException;
import printers.errorhandling.exceptions.PrinterNotValidException;
import printers.model.LowBudgetRetryParameters;
import printers.model.PrintCommand;
import printers.model.PrintReport;
import printers.model.PrinterType;
import printers.support.Utils;

import java.util.Optional;

public abstract class BasePrinter implements Printer {
    @Autowired
    private LowBudgetRetryParameters lowBudgetRetryParameters;

    private final PrinterType printerType;
    private final double costPerSecond;

    protected BasePrinter(PrinterType printerType, double costPerSecond) {
        this.printerType = printerType;
        this.costPerSecond = costPerSecond;
    }

    public PrintReport print(PrintCommand printCommand) throws PrinterNotValidException, PrintTooSlowException, PrintTooExpensiveException {
        return print(printCommand, 0);
    }

    public PrintReport print(PrintCommand printCommand, int retryCount) throws PrinterNotValidException, PrintTooSlowException, PrintTooExpensiveException {
        verifyValidPrint(printCommand);

        long printTime = (long) Utils.getPrintTime(printerType, printCommand);

        if (printTime * costPerSecond <= printCommand.getMaxCost()) {
            Utils.print(printerType, printCommand);
            return new PrintReport(printerType, printTime, printTime * costPerSecond);
        }
        else {
            return expensivePrint(printCommand, retryCount);
        }
    }

    private PrintReport expensivePrint(PrintCommand printCommand, int retryCount) throws PrinterNotValidException, PrintTooSlowException, PrintTooExpensiveException {
        retryCount++;

        if (printCommand.isLowBudgetOption() && retryCount <= lowBudgetRetryParameters.getMaxRetriesCount()) {
            printCommand = lowBudgetRetryParameters.getLowBudgetRetryFunctionality(retryCount).apply(printCommand);
            return print(printCommand, retryCount);
        }

        long printTime = (long) Utils.getPrintTime(printerType, printCommand);
        throw new PrintTooExpensiveException(printerType, printCommand.getId(), printCommand.getMaxCost(), printTime * costPerSecond);
    }

    protected void verifyValidPrint(PrintCommand printCommand) throws PrinterNotValidException {
        Optional<String> optionalInvalidReason = isValidPrint(printCommand);
        if (optionalInvalidReason.isPresent()) {
            throw new PrinterNotValidException(printerType, printCommand.getId(), optionalInvalidReason.get());
        }
    }
}