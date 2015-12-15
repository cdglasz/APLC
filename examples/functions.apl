⍝ APL user-defined operator overview
⍝ Inline operators can be defined like so:
⍝		f ← { . . . }
⍝ ⍵ denotes right-hand side arguments to inline operators
⍝ ⍺ denotes left-hand side arguments to inline operators


⍝ Here’s a simple monadic operator to find the area of a circle
area ← { ○1×⍵*2 }

⍝ This dyadic operator calculates the volume of a cylinder
vol ← { ⍵ × area ⍺ }

⍝ The volume of a cylinder with radius 2 and height 4
2 vol 4

⍝ User-defined operators can be used with adverbs and conjugates the same way 
⍝  built-in operators can be
⍝ A table of volumes of cylinders with radii and heights 1 to 5
(⍳5) ∘.vol ⍳5


⍝ This operator takes a number of years and an interest rate and calculates
⍝  10 years of compounding interest on $10,000. When given vectors of
⍝  years and interest rates, a table is formed
interest ← { ⍉ 2 ⍕ 10000 × (1+⍺) ∘.* ⍵ }

⍝ 20 years of interest, given interest rates of 0.5%, 0.8%, 1.2%, 2%, and 5%
0.005 0.008 0.012 0.02 0.05 interest ⍳20


⍝ This operator converts ⍵ to base ⍺. 
base ← { ⍉((1 + ⌊⍺ ⍟ ⌈/ ⍵) ⍴ ⍺) ⊤ ⍵ }

⍝ All the numbers from 1 to 31 in base 2
2 base ⍳31


⍝ The distance formula
dist ← { (+/ (⍺-⍵) * 2) * ÷2 }

⍝ The distance from the origin to (42, -12, 67)
0 dist 42 ¯12 67


⍝ The obvious method of finding primes is O(n^3) in time, O(n^2) in space
primes ← { (~R ∊ R ∘.× R) / R ← ⍳⍵ }

⍝ The Sieve of Eratosthenes is O(n^2) in time, O(n^2) in space
primes ← {(2 = +⌿ 0 = R ∘.| R) / R ← ⍳⍵}

⍝ All primes between 1 and 75
primes 75



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
∇ value ← x poly coefficients 
	exponents ← { ¯1 + ⍳ ⍴ ⍵ } 
	terms ← coefficients × x * exponents coefficients 
	value ← +/ terms
∇

⍝ 5 + 3(2) + 7(2^2) + 8(2^3) + 2(2^4) + 3(2^5)
2 poly 5 3 7 8 2 3 
