form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean;
    hasBoughtHouse: "Did you by a house in 2010?" boolean;
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money;
        valueResidue: "Value residue:" int(5 - 1);
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
    }
}
