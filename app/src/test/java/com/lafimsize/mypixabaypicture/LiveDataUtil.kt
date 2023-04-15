package com.lafimsize.mypixabaypicture

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.getOrAwaitValueTest(time:Long=2,timeUnit:TimeUnit=TimeUnit.SECONDS):T{

    var data:T?=null
    val latch=CountDownLatch(1)
    val observer=object :Observer<T>{
        override fun onChanged(o: T?) {
            data=o
            latch.countDown()
            this@getOrAwaitValueTest.removeObserver(this)
        }
    }
    this.observeForever(observer)
    val bekleyis=latch.await(time,timeUnit)
    if(!bekleyis){
        throw TimeoutException("LiveData verisi hiç set edilmedi.")
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}