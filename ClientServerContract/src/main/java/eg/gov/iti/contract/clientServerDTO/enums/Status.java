package eg.gov.iti.contract.clientServerDTO.enums;

public enum Status {
    AVAILABLE(1),
    BUSY(2),
    AWAY(3);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
