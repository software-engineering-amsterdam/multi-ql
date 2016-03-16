// Illegal questions

form form1 {
    q1: 1 boolean
}

#->

form form1 {
    "": 1 boolean
}

#->

form form1 {
    q1: q1 boolean
}

#->

form form1 {
    q1: "text" text"
}