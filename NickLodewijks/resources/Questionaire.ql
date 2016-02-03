form taxOfficeExample{
    bool hasSoldHouse "Did you sell a house in 2010?"
    bool hasBoughtHouse "Did you buy a house in 2010?"
    bool hasMaintLoan "Did you enter a loan?"
    
    if(hasSoldHouse && hasMaintLoan){
        int sellingPrice "What was the selling price?"
        int privateDebt "Private debts for the sold house:"
        int valueResidue "Value residue:"
    }
}