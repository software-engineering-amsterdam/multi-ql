form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean = (true == true)
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean

  if (!hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:Did you buy a house in 2010?Did you buy a house in 2010?Did you buy a house in 2010?Did you buy a house in 2010?Did you buy a house in 2010?"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (100 - 10)
  }

}