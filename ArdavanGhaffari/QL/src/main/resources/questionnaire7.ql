form SalarySurvey{
	name: "Your name pls" string
	lastName: "Your last name pls" string
	myAge: "How old are you?" integer
	bothNames: "Both names" string (name + lastName)
	gotMom: "Do you have a mom? (yes/no)" string
	if(gotMom == "yes"){
		momAge: "What age is ur mom?" integer
		diffAge: "Different age" integer (momAge - myAge)
		if(bothNames == "ArdavanGhaffari"){
			goodBoy: "You are a good boy" boolean
		}	
	} else {
		if (gotMom == "no") {
			gotDad: "Do you have a dad?" boolean
			if (gotDad) {
				dadAge: "What age is ur dad?" integer
				if (dadAge - myAge > 20) {
					goodBoy: "You are a good boy" boolean
				}
			}
		}
	}
	if (2>3 == 3>5) {
		hello: "say hello" string
	}
}
