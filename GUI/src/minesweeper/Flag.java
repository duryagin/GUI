package minesweeper;

public class Flag {

    private Matrix mapOfFlags;
    private int countOfСlosedMines;

    public void start () {
        mapOfFlags = new Matrix(Cell.CLOSED);
        countOfСlosedMines = Ranges.getSize().x * Ranges.getSize().y;
    }

    Cell get (Coord coord) {
        return mapOfFlags.get(coord);
    }

    public void setOpenedToCell(Coord coord) {
        mapOfFlags.set(coord, Cell.CLICK);
        countOfСlosedMines--;
    }
    
    // ставим или убираем флажок
    void toggleFlaggedToCell(Coord coord) {
        switch (mapOfFlags.get(coord)) {
            case FLAGGED: setClosedToCell(coord);
            break;
            case CLOSED: setFlaggedToMine(coord);
            break;
        }
    }

    private void setClosedToCell(Coord coord) {
        mapOfFlags.set(coord, Cell.CLOSED);
    }

    public void setFlaggedToMine(Coord coord) {
        mapOfFlags.set(coord, Cell.FLAGGED);
    }
    
    // кол-во закрытых ячеек
    int getCountOfClosedCells() {
        return countOfСlosedMines;
    }

    void setMinedToCell(Coord coord) {
        mapOfFlags.set(coord, Cell.FAIL);
    }
    
    // в конце игры показать неотмеченные мины
    void setOpenedToClosedMineCell(Coord coord) {
        if (mapOfFlags.get(coord) == Cell.CLOSED)
            mapOfFlags.set(coord, Cell.CLICK);
    }
    
    // в конце игры отмечаем неверно поставленные флажки
    void setError(Coord coord) {
        if (mapOfFlags.get(coord) == Cell.FLAGGED)
            mapOfFlags.set(coord, Cell.ERROR);
    }

    // кол-во флажков вокруг ячейки
    int getCountOfFlaggedCellsAround(Coord coord) {
        int count = 0;
        for (Coord coords : Ranges.getCoordsArround(coord))
            if (mapOfFlags.get(coords) == Cell.FLAGGED)
                count++;
        return count;
    }
}