package com.breakingbad

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.breakingbad.databinding.SingleItemBinding
import com.breakingbad.network.SingleActor

class ActorListAdapter(val onClickListener: OnClickListener) : ListAdapter<SingleActor, ActorListAdapter.ActorViewHolder>(DiffCallback), Filterable {

    companion object DiffCallback : DiffUtil.ItemCallback<SingleActor>() {
        override fun areItemsTheSame(oldItem: SingleActor, newItem: SingleActor): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SingleActor, newItem: SingleActor): Boolean {
            return oldItem.char_id == newItem.char_id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorListAdapter.ActorViewHolder {
        return ActorViewHolder(SingleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class ActorViewHolder(private var binding: SingleItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(singleActor: SingleActor) {
            binding.actor = singleActor
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ActorListAdapter.ActorViewHolder, position: Int) {
        val singleActor = getItem(position)


        holder.itemView.setOnClickListener {
            onClickListener.onClick(singleActor)
        }
        holder.bind(singleActor)
    }

    class OnClickListener(val clickListener: (singleActor: SingleActor) -> Unit) {
        fun onClick(singleActor:SingleActor) = clickListener(singleActor)
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    //TODO show whole list
                } else {
                    //TODO show filtered list
//                    val resultList = ArrayList<String>()
//                    for (row in cactorList) {
//                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
//                            resultList.add(row)
//                        }
//                    }
//                    actorFilterList = resultList
                }
                val filterResults = FilterResults()
//                filterResults.values = actorFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, result: FilterResults?) {
//                actorFilterList = results?.values as ArrayList<SingleActor>
                notifyDataSetChanged()
            }

        }
    }

}