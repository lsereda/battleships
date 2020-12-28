package uj.lsereda.battleships.map;

public enum CellType {
    WATER('.'),
    SHIP('#'),
    MISS('@'),
    HIT('X'),
    FOG('?');

    private final char value;

    CellType(char value) {
        this.value = value;
    }

    public static CellType fromChar(char ch) { // TODO refactor
        if (ch == '#') {
            return SHIP;
        } else {
            return WATER;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
