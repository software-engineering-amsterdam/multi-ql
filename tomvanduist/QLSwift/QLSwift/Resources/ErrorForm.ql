form formWithErrors {
    dutch       : "Are you a Dutch" boolean
    typeError   : "Type Error"      1 + dutch

    multipleDeclarationError    : "What is your name?"          string
    multipleDeclarationError    : "MultipleDeclarationError"    integer

    age                     : "What is your age?"       cyclicDependencyError
    cyclicDependencyError   : "Cyclic Dependency Error" 1 + age

    outOfScopeError : "Out of scope" student
    if (true) {
        student : "Are you a student?" boolean
    }
}