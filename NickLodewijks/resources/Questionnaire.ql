form taxOfficeExample{
    bool hasSoldHouse "Did you sell a house in 2010?"
    bool hasBoughtHouse "Did you buy a house in 2010?"
    bool hasMaintLoan "Did you enter a loan for maintenance/reconstruction?"
    
    if(hasMaintLoan){
        bool reallyHasMaintLoan "Did you really do this?"
    }
    
    if(reallyHasMaintLoan){
        str hasMaintLoanInText "Type Yes to confirm."
    }
    
    if(hasSoldHouse){
        int sellingPrice "Price the house was sold for:"
        int privateDebt "Private debts for the sold house:"
        int valueResidue "Value residue:" sellingPrice-privateDebt
        int valueResidue2 "Value residue2:" valueResidue + 20
        int valueResidue3 "Value residue3:" valueResidue2 + 20
    }
    
    if(valueResidue3 > 1000){
        bool hasMoreDebt "Did you have any other debts?"
            
		if(hasMoreDebt){
        	int moreDebt "Other debts: "
        	
        	if(moreDebt > 500){
        		str moreDebtCause "Explain why this is more than 500e: "
        	}
    	}
    }
}