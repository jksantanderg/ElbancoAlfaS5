package model;

import java.util.stream.Stream;

public class Balance {
	private int amount;
	private String description;
	private String type;
	private String date;
	
	public Balance(int amount, String description, String type, String date) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
