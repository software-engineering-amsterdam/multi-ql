form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean;
    hasBoughtHouse: "Did you buy a house in 2010?" boolean;
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money;
        valueResidue: "Value residue:" money(sellingPrice - privateDebt);
        duplicateLabel: "Is this label duplicate?" boolean;
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
        duplicateLabel: "Is this label duplicate?" boolean;
    }
}
