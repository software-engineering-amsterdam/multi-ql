form Box1HouseOwning {
	hasSoldHouse: boolean "Did you sell a house in 2010?" 
	hasBoughtHouse: boolean "Did you by a house in 2010?"
	hasMaintLoan: boolean "Did you enter a loan for maintenance/reconstruction?"
	age: int "How old are you?"
	if (hasSoldHouse) {
		sellingPrice: int "Price the house was sold for:" 
		privateDebt: int "Private debts for the sold house:"
		valueResidue: int "Value residue:" (2 - 3)
		test: boolean "example wrong expression:" (3+2*1<9)
		booltest: boolean "This should be accepted" (true)
		booltest: int "duplicate with different type"
	}
	
	if(laterDeclared){
		fault: boolean "this does not work" (true < 2)
	}
	
	laterDeclared: boolean "This should be accepted"
	
	if(unknownVariable){
	}
}