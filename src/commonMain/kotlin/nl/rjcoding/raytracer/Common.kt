package nl.rjcoding.raytracer

import kotlin.math.abs

fun eq(a: Double, b: Double): Boolean = abs(a - b) < RayTracerEnvironment.epsilon