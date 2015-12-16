⍝ Some random float, integer and boolean tensors to work with

a1 ← 1  2  ¯1 ¯2 3
b1 ← 3 4  ⍴ ¯2  4  ¯3  ¯4  6  2  ¯1  ¯5  7  1  3  5  
c1 ← 3 4 5 ⍴ 6  1  ¯13  ¯28  ¯3  ¯1  ¯22  20  16  10  ¯10  ¯27  ¯12  26  5  ¯21  29  ¯2  4  25  24  21  ¯18  ¯16  ¯9  12  3  ¯26  14  19  18  ¯29  ¯17  ¯25  ¯11  13  30  ¯6  8  ¯24  27  9  ¯8  ¯20  ¯5  2  ¯19  15  23  ¯4  31  ¯23  17  ¯14  ¯7  22  11  7  ¯15  28  
a2 ← ¯2  2  1  3 ¯1
b2 ← 3 4 ⍴ 5  ¯3  ¯2  6  ¯4  ¯5  ¯6  1  4  7  2  3  
c2 ← 3 4 5 ⍴ 3  ¯19  ¯15  9  ¯1  12  ¯23  ¯2  7  16  25  19  ¯17  24  ¯9  ¯29  1  27  10  ¯21  ¯4  ¯20  18  14  ¯18  11  ¯27  ¯24  20  ¯28  2  17  ¯30  30  28  ¯6  ¯16  23  4  ¯13  5  8  ¯3  ¯12  ¯8  6  ¯25  15  29  ¯14  ¯10  31  21  26  ¯7  ¯26  ¯11  13  ¯22  22  

ba1 ← 2 ||⌈ fa1 ← (○÷3)× a1
bb1 ← 2 ||⌈ fb1 ← (○÷3)× b1
bc1 ← 2 ||⌈ fc1 ← (○÷3)× c1
ba2 ← 2 ||⌈ fa2 ← (○÷3)× a2
bb2 ← 2 ||⌈ fb2 ← (○÷3)× b2
bc2 ← 2 ||⌈ fc2 ← (○÷3)× c2

⍝ For certain operations involving shapes and indices
i ← 3
j ← ¯2 1
k ← 2 2 ¯1

⍝ We’ll use this as a separator
sep ← 5 ⍴ 88888888888888


⍝ Dyadic Operators
⍝ These operators take two arguments, one from the right and one from the left
⍝  In the following descriptions, the right argument is denoted by ⍵, the left by ⍺


⍝ Contains
⍝	Gives 1 when ⍺ exists in ⍵, 0 otherwise
a1 ∊ a2
b1 ∊ b2
c1 ∊ c2
sep

⍝ Less
⍝	Gives 1 when ⍺ is less than ⍵, 0 otherwise
a1 < a2
b1 < b2
c1 < c2
sep

⍝ Less or Equal
⍝	Gives 1 when ⍺ is less than or equal to ⍵, 0 otherwise
a1 ≤ a2
b1 ≤ b2
c1 ≤ c2
sep

⍝ Equals
⍝	Gives 1 when ⍺ is equal to ⍵, 0 otherwise
a1 = a2
b1 = b2
c1 = c2
sep

⍝ Equivalence 
⍝	Gives 1 when ⍺ and ⍵ are identical, 0 otherwise
a1 ≡ a1
b1 ≡ b2
c1 ≡ c2
sep

⍝ Grater or Equal
⍝	Gives 1 when ⍺ is greater than or equal to ⍵, 0 otherwise
a1 ≥ a2
b1 ≥ b2
c1 ≥ c2
sep

⍝ Greater
⍝	Gives 1 when ⍺ is greater than ⍵, 0 otherwise
a1 > a2
b1 > b2
c1 > c2
sep

⍝ Not Equal
⍝	Gives 1 when ⍺ is not equal to ⍵, 0 otherwise
a1 ≠ a2
b1 ≠ b2
c1 ≠ c2
sep

⍝ GCD
⍝	Gives the greatest common denominator of ⍺ and ⍵. Doubles as boolean OR
(|a1) ∨ |a2
(|b1) ∨ |b2
(|c1) ∨ |c2
sep

⍝ LCM
⍝	Gives the least common multiple of ⍺ and ⍵. Doubles as boolean AND
(|a1) ∧ |a2
(|b1) ∧ |b2
(|c1) ∧ |c2
sep

⍝ Nor
⍝	Gives boolean NOR of ⍺ and ⍵
ba1 ⍱ ba2
bb1 ⍱ bb2
bc1 ⍱ bc2
sep

⍝ Nand
⍝	Gives boolean NAND of ⍺ and ⍵
ba1 ⍲ ba2
bb1 ⍲ bb2
bc1 ⍲ bc2
sep

⍝ Take
⍝	Takes the first ⍺ elements from ⍵, discards the rest. If ⍵ is negative, takes the
⍝	last elements. This is done along the axes, so (2 2 ↑ 3 3 ⍴ ⍳9) ≡ (2 2 ⍴ 1 2 4 5)
i ↑ a2
j ↑ b2
k ↑ c2
sep

