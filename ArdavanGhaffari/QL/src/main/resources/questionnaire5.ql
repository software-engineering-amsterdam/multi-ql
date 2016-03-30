form Box {
	A: "A" boolean (true)
	if (A) {
		ID1: "1" integer (ID2 + ID3)
	} else
	{
		ID1: "1" boolean (ID4 + ID5)
	}
	ID2: "2" integer (ID1)
	ID9: "2" integer (3 + 2)
}