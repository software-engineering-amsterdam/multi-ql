form Box1HouseOwning {
	boolA: boolean "condition" 
	soldhouse: boolean "sold house?"
	if(boolA){
		boolB: boolean "condition B" (true)
	}
	
	if(soldhouse){
		var: int "2 time 5 equals: " (2*5)
	}
}