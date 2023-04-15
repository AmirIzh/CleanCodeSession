package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class ColorPrinter extends BasePrinter implements Printer {

    public ColorPrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond);
    }

    @Override
    public boolean isValidPrint(PrintCommand printCommand) {
        return !Utils.isLargePrint(printCommand) && !Utils.isWoodenPrint(printCommand);
    }
}
