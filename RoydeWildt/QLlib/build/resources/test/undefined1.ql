/*
 * Fail cases:
 * Q4_undefined
 * Q5_scope
 */
form undefined1 {
  "Q1"
    Q1: boolean
  "Q2"
    Q2: boolean
  "Q3"
    Q3: boolean

  if (Q3 && Q4_Undefined) {
    "Q5"
      Q5_scope: boolean
  }

  if (Q5_scope) {
    "Q6"
       Q6: money
  }

}