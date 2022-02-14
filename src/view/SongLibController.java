package view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class SongLibController {
	@FXML
	ListView<String> listView;

	private ObservableList<String> obsList;

	public void start(Stage mainStage) {
		// create an ObservableList
		// from an ArrayList
		List<Song> SongList = new ArrayList<>();
		SongList.add(new Song( "Hot Tub", "Yung Gravy","Cake and Cognac", 2022));
		SongList.add(new Song("Mukkbang","Lil Cherry", "", 2021));
		obsList = FXCollections.observableArrayList();
		for(int i = 0; i < SongList.size(); i++) {
			obsList.add(SongList.get(i).getName());
		}

		listView.setItems(obsList);

		// select the first item
		listView.getSelectionModel().select(0);

		// set listener for the items
		listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) ->
		// showItem(mainStage)
		showItemInputDialog(mainStage));

	}

	private void showItem(Stage mainStage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(mainStage);
		alert.setTitle("List Item");
		alert.setHeaderText("Selected list item properties");

		String content = "Index: " + listView.getSelectionModel().getSelectedIndex() + "\nValue: "
				+ listView.getSelectionModel().getSelectedItem();

		alert.setContentText(content);
		alert.showAndWait();
	}

	private void showItemInputDialog(Stage mainStage) {
		String item = listView.getSelectionModel().getSelectedItem();
		int index = listView.getSelectionModel().getSelectedIndex();

		TextInputDialog dialog = new TextInputDialog(item);
		dialog.initOwner(mainStage);
		dialog.setTitle("List Item");
		dialog.setHeaderText("Selected Item (Index: " + index + ")");
		dialog.setContentText("Enter name: ");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			obsList.set(index, result.get());
		}
	}

}

class Song implements Comparable<Song>{
	private String NAME;
	private String ARTIST;
	private String ALBUM;
	private int YEAR;
	public Song(String name, String artist) {
		this.NAME = name.toUpperCase();
		this.ARTIST = artist.toUpperCase();
	}
	public Song(String name, String artist, String album, int year) {
		this.NAME = name.toUpperCase();
		this.ARTIST = artist.toUpperCase();
		this.ALBUM = album.toUpperCase();
		this.YEAR = year;
	}
	public String getName() {
		return this.NAME;
	}
	public String getArtist() {
		return this.ARTIST;
	}
	public String getAlbum() {
		return this.ALBUM;
	}
	public int getYear() {
		return this.YEAR;		
	}
	public void setName(String name) {
		this.NAME = name;
	}
	public void setArtist(String artist) {
		this.ARTIST = artist;
	}
	public void setAlbum(String album) {
		this.ALBUM = album;
		
	}
	public void setYear(int year) {
		this.YEAR = year;	
	}
	public int compareTo(Song s)
    {
        if (this.NAME.compareTo(s.NAME) > 0) {
            return 1;
        }
        else if (this.NAME.equals(s.NAME)) {
            return 0;
        }
        else {
            return -1;
        }
    }
	
}

    


