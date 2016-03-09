form taxOfficeExample {
  "What is your name?"
    name: text
  "Your name is:"
    nameis: text = (name)
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Dit you give it away for free?"
    forFree: boolean


  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
    if (name == "roy"){
      "You get everything for free my man"
        free: boolean = (true)
    }
  }

}