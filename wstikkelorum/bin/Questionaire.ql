form Box1HouseOwning {
	boolA: boolean "condition" 
	soldhouse: boolean "sold house?"
	
	if(!boolA){
		boolB: boolean "condition B" (true)
	}
	
	if(soldhouse){
		var: int "2 time 5 equals: " (2*5)
		if(boolA){
			name: string "what is your name?"
		}
	}
	
	pname: string "Your name is: " (name)
	
	inta: int "must be greater than 5"
	intb: int "must be smaller than 3"
	
	if(inta > 5 && intb < 3){ 
		surname: string "What is your last name?"
	}
}