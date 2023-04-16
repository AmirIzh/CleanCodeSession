package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class WoodPrinter extends BasePrinter implements Printer {

    public WoodPrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond, false);
    }

    @Override
    public String isValidPrint(PrintCommand printCommand) {
        if (Utils.isColoredPrint(printCommand)) {
            return "color";
        }
        else if (Utils.isLargePrint(printCommand)) {
            return "paper size";
        }
        else {
            return null;
        }
    }
}
