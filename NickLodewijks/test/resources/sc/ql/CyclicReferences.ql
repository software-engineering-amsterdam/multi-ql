form cyclicDependency{

    // Expected output: 3 errors: 
    // - found cyclic dependency between (a -> b -> c -> a)
    // - found cyclic dependency between (b -> c -> a -> b)
    // - found cyclic dependency between (c -> a -> b -> c)
    int a "QUESTION_A" b
    int b "QUESTION_B" c
    int c "QUESTION_C" a
    
    
    // Expected output: 2 errors:
    // - found cyclic dependency between (e -> f -> e)
    // - found cyclic dependency between (f -> e -> f)
    int d "NESTED_QUESTION_D"
    if(true){
        int e "NESTED_QUESTION_E" f
        int f "NESTED_QUESTION_F" d + e
    }
    
    // Expected output: 1 error: 
    // - found cyclic dependency between (g -> g)
    int g "SELF_REFERENCE_QUESTION" g
    
}