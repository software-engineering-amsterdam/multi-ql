form smallExampleForTesting{
	hasSoldHouse: boolean "Did you sell a house in 2010?"
	stringvar: string "What is your name?"
	intvar: int "How old are you?"
	
	if(hasSoldHouse){
		showSold: boolean "Did you sell a house in 2010?" (hasSoldHouse)
		showName: string "What is your name?" (stringvar)
		showAge: int "How old are you?" (intvar)
	}
}