package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BalanceData {
	public static ObservableList<Balance> data = FXCollections.observableArrayList();
	
	public static ArrayList<Balance> balances;
	
	
	public void addMovie(Balance balance) {
		balances.add(balance);
	}
	
}
