package edu.westga.cs.babble.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * tests for the PlayedWord#matches() method
 * @author Megan Brown
 *
 */
public class TestPlayedWordMatches {
	
	@Test
	public void hasTilesForAMultipleLetterWord() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('P'));
		playedWord.append(new Tile('I'));
		playedWord.append(new Tile('G'));
		assertTrue(playedWord.matches("PIG"));
	}
	
	@Test
	public void hasTilesForASingleLetterWord() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('I'));
		assertTrue(playedWord.matches("I"));
	}
	
	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('G'));
		playedWord.append(new Tile('P'));
		playedWord.append(new Tile('I'));
		assertFalse(playedWord.matches("PIG"));
	}
	
	@Test
	public void cannotMatchWordIfInsufficientTiles() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('G'));
		playedWord.append(new Tile('P'));
		assertFalse(playedWord.matches("PIG"));
	}
	
	@Test
	public void canMatchWordWithDuplicateLetters() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('P'));
		playedWord.append(new Tile('I'));
		playedWord.append(new Tile('G'));
		playedWord.append(new Tile('G'));
		playedWord.append(new Tile('Y'));
		assertTrue(playedWord.matches("PIGGY"));
	}
	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {
		PlayedWord playedWord = new PlayedWord();
		playedWord.append(new Tile('P'));
		playedWord.append(new Tile('I'));
		playedWord.append(new Tile('G'));
		assertFalse(playedWord.matches(""));
	}
	
	@Test
	public void emptyWordShouldNotMatchEmptyText() {
		PlayedWord playedWord = new PlayedWord();
		assertFalse(playedWord.matches(""));
	}
	
	@Test
	public void shouldNotAllowNull() {
		PlayedWord playedWord = new PlayedWord();
		assertThrows(IllegalArgumentException.class, () -> {
			playedWord.matches(null);
		});
	}
}
