package minesweeper;

public class Mine {

    private Matrix mapOfMines;
    private int totalMines;

    Mine(int totalMines) { // устанавливаем кол-во мин
        this.totalMines = totalMines;
        fixCountOfMines();
    }

    void start() { // расстановка мин
        mapOfMines = new Matrix(Cell.ZERO);
        for (int j = 0; j < totalMines; j++) placeOfMine();
    }

    Cell get (Coord coord) {
        return mapOfMines.get(coord);
    }

    private void fixCountOfMines() {
    	// установим max кол-во мин
        int max = Ranges.getSize().x * Ranges.getSize().y;
        if (totalMines > max) totalMines = max;
    }

    private void placeOfMine() { // устанавливаем мину
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            // проверяем ячейку на наличие мины
            if (mapOfMines.get(coord) == Cell.MINE) continue;
            mapOfMines.set(new Coord(coord.x, coord.y), Cell.MINE);
            incNumbersAroundMine(coord);
            break;
        }
    }
    
    // увеличение чисел вокруг мин
    private void incNumbersAroundMine(Coord coord) {
        for (Coord around : Ranges.getCoordsArround(coord))
            if (mapOfMines.get(around) != Cell.MINE)
                mapOfMines.set(around, mapOfMines.get(around).getNextNumberOfCell());
    }

    public int getTotalMines() {
        return totalMines;
    }
}