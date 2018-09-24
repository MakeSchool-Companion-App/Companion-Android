package com.makeschool.companion.estimote

import android.content.Context
import android.util.Log
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials
import com.makeschool.companion.MainActivity
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.estimote.proximity_sdk.api.ProximityZoneBuilder
import java.util.*

class ProximityContentManager(private val context: Context) {

    private var proximityObserverHandler: ProximityObserver.Handler? = null

    fun start() {
        val cloudCredentials =  EstimoteCloudCredentials("liwa-johnson-gmail-com-s-p-9lr", "0f5f4bdbcf163a8c7539e81b93f9fd7b")

        val proximityObserver = ProximityObserverBuilder(context, cloudCredentials)
                .withTelemetryReportingDisabled()
                .withEstimoteSecureMonitoringDisabled()
                .onError { throwable ->
                    Log.e("app", "proximity observer error: $throwable")
                }
                .withBalancedPowerMode()
                .build()

        val zone = ProximityZoneBuilder()
                .forTag("liwa-johnson-gmail-com-s-p-9lr")
                .inNearRange()
                .onContextChange { contexts ->
                    val nearbyContent = ArrayList<ProximityContent>(contexts.size)
                    for (context in contexts) {
                        val title: String = context.attachments["liwa-johnson-gmail-com-s-p-9lr/title"] ?: "unknown"
                        val subtitle = Utils.getShortIdentifier(context.deviceId)
                        nearbyContent.add(ProximityContent(title, subtitle))
                    }
                    (context as MainActivity).setNearbyContent(nearbyContent)
                }
                .build()

        proximityObserverHandler = proximityObserver.startObserving(zone)
    }

    fun stop() {
        proximityObserverHandler?.stop()
    }
}
