package printers.support;

import printers.model.PrintCommand;
import printers.model.PrinterType;

public class PrintCostCalculator {

    public double getPrintCost(PrinterType printerType, double costPerSecond, PrintCommand printCommand) {
        long weightTax = getWeightTax(printCommand);
        long printTime = (long) Utils.getPrintTime(printerType, printCommand);

        return (printTime * costPerSecond) + weightTax;
    }

    private long getWeightTax(PrintCommand printCommand) {
        long pagesCount = getPagesCount(printCommand);

        return pagesCount * printCommand.getPaperMaterial().getWeight();
    }

    private long getPagesCount(PrintCommand printCommand) {
        long fullPages = printCommand.getText().length() / 100;

        if (printCommand.getText().length() % 100 == 0) {
            return fullPages;
        }

        return fullPages + 1;
    }
}
