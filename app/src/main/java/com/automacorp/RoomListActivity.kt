package com.automacorp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.automacorp.adapter.RoomsAdapter
import com.automacorp.service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomListActivity : BasicActivity(), OnRoomClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val roomsAdapter = RoomsAdapter(this)

        findViewById<RecyclerView>(R.id.list_rooms).also { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = roomsAdapter
        }

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices.roomsApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        roomsAdapter.setItems(it.body() ?: emptyList()) }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        it.printStackTrace()
                        Toast.makeText(applicationContext, "Error on rooms loading $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    override fun selectRoom(id: Long) {
        val intent = Intent(this, RoomActivity::class.java).putExtra(MainActivity.ROOM_ID_PARAM, id)
        startActivity(intent)
    }
}