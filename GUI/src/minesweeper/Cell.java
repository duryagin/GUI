package minesweeper;

public enum Cell {

    // объекты нижнего слоя
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINE,
    // объекты верхнего слоя
    CLICK, CLOSED, FLAGGED, FAIL, ERROR;

    public Object image;
    
    int getNumber() {

        return this.ordinal();

    }

    // увеличиваем номер ячейки при расстановке мин
    Cell getNextNumberOfCell() {

        return Cell.values() [this.ordinal() + 1];

    }

}
