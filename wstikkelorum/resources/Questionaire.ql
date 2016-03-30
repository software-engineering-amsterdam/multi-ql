form Box1HouseOwning {
	boolA: boolean "condition" 
	soldhouse: boolean "sold house?"
	
	if(!boolA){
		boolB: boolean "condition B" (true)
	}
	
	if(soldhouse){
		house: int "how much was the house?"
		var: int "house including taxes: " (2*house)
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