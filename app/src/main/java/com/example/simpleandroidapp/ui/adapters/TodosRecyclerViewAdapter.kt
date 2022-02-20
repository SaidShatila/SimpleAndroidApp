package com.example.simpleandroidapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleandroidapp.R
import com.example.simpleandroidapp.data.remote.response.Todos
import com.example.simpleandroidapp.databinding.ItemTodosBinding

class TodosRecyclerViewAdapter : RecyclerView.Adapter<TodosRecyclerViewAdapter.TodosViewHolder>() {

    private var todosList: List<Todos> = emptyList()
    private lateinit var context : Context
    @SuppressLint("NotifyDataSetChanged")
    fun setUpData(todosList: List<Todos>) {
        this.todosList = todosList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemTodosBinding.inflate(inflater,parent,false)
        return TodosViewHolder(binding,context)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        holder.bind(todosList[position])
    }


    override fun getItemCount(): Int = todosList.size

    class TodosViewHolder(private val binding: ItemTodosBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todos: Todos) {
            with(binding) {
                tvId.text = todos.id.toString()
                tvName.text = todos.title
                tvUserId.text = todos.userId.toString()
                tvCompleted.text = todos.completed.toString()

                if (todos.completed) {
                            itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.purple_200))
                }
            }
        }
    }

}