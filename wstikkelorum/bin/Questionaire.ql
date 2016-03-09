form Box1HouseOwning {
	hasSoldHouse: boolean "Did you sell a house in 2010?" 
	hasBoughtHouse: boolean "Did you by a house in 2010?"
	hasMaintLoan: boolean "Did you enter a loan for maintenance/reconstruction?"
	
	if(hasSoldHouse){
		hasSoldCar: int "How much?" (2000)
		
			if(laterDeclaredVariable){
				number10: int "2 times 5" (2 * 5)
				
				if(2<3){
					number5: int "just 10/2" (10 / 2) 
				}
			}
	}
	
	laterDeclaredVariable: boolean "This should work" (true)
}