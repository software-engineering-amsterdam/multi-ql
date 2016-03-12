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
      "What was the selling price3124123412341234?"
      	sellingPrice3: int   
  }
  
  if (true){
    "What was the describe price5?"
      sellingPrice5: string
    "What was the selling price6?"
      sellingPrice6: int
    "What was the selling price7?"
      sellingPrice7: int
    "What was the selling price8?"
      sellingPrice8: int
    "What was the selling price9?"
      sellingPrice9: int
    "What was the selling price10?"
      sellingPrice10: int
      
      /*
          "What was the selling price11?"
      sellingPrice11: int
          "What was the selling price12?"
      sellingPrice12: int
          "What was the selling price13?"
      sellingPrice13: int
          "What was the selling price14?"
      sellingPrice14: int
          "What was the selling price15?"
      sellingPrice15: int
          "What was the selling price16?"
      sellingPrice16: int
      */
  }
}