package printers.printers;

import lombok.AllArgsConstructor;
import printers.errorhandling.exceptions.PrintTooExpensiveException;
import printers.errorhandling.exceptions.PrintTooSlowException;
import printers.errorhandling.exceptions.PrinterNotValidException;
import printers.model.PrintCommand;
import printers.model.PrintReport;
import printers.model.PrinterType;
import printers.support.Utils;

import java.awt.*;

@AllArgsConstructor
public abstract class BasePrinter implements Printer {
    private PrinterType printerType;
    private double costPerSecond;

    public PrintReport print(PrintCommand printCommand) throws PrinterNotValidException, PrintTooSlowException, PrintTooExpensiveException {
        String reason = isValidPrint(printCommand);

        if (reason == null) {
            long printTime = (long) Utils.getPrintTime(printerType, printCommand);

            if (printTime * costPerSecond <= printCommand.getMaxCost()) {
                Utils.print(printerType, printCommand);
                return new PrintReport(printerType, printTime, printTime * costPerSecond);
            }
            else if (printCommand.isLowBudgetOption()) {
                printCommand.setTextColor(Color.BLACK);
                printCommand.setPaperBackgroundColor(Color.WHITE);
                if (printTime * costPerSecond <= printCommand.getMaxCost()) {
                    Utils.print(printerType, printCommand);
                    return new PrintReport(printerType, printTime, printTime * costPerSecond);
                }
                else {
                    printCommand.setTextSize(printCommand.getTextSize() / 2);
                    if (printTime * costPerSecond <= printCommand.getMaxCost()) {
                        Utils.print(printerType, printCommand);
                        return new PrintReport(printerType, printTime, printTime * costPerSecond);
                    }
                    else {
                        printCommand.setTextSize(printCommand.getTextSize() / 2);
                        if (printTime * costPerSecond <= printCommand.getMaxCost()) {
                            Utils.print(printerType, printCommand);
                            return new PrintReport(printerType, printTime, printTime * costPerSecond);
                        }
                        else {
                            throw new PrintTooExpensiveException(printerType, printCommand.getId(), printCommand.getMaxCost(), printTime * costPerSecond);
                        }
                    }
                }
            }
            else {
                throw new PrintTooExpensiveException(printerType, printCommand.getId(), printCommand.getMaxCost(), printTime * costPerSecond);
            }
        }
        else {
            throw new PrinterNotValidException(printerType, printCommand.getId(), reason);
        }
    }
}