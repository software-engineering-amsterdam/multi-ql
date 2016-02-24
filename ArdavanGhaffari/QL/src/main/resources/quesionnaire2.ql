form Box1HouseOwning {
	hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean (!2)
	if (hasSoldHouse) {
		sellingPrice: "Price the house was sold for:" money
		privateDebt: "Private debts for the sold house:" money
		valueResidue: "Value residue:" boolean (false - privateDebt)
	}
}