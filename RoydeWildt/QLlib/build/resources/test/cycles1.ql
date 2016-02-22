/*
 * Fail cases:
 * Q1
 * Q4
 */
 
 form cycles1 {
  //Fail
  "Q1"
    Q1: money = (Q1)

  //OK
  "Q2"
    Q2: money = (Q3)

  //OK
  "Q3"
    Q3: money = (Q4)

  //Fail
  "Q4"
    Q4: money = (Q2)

  //OK
  "Q5"
    Q5: money = (Q6)

  //OK
  "Q6"
    Q6: money = (1)
}