package nl.rjcoding.raytracer

object RayTracerEnvironment {

    private val EPSILON = 1E-9

    var epsilon: Double = EPSILON

    fun reset() {
        epsilon = EPSILON
    }

    fun with(
        epsilon: Double = this.epsilon,
        block: () -> Unit
    ) {
        this.epsilon = epsilon
        block()
        reset()
    }
}