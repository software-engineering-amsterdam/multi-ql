form gui3 {
  "Q1"
    Q1: boolean
  "Q2"
    Q2: boolean = (true)

  if (Q1) {
    if (!Q1) {
      "Q4_hidden"
        Q4_hidden: money
    }
  }

  if (Q2) {
    if (!Q1) {
      "Q5_visible"
        Q5_visible: money
    }
  }

}