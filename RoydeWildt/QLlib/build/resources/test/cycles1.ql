/*
 * Fail cases:
 * Q1
 * Q2
 */
 
 form cycles1 {
  //Fail
  "Q1"
    Q1: money = (Q1)

  //Fail
  "Q2"
    Q2: money = (Q3)

  //OK
  "Q3"
    Q3: money = (Q2)


}