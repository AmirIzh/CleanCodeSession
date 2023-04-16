package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class ColorPrinter extends BasePrinter implements Printer {

    public ColorPrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond);
    }

    @Override
    public String isValidPrint(PrintCommand printCommand) {
        if (Utils.isLargePrint(printCommand)) {
            return "paper size";
        }
        else if (Utils.isWoodenPrint(printCommand)) {
            return "paper material";
        }
        else {
            return null;
        }
    }
}
