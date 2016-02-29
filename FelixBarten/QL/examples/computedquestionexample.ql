  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you sell a house in 2011?"
    hasSoldHouse2: boolean
  "Did you sell a house in 2013?"
    hasSoldHouse3: boolean = (hasSoldHouse2)
  "How much did you sell your house for?"
  	howMuchHouse: money
  "How much did you make in 2013?"
  	howMuchIncome: money
  "Total amount of money made from income and house sales?"
  	totalIncome: money = ( howMuchHouse + howMuchIncome)