form Box1HouseOwning {
	if (hasSoldHouse) {
		sellingPrice: "Price the house was sold for:" money (valueResidue)
		privateDebt: "price the house was sold for:" money
		publicDebt: "public debts for the sold house:" money (sellingPrice - 1000)
		valueResidue: "Value residue:" boolean (publicDebt - privateDebt)
	}
}