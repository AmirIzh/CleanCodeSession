package printers.errorhandling.exceptions;

import printers.model.PrinterType;

import java.util.UUID;

public class PrinterNotValidException extends Exception {
    public PrinterNotValidException(PrinterType type, UUID commandId, String reason) {
        super(String.format("%s printer is not valid due to parameter %s for print command %s", type.name(), reason, commandId));
    }
}
