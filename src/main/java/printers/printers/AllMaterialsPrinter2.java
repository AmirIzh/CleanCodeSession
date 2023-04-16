package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class AllMaterialsPrinter2 extends WoodPrinter implements Printer {

    public AllMaterialsPrinter2() {
        super(PrinterType.ALL_MATERIALS, 10);
    }

    @Override
    public String isValidPrint(PrintCommand printCommand) {
        String reason = super.isValidPrint(printCommand);

        if (Utils.isWoodenPrint(printCommand)) {
            return "paper material";
        }
        if (reason.equals("paper material")) {
            return null;
        }

        return reason;
    }
}
