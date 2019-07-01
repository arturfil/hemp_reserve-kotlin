package com.arturofilio.hemp_reserve_mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ListView
import com.arturofilio.hemp_reserve_mvvm.R
import com.arturofilio.hemp_reserve_mvvm.model.Product
import com.arturofilio.hemp_reserve_mvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val productsAdapter = ProductListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        productsList.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = productsAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.products.observe(this, Observer { products ->
            productsList.visibility = View.VISIBLE
            products?.let { productsAdapter.updateProudct(it)}
        })

        viewModel.productLoadError.observe(this, Observer { isError ->
            isError?.let{list_error.visibility = if(it) View.VISIBLE else View.GONE}
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    productsList.visibility = View.GONE
                }
            }
        })
    }
}
