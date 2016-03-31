form EvaluationTestForm {
	hasSoldHouse: boolean "Did you sell a house?"
	if(!hasSoldHouse){
		age: int "How old are you?" 
	}
}