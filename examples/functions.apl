⍝ APL user-defined operator overview
⍝ Inline operators can be defined like so:
⍝		f ← { . . . }
⍝ ⍵ denotes right-hand side arguments to inline operators
⍝ ⍺ denotes left-hand side arguments to inline operators


⍝ Here’s a simple monadic operator to find the area of a circle
area ← { ○1×⍵*2 }

⍝ The area of a circle of radius 6.759
area 6.759


⍝ This dyadic operator calculates the volume of a cylinder
vol ← { ⍵ × area ⍺ }

⍝ The volume of a cylinder with radius 2 and height 4
2 vol 4


⍝ This operator takes a number of years and an interest rate and calculates dollar 
⍝  amount after 10 years of compounding interest on $5000. When given vectors of
⍝  years and interest rates, a table is formed
interest ← { ⍉ 2 ⍕ 5000 × (1+⍺) ∘.* ⍵ }

⍝ 20 years of interest, given interest rates of 0.5%, 0.8%, 1.2%, 2%, and 5%
0.005 0.008 0.012 0.02 0.05 interest ⍳20


⍝ This operator converts ⍵ to base ⍺. 
base ← { ⍉((1+⌈⍺⍟⌈/⍵)⍴⍺)⊤⍵ }

⍝ All the numbers from 1 to 32 in base 2
2 base ⍳32


⍝ The distance formula
dist ← { (+/(⍺-⍵)*2)*÷2 }

⍝ The distance from the origin to (42, -12, 67)
0 dist 42 ¯12 67



⍝ More complex operators 
⍝ We can define more complex operators using the following syntax
⍝	∇ out ← [lhsvar] function [rhsvar]
⍝		. . .
⍝	∇
⍝ This allows for operations with multiple statements, and even nested inline
⍝  operator definitions and local variables. (There is no notion of scoping in APL,
⍝  so any variables defined inside the operator definition are also accessible 
⍝  outside of it)


⍝ Evaluation of a polynomial
∇ p ← x_value poly coefficients 
	exponents ← { ¯1 + ⍳ ⍴ ⍵ } 
	terms ← coefficients × x_value * exponents coefficients 
	p ← +/ terms
∇

⍝ 5 + 3(2) + 7(2^2) + 8(2^3) + 2(2^4) + 3(2^5)
2 poly 5 3 7 8 2 3 
