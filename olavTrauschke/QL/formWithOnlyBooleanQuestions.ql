form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean;
    hasBoughtHouse: "Did you by a house in 2010?" boolean;
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for higher than 1000000 USD?" boolean;
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
    }
}
