form conditions1 {
  "Q1"
    Q1_money: money
  "Q2"
    Q2_money: money
  "Q3"
    Q3_boolean: boolean
  "Q4"
    Q4_boolean: boolean


  if (Q1_money + Q2_money) {
    "Q5"
      Q5: money
  }

  if (Q3_boolean == Q4_boolean) {
      "Q5"
        Q5: money
  }

  if (Q1_money + Q3_boolean) {
    "Q5"
      Q5: money
  }

  if (Q2_money == Q4_boolean) {
    "Q5"
      Q5: money
  }

  if (1 + 1){
    "Q5"
      Q5: money
  }

  if (true && true){
      "Q5"
        Q5: money
    }

}