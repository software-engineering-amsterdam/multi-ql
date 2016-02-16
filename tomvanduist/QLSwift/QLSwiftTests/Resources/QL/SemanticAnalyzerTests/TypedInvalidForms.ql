form form1 {
    q1: "q1" boolean
    q2: "q2" 1

    if (q1 && q2) {
    }
}

#->

form form1 {
    q1: "q1" boolean
    q2: "q2" money(q1 + 1)
}

#->

form form1 {
    if (1 > true) {
    }
}

#->

form form1 {
    if ("1" == 1) {
    }
}
