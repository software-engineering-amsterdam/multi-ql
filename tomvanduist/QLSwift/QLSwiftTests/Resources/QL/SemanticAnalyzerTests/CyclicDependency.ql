// Invalid because of cyclic dpendency

form form1 {
    q1: "q1" q2
    q2: "q2" q1
}

#->

form form1 {
    q1: "q1" q1
}

#->

form form1 {
    q1: "q1" qx

    q2: "q2" q1
    q3: "q3" (1 + q2)

    if (q1 >= 1) {
        qx: "q4" q3
    }
}