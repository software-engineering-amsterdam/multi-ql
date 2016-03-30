form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean;
    hasBoughtHouse: "Did you buy a house in 2010?" boolean;
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money;
        remainingMortgage: "Mortgage remaining after selling:" money;
        valueResidue: "Value residue:" money(sellingPrice - remainingMortgage);
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
    }
}
