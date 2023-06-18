package printers.errorhandling.exceptions;

import lombok.Getter;
import printers.model.PrinterType;

import java.util.UUID;

@Getter
public class PrinterNotValidException extends Exception {
    private final String invalidReason;

    public PrinterNotValidException(PrinterType type, UUID commandId, String invalidReason) {
        super(String.format("%s printer is not valid for print command %s due to invalid parameter - %s", type.name(), commandId, invalidReason));

        this.invalidReason = invalidReason;
    }
}
