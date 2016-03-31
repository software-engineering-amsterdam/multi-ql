form Box1HouseOwning {
    name: "What's your name?" string;
    nameFeedback: "Your name is:" string(name);
    hasSoldHouse: "Did you sell a house in 2010?" boolean;
    hasBoughtHouse: "Did you buy a house in 2010?" boolean;
    hasSoldOrBoughtHouse: "You bought or sold a house in 2010:" boolean(hasSoldHouse || hasBoughtHouse);
    if (hasSoldHouse) {
        sellingPrice: "For what price did you sell a house?" int;
        buyingPrice: "For what price did you sell the house you bought?" decimal;
        moneyLoss: "Money you lost by selling your house:" decimal(buyingPrice-sellingPrice);
    }
    else {
        wantsToSellHouse: "Do you want to sell a house in 2011?" boolean;
    }
}
