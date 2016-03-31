form taxOfficeExample{
    bool hasSoldHouse "Did you sell a house in 2010?"
    bool hasBoughtHouse "Did you buy a house in 2010?"
    bool hasMaintLoan "Did you enter a loan?"
    
    if(hasMaintLoan){
        bool reallyHasMaintLoan "Did you really enter a loan?"
    }
    
    if(reallyHasMaintLoan && hasMaintLoan){
        str hasMaintLoanInText "Type Yes"
    }
    
    if(hasSoldHouse && hasMaintLoan){
        int sellingPrice "What was the selling price?"
        int privateDebt "Private debts for the sold house:"
        int valueResidue "Value residue:" sellingPrice-privateDebt
        int valueResidue2 "Value residue2:" valueResidue + 20
        int valueResidue3 "Value residue3:" valueResidue2 + 20
        
        if(valueResidue3 > 100){
            bool areYouSure "Are you sure?"
            
            if(areYouSure){
                int enterAnumber "Please enter a number."
            }
        }
    }
}