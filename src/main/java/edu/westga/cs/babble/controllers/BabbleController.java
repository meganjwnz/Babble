package edu.westga.cs.babble.controllers;

import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileRack;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class BabbleController {
	
	private PlayedWord word;
	private TileBag tileBag;
	private TileRack tileRack;
	private WordDictionary dictionary;
	
	@FXML
	private Button reset;
	
	@FXML
	private Button playWord;
	
	@FXML
	private ListView randomTiles;
	
	@FXML
	private ListView playedTiles;
	
	@FXML
	private TextField playerScore ;
	
	public BabbleController() {
		this.word = new PlayedWord();
		this.tileBag = new TileBag();
		this.tileRack = new TileRack();
		this.dictionary = new WordDictionary();
	}
	
	public void startGame() {
		
	}
}
