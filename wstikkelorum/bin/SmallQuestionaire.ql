form smallExampleForTesting{
	hasSoldHouse: boolean "Did you sell a house in 2010?"
	if(hasSoldHouse){
		valueResidue: int "Value redisue" (200 - 103)
	}
}