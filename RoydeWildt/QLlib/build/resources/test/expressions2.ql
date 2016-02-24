/*
 * Fail cases:
 * 1 + 1 || true && true
 * true || true || 1
 * <1: Q2_money + Q2_money == Q3_boolean> && Q3_boolean != Q4_boolean
 * <1: Q1_boolean + 1> == Q1_money &&  <2: Q3_boolean != 1>
 */

form conditions2 {
  "Q1"
    Q1_money: money
  "Q2"
    Q2_money: money
  "Q3"
    Q3_boolean: boolean
  "Q4"
    Q4_boolean: boolean

  //FAIL
  if (1 + 1 || true && true){
    "Q5"
      Q5: money
  }

  //FAIL
  if (true || true || 1){
      "Q5"
        Q5: money
  }

  //FAIL
    if (1 + 1 || true && true){
      "Q5"
        Q5: money
    }

    //OK
    if (true || 1 > 0){
        "Q5"
          Q5: money
    }

    //OK
    if (1 * 10 > 1 + 10 &&  1 * 10 == 100 - 100 + 100){
          "Q5"
            Q5: money
    }

    //OK
    if (true != false && false != true){
          "Q5"
            Q5: money
    }

    //OK
    if (true != false && 1 * 10 > 1 + 10){
          "Q5"
            Q5: money
    }

    //OK
    if (Q2_money + Q2_money == Q1_money && Q3_boolean != Q4_boolean){
          "Q5"
            Q5: money
    }

    //FAIL
    if (Q2_money + Q2_money == Q3_boolean && Q3_boolean != Q4_boolean){
          "Q5"
            Q5: money
    }

    //OK
    if (Q2_money + 1 == Q1_money && Q3_boolean != true){
          "Q5"
            Q5: money
    }

    //FAIL
    if (Q3_boolean + 1 == Q1_money && Q3_boolean != 1){
          "Q5"
            Q5: money
    }
}