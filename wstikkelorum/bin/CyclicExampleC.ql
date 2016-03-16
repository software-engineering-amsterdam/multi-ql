form CyclicExampleC {
	A: int "Dependent on B" (B + 1)
	B: int "Dependent on C" (C + 1)
	C: int "Dependent on B" (B + 1)
}