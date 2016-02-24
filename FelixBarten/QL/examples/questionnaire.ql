form taxOfficeExample { 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean


}


form postOfficeExample { 
  "Did you send a package in 2015?"
    hasSentPackages: boolean
  "Did you receive any packages in 2015?"
    hasReceivedPackages: boolean
  "How many packages did you receive in 2015?" 
  	amountOfPackagesReceived : integer

  "How much did you sell your house for?"
  	howMuchHouse: money
  "How much did you make in 2013?"
  	howMuchIncome: money
  "Total amount of money made from income and house sales?"
  	totalIncome: money = ( howMuchHouse + howMuchIncome)
}