// Invalid because declarations are out of scope

form form1 {
    q1: "q1" boolean
    q2: "q2" q3 // Out of scope

    if (q1) {
        q3: "q3" boolean
    }
}

#->

form form1 {
    q1: "q1" boolean

    if (q1) {
        q2: "q2" q1

        if (q2) {
            q3: "q3" boolean
        }

        q4: "q4" q3 // Out of scope
    }
}

#->

form form1 {
    q1: "q1" boolean

    if (q1 && q2) { // Out of scope
        q2: "q2" boolean
    }
}