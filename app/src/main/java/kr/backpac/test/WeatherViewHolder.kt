package kr.backpac.test

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.weather_list_row.*
import kr.backpac.test.common.Define
import kr.backpac.test.model.LocationInfo

class WeatherViewHolder private constructor(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer  {

    companion object {
        fun newInstance(parent: ViewGroup): WeatherViewHolder {
            return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_list_row, parent, false))
        }
    }

    fun bindData(info: LocationInfo) {
        tvLocation.text = info.title

        if (info.consolidated_weather != null && info.consolidated_weather.size >= 2) {
            val todayWeather = info.consolidated_weather[0]
            val tomorrowWeather = info.consolidated_weather[1]

            tvTodayState.text = todayWeather.weather_state_name
            tvTodayTemp.text = "${todayWeather.the_temp.toInt()}\u2103"
            tvTodayHumidity.text = "${todayWeather.humidity}%"
            Glide.with(ivTodayState).asBitmap().placeholder(0).load(String.format(Define.FORMAT_ICON_URL, todayWeather.weather_state_abbr)).into(ivTodayState)

            tvTomorrowState.text = tomorrowWeather.weather_state_name
            tvTomorrowTemp.text = "${tomorrowWeather.the_temp.toInt()}\u2103"
            tvTomorrowHumidity.text = "${tomorrowWeather.humidity}%"
            Glide.with(ivTomorrowState).asBitmap().placeholder(0).load(String.format(Define.FORMAT_ICON_URL, tomorrowWeather.weather_state_abbr)).into(ivTomorrowState)


        }

    }
}