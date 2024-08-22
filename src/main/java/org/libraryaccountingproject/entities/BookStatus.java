package org.libraryaccountingproject.entities;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BookStatus {
    AVAILABLE( "Available"),
    OUT("Out"),
    ORDERED( "Ordered");

    private String title;
}
