data ← ⍳1000;
a ← 10 ⍴ data;
b ← 4 5 ⍴ data;
c ← 3 4 5 ⍴ data;

⍝ Reduce applies the operator between elements along the last axis
⍝  In the following cases, we are calculating a summation
+/a
+/b
+/c

⍝ The following is the same operation performed along the first axis
+⌿a
+⌿b
+⌿c

⍝ Scan is similar to reduce, but keeps a running sum
+\a
+\b
+\c

⍝ Again, the same operation along the first axis
+⍀a
+⍀b
+⍀c
