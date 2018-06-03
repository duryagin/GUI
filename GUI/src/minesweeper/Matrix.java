package minesweeper;

class Matrix {
	
	private Cell[][] matrix;
	
	Matrix(Cell cell) {
        matrix = new Cell[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords()) // цикл с заполнением матрицы
            matrix[coord.x][coord.y] = cell;
    }
	
	Cell get(Coord coord) { // не для всей матрицы, а только для указанных координат
        if (Ranges.inRange(coord)) // сначала проверяем расположение координаты в пределах поля
            return matrix[coord.x][coord.y];
        return null;
    }
	
	void set(Coord coord, Cell cell) {
        if (Ranges.inRange(coord)) matrix[coord.x][coord.y] = cell;
    }

}