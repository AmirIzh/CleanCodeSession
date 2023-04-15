package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class LaserPrinter extends BasePrinter implements Printer {

    public LaserPrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond);
    }

    @Override
    public boolean isValidPrint(PrintCommand printCommand) {
        return !Utils.isLargePrint(printCommand) && !Utils.isWoodenPrint(printCommand) && !Utils.isColoredPrint(printCommand);
    }
}
