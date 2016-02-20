form taxOfficeExample { 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: money = -2.2
    

  if (true) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money = 2 +
        (sellingPrice - privateDebt)
  } else if (true) {
      "What was the selling price?"
      	sellingPrice: money   
  } else {
      "What was the selling price?"
      	sellingPrice: money   
  }
  
}