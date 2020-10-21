package com.gujun.kotlincoroutinesapplication.networkOld

import com.gujun.kotlincoroutinesapplication.networkOld.model.Data
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 *    author : gujun
 *    date   : 2020/10/21 14:15
 *    desc   : 请求接口,网上找的公开API接口：https://api.nowtime.cc/v1/ipv4?ip=223.5.5.5
 */
interface ApiService {

    companion object {
        const val baseUrl = "https://api.nowtime.cc/"
    }

    //1、******  这里使用的返回值是 Deferred<T> 对象，这就意味着使用的时候要通过 await 来获取返回值  ******
    @GET("v1/ipv4")
    fun getDataAsync(@QueryMap map: Map<String, String>): Deferred<Data>

}