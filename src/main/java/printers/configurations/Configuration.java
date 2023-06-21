package printers.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import printers.model.LowBudgetRetryParameters;
import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.printers.*;
import printers.support.PrintCostCalculator;

import java.awt.*;
import java.util.function.UnaryOperator;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:app.properties")
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
    @Value("${printer.all.materials.1.cost.per.second}")
    private int printerAllMaterialsCostPerSecond1;
    @Value("${printer.all.materials.2.cost.per.second}")
    private int printerAllMaterialsCostPerSecond2;
    @Value("${max.retry.count}")
    private int maxRetryCount;

    @Bean
    UnaryOperator<PrintCommand> noColorFunctionality() {
        return printCommand -> {
            printCommand.setTextColor(Color.BLACK);
            return printCommand;
        };
    }

    @Bean
    UnaryOperator<PrintCommand> halfTextSizeFunctionality() {
        return printCommand -> {
            int newTextSize = printCommand.getTextSize() == 1 ? 1 : printCommand.getTextSize() / 2;
            printCommand.setTextSize(newTextSize);

            return printCommand;
        };
    }

    @Bean
    LowBudgetRetryParameters lowBudgetRetryFunctionalities (
            UnaryOperator<PrintCommand> noColorFunctionality,
            UnaryOperator<PrintCommand> halfTextSizeFunctionality) {
        LowBudgetRetryParameters lowBudgetRetryFunctionalities = new LowBudgetRetryParameters(maxRetryCount);

        lowBudgetRetryFunctionalities.setLowBudgetRetryFunctionality(1, noColorFunctionality);
        lowBudgetRetryFunctionalities.setLowBudgetRetryFunctionality(2, halfTextSizeFunctionality);
        lowBudgetRetryFunctionalities.setLowBudgetRetryFunctionality(3, halfTextSizeFunctionality);

        return lowBudgetRetryFunctionalities;
    }

    @Bean
    PrintCostCalculator weightTaxCalculator() {
        return new PrintCostCalculator();
    }

    @Bean
    Printer colorPrinter() {
        return new ColorPrinter(PrinterType.COLOR, printerColorCostPerSecond);
    }

    @Bean
    Printer largePrinter() {
        return new LargePrinter(PrinterType.LARGE, printerLargeCostPerSecond);
    }

    @Bean
    Printer laserPrinter() {
        return new LaserPrinter(PrinterType.LASER, printerLaserCostPerSecond);
    }

    @Bean
    Printer multiPrinter() {
        return new MultiPrinter(PrinterType.MULTI, printerMultiCostPerSecond);
    }

    @Bean
    Printer simplePrinter() {
        return new SimplePrinter(PrinterType.SIMPLE, printerSimpleCostPerSecond);
    }

    @Bean
    Printer woodPrinter() {
        return new WoodPrinter(PrinterType.WOOD, printerWoodCostPerSecond);
    }

    @Bean
    Printer allMaterialsPrinter1() {
        return new AllMaterialsPrinter(PrinterType.ALL_MATERIALS, printerAllMaterialsCostPerSecond1);
    }

    @Bean
    Printer allMaterialsPrinter2() {
        return new AllMaterialsPrinter(PrinterType.ALL_MATERIALS, printerAllMaterialsCostPerSecond2);
    }
}
