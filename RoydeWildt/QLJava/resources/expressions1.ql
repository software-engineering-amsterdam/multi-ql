/*
 * Fail cases:
 * true && 1
 * true + true
 * !1
 * -true
 * Q3_boolean + 1
 * Q1_money == true
 * Q1_money + Q3_boolean
 * Q2_money == Q4_boolean
 */

form conditions1 {
  "Q1"
    Q1_money: money
  "Q2"
    Q2_money: money
  "Q3"
    Q3_boolean: boolean
  "Q4"
    Q4_boolean: boolean

  //OK
  if (1){
      "Q5"
        Q5: money
  }

  //OK
  if (!true){
        "Q5"
          Q5: money
  }

  //OK
  if (-1){
        "Q5"
          Q5: money
  }
  //FAIL
  if (!1){
        "Q5"
          Q5: money
  }

  //FAIL
  if (-true){
      "Q5"
        Q5: money
  }

  //OK
  if (1 + 1){
    "Q5"
      Q5: money
  }

  //OK
  if (true && true){
      "Q5"
        Q5: money
  }

  //FAIL
  if (true && 1){
      "Q5"
        Q5: money
  }

  //FAIL
  if (true + true){
      "Q5"
        Q5: money
  }

   //FAIL
   if (Q3_boolean + 1){
      "Q5"
        Q5: money
   }

    //FAIL
    if (Q1_money == true){
        "Q5"
          Q5: money
    }


   //OK
   if (Q1_money + 1){
      "Q5"
        Q5: money
   }

    //OK
    if (Q3_boolean == true){
        "Q5"
          Q5: money
    }

  //OK
  if (Q1_money + Q2_money) {
    "Q5"
      Q5: money
  }

  //OK
  if (Q3_boolean == Q4_boolean) {
      "Q5"
        Q5: money
  }

  //FAIL
  if (Q1_money + Q3_boolean) {
    "Q5"
      Q5: money
  }

  //FAIL
  if (Q2_money == Q4_boolean) {
    "Q5"
      Q5: money
  }

}