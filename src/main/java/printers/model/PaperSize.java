package printers.model;

import lombok.Getter;

public enum PaperSize {
    A0(50),
    A1(40),
    A2(30),
    A3(20),
    A4(10);

    @Getter
    private final int size;

    PaperSize(int size) {
        this.size = size;
    }
}
