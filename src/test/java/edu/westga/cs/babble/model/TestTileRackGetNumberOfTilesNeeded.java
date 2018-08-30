package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * tests for the TileRack#getNumberOfTilesNeeded() method
 * @author Megan Brown
 *
 */
public class TestTileRackGetNumberOfTilesNeeded {
	
	@Test
	public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
		TileRack tileRack = new TileRack();
		assertEquals(7, tileRack.getNumberOfTilesNeeded());		
	}
	
	@Test
	public void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() {
		TileRack tileRack = new TileRack();
		tileRack.append(new Tile('E'));
		assertEquals(6, tileRack.getNumberOfTilesNeeded());
		assertEquals("E", tileRack.getHand());
	}
	
	@Test
	public void tileRackWithSeveralTilesShouldNeedSomeTiles() {
		TileRack tileRack = new TileRack();
		tileRack.append(new Tile('E'));
		tileRack.append(new Tile('A'));
		tileRack.append(new Tile('Q'));
		tileRack.append(new Tile('R'));
		assertEquals(3, tileRack.getNumberOfTilesNeeded());
		assertEquals("EAQR", tileRack.getHand());
	}
	
	@Test
	public void fullRackNeedsZeroTiles() {
		TileRack tileRack = new TileRack();
		tileRack.append(new Tile('E'));
		tileRack.append(new Tile('A'));
		tileRack.append(new Tile('Q'));
		tileRack.append(new Tile('R'));
		tileRack.append(new Tile('P'));
		tileRack.append(new Tile('L'));
		tileRack.append(new Tile('M'));
		assertEquals(0, tileRack.getNumberOfTilesNeeded());
		assertEquals("EAQRPLM", tileRack.getHand());
	}
}
