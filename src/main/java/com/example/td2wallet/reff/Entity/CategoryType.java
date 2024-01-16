package com.example.td2wallet.reff.Entity;

public enum CategoryType {
    Loan("Loan"),
    Outgoing("Outgoing");

    private final String label;

    CategoryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CategoryType fromLabel(String label) {
        for (CategoryType categoryType : values()) {
            if (categoryType.label.equals(label)) {
                return categoryType;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}
