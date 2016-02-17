form taxOfficeExample{

    // Result should be: 3 warnings, 1 error.

    // Two duplicate questions (1 warning)
    bool hasSoldHouse1 "DUPLICATE_QUESTION_ONE"
    bool hasSoldHouse2 "DUPLICATE_QUESTION_ONE"
    
    // Two duplicate questions (1 warning)
    bool hasBoughtHouse1 "DUPLICATE_QUESTION_TWO"
    bool hasBoughtHouse2 "DUPLICATE_QUESTION_TWO"
    
    // Duplicate question with different types (1 warning, 1 error)
    int sellingPrice1 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
    str sellingPrice2 "DUPLICATE_QUESTION_DIFFERENT_TYPE"
}