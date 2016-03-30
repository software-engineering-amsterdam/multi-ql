form Box1HouseOwning {
	hasSoldHouse: "Did you sell a house in 2010?" boolean
	if (hasSoldHouse) {
		amountSold: "What is the amount?" money
		if (amountSold > 1000) {
			hello: "hello" boolean (amountSold < 2000)
		}
	}
}