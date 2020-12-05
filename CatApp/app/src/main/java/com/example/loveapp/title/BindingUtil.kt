package com.example.loveapp.title

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.loveapp.R
import com.example.loveapp.dao.LoveResultSave


@BindingAdapter("resultImage")
fun ImageView.setImage(item: LoveResultSave?) {
    item?.let {
      setImageResource(R.drawable.heart)
    }
}

@BindingAdapter("resultPerc")
fun TextView.setPerc(item: LoveResultSave?) {
    item?.let {
        text = item.percentage.toString()+"%"
    }
}

@BindingAdapter("resultName1")
fun TextView.setName1(item: LoveResultSave?) {
    item?.let {
        text = item.fname
    }
}

@BindingAdapter("resultName2")
fun TextView.setName2(item: LoveResultSave?) {
    item?.let {
        text = item.sname
    }
}

@BindingAdapter("resultResult")
fun TextView.setResult(item: LoveResultSave?) {
    item?.let {
        text = item.result
    }
}