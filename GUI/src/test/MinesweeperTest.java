package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import minesweeper.Cell;
import minesweeper.Coord;
import minesweeper.Game;
import minesweeper.Matrix;
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

}
