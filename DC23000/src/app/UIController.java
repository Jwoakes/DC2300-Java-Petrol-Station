package app;


import java.awt.TextArea;

import javafx.fxml.FXML;
import javafx.scene.control.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class UIController {
	@FXML
	private ToggleButton TrucksEnabledBtn;
	@FXML
	private Button StartSimBtn;
	@FXML
	private Button RunMinBtn;
	@FXML
	private Button RunHourBtn;
	@FXML
	private Button FinishSimBtn;
	@FXML
	private TextField FuelPriceTxt;
	@FXML
	private TextField  NumberOfPumpsTxt;
	@FXML
	private TextArea MinsPastTxt;
	@FXML
	private TextArea HoursPastTxt;
	@FXML
	private TextArea TicksPastTxt;
	@FXML
	private TextField ShopCustTxt;
	@FXML
	private TextField ShopSpendTxt;
	@FXML
	private TextField ProfitMadeTxt;
	@FXML
	private TextField ProfitLostTxt;
	@FXML
	private TextField FuelSoldTxt;
	@FXML
	private TextField FuelLostTxt;
	@FXML
	private TextField BikesTxt;
	@FXML
	private TextField SmallCarsTxt;
	@FXML
	private TextField FamilySedanTxt;
	@FXML
	private TextField TrucksTxt;
	@FXML
	private BarChart ProfitLossGraph;
	@FXML
	private BarChart VehicleGraph;
	
	public void initialize() {
	}
	
	public void tick() {
		
	}
	
}
