package printers.support;

import lombok.SneakyThrows;
import printers.errorhandling.exceptions.PrintTooSlowException;
import printers.model.PaperMaterial;
import printers.model.PaperSize;
import printers.model.PrintCommand;
import printers.model.PrinterType;

import java.awt.*;

import static printers.support.TimeFactorConstants.*;

public class Utils {
    private Utils() {}

    @SneakyThrows
    public static void print(PrinterType printerType, PrintCommand printCommand) throws PrintTooSlowException {
        long printTime = (long) getPrintTime(printerType, printCommand);

        if (printTime <= printCommand.getUrgencyInSeconds()) {
            Thread.sleep(printTime);
        }
        else {
            throw new PrintTooSlowException(printerType, printCommand.getId(), printCommand.getUrgencyInSeconds(), printTime);
        }
    }

    public static double getPrintTime(PrinterType printerType, PrintCommand printCommand) {
        double time = getPrintTimeByText(printCommand) + getPrintTimeByPaper(printCommand);
        time *= getPrintTimeFactorByPrinterType(printerType);
        return time * printCommand.getCopies();
    }

    private static double getPrintTimeByText(PrintCommand printCommand) {
        double time = printCommand.getText().length() * TEXT_LENGTH_TIME_FACTOR;
        time *= printCommand.getTextSize() * TEXT_SIZE_TIME_FACTOR;

        if (printCommand.getTextColor() != Color.BLACK) {
            time *= TEXT_COLOR_TIME_FACTOR;
        }

        return time;
    }

    private static double getPrintTimeByPaper(PrintCommand printCommand) {
        double time = printCommand.getPaperSize().getSize() * PAPER_SIZE_TIME_FACTOR;
        time *= printCommand.getPaperMaterial().getWeight() * PAPER_MATERIAL_TIME_FACTOR;

        if (printCommand.getPaperBackgroundColor() != Color.WHITE) {
            time *= PAPER_BACKGROUND_COLOR_TIME_FACTOR;
        }

        return time;
    }

    private static double getPrintTimeFactorByPrinterType(PrinterType printerType) {
        if (printerType == PrinterType.LASER) {
            return QUICK_PRINTER_TIME_FACTOR;
        }
        else {
            return NON_QUICK_PRINTER_TIME_FACTOR;
        }
    }

    public static boolean isLargePrint(PrintCommand printCommand) {
        return printCommand.getPaperSize() == PaperSize.A0 || printCommand.getPaperSize() == PaperSize.A1;
    }

    public static boolean isColoredPrint(PrintCommand printCommand) {
        return printCommand.getTextColor() != Color.BLACK || printCommand.getPaperBackgroundColor() != Color.WHITE;
    }

    public static boolean isWoodenPrint(PrintCommand printCommand) {
        return printCommand.getPaperMaterial() == PaperMaterial.WOOD || printCommand.getPaperMaterial() == PaperMaterial.BAMBOO;
    }

    public static boolean isMetalPrint(PrintCommand printCommand) {
        return printCommand.getPaperMaterial() == PaperMaterial.IRON || printCommand.getPaperMaterial() == PaperMaterial.STEEL;
    }
}
