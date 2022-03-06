package control;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Balance;
import model.BalanceData;


public class MainWindow implements Initializable{

    @FXML
    private TableView<Balance> BalanceTable;

    @FXML
    private TableColumn<Balance, String> amountTC;

    @FXML
    private TableColumn<Balance, String> descriptionTC;

    @FXML
    private TableColumn<Balance, String> typeTC;

    @FXML
    private TableColumn<Balance, String> dateTC;

    @FXML
    private TextField amountTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField typeTF;
    
    @FXML
    private ComboBox<String> typeCB;

    @FXML
    private DatePicker dateTF;
    
    @FXML
    private DatePicker initDate;

    @FXML
    private DatePicker finalDate;
    
    @FXML
    private Label ingresoLbl;

    @FXML
    private Label gastoLbl;

    @FXML
    private Label saldoLbl;
    
    private Balance stClicked;
    public static ArrayList<Integer> gastos = new ArrayList();
    public static ArrayList<Integer> ingresos = new ArrayList();

    
	public void DefineBalance() {
		
		if(typeCB.getSelectionModel().getSelectedItem().equals("Gasto")) {
			System.out.println("Gasto");			
			int actual = Integer.parseInt(amountTF.getText());
			
			
			gastos.add(actual);
		
		}if(typeCB.getSelectionModel().getSelectedItem().equals("Ingreso")) {
			System.out.println("Ingreso");
			int actual = Integer.parseInt(amountTF.getText());
			
			ingresos.add(actual);
			
		}	
		
	}
    
    void Saldo() {

    	int ingreso = 0;
    	int gasto = 0;
    	int saldo = 0;
    	
    	Iterator it = ingresos.iterator();
        while (it.hasNext()) {
                 ingreso = ingreso + (Integer) it.next();
                 System.out.println(ingreso);
        }
        
        
    	Iterator itg = gastos.iterator();
        while (itg.hasNext()) {
                 gasto = gasto + (Integer) itg.next();
                 System.out.println(gasto);
        }
        
    	saldo = ingreso-gasto;
    	System.out.println(saldo);
    	
    	ingresoLbl.setText(Integer.toString(ingreso));
		gastoLbl.setText(Integer.toString(gasto));
		saldoLbl.setText(Integer.toString(saldo));

    	
    }
    
    @FXML
    void addIngGas(ActionEvent event) {
    	
    	int amount = Integer.parseInt(amountTF.getText());
    	String description = descriptionTF.getText();
    	String type = typeCB.getSelectionModel().getSelectedItem();
    	LocalDate myDate = dateTF.getValue();
    	String date = myDate.toString();
   	 	
   	
   	 	Balance st = new Balance(amount,description,type,date);
   	 	BalanceData.data.add(st);
   	 	
   	 	DefineBalance();
   	 	Saldo();
    }
    
    @FXML
    void Delete(ActionEvent event) {
    	BalanceData.data.remove(stClicked);
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	
		amountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	descriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));
    	typeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
    	dateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
    	
    	BalanceData.data.add(new Balance(0,"comida","Gasto","2022-02-22"));
    	
    	
    	BalanceTable.setItems(BalanceData.data);
    	
    	BalanceTable.setOnMouseClicked(event ->{
    		Balance stClicked = BalanceTable.getSelectionModel().getSelectedItem();
    		System.out.println(stClicked.getType());
    	});
    	
    	
    	ArrayList<String>list = new ArrayList<>();
    	Collections.addAll(list, new String[]{"Gasto","Ingreso"});
    	
    	typeCB.getItems().addAll(list);
    	
    }
    
    
    @FXML
    void FilterDate(ActionEvent event) {
    	calculeIngreso(event);
    }
    
    void calculeIngreso(ActionEvent event) {
    	Object evt = event.getSource();
    	if(evt.equals(typeCB)) {
    		System.out.println(typeCB.getSelectionModel().getSelectedItem());
    	}
   	 	
    }
    
    void date() {
    	
		Date now = new Date();
		System.out.println(now);
		
		long uts = now.getTime();
		System.out.println(uts);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowSTR = sdf.format(now);
		System.out.println(nowSTR);
		
		LocalDate myDateInit = initDate.getValue();
		String initDate = myDateInit.toString();
		LocalDate myDateFinal = finalDate.getValue();
		String finalDate = myDateFinal.toString();
		
		try{
			Date f1 = sdf.parse(initDate);
			Date f2 = sdf.parse(finalDate);
			
			if(now.getTime()>f1.getTime()&& now.getTime()<f2.getTime()) {
				System.out.println("Está dentro de las fechas");
			}else {
				System.out.println("No está dentro de ñas fechas");
			}
		}catch(ParseException ex) {
			
		}
    }

}
