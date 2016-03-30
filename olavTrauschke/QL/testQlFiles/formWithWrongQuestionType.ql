form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean;
    hasBoughtHouse: "Did you buy a house in 2010?" boolean;
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money;
        valueResidue: "Value residue:" money(sellingPrice - privateDebt);
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
    }
    test1: "Test 1" string;
    test2: "Test 2" string;
    test3: "Test 3" decimal (test1 + test2);
}
