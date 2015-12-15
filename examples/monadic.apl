⍝ Some random float, integer and boolean tensors to work with

a ← 1  ¯2  ¯1  2  3  
b ← 3 4  ⍴ ¯2  4  ¯3  ¯4  6  2  ¯1  ¯5  7  1  3  5  
c ← 3 4 5 ⍴ 6  1  ¯13  ¯28  ¯3  ¯1  ¯22  20  16  10  ¯10  ¯27  ¯12  26  5  ¯21  29  ¯2  4  25  24  21  ¯18  ¯16  ¯9  12  3  ¯26  14  19  18  ¯29  ¯17  ¯25  ¯11  13  30  ¯6  8  ¯24  27  9  ¯8  ¯20  ¯5  2  ¯19  15  23  ¯4  31  ¯23  17  ¯14  ¯7  22  11  7  ¯15  28  


ba ← 2 ||⌈ fa ← (○÷3)× a
bb ← 2 ||⌈ fb ← (○÷3)× b
bc ← 2 ||⌈ fc ← (○÷3)× c

⍝ We’ll use this as a separator
sep ← 5 ⍴ 88888888888888


⍝ Monadic Operators
⍝ These operators take one argument from the right
⍝  In the following descriptions, the argument is denoted by ⍵


⍝ Clone 
⍝	Does nothing at all
+ a
+ b
+ c
sep

⍝ Negation
⍝	Changes the sign of ⍵
- a
- b
- c
sep

⍝ Sign
⍝	Gives the sign of ⍵
× a
× b
× c
sep

⍝ Reciprocal
⍝	Gives 1 divided by ⍵
÷ a
÷ b
÷ c
sep

⍝ Exp
⍝	Gives e to the ⍵ power
* a
* b
* c
sep

⍝ Not
⍝	The boolean not operator. Treats 0 as false and nonzero as true
~ ba
~ bb
~ bc
sep

⍝ Grade up 
⍝	Sorts ⍵ in ascending order along the last axis
⍋ a
⍋ b
⍋ c
sep

⍝ Grade down
⍝	Sorts ⍵ in descending order along the last axis
⍝	Note: this differs from the GNU implementation, which gives the indices according
⍝		  their sorted order
⍒ a
⍒ b
⍒ c
sep

⍝ Roll
⍝	Gives one random number in the range provided
⍝	Note: this differs from the GNU implementation, which gives the indices according
⍝		  their sorted order
? |a
? |b
? |c
sep

⍝ Ceiling
⍝	Gives the ceiling of ⍵
⌈ fa 
⌈ fb 
⌈ fc 
sep

⍝ Floor
⍝	Gives the floor of ⍵
⌊ fa
⌊ fb
⌊ fc
sep

⍝ First
⍝	Gives the first element of ⍵
↑ a
↑ b
↑ c
sep

⍝ Shape
⍝	Gives the shape of ⍵
⍴ a
⍴ b
⍴ c
sep

⍝ Abs
⍝	Gives the absolute value of ⍵
| a
| b
| c
sep

⍝ Index 
⍝	Gives the integers between 1 and ⍵
⍳ 10
sep

⍝ Ravel
⍝	Flattens the shape of ⍵ to a 1-dimensional vector
, a
, b
, c
sep

⍝ Pi
⍝	Gives pi times ⍵
○ a
○ b
○ c
sep

⍝ Log
⍝	Gives the natural logarithm of ⍵
⍟ |a
⍟ |b
⍟ |c
sep

⍝ Reverse
⍝	Reverses ⍵ about the last axis
⌽ a
⌽ b
⌽ c
sep

⍝ Upset
⍝	Reverses ⍵ about the first axis
⊖ a
⊖ b
⊖ c
sep

⍝ Transpose
⍝	Reverses the dimensions of ⍵
⍉ a
⍉ b
⍉ c
sep

⍝ Factorial
⍝	Gives the factorial of ⍵
! |a
! |b
! |c
sep

⍝ Format
⍝	Rounds ⍵ to the nearest integer
⍝	Note: This differs from the GNU implementation, which has a very complex ruleset
⍕ fa
⍕ fb
⍕ fc





