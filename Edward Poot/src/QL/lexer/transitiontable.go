
package lexer



/*
Let s be the current state
Let r be the current input rune
transitionTable[s](r) returns the next state.
*/
type TransitionTable [NumStates] func(rune) int

var TransTab = TransitionTable{
	
		// S0
		func(r rune) int {
			switch {
			case r == 9 : // ['\t','\t']
				return 1
			case r == 10 : // ['\n','\n']
				return 1
			case r == 13 : // ['\r','\r']
				return 1
			case r == 32 : // [' ',' ']
				return 1
			case r == 33 : // ['!','!']
				return 2
			case r == 34 : // ['"','"']
				return 3
			case r == 38 : // ['&','&']
				return 4
			case r == 40 : // ['(','(']
				return 5
			case r == 41 : // [')',')']
				return 6
			case r == 42 : // ['*','*']
				return 7
			case r == 43 : // ['+','+']
				return 8
			case r == 45 : // ['-','-']
				return 9
			case r == 47 : // ['/','/']
				return 10
			case 48 <= r && r <= 57 : // ['0','9']
				return 11
			case r == 58 : // [':',':']
				return 12
			case r == 59 : // [';',';']
				return 13
			case r == 60 : // ['<','<']
				return 14
			case r == 61 : // ['=','=']
				return 15
			case r == 62 : // ['>','>']
				return 16
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 97 : // ['a','a']
				return 17
			case r == 98 : // ['b','b']
				return 18
			case r == 99 : // ['c','c']
				return 19
			case r == 100 : // ['d','d']
				return 20
			case r == 101 : // ['e','e']
				return 21
			case r == 102 : // ['f','f']
				return 22
			case 103 <= r && r <= 104 : // ['g','h']
				return 17
			case r == 105 : // ['i','i']
				return 23
			case 106 <= r && r <= 108 : // ['j','l']
				return 17
			case r == 109 : // ['m','m']
				return 24
			case 110 <= r && r <= 114 : // ['n','r']
				return 17
			case r == 115 : // ['s','s']
				return 25
			case r == 116 : // ['t','t']
				return 26
			case 117 <= r && r <= 122 : // ['u','z']
				return 17
			case r == 123 : // ['{','{']
				return 27
			case r == 124 : // ['|','|']
				return 28
			case r == 125 : // ['}','}']
				return 29
			
			
			
			}
			return NoState
			
		},
	
		// S1
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S2
		func(r rune) int {
			switch {
			case r == 61 : // ['=','=']
				return 30
			
			
			
			}
			return NoState
			
		},
	
		// S3
		func(r rune) int {
			switch {
			case r == 34 : // ['"','"']
				return 31
			
			
			default:
				return 3
			}
			
		},
	
		// S4
		func(r rune) int {
			switch {
			case r == 38 : // ['&','&']
				return 32
			
			
			
			}
			return NoState
			
		},
	
		// S5
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S6
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S7
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S8
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S9
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S10
		func(r rune) int {
			switch {
			case r == 42 : // ['*','*']
				return 33
			case r == 47 : // ['/','/']
				return 34
			
			
			
			}
			return NoState
			
		},
	
		// S11
		func(r rune) int {
			switch {
			case r == 44 : // [',',',']
				return 35
			case r == 46 : // ['.','.']
				return 36
			case 48 <= r && r <= 57 : // ['0','9']
				return 11
			
			
			
			}
			return NoState
			
		},
	
		// S12
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S13
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S14
		func(r rune) int {
			switch {
			case r == 61 : // ['=','=']
				return 37
			
			
			
			}
			return NoState
			
		},
	
		// S15
		func(r rune) int {
			switch {
			case r == 61 : // ['=','=']
				return 38
			
			
			
			}
			return NoState
			
		},
	
		// S16
		func(r rune) int {
			switch {
			case r == 61 : // ['=','=']
				return 39
			
			
			
			}
			return NoState
			
		},
	
		// S17
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S18
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 110 : // ['a','n']
				return 17
			case r == 111 : // ['o','o']
				return 40
			case 112 <= r && r <= 122 : // ['p','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S19
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 116 : // ['a','t']
				return 17
			case r == 117 : // ['u','u']
				return 41
			case 118 <= r && r <= 122 : // ['v','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S20
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case r == 97 : // ['a','a']
				return 42
			case 98 <= r && r <= 100 : // ['b','d']
				return 17
			case r == 101 : // ['e','e']
				return 43
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S21
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 107 : // ['a','k']
				return 17
			case r == 108 : // ['l','l']
				return 44
			case 109 <= r && r <= 122 : // ['m','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S22
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case r == 97 : // ['a','a']
				return 45
			case 98 <= r && r <= 110 : // ['b','n']
				return 17
			case r == 111 : // ['o','o']
				return 46
			case 112 <= r && r <= 122 : // ['p','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S23
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 101 : // ['a','e']
				return 17
			case r == 102 : // ['f','f']
				return 47
			case 103 <= r && r <= 109 : // ['g','m']
				return 17
			case r == 110 : // ['n','n']
				return 48
			case 111 <= r && r <= 122 : // ['o','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S24
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 110 : // ['a','n']
				return 17
			case r == 111 : // ['o','o']
				return 49
			case 112 <= r && r <= 122 : // ['p','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S25
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 115 : // ['a','s']
				return 17
			case r == 116 : // ['t','t']
				return 50
			case 117 <= r && r <= 122 : // ['u','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S26
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 113 : // ['a','q']
				return 17
			case r == 114 : // ['r','r']
				return 51
			case 115 <= r && r <= 122 : // ['s','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S27
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S28
		func(r rune) int {
			switch {
			case r == 124 : // ['|','|']
				return 52
			
			
			
			}
			return NoState
			
		},
	
		// S29
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S30
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S31
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S32
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S33
		func(r rune) int {
			switch {
			case r == 42 : // ['*','*']
				return 53
			
			
			default:
				return 33
			}
			
		},
	
		// S34
		func(r rune) int {
			switch {
			case r == 10 : // ['\n','\n']
				return 54
			
			
			default:
				return 34
			}
			
		},
	
		// S35
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 55
			
			
			
			}
			return NoState
			
		},
	
		// S36
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 56
			
			
			
			}
			return NoState
			
		},
	
		// S37
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S38
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S39
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S40
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 110 : // ['a','n']
				return 17
			case r == 111 : // ['o','o']
				return 57
			case 112 <= r && r <= 122 : // ['p','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S41
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 113 : // ['a','q']
				return 17
			case r == 114 : // ['r','r']
				return 58
			case 115 <= r && r <= 122 : // ['s','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S42
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 115 : // ['a','s']
				return 17
			case r == 116 : // ['t','t']
				return 59
			case 117 <= r && r <= 122 : // ['u','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S43
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 98 : // ['a','b']
				return 17
			case r == 99 : // ['c','c']
				return 60
			case 100 <= r && r <= 122 : // ['d','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S44
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 114 : // ['a','r']
				return 17
			case r == 115 : // ['s','s']
				return 61
			case 116 <= r && r <= 122 : // ['t','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S45
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 107 : // ['a','k']
				return 17
			case r == 108 : // ['l','l']
				return 62
			case 109 <= r && r <= 122 : // ['m','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S46
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 113 : // ['a','q']
				return 17
			case r == 114 : // ['r','r']
				return 63
			case 115 <= r && r <= 122 : // ['s','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S47
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S48
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 115 : // ['a','s']
				return 17
			case r == 116 : // ['t','t']
				return 64
			case 117 <= r && r <= 122 : // ['u','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S49
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 109 : // ['a','m']
				return 17
			case r == 110 : // ['n','n']
				return 65
			case 111 <= r && r <= 122 : // ['o','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S50
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 113 : // ['a','q']
				return 17
			case r == 114 : // ['r','r']
				return 66
			case 115 <= r && r <= 122 : // ['s','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S51
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 116 : // ['a','t']
				return 17
			case r == 117 : // ['u','u']
				return 67
			case 118 <= r && r <= 122 : // ['v','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S52
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S53
		func(r rune) int {
			switch {
			case r == 42 : // ['*','*']
				return 53
			case r == 47 : // ['/','/']
				return 68
			
			
			default:
				return 33
			}
			
		},
	
		// S54
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S55
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 55
			
			
			
			}
			return NoState
			
		},
	
		// S56
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 56
			
			
			
			}
			return NoState
			
		},
	
		// S57
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 107 : // ['a','k']
				return 17
			case r == 108 : // ['l','l']
				return 69
			case 109 <= r && r <= 122 : // ['m','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S58
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 113 : // ['a','q']
				return 17
			case r == 114 : // ['r','r']
				return 70
			case 115 <= r && r <= 122 : // ['s','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S59
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 71
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S60
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 104 : // ['a','h']
				return 17
			case r == 105 : // ['i','i']
				return 72
			case 106 <= r && r <= 122 : // ['j','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S61
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 73
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S62
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 114 : // ['a','r']
				return 17
			case r == 115 : // ['s','s']
				return 74
			case 116 <= r && r <= 122 : // ['t','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S63
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 108 : // ['a','l']
				return 17
			case r == 109 : // ['m','m']
				return 75
			case 110 <= r && r <= 122 : // ['n','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S64
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 76
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S65
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 77
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S66
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 104 : // ['a','h']
				return 17
			case r == 105 : // ['i','i']
				return 78
			case 106 <= r && r <= 122 : // ['j','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S67
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 79
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S68
		func(r rune) int {
			switch {
			
			
			
			}
			return NoState
			
		},
	
		// S69
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 80
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S70
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 81
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S71
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S72
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 108 : // ['a','l']
				return 17
			case r == 109 : // ['m','m']
				return 82
			case 110 <= r && r <= 122 : // ['n','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S73
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S74
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 83
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S75
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S76
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 102 : // ['a','f']
				return 17
			case r == 103 : // ['g','g']
				return 84
			case 104 <= r && r <= 122 : // ['h','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S77
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 120 : // ['a','x']
				return 17
			case r == 121 : // ['y','y']
				return 85
			case r == 122 : // ['z','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S78
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 109 : // ['a','m']
				return 17
			case r == 110 : // ['n','n']
				return 86
			case 111 <= r && r <= 122 : // ['o','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S79
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S80
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case r == 97 : // ['a','a']
				return 87
			case 98 <= r && r <= 122 : // ['b','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S81
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 109 : // ['a','m']
				return 17
			case r == 110 : // ['n','n']
				return 88
			case 111 <= r && r <= 122 : // ['o','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S82
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case r == 97 : // ['a','a']
				return 89
			case 98 <= r && r <= 122 : // ['b','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S83
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S84
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 100 : // ['a','d']
				return 17
			case r == 101 : // ['e','e']
				return 90
			case 102 <= r && r <= 122 : // ['f','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S85
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S86
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 102 : // ['a','f']
				return 17
			case r == 103 : // ['g','g']
				return 91
			case 104 <= r && r <= 122 : // ['h','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S87
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 109 : // ['a','m']
				return 17
			case r == 110 : // ['n','n']
				return 92
			case 111 <= r && r <= 122 : // ['o','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S88
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 98 : // ['a','b']
				return 17
			case r == 99 : // ['c','c']
				return 93
			case 100 <= r && r <= 122 : // ['d','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S89
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 107 : // ['a','k']
				return 17
			case r == 108 : // ['l','l']
				return 94
			case 109 <= r && r <= 122 : // ['m','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S90
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 113 : // ['a','q']
				return 17
			case r == 114 : // ['r','r']
				return 95
			case 115 <= r && r <= 122 : // ['s','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S91
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S92
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S93
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 120 : // ['a','x']
				return 17
			case r == 121 : // ['y','y']
				return 96
			case r == 122 : // ['z','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S94
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S95
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
		// S96
		func(r rune) int {
			switch {
			case 48 <= r && r <= 57 : // ['0','9']
				return 17
			case 65 <= r && r <= 90 : // ['A','Z']
				return 17
			case r == 95 : // ['_','_']
				return 17
			case 97 <= r && r <= 122 : // ['a','z']
				return 17
			
			
			
			}
			return NoState
			
		},
	
}
