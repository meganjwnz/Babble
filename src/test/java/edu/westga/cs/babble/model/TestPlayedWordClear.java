package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for class Tile's constructor
 * @author Megan Brown
 * @version 8/28/18
 */
public class TestPlayedWordClear {
	
	@Test
	public void shouldClearEmptyWord() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.clear();
		assertEquals(true, playedWord.tiles().isEmpty());
		assertEquals("", playedWord.getHand());
		assertEquals(0, playedWord.getHand().length());
		
	}
	
	@Test
	public void shouldClearWordWithOneTile() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('I'));
		assertEquals(false, playedWord.tiles().isEmpty());
		assertEquals("I", playedWord.getHand());
		assertEquals(1, playedWord.getHand().length());
		
		playedWord.clear();
		
		assertEquals(true, playedWord.tiles().isEmpty());
		assertEquals("", playedWord.getHand());
		assertEquals(0, playedWord.getHand().length());
	}
	
	@Test
	public void shouldClearWordWithManyTiles() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('I'));
		playedWord.append(new Tile('F'));
		playedWord.append(new Tile('M'));
		playedWord.append(new Tile('N'));
		playedWord.append(new Tile('I'));
		playedWord.append(new Tile('F'));
		playedWord.append(new Tile('W'));
		assertEquals(false, playedWord.tiles().isEmpty());
		assertEquals("IFMNIFW", playedWord.getHand());
		assertEquals(7, playedWord.getHand().length());
		
		playedWord.clear();
		
		assertEquals(true, playedWord.tiles().isEmpty());
		assertEquals("", playedWord.getHand());
		assertEquals(0, playedWord.getHand().length());
	}
}
