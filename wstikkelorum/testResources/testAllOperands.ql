form testAllOperands {
	summation: int "2 plus 4" (2 + 4)
	multiplication: int "summation times 2" (summation * 2)
	division: int "summation divided by 3" (summation / 2)
	subtraction: int "10 minus 1" (10 - 1)
	positive: int "just 4" (+4)
	negative: int "just minus 4" (-4)
	
	not: boolean "not true" (!true)
	or: boolean "not or true" (not || true)
	and: boolean "true and true" (true && true)
	
	less: boolean "5 < 9" (5 < 9)
	lessEquals: boolean "5 <= 5" (5 <= 5)
	greater: boolean "1>0" (1 > 0)
	greaterEquals: boolean "1 >= 1" (1 >= 1)
	equals: boolean "summation == summation" (summation == summation)
	notEquals: boolean "positive != negative" (positive != negative)
}