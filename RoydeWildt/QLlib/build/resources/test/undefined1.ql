/*
 * Fail cases:
 * Q4_undefined
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
      Q5_outscope: boolean
  }

  if (Q5_outscope) {
    "Q6"
       Q6: money
  }

  if (Q7_after) {
      "Q6"
         Q6: money
    }

  "Q7"
      Q7_after: boolean

}