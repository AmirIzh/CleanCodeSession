package printers.errorhandling.exceptions;

import printers.model.PrinterType;

import java.util.UUID;

public class PrintTooSlowException extends Exception {
    public PrintTooSlowException (PrinterType type, UUID commandId, long printUrgency, long printActualTime) {
        super(String.format("Command %s request max of %s seconds of print time, but printer %s will actually take %s seconds", commandId, printUrgency, type.name(), printActualTime));
    }
}
