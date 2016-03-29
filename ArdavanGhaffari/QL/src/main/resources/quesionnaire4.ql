form Box {
	A: "A" string ("ardavan")
	G: "G" string ("bbb")
	F: "F" boolean (! A)
	if (A && G) {
		B: "B" boolean (10 > true)
		C: "C" boolean (A < G)
		D: "C" boolean (!B)
		E: "E" boolean (B || F)
	}
}