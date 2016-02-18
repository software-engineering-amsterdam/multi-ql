/*
 * Fail cases:
 * Q2
 */

 form cycles1 {
  //OK
  "Q1"
    Q1: money = (Q1)

  //Fail
  "Q1"
    Q2: money = (Q3)

}