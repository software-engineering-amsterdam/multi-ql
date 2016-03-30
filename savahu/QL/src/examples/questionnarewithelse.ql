form taxOfficeExample { 

     age: "What is your age?" integer
     birthDate: "What is your date of Birth?" integer
     hasSoldHouse: "Did you sell a house in 2010?" boolean
     hasBoughtHouse: "Did you buy a house in 2010?" boolean
     hasMaintLoan: "Did you enter a loan?" boolean
     if (hasSoldHouse) {
          sellingPrice: "What was the selling price?" money
          valueResidue: "Value residue:" money(sellingPrice - privateDebt)
          privateDebt: "Private debts for the sold house:" money
          giftMoneys: "Parents gift money:" money
          giftMoney: "Parents gift money:" money
     }
     else {
          carPrice: "For what price did you buy a car?" money
     }
     if (age && 5) {
          children: "Do you have children?" boolean
     }
     hasStolen: "Did you steal in 2010?" boolean
     if (hasStolen && true) {
          stealingAmount: "How much did you steal?" money
          hurtAnyone: "Did you hurt anyone in the process?" boolean
          gotCaught: "Did you get caught?" boolean
          gotCaught: "Did you get caught?" money
     }

}