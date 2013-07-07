module Nat

data Nat : Set where
    zero : Nat
    succ : Nat -> Nat

_+_ : Nat -> Nat -> Nat
zero + m = m
succ n + m = succ (n + m)

_*_ : Nat -> Nat -> Nat
zero * m = zero
succ n * m = (n * m) + m
