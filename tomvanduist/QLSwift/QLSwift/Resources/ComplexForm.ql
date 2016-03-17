form testForm1 {
    booleanResult  : "Show form?" boolean

    if (booleanResult) {
        age     : "Enter age:"     integer

        if (age >= 18) {
            savings     : "Enter your savings:"             integer
            savings1    : "Comulative savings in 1 year:"   savings * 1.02
            savings2    : "Comulative savings in 4 year:"   savings * 1.02 * 1.02 * 1.02 * 1.02

            if (savings2 >= 5000) {
                doInvest    : "You have saved quite a lot of money; are you interested in investing that money into stocks or bonds?"     boolean

                if (doInvest) {
                    risk    : "How much risk are you willing to take? On a scale of 1 (low risk) to 5 (high risk). See http://www.2020directinvest.com.au/investor-education/risk-profiles.aspx for more details" integer

                    if (risk < 1 || risk > 5) {
                        riskError : "Invalid risk category selected" "Please select a risk category of 1 - 5"
                    }
                    if (risk == 1) {
                        bonds : "How much (in %) do you want to invest in bonds?" integer
                    }
                    if (risk == 2) {
                        stocks : "How much (in %) do you want to invest in stocks?" integer
                    }
                    if (risk >= 3 && risk <= 5) {
                        andItsGone : "..aaand its gone!" "Next"
                    }
                }
            }
        }
        if (age < 18) {
            allowance   : "Enter your weekly allowence:"    integer
            allowance1  : "In one yearr you received:"      allowance * 52
            allowance3  : "In three years you received:"    allowance1 * 3
        }
    }
}