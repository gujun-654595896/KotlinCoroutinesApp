package com.gujun.kotlincoroutinesapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gujun.kotlincoroutinesapplication.networkOld.Requester
import com.gujun.kotlincoroutinesapplication.networkOld.model.Data
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivityOld : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            getData()
        }
    }

    private suspend fun getData() {
        try {
            val requestMap = HashMap<String, String>()
            requestMap["ip"] = "223.5.5.5"

            //3、******  接口返回的是Deferred<T> 对象，此处需要用await()获取真实数据 ******
            val data = Requester.apiService().getDataAsync(requestMap).await()
            updateUi(data)
        } catch (e: Exception) {
            //4、捕获接口的异常
            Toast.makeText(this, "请求接口失败", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUi(data: Data) {
        value.text = "${data.code}"
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}