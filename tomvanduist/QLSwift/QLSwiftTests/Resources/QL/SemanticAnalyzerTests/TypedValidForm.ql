form form1 {
    q1: "q1" boolean
    q2: "q2" true
    q4: "q4" (q3 * 2)
    q3: "q3" 1
    q5: "q5" string

    if (q1 && q2 && true) {
    }
    if (q3 > q4 && q1 && q2 && "test" == q5) {
    }
}