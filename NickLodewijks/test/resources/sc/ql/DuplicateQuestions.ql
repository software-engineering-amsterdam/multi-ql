form taxOfficeExample{

    // Result should be: 4 warnings, 2 error.

    // Two duplicate question labels (1 warning)
    bool hasSoldHouse1 "DUPLICATE_QUESTION_ONE"
    bool hasSoldHouse2 "DUPLICATE_QUESTION_ONE"
    
    // Three duplicate question labels (1 warning)
    bool hasBoughtHouse1 "DUPLICATE_QUESTION_TWO"
    bool hasBoughtHouse2 "DUPLICATE_QUESTION_TWO"
    bool hasBoughtHouse3 "DUPLICATE_QUESTION_TWO"
    
    // Two duplicate question labels with different types (1 warning)
    int sellingPrice1 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
    str sellingPrice2 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
    
    // Two duplicate question names with different types (1 error)
    int sellingPrice1 "DUPLICATE_NAME_DIFFERENT_TYPE1"
    str sellingPrice1 "DUPLICATE_NAME_DIFFERENT_TYPE2"
    
    // Two duplicate question names with different types (1 warning, 1 error)
    int dup3 "DUPLICATE_NAME_LABEL_DIFFERENT_TYPE"
    str dup3 "DUPLICATE_NAME_LABEL_DIFFERENT_TYPE"
}