form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" 1
    hasBoughtHouse: "Did you by a house in 2010?" 2
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" money(hasSoldHouse + hasBoughtHouse)

    if (hasMaintLoan > 0) {
        sellingPrice: "Price the house was sold for:" money
        privateDebt: "Private debts for the sold house:" (hasBoughtHouse * 10)
        valueResidue: "Value residue:" money(privateDebt + privateDebt)
    }
}