package net.annedawson.recyclerviewexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*
// if kotlinx is highlighted in red, make sure to add
// id 'kotlin-android-extensions'
// to the list of plugins in build.gradle(:app)
// then synch - but see below...

// https://stackoverflow.com/questions/64431882/how-to-enable-kotlin-android-extensions-by-default-in-android-studio-4-1

// https://developer.android.com/topic/libraries/view-binding/migration
// kotlin android extensions is deprecated - use Jetpack view binding instead


// the adapter gets passed the data to be displayed in the RecyclerView card.

class ExampleAdapter(private val exampleList: List<ExampleItem>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>(){
    // must implement these 3 member methods:  press Ctrl I at this location

    // called a few times, once for each item that appears on the screen
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
           parent, false)
        // the line above is a bit complex but you don't need to understand it, just use it as is
        // and all should be well. parent is the RecyclerView this viewholder will be placed in
        // LayoutInflater converts an xml layout into a view object
        // context is the main_activity
        // don't worry about understanding the line above. Use as is.
        return ExampleViewHolder(itemView)
    }

    // called over and over again, either when we scroll items on the screen or update them
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1   // Ctrl D to duplicate a line
        holder.textView2.text = currentItem.text2   // kotlin's property syntax
        //the above uses the three cached view references we created below

        // the above is equivalent to
        // holder.itemView.text_view_1.text = currentItem.text2
        // it will work but it uses findViewById which we want to avoid as this method
        // is called over and over again

           }

    override fun getItemCount() = exampleList.size //Kotlin's single expression syntax for functions

    // nested class
    // itemView is a single row of data
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // a ViewHolder represents a single row in our list - the card view
        // references to each view in each row image view and two text views.
        // cached references in ViewHolder and ViewHolder is recycled
        // three properties:
        val imageView: ImageView = itemView.image_view // image_view is a synthetic property  // calls findViewById internally
        val textView1: TextView = itemView.text_view_1 // text_view_1 is a synthetic property
        val textView2: TextView = itemView.text_view_2 // text_view_2 is a synthetic property
        // the above is equivalent to itemView.findViewById(R.id.text_view_2)
    }


}