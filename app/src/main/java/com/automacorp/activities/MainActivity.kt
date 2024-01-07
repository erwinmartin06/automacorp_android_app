package com.automacorp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.automacorp.OnBuildingClickListener
import com.automacorp.R
import com.automacorp.adapter.BuildingsAdapter
import com.automacorp.service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BasicActivity(), OnBuildingClickListener {

    companion object {
        const val BUILDING_NAME_PARAM = "com.automacorp.buildingname.attribute"
        const val ROOM_ID_PARAM = "com.automacorp.roomname.attribute"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchBuildingDetails()
    }

    /** Called when the user taps the button */
    fun openBuilding(view: View) {
        // Extract value filled in editext identified with txt_building_name id
        val buildingName = findViewById<EditText>(R.id.txt_building_name).text.toString()

        val intent = Intent(this, RoomListByBuildingActivity::class.java).apply {
            putExtra(BUILDING_NAME_PARAM, buildingName)
        }
        // Display a message
        Toast.makeText(this, "You choose $buildingName", Toast.LENGTH_LONG).show()
        startActivity(intent)
    }

    private fun fetchBuildingDetails() {
        val buildingsAdapter = BuildingsAdapter(this)

        findViewById<RecyclerView>(R.id.list_buildings).also { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = buildingsAdapter
        }

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices.buildingsApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        buildingsAdapter.setItems(it.body() ?: emptyList()) }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        it.printStackTrace()
                        Toast.makeText(applicationContext, "Error on buildings loading $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    override fun selectBuilding(nameBuilding: String) {
        val intent = Intent(this, RoomListByBuildingActivity::class.java).putExtra(BUILDING_NAME_PARAM, nameBuilding)
        startActivity(intent)
    }
}