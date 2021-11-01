package com.example.horizontalscrollbar

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.customview.view.AbsSavedState
import androidx.recyclerview.widget.RecyclerView
import com.example.horizontalscrollbar.databinding.ViewgroupHorizontalScrollBarBinding
import com.google.android.material.card.MaterialCardView

class HorizontalScrollBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflateView(context)
        attrs?.let { _ -> setupStyleables(attrs) }
        setupView()
    }

    // View
    private lateinit var scrollBarContainer: FrameLayout
    private lateinit var scrollBarTrack: MaterialCardView
    private lateinit var scrollBarThumb: MaterialCardView

    private var cornerRadius: Float = 0f
    private var trackColor: Int = 0

    private var thumbWidth: Float = 0f
    private var thumbColor: Int = 0

    private var xThumbPosition: Float = 0f

    private fun inflateView(context: Context) {
        val binding = ViewgroupHorizontalScrollBarBinding.inflate(LayoutInflater.from(context), this, true)
        findView(binding)
    }

    private fun findView(binding: ViewgroupHorizontalScrollBarBinding) {
        scrollBarContainer = binding.linemanHorizontalScrollBarContainer
        scrollBarTrack = binding.linemanHorizontalScrollBarTrack
        scrollBarThumb = binding.linemanHorizontalScrollBarThumb
    }

    private fun setupStyleables(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalScrollBar)
        cornerRadius = typedArray.getDimension(R.styleable.HorizontalScrollBar_horizontalScrollBar_cornerRadius, 0f)
        trackColor = typedArray.getColor(R.styleable.HorizontalScrollBar_horizontalScrollBar_trackColor, 0)
        thumbWidth = typedArray.getDimension(R.styleable.HorizontalScrollBar_horizontalScrollBar_thumbWidth, 0f)
        thumbColor = typedArray.getColor(R.styleable.HorizontalScrollBar_horizontalScrollBar_thumbColor, 0)
        typedArray.recycle()
    }

    private fun setupView() {

        if (cornerRadius != 0f) {
            scrollBarTrack.radius = cornerRadius
            scrollBarThumb.radius = cornerRadius
        }

        if (trackColor != 0) {
            scrollBarTrack.setCardBackgroundColor(trackColor)
        }

        if (thumbWidth != 0f) {
            scrollBarThumb.layoutParams.width = thumbWidth.toInt()
        }

        if (thumbColor != 0) {
            scrollBarThumb.setCardBackgroundColor(thumbColor)
        }

        scrollBarThumb.translationX = xThumbPosition
    }

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var maxScrollRange = Int.MAX_VALUE
            private var xScrollPosition = 0
            private val paddingStart = recyclerView.paddingStart
            private val paddingEnd = recyclerView.paddingEnd

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                xScrollPosition += dx
                maxScrollRange = maxScrollRange.coerceAtMost(recyclerView.computeHorizontalScrollRange())
                val totalPadding = paddingStart + paddingEnd
                val totalArea = maxScrollRange + totalPadding
                val viewWidth = recyclerView.measuredWidth
                if (totalArea <= viewWidth) {
                    scrollBarContainer.visibility = View.GONE
                    return
                } else {
                    scrollBarContainer.visibility = View.VISIBLE
                }
                val scrollExtent = recyclerView.computeHorizontalScrollExtent()
                val maxThumbRange = scrollBarTrack.measuredWidth - scrollBarThumb.measuredWidth
                val scrollableAreaWidth = totalArea - (scrollExtent + totalPadding)
                xThumbPosition = (xScrollPosition.toFloat() * maxThumbRange) / scrollableAreaWidth
                scrollBarThumb.translationX = xThumbPosition
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState: Parcelable? = super.onSaveInstanceState()
        superState?.let {
            val state = SavedState(superState)
            state.cornerRadius = this.cornerRadius
            state.trackColor = this.trackColor
            state.thumbWidth = this.thumbWidth
            state.thumbColor = this.thumbColor
            state.xThumbPosition = this.xThumbPosition
            return state
        } ?: run {
            return superState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        when (state) {
            is SavedState -> {
                super.onRestoreInstanceState(state.superState)
                this.cornerRadius = state.cornerRadius
                this.trackColor = state.trackColor
                this.thumbWidth = state.thumbWidth
                this.thumbColor = state.thumbColor
                this.xThumbPosition = state.xThumbPosition
                setupView()
            }
            else -> {
                super.onRestoreInstanceState(state)
            }
        }
    }

    internal class SavedState : AbsSavedState {

        var cornerRadius: Float = 0f
        var trackColor: Int = 0
        var thumbWidth: Float = 0f
        var thumbColor: Int = 0
        var xThumbPosition: Float = 0f

        constructor(superState: Parcelable) : super(superState)

        constructor(source: Parcel, loader: ClassLoader?) : super(source, loader) {
            cornerRadius = source.readFloat()
            trackColor = source.readInt()
            thumbWidth = source.readFloat()
            thumbColor = source.readInt()
            xThumbPosition = source.readFloat()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeFloat(cornerRadius)
            out.writeInt(trackColor)
            out.writeFloat(thumbWidth)
            out.writeInt(thumbColor)
        }

        companion object {

            @JvmField
            val CREATOR: Parcelable.ClassLoaderCreator<SavedState> = object :
                Parcelable.ClassLoaderCreator<SavedState> {
                override fun createFromParcel(source: Parcel, loader: ClassLoader): SavedState {
                    return SavedState(source, loader)
                }

                override fun createFromParcel(source: Parcel): SavedState {
                    return SavedState(source, null)
                }

                override fun newArray(size: Int): Array<SavedState> {
                    return newArray(size)
                }
            }
        }
    }
}