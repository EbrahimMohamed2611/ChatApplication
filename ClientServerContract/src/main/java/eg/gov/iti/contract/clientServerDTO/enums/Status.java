package eg.gov.iti.contract.clientServerDTO.enums;

import javafx.scene.paint.Color;

import java.io.Serializable;

public enum Status implements Serializable {
    AVAILABLE(Color.GREEN),
    BUSY(Color.RED),
    AWAY(Color.GRAY);

    private final Color status;

    Status(Color status) {
        this.status = status;
    }

    public Color getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status == Color.GREEN ? "AVAILABLE" :
                status == Color.RED ? "BUSY" : "AWAY";
    }
}