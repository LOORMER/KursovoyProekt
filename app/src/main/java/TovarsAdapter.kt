import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kursach.R
import com.google.firebase.database.FirebaseDatabase

class TovarsAdapter( val tovarList: MutableList<Tovars>) : RecyclerView.Adapter<TovarsAdapter.tovarViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tovarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle, parent, false)

        return tovarViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tovarList.size
    }

    override fun onBindViewHolder(holder: tovarViewHolder, position: Int) {
        val currentitem = tovarList[position]
        holder.idTovar.text = currentitem.id.toString()
        holder.nameHotels.text = currentitem.name
        //holder.cost.text =currentitem.cost.toString()
        holder.commentHotels.text = currentitem.comments
        val db = FirebaseDatabase.getInstance().getReference()
        holder.useHotelBtn.setOnClickListener {
            db.child("Tovars").child(currentitem.id.toString()).child("byTicket").setValue(true)
        }

    }


    class tovarViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        val idTovar : TextView = itemView.findViewById(R.id.idHotels)
        val nameHotels : TextView = itemView.findViewById(R.id.nameHotels)
        //val cost :TextView = itemView.findViewById(R.id.cost)
        val commentHotels : TextView = itemView.findViewById(R.id.commentHotels)
        val useHotelBtn: Button = itemView.findViewById(R.id.useHotelBtn)

    }
}