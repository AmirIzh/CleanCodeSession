package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;

public class AllMaterialsPrinter extends WoodPrinter implements Printer {

    public AllMaterialsPrinter() {
        super(PrinterType.ALL_MATERIALS, 6);
    }

    @Override
    public String isValidPrint(PrintCommand printCommand) {
        String reason = super.isValidPrint(printCommand);

        if (reason.equals("paper material")) {
            return null;
        }

        return reason;
    }
}
