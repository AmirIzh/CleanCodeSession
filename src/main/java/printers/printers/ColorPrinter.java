package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

import java.util.List;
import java.util.Optional;

public class ColorPrinter extends BasePrinter implements Printer {
    public ColorPrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond);
    }

    @Override
    public Optional<String> isValidPrint(PrintCommand printCommand) {
        return Utils.isValidPrint(List.of(
                () -> Utils.isLargePrint(printCommand),
                () -> Utils.isWoodenPrint(printCommand)));
    }
}