⍝ Drop
⍝	Drops the first ⍺ elements from ⍵, keeping the rest. If ⍵ is negative, drops the
⍝	last elements. This is done along the axes, so (1 1 ↑ 3 3 ⍴ ⍳9) ≡ (2 2 ⍴ 5 6 8 9)
i ↓ a2
j ↓ b2
k ↓ c2
sep

⍝ Base
⍝	⍺⊥⍵ is the item +/W×⍵ , where W is the list of weights such that 
⍝	W[k] ≡ ×/(k+1)↓(⍴⍵)⍴⍺. When ⍺ is a single value, this can be thought of the 
⍝	evaluation of a polynomial with coefficients ⍵ at x = ⍺
((↑⍴a2)⍴10) ⊥ |a2
((↑⍴b2)⍴10) ⊥ |b2
((↑⍴c2)⍴10) ⊥ |c2
sep

⍝ Antibase
⍝	The inverse function of base. Defined as (×/⍺)|⍵ ≡ ⍺⊥⍺⊤⍵. 
((↑⍴a2)⍴10) ⊤ |a2
((↑⍴b2)⍴10) ⊤ |b2
((↑⍴c2)⍴10) ⊤ |c2
sep

⍝ Deal
⍝	Gives ⍺ distinct values chosen from ⍳⍵. Can be thought of as shutting the first 
⍝	⍵ integers, and then ⍺ of them without replacement
(⍴,a1) ? ⍴,a1
(⍴,b1) ? ⍴,b1
(⍴,c1) ? ⍴,c1
sep

⍝ Max
⍝	Gives the higher value of ⍵ and ⍺
a1 ⌈ a2
b1 ⌈ b2
c1 ⌈ c2
sep

⍝ Min
⍝	Gives the lower value of ⍵ and ⍺
a1 ⌊ a2
b1 ⌊ b2
c1 ⌊ c2
sep

⍝ Reshape
⍝	Reshapes ⍵ into shape ⍺
(|i) ⍴ a2
(|j) ⍴ b2
(|k) ⍴ c2
sep

⍝ Modulus
⍝	Gives the remainder of ⍵÷⍺
a1 | a2
b1 | b2
c1 | c2
sep

⍝ Index Of
⍝	Gives the index of ⍺ in ⍵
a1 ⍳ a2
b1 ⍳ b2
c1 ⍳ c2
sep

⍝ Power
⍝	Raises ⍺ to the ⍵ power
a1 * a2
b1 * b2
c1 * c2
sep

⍝ Subtract
⍝	Gives ⍺ minus ⍵
a1 - a2
b1 - b2
c1 - c2
sep

⍝ Add
⍝	Gives ⍺ plus ⍵
a1 + a2
b1 + b2
c1 + c2
sep

⍝ Multiply
⍝	Gives ⍺ times ⍵
a1 × a2
b1 × b2
c1 × c2
sep

⍝ Divide
⍝	Gives ⍺ divided by ⍵
a1 ÷ a2
b1 ÷ b2
c1 ÷ c2
sep

⍝ Catenate
⍝ 	Gives ⍺ catenated with ⍵ along the last axis
a1 , a2
b1 , b2
c1 , c2
sep

⍝ Trig
⍝	Performs various trig functions according to the value of ⍺:
⍝
⍝			 0			   1	   	   2	   	   3	   
⍝	+	(1-⍵*2)*.5		sin ⍵		cos ⍵		tan ⍵
⍝	-	(1-⍵*2)*.5		arcsin ⍵	arccos ⍵	arctan ⍵
⍝
⍝			 4			   5		   6		   7
⍝	+	( 1+⍵*2)*.5		sinh ⍵		cosh ⍵		tanh ⍵
⍝	-	(¯1+⍵*2)*.5		arcsinh ⍵	arccosh ⍵	arctanh ⍵
⍝
⍝			 8
⍝	+	 (¯1-⍵*2)*.5
⍝	—	-(¯1-⍵*2)*.5
1 ○ a2
2 ○ b2
4 ○ c2
sep

⍝ Log
⍝	Log base ⍺ of ⍵
(|a1) ⍟ |a2
(|b1) ⍟ |b2
(|c1) ⍟ |c2
sep

⍝ Permute
⍝	Changes the dimensions of ⍵ according to ⍵. 1 2 3 ⍉ 3 3 3 ⍴ ⍳27 ≡ ⍉ 3 3 3 ⍴ ⍳27
(|i) ⍉ a2
(|j) ⍉ b2
(|k) ⍉ c2
sep

⍝ Choice
⍝	Gives ⍵ choose ⍺
(|a1) ! |a2
(|b1) ! |b2
(|c1) ! |c2
sep

⍝ Format
⍝	Rounds ⍵ to ⍺ decimals. 2 ⍕ 14232.3462 ≡ 14232.35    ¯2 ⍕ 14232.3462 ≡ 14200
2 ⍕ fa2
3 ⍕ fb2
6 ⍕ fc2
sep

⍝ Compress
⍝	Gives ⍺ instances of ⍵. 3 1 4 / 1 2 3 ≡ 1 1 1 2 3 3 3 3   1 0 1 / 1 2 3 ≡ 1 3
((⍴a2)⍴¯1+|a1) / a2
((⍴b2)⍴¯1+|b1) / b2
((⍴c2)⍴¯1+|c1) / c2


