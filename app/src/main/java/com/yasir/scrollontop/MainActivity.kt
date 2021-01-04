package com.yasir.scrollontop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val modelMainList: ArrayList<ModelMain> = ArrayList()
    var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainAdapter = MainAdapter(modelMainList)

        rv_main.setLayoutManager(LinearLayoutManager(this))
        rv_main.setHasFixedSize(true)
        rv_main.setAdapter(mainAdapter)

        fabBackOnTop.setOnClickListener {
            rv_main.smoothScrollToPosition(0)
        }

        getData()
    }

    private fun getData(){
        AndroidNetworking.get("https://dev.farizdotid.com/api/purwakarta/kuliner")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val playerArray = response.getJSONArray("kuliner")
                        for (i in 0 until playerArray.length()) {
                            val jsonObject = playerArray.getJSONObject(i)
                            val dataApi = ModelMain()
                            dataApi.name = jsonObject.getString("nama")
                            dataApi.jam = jsonObject.getString("jam_buka_tutup")
                            dataApi.alamat = jsonObject.getString("alamat")
                            modelMainList.add(dataApi)
                        }
                        mainAdapter?.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(this@MainActivity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                }
            })
    }
}