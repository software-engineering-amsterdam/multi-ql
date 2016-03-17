form personalDetails {
    name    : "Name:" string
    age     : "Age:" integer
    country : "Country:" string

    if (name && age && country) {
        _name    : "Name" name
        _age     : "Age" age
        _country : "Country" country
    }
}