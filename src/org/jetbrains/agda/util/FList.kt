package org.jetbrains.agda.util

public inline fun <I, O> List<I>.case(inline nil : () -> O, inline cons : (I, List<I>) -> O) : O {
    if (this.isEmpty()) {
        return nil()
    } else {
        return cons(this.get(0), this.subList(1, this.size()))
    }
}

abstract class FList<in T>() : List<T> {

    public fun append(list : FList<T>) : FList<T> = case<T, FList<T>>(
        { list },
        { value, tail -> Cons(value, (tail as FList<T>).append(list)) }
    )

    abstract fun fold<Acc>(start : Acc, f : (T, () -> Acc) -> Acc) : Acc
}

class Nil<in T>() : FList<T>() {

    override fun <Acc> fold(start: Acc, f: (T, () -> Acc) -> Acc) : Acc = start;


    public override fun size(): Int {
        throw UnsupportedOperationException()
    }

    public override fun isEmpty(): Boolean {
        throw UnsupportedOperationException()
    }

    public override fun contains(o: Any?): Boolean {
        throw UnsupportedOperationException()
    }

    public override fun iterator(): Iterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun toArray(): Array<Any?> {
        throw UnsupportedOperationException()
    }

    public override fun <T> toArray(a: Array<out T>): Array<T> {
        throw UnsupportedOperationException()
    }

    public override fun containsAll(c: Collection<Any?>): Boolean {
        throw UnsupportedOperationException()
    }

    public override fun get(index: Int): T {
        throw UnsupportedOperationException()
    }

    public override fun indexOf(o: Any?): Int {
        throw UnsupportedOperationException()
    }

    public override fun lastIndexOf(o: Any?): Int {
        throw UnsupportedOperationException()
    }

    public override fun listIterator(): ListIterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun listIterator(index: Int): ListIterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        throw UnsupportedOperationException()
    }

    public override fun hashCode(): Int {
        throw UnsupportedOperationException()
    }

    public override fun equals(other: Any?): Boolean {
        throw UnsupportedOperationException()
    }

}

class Cons<in T>(val head : T, val tail : FList<T>) : FList<T>() {

    override fun <Acc> fold(start: Acc, f: (T, () -> Acc) -> Acc): Acc =
        f(head, { tail.fold<Acc>(start, f) })

    public override fun size(): Int {
        throw UnsupportedOperationException()
    }

    public override fun isEmpty(): Boolean {
        throw UnsupportedOperationException()
    }

    public override fun contains(o: Any?): Boolean {
        throw UnsupportedOperationException()
    }

    public override fun iterator(): Iterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun toArray(): Array<Any?> {
        throw UnsupportedOperationException()
    }

    public override fun <T> toArray(a: Array<out T>): Array<T> {
        throw UnsupportedOperationException()
    }

    public override fun containsAll(c: Collection<Any?>): Boolean {
        throw UnsupportedOperationException()
    }

    public override fun get(index: Int): T {
        throw UnsupportedOperationException()
    }

    public override fun indexOf(o: Any?): Int {
        throw UnsupportedOperationException()
    }

    public override fun lastIndexOf(o: Any?): Int {
        throw UnsupportedOperationException()
    }

    public override fun listIterator(): ListIterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun listIterator(index: Int): ListIterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        throw UnsupportedOperationException()
    }

    public override fun hashCode(): Int {
        throw UnsupportedOperationException()
    }

    public override fun equals(other: Any?): Boolean {
        throw UnsupportedOperationException()
    }
}

fun singeFList<T>(value : T) = Cons<T>(value, Nil());