package model;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Fácil";
            case MEDIUM -> "Intermedio";
            case HARD -> "Difícil";
        };
    }

    public int getCellsToRemove() {
        return switch (this) {
            case EASY -> 30;
            case MEDIUM -> 40;
            case HARD -> 50;
        };
    }
}
