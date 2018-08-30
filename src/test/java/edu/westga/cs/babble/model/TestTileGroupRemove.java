package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class TestTileGroupRemove {
	
	private class DummyGroup extends TileGroup {
	}
	
	@Test
	public void shouldNotAllowNull() {
		DummyGroup dummy = new DummyGroup();
		assertThrows(IllegalArgumentException.class, () -> {
			dummy.remove(null);
		});
	}
	
	@Test
	public void canNotRemoveFromEmptyTileGroup() {
		DummyGroup dummy = new DummyGroup();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			dummy.remove(dummy.tiles().get(0));
		});
	}
	
	@Test
	public void canNotRemoveTileNotInTileGroup() {
		DummyGroup dummy = new DummyGroup();
		Tile tile = new Tile('E');
		assertThrows(TileNotInGroupException.class, () -> {
			dummy.remove(tile);
		});
	}
	
	@Test
	public void canRemoveOnlyTileInTileGroup() {
		DummyGroup dummy = new DummyGroup();
		Tile tile1 = new Tile('E');
		Tile tile2 = new Tile('B');
		dummy.append(tile1);
		dummy.append(tile2);
		assertEquals("EB", dummy.getHand());
		try {
			dummy.remove(tile1);
		} catch (TileNotInGroupException e) {
			e.printStackTrace();
		}
		assertEquals("B", dummy.getHand());
	}
	
	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() {
		DummyGroup dummy = new DummyGroup();
		Tile tile1 = new Tile('E');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('A');
		Tile tile5 = new Tile('D');
		Tile tile6 = new Tile('Z');
		Tile tile7 = new Tile('U');
		Tile tile8 = new Tile('G');
		Tile tile9 = new Tile('P');
		Tile tile10 = new Tile('L');
		dummy.append(tile1);
		dummy.append(tile2);
		dummy.append(tile3);
		dummy.append(tile4);
		dummy.append(tile5);
		dummy.append(tile6);
		dummy.append(tile7);
		dummy.append(tile8);
		dummy.append(tile9);
		dummy.append(tile10);
		assertEquals("EBEADZUGPL", dummy.getHand());
		assertEquals(10, dummy.getHand().length());
		try {
			dummy.remove(dummy.tiles().get(0));
		} catch (TileNotInGroupException e) {
			e.printStackTrace();
		}
		assertEquals("BEADZUGPL", dummy.getHand());
		assertEquals(9, dummy.getHand().length());
	}
	
	@Test
	public void canRemoveLastOfManyTilesFromTileGroup() {
		DummyGroup dummy = new DummyGroup();
		Tile tile1 = new Tile('E');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('A');
		Tile tile5 = new Tile('D');
		Tile tile6 = new Tile('Z');
		Tile tile7 = new Tile('U');
		Tile tile8 = new Tile('G');
		Tile tile9 = new Tile('P');
		Tile tile10 = new Tile('L');
		dummy.append(tile1);
		dummy.append(tile2);
		dummy.append(tile3);
		dummy.append(tile4);
		dummy.append(tile5);
		dummy.append(tile6);
		dummy.append(tile7);
		dummy.append(tile8);
		dummy.append(tile9);
		dummy.append(tile10);
		assertEquals("EBEADZUGPL", dummy.getHand());
		assertEquals(10, dummy.getHand().length());
		try {
			dummy.remove(dummy.tiles().get(9));
		} catch (TileNotInGroupException e) {
			e.printStackTrace();
		}
		assertEquals("EBEADZUGP", dummy.getHand());
		assertEquals(9, dummy.getHand().length());
	}
	
	@Test
	public void canRemoveMiddleOfManyTilesFromTileGroup() {
		DummyGroup dummy = new DummyGroup();
		Tile tile1 = new Tile('E');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('A');
		Tile tile5 = new Tile('D');
		Tile tile6 = new Tile('Z');
		Tile tile7 = new Tile('U');
		Tile tile8 = new Tile('G');
		Tile tile9 = new Tile('P');
		Tile tile10 = new Tile('L');
		dummy.append(tile1);
		dummy.append(tile2);
		dummy.append(tile3);
		dummy.append(tile4);
		dummy.append(tile5);
		dummy.append(tile6);
		dummy.append(tile7);
		dummy.append(tile8);
		dummy.append(tile9);
		dummy.append(tile10);
		assertEquals("EBEADZUGPL", dummy.getHand());
		assertEquals(10, dummy.getHand().length());
		try {
			dummy.remove(dummy.tiles().get(4));
		} catch (TileNotInGroupException e) {
			e.printStackTrace();
		}
		assertEquals("EBEAZUGPL", dummy.getHand());
		assertEquals(9, dummy.getHand().length());
	}
	
	@Test
	public void canRemoveMultipleTilesFromTileGroup() {
		DummyGroup dummy = new DummyGroup();
		Tile tile1 = new Tile('E');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('A');
		Tile tile5 = new Tile('D');
		Tile tile6 = new Tile('Z');
		Tile tile7 = new Tile('U');
		Tile tile8 = new Tile('G');
		Tile tile9 = new Tile('P');
		Tile tile10 = new Tile('L');
		dummy.append(tile1);
		dummy.append(tile2);
		dummy.append(tile3);
		dummy.append(tile4);
		dummy.append(tile5);
		dummy.append(tile6);
		dummy.append(tile7);
		dummy.append(tile8);
		dummy.append(tile9);
		dummy.append(tile10);
		assertEquals("EBEADZUGPL", dummy.getHand());
		assertEquals(10, dummy.getHand().length());
		try {
			dummy.remove(dummy.tiles().get(1));
			dummy.remove(dummy.tiles().get(3));
			dummy.remove(dummy.tiles().get(6));
			dummy.remove(dummy.tiles().get(4));
		} catch (TileNotInGroupException e) {
			e.printStackTrace();
		}
		assertEquals("EEAZGL", dummy.getHand());
		assertEquals(6, dummy.getHand().length());
	}
	
	@Test
	public void doesNotRemoveDuplicateTilesFromTileGroup() {
		DummyGroup dummy = new DummyGroup();
		Tile tile1 = new Tile('E');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('A');
		Tile tile5 = new Tile('D');
		Tile tile6 = new Tile('Z');
		Tile tile7 = new Tile('U');
		Tile tile8 = new Tile('G');
		Tile tile9 = new Tile('P');
		Tile tile10 = new Tile('L');
		dummy.append(tile1);
		dummy.append(tile2);
		dummy.append(tile3);
		dummy.append(tile4);
		dummy.append(tile5);
		dummy.append(tile6);
		dummy.append(tile7);
		dummy.append(tile8);
		dummy.append(tile9);
		dummy.append(tile10);
		assertEquals("EBEADZUGPL", dummy.getHand());
		assertEquals(10, dummy.getHand().length());
		assertThrows(TileNotInGroupException.class, () -> {
			dummy.remove(tile1);
			dummy.remove(tile1);
		});
	}
}
