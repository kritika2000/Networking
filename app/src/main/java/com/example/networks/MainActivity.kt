package com.example.networks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val okhttp=OkHttpClient()
        val request=Request.Builder()
            .url("https://api.github.com/users/kritika2000")
            .build()
        GlobalScope.launch(Dispatchers.Main) {
            lateinit var USER:GithubUser
            USER=withContext(Dispatchers.IO) {
                val response = okhttp.newCall(request).execute()
                Log.i("On Execution", response.toString())
                 val gson = Gson()
                 gson.fromJson<GithubUser>(response.body?.string(), GithubUser::class.java)
            }
            Log.i("User","$USER")
            Picasso.get().load(USER.avatarUrl).into(iv)
        }
    }
}
