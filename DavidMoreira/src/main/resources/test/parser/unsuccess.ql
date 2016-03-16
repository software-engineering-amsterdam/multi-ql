form taxOfficeExample { 
  
  // type int2 doen't exist
  "Did you buy a house in 2010?"
    hasBoughtHouse: int2
  
  // can't compare in an assignment
  "Did you enter a loan?"
    hasMaintLoan: boolean != 2 + 2 * 4
    
	// can't assign in a if-statement
	if(hasMaintLoan = 2){
		
		// can't have multiple scopes of if-statements
   	 	if(true){
   	 	  
   	 	}
		
		// can't have a label without quotes
		Did you buy a house in 2010?
   	 	  hasBoughtHouse: int
	}
	
}