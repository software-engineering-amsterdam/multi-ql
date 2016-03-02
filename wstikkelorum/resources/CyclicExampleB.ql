form CyclicExampleB{
	identA: int "Dependent on identB" (identB + 1)
	identB: int "Dependent on identA" (identA + 1)
	
}