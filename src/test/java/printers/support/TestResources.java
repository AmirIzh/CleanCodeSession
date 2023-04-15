package printers.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import printers.printers.Printer;

@Component
public class TestResources {
    @Autowired
    protected Printer colorPrinter;
    @Autowired
    protected Printer largePrinter;
    @Autowired
    protected Printer laserPrinter;
    @Autowired
    protected Printer multiPrinter;
    @Autowired
    protected Printer simplePrinter;
    @Autowired
    protected Printer woodPrinter;

    protected final long A_LOT_OF_TIME = 20;
    protected final long SHORT_TIME = 5;
    protected final double HIGH_MAX_COST = 100;
    protected final double LOW_MAX_COST = 3;
    protected final String LOREM_IPSUM =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
}
