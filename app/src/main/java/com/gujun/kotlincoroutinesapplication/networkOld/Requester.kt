package com.gujun.kotlincoroutinesapplication.networkOld

import com.gujun.kotlincoroutinesapplication.networkNew.interceptor.HeaderInterceptor
import com.gujun.kotlincoroutinesapplication.networkOld.interceptor.LoginInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *    author : gujun
 *    date   : 2020/10/21 14:19
 *    desc   : retrofit请求工具类
 */
class Requester {

    companion object {
        private fun <T> getService(baseUrl: String, service: Class<T>): T {
            var client = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())//添加拦截器
                .addInterceptor(LoginInterceptor())//添加拦截器
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//需要引入retrofit的converter-gson库

                //2、****** 我们需要人工返回 Deferred<T> 对象 ******
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())//正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或协程的Deferred对象等
                .client(client)
                .build()
            return retrofit.create(service)
        }

        fun apiService(): ApiService {
            return getService(ApiService.baseUrl, ApiService::class.java)
        }
    }

}