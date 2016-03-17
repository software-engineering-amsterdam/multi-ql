form form1 {
    _float: "f" float
    _string: "s" string
    _integer: "i" integer
    _boolean: "b" boolean


    _boolean_inferred_0: "b" _boolean
    _boolean_inferred_1: "b" _boolean && _boolean_inferred_0
    _boolean_inferred_2: "b" "d" == _string
    _boolean_inferred_3: "b" (_integer + 1) > 5


    _integer_inferred_0: "i" 1 + 1
    _integer_inferred_1: "i" _integer_inferred_0


    _float_inferred_0: "f" 1.0 + 1
    _float_inferred_1: "f" _float_inferred_0
    _float_inferred_2: "f" 1 + _float
}