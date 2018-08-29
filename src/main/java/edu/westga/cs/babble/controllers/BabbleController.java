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

public class BabbleController {

	private PlayedWord word;
	private TileBag tileBag;
	private TileRack tileRack;
	private WordDictionary dictionary;
	private IntegerProperty forceInteger;

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

	public BabbleController() {
		this.word = new PlayedWord();
		this.tileBag = new TileBag();
		this.tileRack = new TileRack();
		this.dictionary = new WordDictionary();
		this.forceInteger = new SimpleIntegerProperty(0);
	}

	public void initialize() {
		this.createRandomTiles();
		this.playableTiles();
		this.displaySelection();
		this.playerScoreResult();

	}

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
		this.playedTiles.setItems(this.word.tiles());
		this.playedTiles.setCellFactory(new TileManager());
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
	 * Clears the word
	 */
	public void clear() {
		this.word.clear();
	}

	/**
	 * Resets the tiles back to the rack
	 */
	public void resetTiles() {
		this.reset.setOnMouseClicked(event -> {
			List<Tile> letters = new ArrayList<Tile>(this.word.tiles());
			for (Tile letter : letters) {
				try {
					this.word.remove(letter);
				} catch (TileNotInGroupException tnige) {
					tnige.printStackTrace();
				}
				this.tileRack.append(letter);
			}
		});
	}

	// *************** helper methods ******************

	/**
	 * Generates random tiles
	 * 
	 * @throws EmptyTileBagException
	 */
	private void createRandomTiles() {
		for (int numberOfTilesInRack = 0; numberOfTilesInRack < this.tileRack.MAX_SIZE; numberOfTilesInRack++) {
			if (this.tileBag.isEmpty()) {
				return;
			}
			Tile tile = null;
			try {
				tile = this.tileBag.drawTile();
			} catch (EmptyTileBagException e) {
				e.printStackTrace();
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
}
