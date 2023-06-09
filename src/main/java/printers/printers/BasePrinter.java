package printers.printers;

import lombok.AllArgsConstructor;
import printers.errorhandling.exceptions.PrintTooExpensiveException;
import printers.errorhandling.exceptions.PrintTooSlowException;
import printers.errorhandling.exceptions.PrinterNotValidException;
import printers.model.PrintCommand;
import printers.model.PrintReport;
import printers.model.PrinterType;
import printers.support.Utils;

@AllArgsConstructor
public abstract class BasePrinter implements Printer {
    private PrinterType printerType;
    private double costPerSecond;

    public PrintReport print(PrintCommand printCommand) throws PrinterNotValidException, PrintTooSlowException, PrintTooExpensiveException {
        if (isValidPrint(printCommand)) {
            long printTime = (long) Utils.getPrintTime(printerType, printCommand);

            if (printTime * costPerSecond <= printCommand.getMaxCost()) {
                Utils.print(printerType, printCommand);
                return new PrintReport(printerType, printTime, printTime * costPerSecond);
            }
            else {
                throw new PrintTooExpensiveException(printerType, printCommand.getId(), printCommand.getMaxCost(), printTime * costPerSecond);
            }
        }
        else {
            throw new PrinterNotValidException(printerType, printCommand.getId());
        }
    }
}
