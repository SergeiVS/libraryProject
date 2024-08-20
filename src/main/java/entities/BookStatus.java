package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public enum BookStatus {
    AVAILABLE( "Available"),
    OUT("Out"),
    ORDERED( "Ordered");

    private String title;
}
