package com.example.menuandsettings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val context: Context,val items: ArrayList<Registration>):
   RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.listview4,
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item=items.get(position)

        holder.viewHolderID.text=item.id.toString()
        holder.viewHolderName.text=item.name
        holder.viewHolderEmail.text=item.email
        holder.viewHolderPass.text=item.password

        if (position%2==0){
            holder.layoutView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.purple_500
                )
            )
        }else{
            holder.layoutView.setBackgroundColor(ContextCompat.getColor(
                context,R.color.white
            ))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val viewHolderID:TextView=view.findViewById(R.id.viewID2)
        val viewHolderName:TextView=view.findViewById(R.id.viewName2)
        val viewHolderEmail:TextView=view.findViewById(R.id.viewEmail2)
        val viewHolderPass:TextView=view.findViewById(R.id.viewPass2)
        val layoutView:LinearLayout=view.findViewById(R.id.listview2ID)


    }
}