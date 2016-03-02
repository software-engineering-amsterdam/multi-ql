form testForm1 {
    booleanResult   : "Enter boolean"   boolean
    booleanResult1  : "Result:"         booleanResult

    if (booleanResult1) {
        moneyResult     : "Enter money"     money
        moneyResult1    : "Result:"         moneyResult

        stringResult    : "Enter string"    string
        stringResult1   : "Result:"         stringResult
    }
}

/*
form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    hasBoughtHouse: "Did you by a house in 2010?" 2
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" money(hasBoughtHouse)

    if (hasMaintLoan > 0) {
        sellingPrice: "Price the house was sold for:" money
        privateDebt: "Private debts for the sold house:" (hasBoughtHouse * 10)
        valueResidue: "Value residue:" money(privateDebt + privateDebt)
    }
}
*/