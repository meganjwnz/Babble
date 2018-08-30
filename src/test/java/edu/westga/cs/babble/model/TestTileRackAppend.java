package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * tests for the TileRack#append() method, which is an override of TileGroup#append()
 * @author Megan Brown
 *
 */
public class TestTileRackAppend {
	
	@Test
	public void shouldNotAppendToFullRack() {
		TileRack tileRack = new TileRack();
		tileRack.append(new Tile('E'));
		tileRack.append(new Tile('A'));
		tileRack.append(new Tile('B'));
		tileRack.append(new Tile('Q'));
		tileRack.append(new Tile('Z'));
		tileRack.append(new Tile('L'));
		tileRack.append(new Tile('P'));
		assertThrows(TileRackFullException.class, () -> {
			tileRack.append(new Tile('O'));
		});
	}
}
