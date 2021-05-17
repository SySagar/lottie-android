package com.airbnb.lottie.compose

import android.graphics.Rect
import androidx.collection.LongSparseArray
import androidx.collection.SparseArrayCompat
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.model.Marker
import org.junit.Assert.*
import org.junit.Test

class LottieAnimationClipSpecTest {

    @Test
    fun testMinFrame() {
        val spec = LottieAnimationClipSpec.MinFrame(20)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(1f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMaxFrame() {
        val spec = LottieAnimationClipSpec.MaxFrame(20)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0f, spec.getMinProgress(composition))
        assertEquals(0.5f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMaxFrameNotInclusive() {
        val spec = LottieAnimationClipSpec.MaxFrame(20, inclusive = false)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0f, spec.getMinProgress(composition))
        assertEquals(0.475f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMinAndMaxFrame() {
        val spec = LottieAnimationClipSpec.MinAndMaxFrame(20, 30)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(0.75f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMinAndMaxFrameNotExclusive() {
        val spec = LottieAnimationClipSpec.MinAndMaxFrame(20, 30, maxFrameInclusive = false)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(0.725f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMinProgress() {
        val spec = LottieAnimationClipSpec.MinProgress(0.5f)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(1f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMaxProgress() {
        val spec = LottieAnimationClipSpec.MaxProgress(0.5f)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0f, spec.getMinProgress(composition))
        assertEquals(0.5f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMinAndMaxProgress() {
        val spec = LottieAnimationClipSpec.MinAndMaxProgress(0.5f, 0.75f)
        val composition = createComposition(endFrame = 40f)
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(0.75f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMinMarker() {
        val spec = LottieAnimationClipSpec.MinMarker("start")
        val composition = createComposition(endFrame = 40f, listOf(Marker("start", 20f, 10f)))
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(1f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMaxMarker() {
        val spec = LottieAnimationClipSpec.MaxMarker("end")
        val composition = createComposition(endFrame = 40f, listOf(Marker("end", 20f, 10f)))
        assertEquals(0f, spec.getMinProgress(composition))
        assertEquals(0.5f, spec.getMaxProgress(composition))
    }


    @Test
    fun testMaxMarkerExclusive() {
        val spec = LottieAnimationClipSpec.MaxMarker("end", playMarkerFrame = false)
        val composition = createComposition(endFrame = 40f, listOf(Marker("end", 20f, 10f)))
        assertEquals(0f, spec.getMinProgress(composition))
        assertEquals(0.475f, spec.getMaxProgress(composition))
    }


    @Test
    fun testMinAndMaxMarker() {
        val spec = LottieAnimationClipSpec.MinAndMaxMarker("start", "end")
        val composition = createComposition(endFrame = 40f, listOf(Marker("start", 20f, 10f), Marker("end", 30f, 10f)))
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(0.75f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMinAndMaxMarkerExclusive() {
        val spec = LottieAnimationClipSpec.MinAndMaxMarker("start", "end", playMaxMarkerStartFrame = false)
        val composition = createComposition(endFrame = 40f, listOf(Marker("start", 20f, 10f), Marker("end", 30f, 10f)))
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(0.725f, spec.getMaxProgress(composition))
    }

    @Test
    fun testMarker() {
        val spec = LottieAnimationClipSpec.Marker("span")
        val composition = createComposition(endFrame = 40f, listOf(Marker("span", 20f, 10f)))
        assertEquals(0.5f, spec.getMinProgress(composition))
        assertEquals(0.75f, spec.getMaxProgress(composition))
    }

    private fun createComposition(endFrame: Float, markers: List<Marker> = emptyList()): LottieComposition {
        val composition = LottieComposition()
        composition.init(
            Rect(),
            0f,
            endFrame,
            30f,
            emptyList(),
            LongSparseArray(),
            emptyMap(),
            emptyMap(),
            SparseArrayCompat(),
            emptyMap(),
            markers,
        )
        return composition
    }
}