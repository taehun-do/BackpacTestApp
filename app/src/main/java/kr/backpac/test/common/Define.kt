package kr.backpac.test.common

class Define {
    companion object {
        const val API_BASE_URL      = "https://www.metaweather.com/api/"

        const val API_PARAM_QUERY   = "query"
        const val API_PATH_WOEID    = "woeid"

        const val API_SEARCH        = "location/search"
        const val API_LOCATION      = "location/{$API_PATH_WOEID}"

        const val TIME_OUT_CONNECTION: Long = 5
        const val TIME_OUT_WRITE: Long     = 10
        const val TIME_OUT_READ: Long       = 10

        const val FORMAT_ICON_URL   = "https://www.metaweather.com/static/img/weather/png/%s.png"
    }
}