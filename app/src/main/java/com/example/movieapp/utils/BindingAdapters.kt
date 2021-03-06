package com.example.movieapp.utils

import android.widget.ImageView
import android.widget.TextView

import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["imageUrl", "placeholderImage", "errorImage"], requireAll = false)
fun loadImageFromInternet(
    view: ImageView,
    imageUrl: String,
    placeholderImage: Boolean,
    errorImage: Boolean,
) {

    val requestOptions = RequestOptions()
    requestOptions.centerCrop()

    if (placeholderImage)
        requestOptions.placeholder(R.drawable.image_placeholder)

    if (errorImage)
        requestOptions.placeholder(R.drawable.error_image)

    val path = BaseUrls.BASE_URL_IMAGE + imageUrl
    Glide.with(view.context)
        .setDefaultRequestOptions(requestOptions)
        .load(path)
        .into(view)

}


@BindingAdapter("dateComparision")
fun dateCompare(textView: TextView, dateValue: String) {

    textView.text = dateValue

    if (dateValue.isNotEmpty()) {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val responseDate: Date? = inputFormat.parse(dateValue)

        val calendar = Calendar.getInstance()
        calendar.time = responseDate!!
        val responseYear = calendar.get(Calendar.YEAR)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR);



        if (responseYear.equals(currentYear)) {
// equal dates
            textView.setBackgroundResource(R.drawable.year_current_bg)
            textView.setTextColor(textView.context.resources.getColor(R.color.white))
        } else {
            textView.setBackgroundResource(R.drawable.year_other_bg)
            textView.setTextColor(textView.context.resources.getColor(R.color.black))
        }

    }


}
