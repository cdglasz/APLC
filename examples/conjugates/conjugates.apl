a ← 1 4 2 6 7 8 3 9 5
b ← 4 3 2 5 7 9 8 1 6
c ← d ← 5 5 ⍴ ⍳25

⍝ Inner Products
⍝  One can tie two operations together to compute what’s called an inner product
⍝  Between two vectors, +.× is a dot product
a +.× b

⍝ Between matrices, its matrix product
c +.× d

⍝ We can use different operators to produce interesting combinations
⍝  This counts matching elements in a and b
a +.= b

⍝ We can use outer products to compute the table of shortest paths between all
⍝  pairs of vertices given a cost matrix
c ← (~(⍳5)∘.=⍳5) × 5 5 ⍴ (25 ? 25)
c ⌊.+ c ⌊.+ c ⌊.+ c




⍝ This computes the outer product of a and b
⍝  That is, the × operation applied to every pairing of elements
a ∘.× b

⍝ Here’s the same operation, except the × operation has been replaced with =
⍝  Since both sides are the same, this gives the identity matrix
a ∘.= a

⍝ We can mask the outer product with the identity matrix to get a matrix with 
⍝  perfect squares down the diagonal. Summed across the last axis, we have a 
⍝  list of perfect squares
+⌿ (a ∘.= a) × a ∘.× a ← ⍳10

⍝ This is of course equivalent to a * 2, but it’s a perfectly good illustration
⍝  of what you can do with outer products
