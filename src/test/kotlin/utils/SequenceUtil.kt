package utils

import java.util.concurrent.atomic.AtomicLong

object SequenceUtil {
    private val INTERNAL_STATE = AtomicLong()

    fun nextLong(): Long {
        return INTERNAL_STATE.andIncrement
    }

    fun nextDouble(): Double {
        return INTERNAL_STATE.andIncrement.toDouble()
    }

    fun nextString(): String {
        return nextString("str")
    }

    fun nextString(prefix: String): String {
        return prefix + INTERNAL_STATE.andIncrement
    }

}
