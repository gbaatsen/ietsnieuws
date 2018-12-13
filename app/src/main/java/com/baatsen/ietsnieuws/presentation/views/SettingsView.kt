package com.baatsen.ietsnieuws.presentation.views

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.presentation.settings.Setting


class SettingsView : ScrollView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var limitsLayout: LinearLayout = LinearLayout(context)
    var onClickListener: OnClickListener? = null

    init {
        limitsLayout.layoutParams =
                ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        limitsLayout.orientation = LinearLayout.VERTICAL
        limitsLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        limitsLayout.elevation = 4f
        addView(limitsLayout)
    }

    fun setSettingItems(settings: List<Setting>?) {
        limitsLayout.removeAllViews()
        settings?.forEach { setting ->
            val view = getView(setting)
            view.setOnClickListener { onClickListener?.onClick(setting) }
            limitsLayout.addView(view)
        }
    }

    private fun getView(setting: Setting): View {
        return SettingsItemView(context).apply {
            setTitle(setting.title)
            setSubTitle(setting.subTitle)
            setIcon(setting.icon)
        }
    }

    interface OnClickListener {
        fun onClick(setting: Setting)
    }
}