package ql2;

public class Currency {
	
	private char currency;
	private String mnemonicCurrency;
	private double amount;
	
	public Currency(double amount) {
		setAmount(amount);
	}
	
	public Currency(double amount, char curr) {
		setAmount(amount);
		setCurrency(curr);
	}
	
	public char getCurrency() {
		return currency;
	}

	public void setCurrency(char currency) {
		this.currency = currency;
	}

	public String getMnemonicCurrency() {
		return mnemonicCurrency;
	}

	public void setMnemonicCurrency(String mnemonicCurrency) {
		this.mnemonicCurrency = mnemonicCurrency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
