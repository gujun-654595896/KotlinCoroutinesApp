package com.gujun.kotlincoroutinesapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gujun.kotlincoroutinesapplication.networkNew.Requester
import com.gujun.kotlincoroutinesapplication.networkNew.model.Data
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivityNew : AppCompatActivity(), CoroutineScope by MainScope() {

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
            //3、******  retrofit2.6.0以后支持suspend挂起修饰，所以能返回真实对象，不再调用await()函数 ******
            val data = Requester.apiService().getDataAsync(requestMap)
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