package kr.backpac.test

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import kr.backpac.test.network.Network

class MainActivity: AppCompatActivity() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Network.request(disposable, Network.getWeatherApi().getSearchLocation("se"), Consumer { locationList ->
            locationList?.apply {
                recyclerView.adapter = WeatherListAdapter(locationList)
                for (i in indices) {
                    val item = get(i)
                    Network.request(disposable, Network.getWeatherApi().getLocationWeather(item.woeid), Consumer { locationInfo ->
                        item.consolidated_weather = locationInfo.consolidated_weather
                        recyclerView.adapter?.notifyItemChanged(i)
                    })
                }
            }
        })
    }
}