package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class LargePrinter extends BasePrinter implements Printer {

    public LargePrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond);
    }

    @Override
    public String isValidPrint(PrintCommand printCommand) {
        if (Utils.isColoredPrint(printCommand)) {
            return "color";
        }
        else if (Utils.isWoodenPrint(printCommand) || Utils.isMetalPrint(printCommand) ) {
            return "paper material";
        }
        else {
            return null;
        }
    }
}
