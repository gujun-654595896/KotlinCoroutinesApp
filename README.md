# KotlinCoroutinesApp

目前只是添加了Retrofit + Coroutines(协程)的网络请求，包括Retrofit 2.6.0之前  和 Retrofit 2.6.0以后版本的请求方式。

主要区别就是Retrofit发布的2.6.0版本内部支持了kotlin协程中的挂起（suspend）修饰符，进一步简化了使用 Retrofit 和协程来进行网络请求的过程。

1、ApiService的返回值：
旧版返回Deferred
新版返回真实对象，但是此函数得用suspend修饰

2、Retrofit对象创建时：
旧版需要添加.addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())人工转成Deferred<>对象
新版不需要添加上述方法

3、接口使用时
旧版返回Deferred，需要调用await()函数
新版返回真实对象，直接使用
