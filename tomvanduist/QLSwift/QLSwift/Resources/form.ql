form testForm1 {
    booleanResult1 : "Boolean result" booleanResult
    booleanResult  : "Enter boolean" boolean

    if (booleanResult) {
        moneyResult     : "Enter money"     integer
        moneyResult1    : "Result:"         moneyResult

        stringResult    : "Enter string"    string
        stringResult1   : "Result:"         stringResult    // TODO: test error on overriding with different type
    }
}

/*
form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    hasBoughtHouse: "Did you by a house in 2010?" 2
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" money(hasBoughtHouse)

    if (hasMaintLoan > 0) {
        sellingPrice: "Price the house was sold for:" integer
        privateDebt: "Private debts for the sold house:" (hasBoughtHouse * 10)
        valueResidue: "Value residue:" money(privateDebt + privateDebt)
    }
}
*/