form Box1HouseOwning {
	hasSoldHouse: "Did you sell a house in 2010" boolean
	if (hasSoldHouse) {
		sellingPrice: "Price the house was sold for:" money
		privateDebt: "Private debts for the sold house:" money
		valueResidue: "Value residue:" money(sellingPrice - privateDebt)
	}
	if (valueResidue < 500) {
		hello1: "hello" string
		hello2: "hello again" string
		if (hello1 == hello2) {
			hello3: "hello equals" boolean
		}
	}
	hasBoughtHouse: "Did you by a house in 2010?" boolean
	hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
	
}