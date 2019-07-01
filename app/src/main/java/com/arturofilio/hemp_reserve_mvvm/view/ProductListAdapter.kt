package com.arturofilio.hemp_reserve_mvvm.view

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturofilio.hemp_reserve_mvvm.R
import com.arturofilio.hemp_reserve_mvvm.model.Product
import com.arturofilio.hemp_reserve_mvvm.util.getProgressDrawable
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListAdapter(var products: ArrayList<Product>): RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    fun updateProudct(newProducts: List<Product>) {
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }


    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val productName = view.name
        private val description = view.description
        private val price = view.price
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(product: Product) {
            productName.text = product.name
            price.text = product.price
            description.text = product.description
        }
    }

}