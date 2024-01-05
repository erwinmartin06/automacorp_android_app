package com.automacorp

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.automacorp.adapter.RoomsAdapter
import com.automacorp.service.RoomService

class RoomListActivity : BasicActivity(), OnRoomClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val roomsAdapter = RoomsAdapter(this)

        findViewById<RecyclerView>(R.id.list_rooms).also { recyclerView -> // (1)
            recyclerView.layoutManager = LinearLayoutManager(this) // (2)
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL)) // (3)
            recyclerView.setHasFixedSize(true) // (4)
            recyclerView.adapter = roomsAdapter // (5)
        }

        roomsAdapter.setItems(RoomService.ROOMS)  // (6)
    }

    override fun selectRoom(id: Long) {
        val intent = Intent(this, RoomActivity::class.java).putExtra(MainActivity.ROOM_ID_PARAM, id)
        startActivity(intent)
    }
}