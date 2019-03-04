package kr.backpac.test.network

import io.reactivex.Single
import kr.backpac.test.common.Define
import kr.backpac.test.model.LocationInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET(Define.API_SEARCH)
    fun getSearchLocation(@Query(Define.API_PARAM_QUERY) query: String): Single<ArrayList<LocationInfo>>

    @GET(Define.API_LOCATION)
    fun getLocationWeather(@Path(Define.API_PATH_WOEID) woeid: Long): Single<LocationInfo>

}