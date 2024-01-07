package com.automacorp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.automacorp.R
import com.automacorp.adapter.HeatersAdapter
import com.automacorp.adapter.WindowsAdapter
import com.automacorp.model.RoomCommandDto
import com.automacorp.model.RoomDto
import com.automacorp.service.ApiServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val roomId = intent.getLongExtra(MainActivity.ROOM_ID_PARAM, 0)
        fetchRoomAndWindowDetails(roomId)
    }

    private fun fetchRoomAndWindowDetails(roomId: Long) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices.roomsApiService.findById(roomId).execute() }
                .onSuccess { response ->
                    withContext(context = Dispatchers.Main) {
                        response.body()?.let { room ->
                            populateScreenRoom(room)
                            fetchWindowAndHeatersDetails(room.name)
                        } ?: Toast.makeText(applicationContext, "Room not found", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        it.printStackTrace()
                        Toast.makeText(applicationContext, "Error on room loading $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    private fun fetchWindowAndHeatersDetails(roomName: String) {
        val windowsAdapter = WindowsAdapter()

        findViewById<RecyclerView>(R.id.list_windows).also { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = windowsAdapter
        }

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices.windowsApiService.findWindowsByRoomName(roomName).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        windowsAdapter.setItems(it.body() ?: emptyList()) }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        it.printStackTrace()
                        Toast.makeText(applicationContext, "Error on windows loading $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }

        fetchHeaterDetails(roomName)
    }

    private fun fetchHeaterDetails(roomName: String) {
        val heaterAdapter = HeatersAdapter()

        findViewById<RecyclerView>(R.id.list_heaters).also { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = heaterAdapter
        }

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices.heatersApiService.findHeatersByRoomName(roomName).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        heaterAdapter.setItems(it.body() ?: emptyList()) }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        it.printStackTrace()
                        Toast.makeText(applicationContext, "Error on heaters loading $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    private fun populateScreenRoom(room: RoomDto?) {
        val roomName = findViewById<EditText>(R.id.txt_room_name2)
        roomName.setText(room?.name)

        val roomCurrentTemperature = findViewById<TextView>(R.id.txt_current_temperature)
        roomCurrentTemperature.text = (room?.currentTemp.toString())

        val roomTargetTemperature = findViewById<EditText>(R.id.txt_target_temperature)
        roomTargetTemperature.setText(room?.targetTemp?.toString() ?: "")

        findViewById<FloatingActionButton>(R.id.saveButton).setOnClickListener {
            saveRoom(room)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<FloatingActionButton>(R.id.saveButton).setOnClickListener(null)
    }

    private fun saveRoom(room: RoomDto?) {
        val roomId = room!!.id
        val roomDto = RoomCommandDto(
            name = findViewById<EditText>(R.id.txt_room_name2).text.toString(),
            currentTemp = room.currentTemp,
            targetTemp = findViewById<EditText>(R.id.txt_target_temperature).text.toString()
                .toDoubleOrNull(),
            floor = room.floor,
            buildingId = room.buildingId
        )
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching {
                    ApiServices.roomsApiService.updateRoom(roomId, roomDto).execute()
            }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        startActivity(Intent(applicationContext, RoomListActivity::class.java))
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        it.printStackTrace()
                        Toast.makeText(applicationContext, "Error on room saving $it", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}