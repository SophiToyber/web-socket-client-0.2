package appearance.application.ui;

import java.awt.TextField;

import javax.annotation.PostConstruct;

import com.sun.javafx.text.TextLine;

import javafx.fxml.FXML;

public class ConnectRoomController {

	@FXML private TextField nameTextLine;
	@FXML private TextField expectedRoomTextLine;
	
	public void initialize() {
		// JavaFX initialization phase
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
	}

}
