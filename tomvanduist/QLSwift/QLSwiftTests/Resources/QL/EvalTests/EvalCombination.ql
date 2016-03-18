form form1 {
    q1: "q1" 1

    if (true) {
        q2: "q2" q1 + 1

        if (true) {
            q3: "q3" q2 + 10
            q4: "q4" q2 < 3 && q2 <= 3 && q2 <= 2 && q2 >= 1 && q2 > 1
            q5: "q5" q3 * 1000 / 100
        }
    }
}