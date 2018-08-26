package edu.westga.cs.babble.controllers;

import javafx.util.Callback;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;

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
		this.playerScoreResult();
	}

	// Binds score to Integer
	public void playerScoreResult() {
		this.playerScore.textProperty().bindBidirectional(this.forceInteger, new NumberStringConverter());
	}

	// Adds the maximum number of tiles to the tileRack
	private void createRandomTiles() {
		for (int numberOfTilesInRack = 0; numberOfTilesInRack < this.tileRack.MAX_SIZE; numberOfTilesInRack++) {
			if (this.tileBag.isEmpty()) {
				return;
			}
			Tile tile = null;
			try {
				tile = this.tileBag.drawTile();
			} catch (EmptyTileBagException etbe) {
				etbe.printStackTrace();
			}
			this.tileRack.append(tile);
		}
	}

	private class TileManager implements Callback<ListView<Tile>, ListCell<Tile>> {
		public ListCell<Tile> call(ListView<Tile> listView) {
			TextFieldListCell<Tile> cellFactory = new TextFieldListCell();
			cellFactory.setConverter(new StringConverter() {
				
				@Override
				public String toString(Object tile) {
					String letter = ((Tile) tile).getLetter() + "";
					return letter;
				}

				@Override
				public Object fromString(String string) {
					return null;
				}
				
			});
			return cellFactory;
		}
	}

	// Adds tiles to the List View
	private void playableTiles() {
		this.randomTiles.setItems(this.tileRack.tiles());
		this.randomTiles.setCellFactory(new TileManager());
		this.randomTiles.setOnMouseClicked(new EventHandler() {

			@Override
			public void handle(Event event) {
				Tile selection = (Tile) BabbleController.this.randomTiles.getSelectionModel().getSelectedItem();
				try {
					BabbleController.this.tileRack.remove(selection);
					BabbleController.this.word.append(selection);
				} catch (TileNotInGroupException e) {
					e.printStackTrace();
				}
			}
		});

	}

	// Adds tiles to play area
	public void displaySelection(MouseEvent event) {
		this.playedTiles.setItems(this.word.tiles());
		Tile letter = (Tile) this.randomTiles.getSelectionModel().getSelectedItem();
	}
}
