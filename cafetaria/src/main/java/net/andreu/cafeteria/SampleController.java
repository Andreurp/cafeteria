package net.andreu.cafeteria;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;


public class SampleController implements Initializable {
	@FXML
	private ComboBox<Cafes> cbxCafes;
	@FXML
	private ComboBox<Ingredients> cbxIngredients;
	@FXML
	private Button btnMes;
	@FXML
	private ListView<Ingredients> lvTotalIngrdients;
	@FXML
	private Label lblPreuCafe;
	@FXML
	private Button btnAfegirCafe;
	@FXML
	private ListView<Linia> lvCafes;
	@FXML
	private Label lblTotalComanda;
	@FXML
	private Button btnAcabar;
	
	EntityManager em;
	List<Cafes> LlistaCafes = new ArrayList<>();
	ObservableList<Cafes> cafesCbx = FXCollections.observableArrayList();
	List<Ingredients> LlistaIngredients = new ArrayList<>();
	ObservableList<Ingredients> ingredientsCbx = FXCollections.observableArrayList();
	ObservableList<Ingredients> itemsIngredientsLv = FXCollections.observableArrayList();
	int preuIngredients = 0;
	int preuComanda = 0;
	
	ObservableList<Linia> itemsCamanda = FXCollections.observableArrayList();
	
	Comanda comanda = new Comanda();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafetaria");		
		em = emf.createEntityManager();
		
		TypedQuery<Cafes> totsElsCafes= em.createQuery("SELECT c FROM Cafes c", Cafes.class);
		LlistaCafes = totsElsCafes.getResultList();
		
		//cbxCafes.setValue("Tria un cafè");
		for(Cafes c : LlistaCafes){
			cafesCbx.add(c);
		}
		cbxCafes.setItems(cafesCbx);
		
		
		TypedQuery<Ingredients> totsElsIngredients= em.createQuery("SELECT i FROM Ingredients i", Ingredients.class);
		LlistaIngredients = totsElsIngredients.getResultList();
		
		//cbxIngredients.setValue("Tria ingredients");
		for(Ingredients i : LlistaIngredients){
			ingredientsCbx.add(i);
		}
		cbxIngredients.setItems(ingredientsCbx);
	}
	
	// Event Listener on Button[#btnMes].onMouseClicked
	@FXML
	public void AfegirIngredient(MouseEvent event) {
		int preuObtingut = 0;
		
		Ingredients igredientSelecionat = cbxIngredients.getSelectionModel().getSelectedItem();
		
		itemsIngredientsLv.add(igredientSelecionat);
		lvTotalIngrdients.setItems(itemsIngredientsLv);
		
		preuObtingut = igredientSelecionat.getPreu();
		preuIngredients += preuObtingut;
		
		lblPreuCafe.setText(""+preuIngredients);
	}
	
	// Event Listener on Button[#btnAfegirCafe].onMouseClicked
	@FXML
	public void AfegirCafe(MouseEvent event) {
		Linia linia = new Linia();
		List<Linia> liniaComanda = new ArrayList<>();
		List<Ingredients> ingradientsTriats = new ArrayList<>();
		
		Cafes cafeTriat = cbxCafes.getSelectionModel().getSelectedItem();
		linia.setCafe(cafeTriat);
		
		for(Ingredients i : itemsIngredientsLv){
			ingradientsTriats.add(i);
		}
		linia.setIngredients(ingradientsTriats);
		
		itemsCamanda.add(linia);
		lvCafes.setItems(itemsCamanda);
		
		preuComanda += preuIngredients;
		lblTotalComanda.setText(""+preuComanda);
		
		preuIngredients = 0;
		lblPreuCafe.setText(""+preuIngredients);
		
		liniaComanda.add(linia);
		comanda.setLinies(liniaComanda);
		
		/*int posicioCafe = 0;
		Cafes cafeTriat = cbxCafes.getSelectionModel().getSelectedItem();
		
		if(cafeTriat.equals("Tria un cafè")){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("No has triat cap cafè");
			alert.showAndWait();
		}else{
			/*while (!cafeTriat.equals(LlistaCafes.get(posicioCafe).getNom()) && posicioCafe < LlistaCafes.size()) {
				posicioCafe++;
			}
			linia.setCafe(LlistaCafes.get(posicioCafe));
			
			for(int i=0; i<itemsIngredients.size(); i++){
				cafeTriat+= ", "+itemsIngredients.get(i);
			}
			
			itemsCamanda.add(cafeTriat);
			lvCafes.setItems(itemsCamanda);;
			
			preuComanda += preuIngredients;
			lblTotalComanda.setText(""+preuComanda);
		}*/
	}
	
	// Event Listener on Button[#btnAcabar].onMouseClicked
	@FXML
	public void AcabaComanda(MouseEvent event) {
		
		em.getTransaction();
        em.persist(comanda);
        em.getTransaction().commit();
        
	}
}
