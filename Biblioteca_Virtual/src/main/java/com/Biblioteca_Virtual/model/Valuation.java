package com.Biblioteca_Virtual.model;

public enum Valuation {
    NONE,
    POOR,
    FAIR,
    GOOD,
    VERY_GOOD,
    EXCELLENT;

    //----------> method to get the number of stars for each valuation <----------------
    public int getStars() {
        return switch (this) {
            case EXCELLENT -> 5;
            case VERY_GOOD -> 4;
            case GOOD -> 3;
            case FAIR -> 2;
            case POOR -> 1;
            case NONE -> 0;
        };
    }
}


