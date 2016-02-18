/*
 * Fail cases:
 * Q1
 */
 
 form cycles1 {
  //Fail
  "Q1"
    Q1: money = (Q1)

  "Q2"
    Q2: money = (Q3)

  //Ok
  "Q3"
    Q3: money = (Q2)


}