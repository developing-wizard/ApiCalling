package com.example.apicalling.userRecycler

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalling.R
import com.example.apicalling.datamodels.UserData


class UserAdapter(context: Context, var userList:ArrayList<UserData>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item,
            parent, false
        )
        return UserAdapter.UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.idUser.text = userList[position].id.toString()
        holder.nameUser.text = userList[position].name
        holder.itemView.setOnClickListener()
        {
            showDialog(holder.itemView.context, position)

        }


    }
    private  fun showDialog(context: Context, position: Int ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_detail)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        val username = dialog.findViewById<TextView>(R.id.Usernamedetail)
        username.text = userList[position].username
        val website = dialog.findViewById<TextView>(R.id.websitedetail)
        website.text = userList[position].website
        val email =  dialog.findViewById<TextView>(R.id.emaildetail)
        email.text = userList[position].email
        val phone = dialog.findViewById<TextView>(R.id.phonedetail)
        phone.text = userList[position].phone.toString()
        val name = dialog.findViewById<TextView>(R.id.namedetail)
        name.text = userList[position].name
        val id = dialog.findViewById<TextView>(R.id.iddetail)
        id.text = userList[position].id.toString()

        dialog.show()
        val dismiss = dialog.findViewById(R.id.dismissBtn) as Button
        dismiss.setOnClickListener {
            dialog.dismiss()
        }
    }
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
      //  val usernameUser : TextView = itemView.findViewById(R.id.usernameUser)
        val nameUser : TextView = itemView.findViewById(R.id.nameUser)
    //    val emailUser : TextView = itemView.findViewById(R.id.emailUser)
    //    val phoneUser : TextView = itemView.findViewById(R.id.phoneUser)
        val idUser : TextView = itemView.findViewById(R.id.idUser)

    }


}