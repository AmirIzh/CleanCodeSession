package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class LargePrinter extends BasePrinter implements Printer {

    public LargePrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond, false);
    }

    @Override
    public String isValidPrint(PrintCommand printCommand) {
        if (Utils.isColoredPrint(printCommand)) {
            return "color";
        }
        else if (Utils.isWoodenPrint(printCommand)) {
            return "paper material";
        }
        else {
            return null;
        }
    }
}
