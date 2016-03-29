form taxOfficeExample { 

     "What is your age?"
          age: integer
     "What is your date of Birth?"
          birthDate: date
     "Did you sell a house in 2010?"
          hasSoldHouse: boolean
     "Did you buy a house in 2010?"
          hasBoughtHouse: boolean
     "Did you enter a loan?"
          hasMaintLoan: boolean
     
     if (hasSoldHouse) {
          "What was the selling price?"
               sellingPrice: money
          "Private debts for the sold house:"
               privateDebt: money
          "Value residue:"
               valueResidue: money = 
                    sellingPrice - privateDebt
          "Parents gift money:"
               giftMoney: money
     }
     else {
          "Did you buy a car?"
               carPrice: money
     }
     

}