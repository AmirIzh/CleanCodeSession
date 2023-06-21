package printers.unit.positive;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import printers.model.*;
import printers.support.TestResources;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = printers.configuration.Configuration.class)
class PrintTest extends TestResources {

    @SneakyThrows
    @Test
    void simplePrinterPrintTest() {
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
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.HEMP)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act:
        PrintReport printReport = simplePrinter.print(printCommand);

        // assert:
        assertEquals(PrinterType.SIMPLE, printReport.getPrinterUsed());
        assertTrue(printReport.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport.getCost() < printCommand.getMaxCost());
    }

    @SneakyThrows
    @Test
    void colorPrinterPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.SERIF)
                .textColor(Color.BLUE)
                .paperSize(PaperSize.A3)
                .paperMaterial(PaperMaterial.COTTON)
                .paperBackgroundColor(Color.GREEN)
                .build();

        // act:
        PrintReport printReport = colorPrinter.print(printCommand);

        // assert:
        assertEquals(PrinterType.COLOR, printReport.getPrinterUsed());
        assertTrue(printReport.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport.getCost() < printCommand.getMaxCost());
    }

    @SneakyThrows
    @Test
    void largePrinterPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.SERIF)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A0)
                .paperMaterial(PaperMaterial.FLAX)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act:
        PrintReport printReport = largePrinter.print(printCommand);

        // assert:
        assertEquals(PrinterType.LARGE, printReport.getPrinterUsed());
        assertTrue(printReport.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport.getCost() < printCommand.getMaxCost());
    }

    @SneakyThrows
    @Test
    void laserPrinterPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.SERIF)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.FLAX)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act:
        PrintReport printReport = laserPrinter.print(printCommand);

        // assert:
        assertEquals(PrinterType.LASER, printReport.getPrinterUsed());
        assertTrue(printReport.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport.getCost() < printCommand.getMaxCost());
    }

    @SneakyThrows
    @Test
    void multiPrinterPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.SERIF)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A1)
                .paperMaterial(PaperMaterial.BAMBOO)
                .paperBackgroundColor(Color.ORANGE)
                .build();

        // act:
        PrintReport printReport = multiPrinter.print(printCommand);

        // assert:
        assertEquals(PrinterType.MULTI, printReport.getPrinterUsed());
        assertTrue(printReport.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport.getCost() < printCommand.getMaxCost());
    }

    @SneakyThrows
    @Test
    void woodPrinterPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.SERIF)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.WOOD)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act:
        PrintReport printReport = woodPrinter.print(printCommand);

        // assert:
        assertEquals(PrinterType.WOOD, printReport.getPrinterUsed());
        assertTrue(printReport.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport.getCost() < printCommand.getMaxCost());
    }

    @SneakyThrows
    @Test
    void allMaterialsPrinter1PrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(VERY_HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.SERIF)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.IRON)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act:
        PrintReport printReport = allMaterialsPrinter1.print(printCommand);

        // assert:
        assertEquals(PrinterType.ALL_MATERIALS, printReport.getPrinterUsed());
        assertTrue(printReport.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport.getCost() < printCommand.getMaxCost());
    }

    @SneakyThrows
    @Test
    void allMaterialsPrinter2PrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(VERY_HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.SERIF)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.IRON)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act:
        PrintReport printReport1 = allMaterialsPrinter1.print(printCommand);
        PrintReport printReport2 = allMaterialsPrinter2.print(printCommand);

        // assert:
        assertEquals(PrinterType.ALL_MATERIALS, printReport2.getPrinterUsed());
        assertTrue(printReport2.getPrintTime() < printCommand.getUrgencyInSeconds());
        assertTrue(printReport2.getCost() < printCommand.getMaxCost());
        assertTrue(printReport2.getCost() > printReport1.getCost());
    }
}