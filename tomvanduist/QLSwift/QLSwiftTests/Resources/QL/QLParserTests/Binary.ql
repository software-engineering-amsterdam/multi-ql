form form1 {
    qBool: "text" boolean
    qInt: "text" 1

    qCalcMoney: "text" (qInt + 1)
    qCalcMoney: "text" (1 + qInt)
    qCalcMoney: "text" (qInt - 1)
    qCalcMoney: "text" (1 - qInt)
    qCalcMoney: "text" (qInt * 2)
    qCalcMoney: "text" (2 * qInt)
    qCalcMoney: "text" (1 / 1)
    qCalcMoney: "text" (qInt / qInt)
    qCalcMoney1: "text" (1 ^ 1)
    qCalcMoney2: "text" (qInt ^ qInt)

    if (qInt < 1) {}
    if (1 < qInt) {}
    if (qInt > 1) {}
    if (1 > qInt) {}
    if (qInt <= 1) {}
    if (1 <= qInt) {}
    if (qInt >= 1) {}
    if (1 >= qInt) {}
    if (qInt == 1) {}
    if (1 == qInt) {}

    if (qBool == true) {}
    if (true == qBool) {}
    //if (qBool != true) {} //TODO: add when test in place
    //if (true != qBool) {}
    if (qBool && true) {}
    if (true && qBool) {}
    if (qBool || true) {}
    if (true || qBool) {}

    if (qInt < qInt + 1) {}
    if (qInt < (qInt + 1)) {}
    if (qInt + 1 * 6 < (qInt + 1)) {}
    if (true && true || false || qBool) {}
    if (!true && true && (-qInt < qInt) && !(-qInt * 2)) {}
}