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
          "Value residue:"
               valueResidue: money = 
                    (sellingPrice - privateDebt) + birthDate
          "Private debts for the sold house:"
               privateDebt: money
          "Parents gift money:"
               giftMoneys: money
          "Parents gift money:"
               giftMoney: money
     }
     else {
          "Did you buy a car?"
               carPrice: money
     }
     
     if (age && 5.1) {
          "Do you have children?"
               children: boolean
     }
     
     "Did you steal in 2010?"
          hasStolen: boolean
          
     if (hasStolen && true) {
          "How much did you steal?"
               stealingAmount: money
          "Did you hurt anyone in the process?"
               hurtAnyone: boolean
          "Did you get caught?"
               gotCaught: boolean
          "Did you get caught?"
               gotCaught: money
     }

}