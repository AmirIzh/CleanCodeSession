package printers.errorhandling.exceptions;

import printers.model.PrinterType;

import java.util.UUID;

public class PrinterNotValidException extends Exception {
    public PrinterNotValidException(PrinterType type, UUID commandId) {
        super(String.format("%s printer is not valid for print command %s", type.name(), commandId));
    }
}
