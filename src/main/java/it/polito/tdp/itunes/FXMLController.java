/**
 /**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.bilancioAlbum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenze"
    private Button btnAdiacenze; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<Album> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA2"
    private ComboBox<Album> cmbA2; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doCalcolaAdiacenze(ActionEvent event) {
    	
    	Album a = cmbA1.getValue();
    	
    	if (a == null) {
    		txtResult.setText("Please select an element form combo box. \n");
    		return;
    	}
    	
    	List<bilancioAlbum> bilanci = model.getAdiacenti(a);
    	
		txtResult.setText("Printing successors of node " + a + ". \n");
		for (bilancioAlbum b : bilanci) {
			txtResult.appendText(b + "\n");
		}
    	
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    	if (!(model.getNumVertices()>0)) {
    		txtResult.setText("Graph not created.");
    		return;
    	}

    	String input = txtX.getText();    	
    	
    	if (input == "") {
    		txtResult.setText("Input string for X is empty.");
    		return;
    	}
    	
    	try {
			int inputNum = Integer.parseInt(input);
			
			Album source = cmbA1.getValue();
			Album target = cmbA2.getValue();
			
			if(source == null || target == null){
	    		txtResult.setText("Please select Album from combo box.");
	    		return;
	    	}
			
			List<Album> path = model.getPath(source, target, inputNum);
			
			if (path.isEmpty()) {
	    		txtResult.setText("No path between " + source + " and " + target );
	    		return;
			}
			
			txtResult.setText("Printing the path between " + source + " and " + target + "\n" );
			
			for (Album a : path)
				txtResult.appendText(""+a + "\n");
			
			
		} catch (NumberFormatException e) {
    		txtResult.setText("Input string for X is not a vaid number.");
		}
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String input = txtN.getText();
    	
    	if (input == "") {
    		txtResult.setText("Input string for N is empty.");
    		return;
    	}
    	
    	try {
			int inputNum = Integer.parseInt(input);
			model.buildGraph(inputNum);
			int numV = model.getNumVertices();
			int numE = model.getNumEdges();
			
    		txtResult.setText("Graph correctly created.\n");
    		txtResult.appendText("Number Vertices: " + numV + "\n");
    		txtResult.appendText("Number Edges: " + numE);

			cmbA1.getItems().setAll(model.getVertices());
			cmbA2.getItems().setAll(model.getVertices());
			
		} catch (NumberFormatException e) {
    		txtResult.setText("Input string for N is not a vaid number.");
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenze != null : "fx:id=\"btnAdiacenze\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA2 != null : "fx:id=\"cmbA2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    
    public void setModel(Model model) {
    	this.model = model;
    }
}