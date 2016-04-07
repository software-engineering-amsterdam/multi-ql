form taxOfficeExample{

    // Result should be: 9 warnings, 4 error.

    // Two duplicate question labels (2 warnings)
    bool hasSoldHouse1 "DUPLICATE_QUESTION_ONE"
    bool hasSoldHouse2 "DUPLICATE_QUESTION_ONE"
    
    // Three duplicate question labels (3 warnings)
    bool hasBoughtHouse1 "DUPLICATE_QUESTION_TWO"
    bool hasBoughtHouse2 "DUPLICATE_QUESTION_TWO"
    bool hasBoughtHouse3 "DUPLICATE_QUESTION_TWO"
    
    // Two duplicate question labels with different types (2 warnings)
    int sellingPrice1 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
    str sellingPrice2 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
    
    // Two duplicate question names with different types (2 errors)
    int sellingPrice3 "DUPLICATE_NAME_DIFFERENT_TYPE1"
    str sellingPrice3 "DUPLICATE_NAME_DIFFERENT_TYPE2"
    
    // Two duplicate question names with different types (2 warnings, 2 errors)
    int dup3 "DUPLICATE_NAME_LABEL_DIFFERENT_TYPE"
    str dup3 "DUPLICATE_NAME_LABEL_DIFFERENT_TYPE"
}