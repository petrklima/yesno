package com.klima.yesno

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity(), View.OnClickListener {

    protected lateinit var viewPager: ViewPager2

    protected val layoutId: Int = R.layout.activity_main

    private val sliderAdapter: ViewsSliderAdapter = ViewsSliderAdapter()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sliderAdapter
        viewPager.setCurrentItem(R.id.yesNoLayout)
    }

    override fun onClick(view: View?) {
    }

    companion object {
        val layouts = arrayOf(R.layout.yesno, R.layout.morestop)
    }
}

class ViewsSliderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
    override fun getItemViewType(position: Int): Int {
        return MainActivity.layouts.get(position)
    }

    override fun getItemCount(): Int {
        return MainActivity.layouts.size
    }

    inner class SliderViewHolder(view: View?) : RecyclerView.ViewHolder(
        view!!
    ) {
    }
}