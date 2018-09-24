package com.makeschool.companion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import com.makeschool.companion.estimote.ProximityContent
import com.makeschool.companion.estimote.ProximityContentAdapter
import com.makeschool.companion.estimote.ProximityContentManager

class MainActivity : AppCompatActivity() {
    private var proximityContentManager: ProximityContentManager? = null
    private var proximityContentAdapter: ProximityContentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        proximityContentAdapter = ProximityContentAdapter(this)
        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = proximityContentAdapter

        RequirementsWizardFactory
                .createEstimoteRequirementsWizard()
                .fulfillRequirements(this,
                        {
                            Log.d("app", "requirements fulfilled")
                            startProximityContentManager()
                        },
                        { requirements ->
                            Log.e("app", "requirements missing: " + requirements)
                        }
                        , { throwable ->
                    Log.e("app", "requirements error: " + throwable)
                })
    }

    private fun startProximityContentManager() {
        proximityContentManager = ProximityContentManager(this)
        proximityContentManager?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        proximityContentManager?.stop()
    }

    fun setNearbyContent(nearbyContent: List<ProximityContent>) {
        proximityContentAdapter?.setNearbyContent(nearbyContent)
        proximityContentAdapter?.notifyDataSetChanged()
    }
}
