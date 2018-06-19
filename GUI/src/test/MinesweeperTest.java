package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import minesweeper.Cell;
import minesweeper.Coord;
import minesweeper.Game;
import minesweeper.Ranges;
import minesweeper.Status;

class MinesweeperTest {

	@Test
	void lose() {
		
		Game game = new Game(1, 1, 1);
		game.start();
		Coord coords = new Coord(0, 0);
		game.pressLeftButton(coords);
		assertEquals(game.getStatus(), Status.LOST);
	
	}
	
	@Test
	void win() {
		
		Game game = new Game(1, 1, 0);
		game.start();
		Coord coords = new Coord(0, 0);
		game.pressLeftButton(coords);
		assertEquals(game.getStatus(), Status.WON);
		
		Game gameTwo = new Game(5, 5, 0);
		gameTwo.start();
		Coord coordsTwo = new Coord(3, 2);
		gameTwo.pressLeftButton(coordsTwo);
		assertEquals(gameTwo.getStatus(), Status.WON);
		
		Game gameThree = new Game(1, 1, 1);
		gameThree.start();
		gameThree.pressRightButton(coords);
		gameThree.pressLeftButton(coords);
		assertEquals(gameThree.getStatus(), Status.WON);

	}
	
	@Test
	void play() {
		
		Game game = new Game(2, 2, 0);
		game.start();
		assertEquals(game.getStatus(), Status.PLAY);
		Coord coords = new Coord(0, 0);
		game.pressRightButton(coords);
		assertEquals(game.getStatus(), Status.PLAY);
	
	}
	
	@Test
	void numOfClosed() {
		
		int columns = 3;
		int rows = 3;
		int count = 0;
		
		Game game = new Game(columns, rows, 0);
		game.start();
		
		for (Coord coord : Ranges.getAllCoords()) {
			if (game.getCell(coord) == Cell.CLOSED)
				count++;
		}
		
		assertEquals(count, 9);
		
		count = 0;
		Coord coords = new Coord(2, 1);
		game.pressLeftButton(coords);
		for (Coord coord : Ranges.getAllCoords()) {
			if (game.getCell(coord) == Cell.CLOSED)
				count++;
		}
		
		assertEquals(count, 0);
	
	}
	
	@Test
	void numOfFlagged() {
		
		int columns = 3;
		int rows = 3;
		int count = 0;
		
		Coord coords = new Coord(2, 1);
		Game game = new Game(columns, rows, 5);
		game.start();
		
		game.pressRightButton(coords);
		assertEquals(game.getCell(coords), Cell.FLAGGED);
		
		for (Coord coord : Ranges.getAllCoords()) {
			/* ячейка с коорд (2, 1) расстаётся с флажком,
			 * остальные ячейки отмечаем
			 */
			game.pressRightButton(coord);
			if (game.getCell(coord) == Cell.FLAGGED)
				count++;
		}
		
		assertEquals(count, 8);
	
	}

}
