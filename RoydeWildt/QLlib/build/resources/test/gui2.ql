form gui2 {
  "Q1"
    Q1: boolean
  "Q2"
    Q2: boolean = (true)
  "Q3"
    Q3: boolean

  if (Q1) {
    "Q4_hidden"
      Q4_hidden: money
  }

  if (Q2) {
      "Q5_visible"
        Q5_visible: money
    }

}