package minesweeper;

public class Game {

    private Mine mine;
    private Flag flag;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public Game (int columns, int rows, int mines) {
        Ranges.setSize(new Coord(columns, rows));
        mine = new Mine(mines);
        flag = new Flag();
    }

    public void start() { // начало игры
        mine.start();
        flag.start();
        status = Status.PLAY;
    }

    public Cell getCell(Coord coord) { // что изображаем в данной ячейке
    	
    	if (flag.get(coord) != Cell.CLICK) // если ячейка не открыта
    		return flag.get(coord); // возвращаем верхний слой
        else
            return mine.get(coord); // возвращаем нижний слой
    }

    private void checkWinner () {
        if (status == Status.PLAY)
            // если кол-во закрытых ячеек = кол-ву мин
            if (flag.getCountOfClosedCells() == mine.getTotalMines())
                status = Status.WON;
    }

    public void pressLeftButton (Coord coord) {
        if (gameOver()) return;
        openCell(coord);
        // после каждого клика ЛКМ...
        checkWinner();
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) return;
        flag.toggleFlaggedToCell(coord);
    }

    private void openCell(Coord coord) {
    	
    	switch (flag.get(coord)) {
    	
    	case CLICK:
    		setOpenedToClosedCellsAroundNumber(coord); return;
    	case FLAGGED: return;
    	case CLOSED:
    		
    		switch (mine.get(coord)) {
    		
    		case ZERO:
    			openCellsAround(coord); // открыть пустые ячейки вокруг
    			return;
    		case MINE: openMines(coord); return;
    		default:
    			flag.setOpenedToCell(coord); return;
    			
    		}
    		default: break;
        }
    }

    private void openMines(Coord mine) {
        status = Status.LOST;
        flag.setMinedToCell(mine);
        for (Coord coord : Ranges.getAllCoords())
            if (this.mine.get(coord) == Cell.MINE)
                flag.setOpenedToClosedMineCell(coord);
            else
                flag.setError(coord);
    }

    private void openCellsAround(Coord coord) {
        flag.setOpenedToCell(coord);
        for (Coord around : Ranges.getCoordsArround(coord))
            openCell(around);
    }

    private boolean gameOver() {
        if (status == Status.PLAY)
            return false;
        start();
        return true;
    }
    
    // открыть закрытые ячейки вокруг числа
    private void setOpenedToClosedCellsAroundNumber(Coord coord) {

        if (mine.get(coord) != Cell.MINE)
            if (flag.getCountOfFlaggedCellsAround(coord) == mine.get(coord).getNumber())
                for (Coord coords : Ranges.getCoordsArround(coord))
                    if (flag.get(coords) == Cell.CLOSED) openCell(coords);
    }

}