package printers.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.UUID;

@Builder
@Getter
public class PrintCommand {
	// Command
	private final UUID id = UUID.randomUUID();

	// General
	private boolean weightTaxOption;
	private boolean lowBudgetOption;
	private int copies;
	private long urgencyInSeconds;
	private double maxCost;

	// Text:
	private String text;
	@Setter
	private int textSize;
	private String textFont;
	@Setter
	private Color textColor;

	// Paper:
	private PaperSize paperSize;
	private PaperMaterial paperMaterial;
	private Color paperBackgroundColor;
}
