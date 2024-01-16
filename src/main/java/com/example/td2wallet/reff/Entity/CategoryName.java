package com.example.td2wallet.reff.Entity;

public enum CategoryName {
    Income("Income"),
    Vehicle("Vehicle"),
    Housing("Housing"),
    Other("Other");

    private final String label;

    CategoryName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CategoryName fromLabel(String label) {
        for (CategoryName categoryName : values()) {
            if (categoryName.label.equals(label)) {
                return categoryName;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}
