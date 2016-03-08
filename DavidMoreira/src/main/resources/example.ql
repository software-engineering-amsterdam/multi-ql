form taxOfficeExample { 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: int = 2 + 2 * 4

  if (hasSoldHouse == true) {
    "What was the selling price2?"
      sellingPrice: int
    "Private debts for the sold house2:"
      privateDebt: int
    "Value residue2:"
      valueResidue: int = 2 +
        (sellingPrice - privateDebt)
    "Did you enter a loan2?"
      hasMaintLoan2: int = 2 + 2 * 4
  } else if (hasBoughtHouse) {
      "What was the selling price3?"
      	sellingPrice3: int   
  }
  
  if (true){
    "What was the selling price5?"
      sellingPrice5: int
  }
  
}