package printers.model;

import lombok.Getter;

public enum PaperMaterial {
    COTTON(1),
    FLAX(3),
    BAMBOO(3),
    WOOD(4),
    HEMP(2),
    IRON(12),
    STEEL(8);

    @Getter
    private final int weight;

    PaperMaterial(int weight) {
        this.weight = weight;
    }
}
