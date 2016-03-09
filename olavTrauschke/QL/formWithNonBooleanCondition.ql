form Box1HouseOwning {
    hasSoldHouse: "How much money did you make by selling houses in 2010?" money;
    hasBoughtHouse: "Did you by a house in 2010?" boolean;
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money;
        valueResidue: "Value residue:" money(sellingPrice - privateDebt);
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
    }
}
