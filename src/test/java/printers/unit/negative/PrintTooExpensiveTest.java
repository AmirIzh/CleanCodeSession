package printers.unit.negative;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import printers.errorhandling.exceptions.PrintTooExpensiveException;
import printers.model.PaperMaterial;
import printers.model.PaperSize;
import printers.model.PrintCommand;
import printers.support.TestResources;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = printers.configuration.Configuration.class)
class PrintTooExpensiveTest extends TestResources {
    @SneakyThrows
    @Test
    void printerTooExpensivePrintTest() {
        // arrange:
        PrintCommand printCommand = PrintCommand
                .builder()
                .copies(1)
                .urgencyInSeconds(A_LOT_OF_TIME)
                .maxCost(LOW_MAX_COST)
                .text(LOREM_IPSUM)
                .textSize(1)
                .textFont(Font.MONOSPACED)
                .textColor(Color.BLACK)
                .paperSize(PaperSize.A2)
                .paperMaterial(PaperMaterial.HEMP)
                .paperBackgroundColor(Color.WHITE)
                .build();

        // act + assert:
        assertThrows(PrintTooExpensiveException.class, () -> simplePrinter.print(printCommand));
    }
}
