form form1 {
    q1: "q1" boolean
    q2: "q2" true
    q4: "q4" (q3 * 2)
    q3: "q3" 1
    q5: "q5" string

    if (q1 && q2 && true) {
        q6: "q6" q4 > q3
        q7: "q7" q6 != q2
    }
    if (q3 > q4 && q1 && q2 && "test" == q5 && q4 >= 5.1) {
        q6: "q6" q4 > q3
        q7: "q7" q6 != q2
    }
}