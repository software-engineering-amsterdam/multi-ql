form taxOfficeExample { 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: money = -2.2
    

  if (hasSoldHouse) {
    "What was the selling price2?"
      sellingPrice: money
    "Private debts for the sold house2:"
      privateDebt: money
    "Value residue2:"
      valueResidue: money = 2 +
        (sellingPrice - privateDebt)
  } else if (hasBoughtHouse) {
      "What was the selling price3?"
      	sellingPrice: money   
  } else {
      "What was the selling price4?"
      	sellingPrice: money   
  }
  
}