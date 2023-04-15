package printers.model;

import lombok.Getter;

@Getter
public class PrintReport {
    private final PrinterType printerUsed;
    private final long printTime;
    private final double cost;

    public PrintReport(PrinterType printerUsed, long printTime, double cost) {
        this.printerUsed = printerUsed;
        this.printTime = printTime;
        this.cost = cost;
    }
}
