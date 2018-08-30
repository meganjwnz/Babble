package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * tests for the PlayedWord#getScore() method
 * @author Megan Brown
 *
 */
public class TestPlayedWordGetScore {
	
	@Test
	public void emptyWordShouldHaveScoreOfZero() {
		PlayedWord playedWord = new PlayedWord();
		assertEquals(0, playedWord.getScore());
	}
	
	@Test
	public void scoreAOneTileWord() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('I'));
		assertEquals(1, playedWord.getScore());
	}
	
	@Test
	public void scoreAWordWithMultipleDifferingTiles() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('P'));
		playedWord.append(new Tile('I'));
		playedWord.append(new Tile('G'));
		assertEquals(6, playedWord.getScore());
	}
	
	@Test
	public void scoreAWordContainingDuplicateTiles() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('P'));
		playedWord.append(new Tile('I'));
		playedWord.append(new Tile('G'));
		playedWord.append(new Tile('G'));
		playedWord.append(new Tile('Y'));
		assertEquals(12, playedWord.getScore());
	}
}
