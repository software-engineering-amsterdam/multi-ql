form taxOfficeExample{

     // Result should be: 9 warnings, 4 errors.

    // Two duplicate question labels (2 warnings)
    bool hasSoldHouse1 "DUPLICATE_QUESTION_ONE"
    if(true){
        bool hasSoldHouse2 "DUPLICATE_QUESTION_ONE"
    }
    
    // Three duplicate question labels (3 warnings)
    bool hasBoughtHouse1 "DUPLICATE_QUESTION_TWO"
    if(true){
        bool hasBoughtHouse2 "DUPLICATE_QUESTION_TWO"
        if(false){
            bool hasBoughtHouse3 "DUPLICATE_QUESTION_TWO"
        }
    }
    
    // Two duplicate question labels with different types (2 warnings)
    int sellingPrice1 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
    if(true){
        str sellingPrice2 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
    }
    
    // Two duplicate question names with different types (2 errors)
    int sellingPrice3 "DUPLICATE_NAME_DIFFERENT_TYPE1"
    if(false){
        str sellingPrice3 "DUPLICATE_NAME_DIFFERENT_TYPE2"
    }
    
    
    // Two duplicate question names with different types (2 warnings, 2 errors)
    int dup3 "DUPLICATE_NAME_LABEL_DIFFERENT_TYPE"
    if(false){
        if(true){
            if(true){
                str dup3 "DUPLICATE_NAME_LABEL_DIFFERENT_TYPE"
            }
        }
    }
}