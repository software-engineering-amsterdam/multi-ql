form form1 {
    _string: "s" string
    _integer: "i" integer
    _boolean: "b" boolean


    _boolean_inferred_0: "b" _boolean
    _boolean_inferred_1: "b" _boolean && _boolean_inferred_0
    _boolean_inferred_2: "b" "d" == _string
    _boolean_inferred_3: "b" (_integer + 1) > 5


    _integer_inferred_0: "i" 1 + 1
    _integer_inferred_1: "i" _integer_inferred_0
}