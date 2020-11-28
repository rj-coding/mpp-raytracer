package nl.rjcoding.raytracer

import kotlin.math.sqrt

typealias Tuple = DoubleArray

fun tuple(x: Double, y: Double, z: Double, w: Double): Tuple = DoubleArray(4).also {
    it[0] = x
    it[1] = y
    it[2] = z
    it[3] = w
}

fun tuple(x: Int, y: Int, z: Int, w: Int): Tuple = tuple(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

fun vector(x: Double, y: Double, z: Double): Tuple = tuple(x, y, z, 0.0)
fun vector(x: Int, y: Int, z: Int): Tuple = tuple(x, y, z, 0)

fun point(x: Double, y: Double, z: Double): Tuple = tuple(x, y, z, 1.0)
fun point(x: Int, y: Int, z: Int): Tuple = tuple(x, y, z, 1)

val Tuple.isVector: Boolean get() = this.size >= 4 && eq(this[3], 0.0)
val Tuple.isPoint: Boolean get() = this.size >= 4 && eq(this[3], 1.0)

val Tuple.x: Double get() = this[0]
val Tuple.y: Double get() = this[1]
val Tuple.z: Double get() = this[2]
val Tuple.w: Double get() = this[3]

val Tuple.magnitude: Double get() = sqrt(x * x + y * y + z * z + w * w)
val Tuple.normalized: Tuple get() = div(this, magnitude)

fun eq(a: Tuple, b: Tuple): Boolean = a.size == b.size
        && eq(a.x, b.x)
        && eq(a.y, b.y)
        && eq(a.z, b.z)
        && eq(a.w, b.w)

fun plus(a: Tuple, b: Tuple): Tuple = tuple(
        a.x + b.x,
        a.y + b.y,
        a.z + b.z,
        a.w + b.w
)

fun minus(a: Tuple, b: Tuple): Tuple = tuple(
        a.x - b.x,
        a.y - b.y,
        a.z - b.z,
        a.w - b.w
)

fun neg(t: Tuple): Tuple = tuple(-t.x, -t.y, -t.z, -t.w)

fun times(t: Tuple, s: Double): Tuple = tuple(s * t.x, s * t.y, s * t.z, s * t.w)

fun div(t: Tuple, s: Double): Tuple = times(t, 1.0/s)