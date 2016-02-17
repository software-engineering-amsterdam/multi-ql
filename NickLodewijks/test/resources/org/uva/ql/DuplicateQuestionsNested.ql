form taxOfficeExample{

    // Result should be: 4 warnings, 2 errors.
    
    // Duplicate questions nested in if statements (2 warnings)
    int privateDebt "DUPLICATE_QUESTION_NESTED"
    if(hasSoldHouse){
        int privateDebt1 "DUPLICATE_QUESTION_NESTED"
        if(hasBoughtHouse){
            int privateDebt2 "DUPLICATE_QUESTION_NESTED"
        }
    }
    
    
    // Duplicate questions with different types nested in if statements (2 warnings, 2 errors)
    bool qtn1 "DUPLICATE_QUESTION_DIFFERENT_TYPE_NESTED"
    if(hasSoldHouse){
        int qtn2 "DUPLICATE_QUESTION_DIFFERENT_TYPE_NESTED"
        if(hasBoughtHouse){
            str qtn3 "DUPLICATE_QUESTION_DIFFERENT_TYPE_NESTED"
        }
    }
}