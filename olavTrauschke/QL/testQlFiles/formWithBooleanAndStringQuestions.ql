form Box1HouseOwning {
    name: "What's your name?" string;
    nameFeedback: "Your name is:" string(name);
    hasSoldHouse: "Did you sell a house in 2010?" boolean;
    hasBoughtHouse: "Did you buy a house in 2010?" boolean;
    hasSoldOrBoughtHouse: "You bought or sold a house in 2010:" boolean(hasSoldHouse || hasBoughtHouse);
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for higher than 1000000 USD?" boolean;
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
    }
}
