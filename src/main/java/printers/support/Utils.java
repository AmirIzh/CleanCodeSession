package printers.support;

import lombok.SneakyThrows;
import printers.errorhandling.exceptions.PrintTooSlowException;
import printers.model.PaperMaterial;
import printers.model.PaperSize;
import printers.model.PrintCommand;
import printers.model.PrinterType;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import static printers.support.TimeFactorConstants.*;

public class Utils {
    private Utils() {}

    public static final String PAPER_SIZE = "paper size";
    public static final String TEXT_COLOR = "text color";
    public static final String PAPER_BACKGROUND_COLOR = "paper background color";
    public static final String PAPER_MATERIAL = "paper material";

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

    public static Optional<String> isLargePrint(PrintCommand printCommand) {
        if (printCommand.getPaperSize() == PaperSize.A0 || printCommand.getPaperSize() == PaperSize.A1) {
          return Optional.of(PAPER_SIZE);
        }
        return Optional.empty();
    }

    public static Optional<String> isColoredPrint(PrintCommand printCommand) {
        if (printCommand.getTextColor() != Color.BLACK) {
            return Optional.of(TEXT_COLOR);
        }
        else if (printCommand.getPaperBackgroundColor() != Color.WHITE) {
            return Optional.of(PAPER_BACKGROUND_COLOR);
        }

        return Optional.empty();
    }

    public static Optional<String> isWoodenPrint(PrintCommand printCommand) {
        if (printCommand.getPaperMaterial() == PaperMaterial.WOOD || printCommand.getPaperMaterial() == PaperMaterial.BAMBOO) {
            return Optional.of(PAPER_MATERIAL);
        }

        return Optional.empty();
    }

    public static Optional<String> isMetalPrint(PrintCommand printCommand) {
        if (printCommand.getPaperMaterial() == PaperMaterial.IRON || printCommand.getPaperMaterial() == PaperMaterial.STEEL) {
            return Optional.of(PAPER_MATERIAL);
        }
        return Optional.empty();
    }

    @SneakyThrows
    public static Optional<String> isValidPrint(List<Callable<Optional<String>>> invalidityPredicates) {
        for (Callable<Optional<String>> validityPredicate : invalidityPredicates) {
            Optional<String> optionalInvalidParameter = validityPredicate.call();

            if (optionalInvalidParameter.isPresent()) {
                return optionalInvalidParameter;
            }
        }

        return Optional.empty();
    }
}
