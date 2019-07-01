package com.arturofilio.hemp_reserve_mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.arturofilio.hemp_reserve_mvvm.model.Product
import com.arturofilio.hemp_reserve_mvvm.model.ProductsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val productsService = ProductsService()
    private val disposable = CompositeDisposable()

    val products = MutableLiveData<List<Product>>()
    val productLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchProducts()
    }

    private fun fetchProducts() {
        loading.value = true
        disposable.add(
            productsService.getProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                    override fun onSuccess(value: List<Product>?) {
                        products.value = value
                        productLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        productLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}