package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * tests for the TileGroup#append() method
 * 
 * @author Megan Brown
 *
 */
public class TestTileGroupAppend {

	private class DummyGroup extends TileGroup {
	}

	@Test
	public void shouldNotAllowNull() {
		DummyGroup dummy = new DummyGroup();
		assertThrows(IllegalArgumentException.class, () -> {
			dummy.append(null);
		});
	}

	@Test
	public void emptyGroupShouldBeEmpty() {
		DummyGroup dummy = new DummyGroup();
		assertTrue(dummy.getHand().isEmpty());
	}

	@Test
	public void shouldHaveOneTileInTileGroup() {
		DummyGroup dummy = new DummyGroup();
		TileBag tile = new TileBag();
		try {
			dummy.append(tile.drawTile());
		} catch (EmptyTileBagException e) {
			e.printStackTrace();
		}
		assertTrue(dummy.getHand().length() == 1);
		assertFalse(dummy.getHand().isEmpty());
	}

	@Test
	public void shouldHaveManyTilesInTileGroup() {
		DummyGroup dummy = new DummyGroup();
		TileBag tile = new TileBag();
		String results = "";
		dummy.append(new Tile('E'));
		dummy.append(new Tile('A'));
		dummy.append(new Tile('P'));
		dummy.append(new Tile('Q'));
		dummy.append(new Tile('L'));
		dummy.append(new Tile('M'));
		dummy.append(new Tile('Z'));
		dummy.append(new Tile('O'));
		dummy.append(new Tile('E'));
		dummy.append(new Tile('E'));
		assertTrue(dummy.getHand().length() == 10);
		for(Tile tiles : dummy.tiles()) {
			 results += tiles.getLetter() + "";
		}
		assertEquals("EAPQLMZOEE", results);
		assertFalse(dummy.getHand().isEmpty());
	}

	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		DummyGroup dummy = new DummyGroup();
		TileBag tile = new TileBag();
		String duplicateValues = "";
		for (int i = 0; i < 15; i++) {
			dummy.append(new Tile('E'));
		}
		for (int i = 0; i < dummy.getHand().length(); i++) {
			duplicateValues += dummy.tiles().get(i).getLetter();
		}
		assertEquals("EEEEEEEEEEEEEEE", duplicateValues);
	}

	@Test
	public void canNotAddSameTileTwice() {
		DummyGroup dummy = new DummyGroup();
		TileBag tileBag = new TileBag();
		Tile tile = new Tile('B');
		assertThrows(IllegalArgumentException.class, () -> {
			if (tile.getLetter() == 'B') {
				dummy.append(tile);
				dummy.append(tile);
			}
			assertTrue(dummy.getHand().length() == 12);
			String duplicate = "";
			while (!dummy.tiles().isEmpty()) {
				for (int i = 0; i < dummy.getHand().length(); i++) {
					duplicate += dummy.tiles().get(i).getLetter();
				}
			}
		});

	}
}
