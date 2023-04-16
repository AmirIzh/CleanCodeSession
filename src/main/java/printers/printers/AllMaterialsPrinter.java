package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class AllMaterialsPrinter extends WoodPrinter implements Printer {

    public AllMaterialsPrinter() {
        super(PrinterType.ALL_MATERIALS, 6);
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
