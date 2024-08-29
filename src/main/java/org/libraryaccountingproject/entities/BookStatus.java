package org.libraryaccountingproject.entities;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BookStatus {
    AVAILABLE( "available"),
    OUT("out"),
    ORDERED( "ordered");

    private String title;
}
