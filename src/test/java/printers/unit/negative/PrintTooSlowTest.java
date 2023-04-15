package printers.unit.negative;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import printers.errorhandling.exceptions.PrintTooSlowException;
import printers.model.PaperMaterial;
import printers.model.PaperSize;
import printers.model.PrintCommand;
import printers.support.TestResources;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = printers.configuration.Configuration.class)
class PrintTooSlowTest extends TestResources {
    @SneakyThrows
    @Test
    void printerTooSlowPrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(2)
                .urgencyInSeconds(SHORT_TIME)
                .maxCost(HIGH_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.HEMP)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        assertThrows(PrintTooSlowException.class, () -> simplePrinter.print(printCommand));
    }
}
