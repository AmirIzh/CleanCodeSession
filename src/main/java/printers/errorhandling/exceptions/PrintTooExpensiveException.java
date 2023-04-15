package printers.errorhandling.exceptions;

import printers.model.PrinterType;

import java.util.UUID;

public class PrintTooExpensiveException extends Exception {
    public PrintTooExpensiveException(PrinterType type, UUID commandId, double maxCost, double printActualCost) {
        super(String.format("Command %s request max cost of %f of print time, but the actual cost by printer %s is %f", commandId, maxCost, type.name(), printActualCost));
    }
}
