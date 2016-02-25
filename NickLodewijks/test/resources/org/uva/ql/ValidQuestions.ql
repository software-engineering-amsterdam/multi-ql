form taxOfficeExample{

    // Result should be: 0 warnings, 0 errors

    // Three valid questions that have nothing in common.
    bool v1 "VALID_QUESTION1"
    str v2 "VALID_QUESTION2"
    int v3 "VALID_QUESTION3"
    
    // Duplicate names, but same type.
    bool dupNameSameTypeBool "DUPLICATE_NAME_SAME_TYPE_BOOL1"
    bool dupNameSameTypeBool "DUPLICATE_NAME_SAME_TYPE_BOOL2"
    bool dupNameSameTypeBool "DUPLICATE_NAME_SAME_TYPE_BOOL3" v1
    
    str dupNameSameTypeStr "DUPLICATE_NAME_SAME_TYPE_STR1"
    str dupNameSameTypeStr "DUPLICATE_NAME_SAME_TYPE_STR2"
    str dupNameSameTypeStr "DUPLICATE_NAME_SAME_TYPE_STR3" v2
    
    int dupNameSameTypeInt "DUPLICATE_NAME_SAME_TYPE_INT1"
    int dupNameSameTypeInt "DUPLICATE_NAME_SAME_TYPE_INT2"
    int dupNameSameTypeInt "DUPLICATE_NAME_SAME_TYPE_INT3" v3
    
    
    // Simple computation
    int q1 "INPUT_1_QUESTION1"
    int q2 "INPUT_2_QUESTION1" 
    int q3 "COMPUTED_QUESTION1" q1+q2 
    
    // Nested boolean questions, computation using question in other scope.
    bool b1 "BOOLEAN_QUESTION"
    if(b1){
        bool b2 "SECOND_BOOLEAN_QUESTION"
    }
    
    if(b2){
        bool b3 "THIRD_BOOLEAN_QUESTION"
    }
    
    bool b4 "COMPUTED_BOOLEAN_QUESTION" b1 && b2
    
        
}