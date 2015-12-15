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

i ← 3
j ← ¯2 1
k ← 2 2 ¯1

⍝ We’ll use this as a separator
sep ← 5 ⍴ 88888888888888


a1 ∊ a2
b1 ∊ b2
c1 ∊ c2
sep

a1 < a2
b1 < b2
c1 < c2
sep

a1 ≤ a2
b1 ≤ b2
c1 ≤ c2
sep

a1 = a2
b1 = b2
c1 = c2
sep

a1 ≡ a1
b1 ≡ b2
c1 ≡ c2
sep

a1 ≥ a2
b1 ≥ b2
c1 ≥ c2
sep

a1 > a2
b1 > b2
c1 > c2
sep

a1 ≠ a2
b1 ≠ b2
c1 ≠ c2
sep

(|a1) ∨ |a2
(|b1) ∨ |b2
(|c1) ∨ |c2
sep

(|a1) ∧ |a2
(|b1) ∧ |b2
(|c1) ∧ |c2
sep

ba1 ⍱ ba2
bb1 ⍱ bb2
bc1 ⍱ bc2
sep

ba1 ⍲ ba2
bb1 ⍲ bb2
bc1 ⍲ bc2
sep

i ↑ a2
j ↑ b2
k ↑ c2
sep

i ↓ a2
j ↓ b2
k ↓ c2
sep

a1 ⊥ a2
b1 ⊥ b2
c1 ⊥ c2
sep

a1 ⊤ a2
b1 ⊤ b2
c1 ⊤ c2
sep

⍴a1 ? ⌈/|a2
⍴b1 ? ⌈/|b2
⍴c1 ? ⌈/|c2
sep

a1 ⌈ a2
b1 ⌈ b2
c1 ⌈ c2
sep

a1 ⌊ a2
b1 ⌊ b2
c1 ⌊ c2
sep

(|i) ⍴ a2
(|j) ⍴ b2
(|k) ⍴ c2
sep

a1 | a2
b1 | b2
c1 | c2
sep

a1 ⍳ a2
b1 ⍳ b2
c1 ⍳ c2
sep

a1 * a2
b1 * b2
c1 * c2
sep

a1 - a2
b1 - b2
c1 - c2
sep

a1 + a2
b1 + b2
c1 + c2
sep

a1 × a2
b1 × b2
c1 × c2
sep

a1 ÷ a2
b1 ÷ b2
c1 ÷ c2
sep

a1 , a2
b1 , b2
c1 , c2
sep

i ○ a2
j ○ b2
k ○ c2
sep

(|a1) ⍟ |a2
(|b1) ⍟ |b2
(|c1) ⍟ |c2
sep

(|i) ⍉ a2
(|j) ⍉ b2
(|k) ⍉ c2
sep

(|a1) ! |a2
(|b1) ! |b2
(|c1) ! |c2
sep

i ⍕ a2
j ⍕ b2
k ⍕ c2
sep

a1 / a2
b1 / b2
c1 / c2

check ← { ⍵≡⍺⊥⍺⊤⍵ }

sh ← 10 10 10;
a ← ? 999
b ← ? 5 ⍴ 999
c ← ? 5 4 ⍴ 999

a ← sh ⊤ a 
b ← sh ⊤ b
c ← sh ⊤ c

check ← { ⍵≡⍺⊤⍺⊥⍵ }

sh ⊥ a
sh ⊥ b
sh ⊥ c


