package olucasmoro.android.desafiodqrtech.presenter.iu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.databinding.CurrencyItemBinding

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private lateinit var mListener: CurrencyListListener
    private var mCurrencyList: List<Currency> = arrayListOf()

    class ViewHolder(
        private val binding: CurrencyItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency, listener: CurrencyListListener) {

            with(binding) {
                textCode.text = currency.name
                textDesc.text = currency.value
            }

            binding.root.setOnClickListener {
                listener.onClick(currency)
            }
        }

        companion object {
            fun inflate(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mCurrencyList[position], mListener)
    }

    override fun getItemCount(): Int {
        return mCurrencyList.size
    }

    fun updateCurrency(list: List<Currency>) {
        mCurrencyList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: CurrencyListListener) {
        mListener = listener
    }
}