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


  //OK
    if (1 + 1 || true && true){
        "Q5"
          Q5: money
    }

}