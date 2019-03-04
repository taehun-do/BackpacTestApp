package kr.backpac.test.model

data class LocationInfo constructor(
        val title: String,          // 지역 명
        val location_type: String,  // 지역 타입
        val woeid: Long,            // 지역 식별자
        val latt_long: String       // 위도, 경도 "lat,long"
) {
    var consolidated_weather: ArrayList<LocationWeather> = ArrayList()
}