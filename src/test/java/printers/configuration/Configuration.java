package printers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import printers.model.PrinterType;
import printers.printers.*;

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
}
