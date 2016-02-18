// Invalid because of cyclic dpendency

form form1 {
    q1: "q1" q2
    q2: "q2" q1
}

#->

form form1 {
    q1: "q1" money(q1)
}