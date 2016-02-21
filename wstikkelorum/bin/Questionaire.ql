form Box1HouseOwning {
	hasSoldHouse: boolean "Did you sell a house in 2010?" 
	hasBoughtHouse: boolean "Did you by a house in 2010?"
	hasMaintLoan: boolean "Did you enter a loan for maintenance/reconstruction?"
	age: int "How old are you?"
	if (sellingPrice) {
		sellingPrice: int "Price the house was sold for:" 
		privateDebt: int "Private debts for the sold house:"
		valueResidue: int "Value residue:" (2 - 3)
		test: boolean "example wrong expression:" (3+2*1<9)
	}
	
	if(test){
		hasSoldHouse: int "Did you sell a house in 2010?"
	}
	
	if(2<test){
	}
}