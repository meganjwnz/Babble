package edu.westga.cs.babble.controllers;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.NumberStringConverter;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

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
	
	//Binds score to Integer
	public void playerScoreResult() {
		this.playerScore.textProperty().bindBidirectional(this.forceInteger, new NumberStringConverter());
	}
	
	//Adds tiles to the List View
	private void playableTiles() {
		for (int tile = 0; tile < this.tileRack.MAX_SIZE; tile++) {
			this.randomTiles.getItems().add(this.tileRack.tiles().get(tile).getLetter());
		}
	}

	//Adds the maximum number of tiles to the tileRack
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
}
