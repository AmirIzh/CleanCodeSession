package printers.unit.negative;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import printers.errorhandling.exceptions.PrinterNotValidException;
import printers.model.PaperMaterial;
import printers.model.PaperSize;
import printers.model.PrintCommand;
import printers.support.TestResources;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static printers.support.Utils.*;

@SpringBootTest(classes = printers.configuration.Configuration.class)
class PrinterNotValidTest extends TestResources {

    @Test
    void simplePrinterColorPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLUE)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.HEMP)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> simplePrinter.print(printCommand));
        assertEquals(TEXT_COLOR, printerNotValidException.getInvalidReason());
    }

    @Test
    void simplePrinterPaperSizePrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A1)
                .paperMaterial(PaperMaterial.HEMP)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> simplePrinter.print(printCommand));
        assertEquals(PAPER_SIZE, printerNotValidException.getInvalidReason());
    }

    @Test
    void simplePrinterWoodPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A3)
                .paperMaterial(PaperMaterial.WOOD)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> simplePrinter.print(printCommand));
        assertEquals(PAPER_MATERIAL, printerNotValidException.getInvalidReason());
    }

    @Test
    void colorPrinterPaperSizePrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLUE)
                .paperSize(PaperSize.A0)
                .paperMaterial(PaperMaterial.FLAX)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> colorPrinter.print(printCommand));
        assertEquals(PAPER_SIZE, printerNotValidException.getInvalidReason());
    }

    @Test
    void colorPrinterWoodPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLUE)
                .paperSize(PaperSize.A4)
                .paperMaterial(PaperMaterial.WOOD)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> colorPrinter.print(printCommand));
        assertEquals(PAPER_MATERIAL, printerNotValidException.getInvalidReason());
    }

    @Test
    void largePrinterColorPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLUE)
                .paperSize(PaperSize.A0)
                .paperMaterial(PaperMaterial.COTTON)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> largePrinter.print(printCommand));
        assertEquals(TEXT_COLOR, printerNotValidException.getInvalidReason());
    }

    @Test
    void largePrinterWoodPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A0)
                .paperMaterial(PaperMaterial.BAMBOO)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> largePrinter.print(printCommand));
        assertEquals(PAPER_MATERIAL, printerNotValidException.getInvalidReason());
    }

    @Test
    void laserPrinterColorPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(SHORT_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLUE)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.HEMP)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> laserPrinter.print(printCommand));
        assertEquals(TEXT_COLOR, printerNotValidException.getInvalidReason());
    }

    @Test
    void laserPrinterPaperSizePrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(SHORT_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A1)
                .paperMaterial(PaperMaterial.HEMP)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> laserPrinter.print(printCommand));
        assertEquals(PAPER_SIZE, printerNotValidException.getInvalidReason());
    }

    @Test
    void laserPrinterWoodPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(SHORT_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A3)
                .paperMaterial(PaperMaterial.WOOD)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> laserPrinter.print(printCommand));
        assertEquals(PAPER_MATERIAL, printerNotValidException.getInvalidReason());
    }

    @Test
    void woodPrinterColorPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A3)
                .paperMaterial(PaperMaterial.BAMBOO)
                .paperBackgroundColor(Color.RED)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> woodPrinter.print(printCommand));
        assertEquals(TEXT_COLOR, printerNotValidException.getInvalidReason());
    }

    @Test
    void woodPrinterPaperSizePrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A1)
                .paperMaterial(PaperMaterial.WOOD)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        PrinterNotValidException printerNotValidException = assertThrows(PrinterNotValidException.class, () -> woodPrinter.print(printCommand));
        assertEquals(PAPER_SIZE, printerNotValidException.getInvalidReason());
    }
}
