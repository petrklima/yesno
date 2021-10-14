package com.klima.yesno

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment

class TextInShape :  Fragment()
{
    private var viewId: Int = -1
    private var color: Int = Color.BLACK
    private var text: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewId = arguments?.getInt("viewId") ?: -1
        color = arguments?.getInt("color") ?: 0
        text = arguments?.getString("text") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_in_shape, container, false)
        view.id = viewId
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = text
        val imageView: ImageView = view.findViewById(R.id.imageView)
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color))
    }

    companion object {
        fun newInstance(viewId: Int, color: Int, text: String): TextInShape {
            val textInCircle = TextInShape()
            val arguments = Bundle()
            arguments.putInt("viewId", viewId)
            arguments.putInt("color", color)
            arguments.putString("text", text)
            textInCircle.arguments = arguments
            return textInCircle
        }
    }
}