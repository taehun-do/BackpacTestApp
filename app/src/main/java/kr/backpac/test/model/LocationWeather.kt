package kr.backpac.test.model

data class LocationWeather(
        val weather_state_name: String, // 날씨 요약
        val weather_state_abbr: String, // 날씨 아이콘
        val the_temp: Double,           // 기온
        val humidity: Int               // 습도
)