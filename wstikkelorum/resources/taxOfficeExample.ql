form taxOfficeExample {
	hasSoldHouse: boolean "Did you sell a house in 2010?"
	hasBougthHouse: boolean "Did you buy a house in 2010?"
	hasMaintLoan: boolean "Did you enter a loan?"
	
	if(hasSoldHouse){
		sellingPrice: int "What was the selling price?"
		privateDebt: int "Private debts for the sold house?"
		valueResidue: int "Value redidue" (sellingPrice - privateDebt)
	}
	
	if(hasMaintLoan){
		name: string "Under which name was the loan?"
	}
}