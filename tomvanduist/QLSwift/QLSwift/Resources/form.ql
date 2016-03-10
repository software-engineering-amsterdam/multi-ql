form testForm1 {
    booleanResult1 : "Boolean result" booleanResult
    booleanResult  : "Enter boolean" boolean

    if (booleanResult) {
        age     : "Enter age:"     integer

        if (age >= 18) {
            savings     : "Enter your savings:"     integer
            savings1    : "Comulative savings in 1 year:"   savings + (savings / 20)
            savings2    : "Inflation corrected:"            savings1 - (savings1 / 50)
        }
        if (age < 18) {
            allowance   : "Enter your weekly allowence:"    integer
            allowance1  : "In one yearr you received:"      allowance * 52
            allowance3  : "In three years you received:"    allowance1 * 3
        }
    }
}