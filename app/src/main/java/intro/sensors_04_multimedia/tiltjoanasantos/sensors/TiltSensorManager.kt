package intro.sensors_04_multimedia.tiltjoanasantos.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class TiltSensorManager(context: Context) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

    var onTiltUp: (() -> Unit)? = null
    var onTiltDown: (() -> Unit)? = null

    private val rotationMatrix = FloatArray(9)
    private val orientation = FloatArray(3)
    private var lastActionTime: Long = 0

    fun start() {
        rotationSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastActionTime < 1500) return // Bloqueio de 1.5s entre palavras

            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            SensorManager.getOrientation(rotationMatrix, orientation)

            val pitch = orientation[1]

            // 45 graus
            if (pitch > 0.8f) {
                lastActionTime = currentTime
                onTiltUp?.invoke()
            } else if (pitch < -0.8f) {
                lastActionTime = currentTime
                onTiltDown?.invoke()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}