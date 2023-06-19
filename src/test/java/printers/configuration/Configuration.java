package printers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import printers.model.LowBudgetRetryFunctionalities;
import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.printers.*;

import java.awt.*;
import java.util.function.UnaryOperator;

@PropertySource("classpath:tests.properties")
public class Configuration {
    @Value("${printer.color.cost.per.second}")
    private int printerColorCostPerSecond;
    @Value("${printer.large.cost.per.second}")
    private int printerLargeCostPerSecond;
    @Value("${printer.laser.cost.per.second}")
    private int printerLaserCostPerSecond;
    @Value("${printer.multi.cost.per.second}")
    private int printerMultiCostPerSecond;
    @Value("${printer.simple.cost.per.second}")
    private int printerSimpleCostPerSecond;
    @Value("${printer.wood.cost.per.second}")
    private int printerWoodCostPerSecond;
    @Value("${max.retry.count}")
    private int maxRetryCount;

    @Bean
    UnaryOperator<PrintCommand> noColorFunctionality() {
        return (printCommand) -> {
            printCommand.setTextColor(Color.BLACK);
            return printCommand;
        };
    }

    @Bean
    UnaryOperator<PrintCommand> halfTextSizeFunctionality() {
        return (printCommand) -> {
            int newTextSize = printCommand.getTextSize() == 1 ? 1 : printCommand.getTextSize() / 2;
            printCommand.setTextSize(newTextSize);

            return printCommand;
        };
    }

    @Bean
    LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities (
            UnaryOperator<PrintCommand> noColorFunctionality,
            UnaryOperator<PrintCommand> halfTextSizeFunctionality) {
        LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities = new LowBudgetRetryFunctionalities();

        lowBudgetRetryFunctionalities.setLowBudgetRetryFunctionality(1, noColorFunctionality);
        lowBudgetRetryFunctionalities.setLowBudgetRetryFunctionality(2, halfTextSizeFunctionality);
        lowBudgetRetryFunctionalities.setLowBudgetRetryFunctionality(3, halfTextSizeFunctionality);

        return lowBudgetRetryFunctionalities;
    }

    @Bean
    Printer colorPrinter(LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        return new ColorPrinter(PrinterType.COLOR, printerColorCostPerSecond, maxRetryCount, lowBudgetRetryFunctionalities);
    }

    @Bean
    Printer largePrinter(LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        return new LargePrinter(PrinterType.LARGE, printerLargeCostPerSecond, maxRetryCount, lowBudgetRetryFunctionalities);
    }

    @Bean
    Printer laserPrinter(LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        return new LaserPrinter(PrinterType.LASER, printerLaserCostPerSecond, maxRetryCount, lowBudgetRetryFunctionalities);
    }

    @Bean
    Printer multiPrinter(LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        return new MultiPrinter(PrinterType.MULTI, printerMultiCostPerSecond, maxRetryCount, lowBudgetRetryFunctionalities);
    }

    @Bean
    Printer simplePrinter(LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        return new SimplePrinter(PrinterType.SIMPLE, printerSimpleCostPerSecond, maxRetryCount, lowBudgetRetryFunctionalities);
    }

    @Bean
    Printer woodPrinter(LowBudgetRetryFunctionalities lowBudgetRetryFunctionalities) {
        return new WoodPrinter(PrinterType.WOOD, printerWoodCostPerSecond, maxRetryCount, lowBudgetRetryFunctionalities);
    }
}
