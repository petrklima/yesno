package com.klima.yesno

import android.content.DialogInterface
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

class TextInCircle : Fragment(), View.OnClickListener {

    var color: Int = Color.BLACK
    var text: String = ""
    var backgroundColor: Int = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        color = arguments?.getInt("color") ?: 0
        text = arguments?.getString("text") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_in_circle, container, false)
        view.setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.setText(text)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color))
    }

    override fun onClick(view: View?) {
        if (backgroundColor == Color.BLACK)
            backgroundColor = color
        else
            backgroundColor = Color.BLACK

        view?.setBackgroundColor(backgroundColor)
    }

    companion object {
        fun newInstance(color: Int, text: String): TextInCircle {
            val textInCircle = TextInCircle()
            val arguments = Bundle()
            arguments.putInt("color", color)
            arguments.putString("text", text)
            textInCircle.arguments = arguments
            return textInCircle
        }
    }
}