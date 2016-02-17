/*
 * Fail cases:
 * 1
 * Q1_money
 * Q3_null
 */

form conditions1 {
  "Q1"
    Q1_money: money
  "Q2"
    Q2_boolean: boolean

  //Fail
  if (1){
      "Q5"
        Q5: money
  }

  //OK
  if (true){
      "Q5"
        Q5: money
  }

  //Fail
    if (Q1_money){
        "Q5"
          Q5: money
    }

    //OK
    if (Q2_boolean){
        "Q5"
          Q5: money
    }
    //Fail
    if (Q3_null){
        "Q5"
          Q5: money
    }

}