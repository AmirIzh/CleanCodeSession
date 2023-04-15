package printers.printers;

import printers.errorhandling.exceptions.PrintTooExpensiveException;
import printers.errorhandling.exceptions.PrintTooSlowException;
import printers.errorhandling.exceptions.PrinterNotValidException;
import printers.model.PrintCommand;
import printers.model.PrintReport;

public interface Printer {
    PrintReport print(PrintCommand printCommand) throws PrinterNotValidException, PrintTooSlowException, PrintTooExpensiveException;
    String isValidPrint(PrintCommand printCommand);
}
