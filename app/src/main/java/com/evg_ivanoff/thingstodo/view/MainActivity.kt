package com.evg_ivanoff.thingstodo.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.evg_ivanoff.thingstodo.R
import com.evg_ivanoff.thingstodo.controller.RetrofitServices
import com.evg_ivanoff.thingstodo.model.Action
import com.evg_ivanoff.thingstodo.model.Common
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService
        btnSearch.setOnClickListener {
//            getRandomActivity()
            val activities = getCheckedActivities()
            getActivityFromCheckBox(activities)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId: Int = item.itemId
        if (itemId == R.id.menu_checkAll) {
            if (!chbBusywork.isChecked || !chbCharity.isChecked || !chbCooking.isChecked ||
                !chbDiy.isChecked || !chbEducation.isChecked || !chbMusic.isChecked ||
                !chbRecreational.isChecked || !chbRelaxation.isChecked || !chbSocial.isChecked
            ) {
                setChecked(true)
            } else {
                setChecked(false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getRandomActivity() {
        mService.getRandomActivity().enqueue(object : Callback<Action> {
            override fun onResponse(
                call: Call<Action>,
                response: Response<Action>
            ) {
                val item = response.body() as Action
                tvDescription.text = item.activity
                tvType.text = item.type
                tvMembers.text = item.participants.toString()
            }

            override fun onFailure(call: Call<Action>, t: Throwable) {
                Toast.makeText(applicationContext, "Server is not empty", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getActivityFromCheckBox(type: List<String>) {
        setEnabled(false)
        mService.getActivityByType(type).enqueue(object : Callback<Action> {
            override fun onResponse(call: Call<Action>, response: Response<Action>) {
                val item = response.body() as Action
                tvDescription.text = item.activity
                tvType.text = item.type
                tvMembers.text = item.participants.toString()
                setEnabled(true)
                tvLabelActivity.isVisible = true
                tvLabelMembers.isVisible = true
                tvLabelType.isVisible = true
            }

            override fun onFailure(call: Call<Action>, t: Throwable) {
                Toast.makeText(applicationContext, "Server is not empty", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun getCheckedActivities(): List<String> {
        val result = mutableListOf<String>()
        if (!chbBusywork.isChecked && !chbCharity.isChecked && !chbCooking.isChecked &&
            !chbDiy.isChecked && !chbEducation.isChecked && !chbMusic.isChecked &&
            !chbRecreational.isChecked && !chbRelaxation.isChecked && !chbSocial.isChecked
        ) {
            return listOf<String>(
                "education", "recreational", "social", "diy", "charity",
                "cooking", "relaxation", "music", "busywork"
            )
        }
        if (chbBusywork.isChecked) result.add("busywork")
        if (chbSocial.isChecked) result.add("social")
        if (chbRelaxation.isChecked) result.add("relaxation")
        if (chbRecreational.isChecked) result.add("recreational")
        if (chbMusic.isChecked) result.add("music")
        if (chbEducation.isChecked) result.add("education")
        if (chbDiy.isChecked) result.add("diy")
        if (chbCooking.isChecked) result.add("cooking")
        if (chbCharity.isChecked) result.add("charity")
        return result
    }

    fun setEnabled(bool: Boolean){
        chbBusywork.isEnabled = bool
        chbSocial.isEnabled = bool
        chbRelaxation.isEnabled = bool
        chbRecreational.isEnabled = bool
        chbMusic.isEnabled = bool
        chbEducation.isEnabled = bool
        chbDiy.isEnabled = bool
        chbCooking.isEnabled = bool
        chbCharity.isEnabled = bool
    }

    fun setChecked(bool: Boolean){
        chbBusywork.isChecked = bool
        chbSocial.isChecked = bool
        chbRelaxation.isChecked = bool
        chbRecreational.isChecked = bool
        chbMusic.isChecked = bool
        chbEducation.isChecked = bool
        chbDiy.isChecked = bool
        chbCooking.isChecked = bool
        chbCharity.isChecked = bool
    }
}