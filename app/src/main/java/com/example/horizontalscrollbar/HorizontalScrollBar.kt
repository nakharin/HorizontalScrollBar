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
import kotlin.math.max

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

    // Style
    private var size: Float = 0f
    private var cornerRadius: Float = 0f

    private var trackWidth: Float = 0f
    private var trackColor: Int = 0

    private var thumbWidth: Float = 0f
    private var thumbColor: Int = 0

    // Variable
    private var transThumbX: Float = 0f
    private var maxRange = 0

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
        size = typedArray.getDimension(R.styleable.HorizontalScrollBar_horizontalScrollBar_size, 0f)
        cornerRadius = typedArray.getDimension(R.styleable.HorizontalScrollBar_horizontalScrollBar_cornerRadius, 0f)
        trackWidth = typedArray.getDimension(R.styleable.HorizontalScrollBar_horizontalScrollBar_trackWidth, 0f)
        trackColor = typedArray.getColor(R.styleable.HorizontalScrollBar_horizontalScrollBar_trackColor, 0)
        thumbWidth = typedArray.getDimension(R.styleable.HorizontalScrollBar_horizontalScrollBar_thumbWidth, 0f)
        thumbColor = typedArray.getColor(R.styleable.HorizontalScrollBar_horizontalScrollBar_thumbColor, 0)
        typedArray.recycle()
    }

    private fun setupView() {
        if (size != 0f) {
            scrollBarTrack.layoutParams.height = size.toInt()
            scrollBarThumb.layoutParams.height = size.toInt()
        }

        if (cornerRadius != 0f) {
            scrollBarTrack.radius = cornerRadius
            scrollBarThumb.radius = cornerRadius
        }

        if (trackWidth != 0f) {
            scrollBarTrack.layoutParams.width = trackWidth.toInt()
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

        scrollBarThumb.translationX = transThumbX
    }

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // The total width of the whole, pay attention to the whole, including the outside of the display area.
                val paddingStart = recyclerView.paddingStart
                val paddingEnd = recyclerView.paddingEnd
                val range = recyclerView.computeHorizontalScrollRange()
                if (maxRange < range) {
                    maxRange = range
                }
                val rangeWithPadding = max(maxRange + paddingStart + paddingEnd, 0)
                // The current offset of thumb
                val offset = recyclerView.computeHorizontalScrollOffset()
                // Screen width of device
                val screenWidth = context.resources.displayMetrics.widthPixels
                // Calculate the width of the scroll bar
                val transMaxRange = (scrollBarTrack.width - scrollBarThumb.width).toFloat()
                // Calculate new offset of scroll bar
                val transX = offset * transMaxRange / (rangeWithPadding - screenWidth)
                transThumbX = if (transX > transMaxRange) transMaxRange else transX

                // Log values for maintenance
                Log.i("Nakharin", "maxRange: $maxRange")
                Log.i("Nakharin", "rangeWithPadding: $rangeWithPadding")
                Log.i("Nakharin", "offset: $offset")
                Log.i("Nakharin", "screenWidth: $screenWidth")
                Log.i("Nakharin", "transMaxRange: $transMaxRange")
                Log.i("Nakharin", "transX: $transX")
                Log.i("Nakharin", "transThumbX: $transThumbX")
                Log.d("Nakharin", "--------------------------------------")

                // Set translationX to scroll bar thumb
                scrollBarThumb.translationX = transThumbX
                // Check can scroll horizontal
                if (rangeWithPadding < screenWidth) scrollBarContainer.visibility = View.GONE else scrollBarContainer.visibility = View.VISIBLE

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState: Parcelable? = super.onSaveInstanceState()
        superState?.let {
            val state = SavedState(superState)
            state.size = this.size
            state.cornerRadius = this.cornerRadius
            state.trackWidth = this.trackWidth
            state.trackColor = this.trackColor
            state.thumbWidth = this.thumbWidth
            state.thumbColor = this.thumbColor
            state.transThumbX = this.transThumbX
            return state
        } ?: run {
            return superState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        when (state) {
            is SavedState -> {
                super.onRestoreInstanceState(state.superState)
                this.size = state.size
                this.cornerRadius = state.cornerRadius
                this.trackWidth = state.trackWidth
                this.trackColor = state.trackColor
                this.thumbWidth = state.thumbWidth
                this.thumbColor = state.thumbColor
                this.transThumbX = state.transThumbX
                setupView()
            }
            else -> {
                super.onRestoreInstanceState(state)
            }
        }
    }

    internal class SavedState : AbsSavedState {

        var size: Float = 0f
        var cornerRadius: Float = 0f
        var trackWidth: Float = 0f
        var trackColor: Int = 0
        var thumbWidth: Float = 0f
        var thumbColor: Int = 0
        var endX: Float = 0f
        var transThumbX: Float = 0f

        constructor(superState: Parcelable) : super(superState)

        constructor(source: Parcel, loader: ClassLoader?) : super(source, loader) {
            size = source.readFloat()
            cornerRadius = source.readFloat()
            trackWidth = source.readFloat()
            trackColor = source.readInt()
            thumbWidth = source.readFloat()
            thumbColor = source.readInt()
            endX = source.readFloat()
            transThumbX = source.readFloat()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeFloat(size)
            out.writeFloat(cornerRadius)
            out.writeFloat(trackWidth)
            out.writeInt(trackColor)
            out.writeFloat(thumbWidth)
            out.writeInt(thumbColor)
            out.writeFloat(endX)
            out.writeFloat(transThumbX)
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