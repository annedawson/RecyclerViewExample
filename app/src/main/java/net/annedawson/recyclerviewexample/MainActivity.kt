package net.annedawson.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val exampleList = generateDummyList(500)  // a property of this class private to the class
    private val adapter = ExampleAdapter(exampleList)  // a ref to list - call by reference - same list as in adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recycler view with synthetic property - deprecated
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this) // this is the current activity
        recycler_view.setHasFixedSize(true)  // just an optimisation if we know if the RecyclerView
                                             // has fixed width and height
    }

    fun insertItem(view: View) { // must match name on onclick in xml and needs view: View but you can ignore it
        val index = Random.nextInt(8)  // 0 to 7 random number
        val newItem = ExampleItem(
            R.drawable.ic_android,
            "New item at position $index",
            "Line 2"
        )
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index) // refreshing list in RecycleView
    }


    fun removeItem(view: View) {
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index) // refreshing list in RecycleView
    }


    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {  // ArrayList is modifyable
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sailing
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}