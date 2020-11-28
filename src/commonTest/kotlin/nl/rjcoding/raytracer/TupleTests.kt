package nl.rjcoding.raytracer

import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TupleTests {

    @Test
    fun tupleWithWEquals1IsAPoint() {
        val a = tuple(4.3, -4.2, 3.1, 1.0)
        assertEquals(4.3, a.x)
        assertEquals(-4.2, a.y)
        assertEquals(3.1, a.z)
        assertEquals(1.0, a.w)
        assertTrue { a.isPoint }
        assertFalse { a.isVector }
    }

    @Test
    fun tupleWithWEquals0IsAVector() {
        val a = tuple(4.3, -4.2, 3.1, 0.0)
        assertEquals(4.3, a.x)
        assertEquals(-4.2, a.y)
        assertEquals(3.1, a.z)
        assertEquals(0.0, a.w)
        assertFalse { a.isPoint }
        assertTrue { a.isVector }
    }

    @Test
    fun pointCreatesTupleWithW1() {
        val p = point(4, -4, 3)
        assertTrue { eq(tuple(4, -4, 3, 1), p) }
    }

    @Test
    fun vectorCreatesTupleWithW0() {
        val p = vector(4, -4, 3)
        assertTrue { eq(tuple(4, -4, 3, 0), p) }
    }

    @Test
    fun addingTwoTuples() {
        val a1 = tuple(3, -2, 5, 1)
        val a2 = tuple(-2, 3, 1, 0)
        assertTrue { eq(tuple(1, 1, 6, 1), plus(a1, a2)) }
    }

    @Test
    fun substractingTwoPoints() {
        val p1 = point(3, 2, 1)
        val p2 = point(5, 6, 7)
        assertTrue { eq(vector(-2, -4, -6), minus(p1, p2)) }
    }

    @Test
    fun substractingAVectorFromAPoint() {
        val p = point(3, 2, 1)
        val v = vector(5, 6, 7)
        assertTrue { eq(point(-2, -4, -6), minus(p, v)) }
    }

    @Test
    fun substractingTwoVectors() {
        val v1 = vector(3, 2, 1)
        val v2 = vector(5, 6, 7)
        assertTrue { eq(vector(-2, -4, -6), minus(v1, v2)) }
    }

    @Test
    fun substractingAVectorFromTheZeroVector() {
        val zero = vector(0, 0, 0)
        val v = vector(1, -2, 3)
        assertTrue { eq(vector(-1, 2, -3), minus(zero, v)) }
    }

    @Test
    fun negatingATuple() {
        val a = tuple(1, -2, 3, -4)
        assertTrue { eq(tuple(-1, 2, -3, 4), neg(a)) }
    }

    @Test
    fun multiplyATupleByAScalar() {
        val a = tuple(1, -2, 3, -4)
        assertTrue { eq(tuple(3.5, -7.0, 10.5, -14.0), times(a, 3.5)) }
    }

    @Test
    fun multiplyATupleByAFraction() {
        val a = tuple(1, -2, 3, -4)
        assertTrue { eq(tuple(0.5, -1.0, 1.5, -2.0), times(a, 0.5)) }
    }

    @Test
    fun dividingATupleByAScalar() {
        val a = tuple(1, -2, 3, -4)
        assertTrue { eq(tuple(0.5, -1.0, 1.5, -2.0), div(a, 2.0)) }
    }

    @Test
    fun magnitude() {
        assertTrue { eq(1.0, vector(1, 0, 0).magnitude) }
        assertTrue { eq(1.0, vector(0, 1, 0).magnitude) }
        assertTrue { eq(1.0, vector(0, 0, 1).magnitude) }
        assertTrue { eq(sqrt(14.0), vector(1, 2, 3).magnitude) }
        assertTrue { eq(sqrt(14.0), vector(-1, -2, -3).magnitude) }
    }

    @Test
    fun normalize() {
        assertTrue { eq(vector(1, 0, 0), vector(4, 0, 0).normalized) }

        RayTracerEnvironment.with(epsilon = 1E-5) {
            assertTrue { eq(vector(0.26726, 0.53452, 0.80178), vector(1, 2, 3).normalized) }
        }

        val v = vector(1, 2, 3)
        val norm = v.normalized
        assertEquals(1.0, norm.magnitude)
    }

    @Test
    fun dot() {
        assertTrue { eq(20.0, dot(vector(1, 2, 3), vector(2, 3, 4))) }
    }

    @Test
    fun cross() {
        val a = vector(1, 2, 3)
        val b = vector(2, 3, 4)
        assertTrue { eq(vector(-1, 2, -1), cross(a, b)) }
        assertTrue { eq(vector(1, -2, 1), cross(b, a)) }
    }

}