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

    // Esta variável impede que saltem várias palavras
    private var canProcess = true

    fun start() {
        rotationSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            SensorManager.getOrientation(rotationMatrix, orientation)

            // RollValue em Landscape deteta a inclinação para a testa ou para o chão
            val rollValue = orientation[2]

            if (canProcess) {
                if (rollValue > 0.8f) { // Inclinar para TRÁS (ACERTOU)
                    canProcess = false
                    onTiltUp?.invoke()
                } else if (rollValue < -0.8f) { // Inclinar para a FRENTE (ERROU/PASSAR)
                    canProcess = false
                    onTiltDown?.invoke()
                }
            } else {
                if (rollValue in -0.4f..0.4f) {
                    canProcess = true
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}