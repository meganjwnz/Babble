package edu.westga.cs.babble.controllers;

import javafx.util.Callback;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.converter.NumberStringConverter;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import javafx.util.StringConverter;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;

/**
 * Controls all functions of Babble application linked to BabbleGui.fmxl
 * @author Megan Brown
 * @version 8/15/18
 */
public class BabbleController {

	private PlayedWord word;
	private TileBag tileBag;
	private TileRack tileRack;
	private WordDictionary dictionary;
	private IntegerProperty forceInteger;

	//Derived from FXML document
	@FXML
	private Button reset;
	@FXML
	private Button playWord;
	@FXML
	private ListView randomTiles;
	@FXML
	private ListView playedTiles;
	@FXML
	private TextField playerScore;

	/**
	 * Initializes various Babble objects
	 */
	public BabbleController() {
		this.word = new PlayedWord();
		this.tileBag = new TileBag();
		this.tileRack = new TileRack();
		this.dictionary = new WordDictionary();
		this.forceInteger = new SimpleIntegerProperty(0);
	}
	
	/**
	 * Loads all functions of application
	 */
	public void initialize() {
		this.createRandomTiles();
		this.playableTiles();
		this.displaySelection();
		this.playerScoreResult();
		this.playWord();
		this.resetTiles();
	}

	/**
	 * Forces the text to be integer only
	 */
	public void playerScoreResult() {
		this.playerScore.textProperty().bindBidirectional(this.forceInteger, new NumberStringConverter());
	}

	/**
	 * Displays tiles that may be chosen to play
	 */
	public void playableTiles() {
		this.randomTiles.setItems(this.tileRack.tiles());
		this.randomTiles.setCellFactory(new TileManager());
		this.randomTiles.setOnMouseClicked(event -> {
			Tile selection = (Tile) BabbleController.this.randomTiles.getSelectionModel().getSelectedItem();
			try {
				this.tileRack.remove(selection);
				this.word.append(selection);
			} catch (TileNotInGroupException tnige) {
				tnige.printStackTrace();
			}
		});
	}

	/**
	 * Displays the tiles added to the Your Word section
	 */
	public void displaySelection() {
		this.playedTiles.setCellFactory(new TileManager());
		this.playedTiles.setItems(this.word.tiles());
		this.playedTiles.setOnMouseClicked(event -> {
			final Tile selection = (Tile) BabbleController.this.playedTiles.getSelectionModel().getSelectedItem();
			if (selection == null) {
				return;
			}
			try {
				this.word.remove(selection);
				this.tileRack.append(selection);
			} catch (final TileNotInGroupException tnig) {
				tnig.printStackTrace();
			}
		});
	}
	
	/**
	 * Resets the tiles back to the rack
	 */
	public void resetTiles() {
		this.reset.setOnMouseClicked(event -> this.reset());
	}
	
	public void playWord() {
		this.playWord.setOnMouseClicked(event -> this.play());
	}

	// ********************** helper methods and classes **************************

	/**
	 * Generates random tiles
	 * 
	 * @throws EmptyTileBagException
	 */
	private void createRandomTiles() {
		int tilesNeeded = this.tileRack.getNumberOfTilesNeeded();
		for (int numberOfTilesInRack = 0; numberOfTilesInRack < tilesNeeded; numberOfTilesInRack++) {
			if (this.tileBag.isEmpty()) {
				break;
			}
			Tile tile = null;
			try {
				tile = this.tileBag.drawTile();
			} catch (EmptyTileBagException etbg) {
				etbg.printStackTrace();
			}
			this.tileRack.append(tile);
		}
	}

	/**
	 * Manages the cell factory of the tiles. Converts tiles into string so that
	 * they can be displayed in the list pane
	 * 
	 * @author Megan Brown
	 *
	 */
	private class TileManager implements Callback<ListView<Tile>, ListCell<Tile>> {
		public ListCell<Tile> call(ListView<Tile> listView) {
			TextFieldListCell<Tile> cellFactory = new TextFieldListCell<Tile>();
			cellFactory.setConverter(new StringConverter<Tile>() {
				@Override
				public String toString(final Tile tile) {
					final String letter = tile.getLetter() + "";
					return letter;
				}
				@Override
				public Tile fromString(final String string) {
					return null;
				}
			});
			return cellFactory;
		}
	}
	
	/**
	 * Action to play the word
	 */
	private void play() {
		if (this.dictionary.isValidWord(this.word.getHand())) {
			int wordScore = this.word.getScore();
			int newScore = this.forceInteger.get();
			newScore += wordScore;
			this.forceInteger.set(newScore);
			this.word.clear();
			this.createRandomTiles();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.contentTextProperty().set("That is not a word!");
			alert.showAndWait();
		}
	}
	
	/**
	 * Action to reset the tiles
	 */
	private void reset() {
		List<Tile> letters = new ArrayList<Tile>(this.word.tiles());
		for (Tile letter : letters) {
			try {
				this.word.remove(letter);
			} catch (TileNotInGroupException tnige) {
				tnige.printStackTrace();
			}
			this.tileRack.append(letter);
		}
	}
}
