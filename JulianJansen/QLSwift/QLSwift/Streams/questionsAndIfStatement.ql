form taxOfficeExample {

    // Comment

    /* Comment */

    "What is you name?"
        name: string

    "What was the selling price?"
        sellingPrice: integer

    "Private debts for the sold house:"
        privateDebt: integer

//    "Private debts for the sold house:"
//        privateDebt2: integer

    "Value residue:"
        valueResidue: integer
            = (sellingPrice + 1200)

    "Deb done:"
        debtDone: boolean

    if (2 * privateDebt > sellingPrice && debtDone) {

        "Did pay debt last year:"
            debtLastYear: boolean

        "Did receive money:"
            receiveMoney: boolean

        "Single:"
            single: boolean
    }

    "What was the selling price 2?"
        sellingPrice2: integer

}