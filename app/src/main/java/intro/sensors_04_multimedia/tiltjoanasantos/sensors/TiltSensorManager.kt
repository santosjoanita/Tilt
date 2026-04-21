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
    private var canProcess = true

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
            // Obtém a matriz de rotação a partir do vetor
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            
            // Se o ecrã estiver virado para o céu, este valor aproxima-se de 1.0.
            // Se o ecrã estiver virado para o chão, aproxima-se de -1.0.
            // Se o telemóvel estiver vertical, aproxima-se de 0.0.
            val screenZ = rotationMatrix[8]

            if (canProcess) {
                // Inclinar para TRÁS-> Certo
                if (screenZ > 0.7f) {
                    canProcess = false
                    onTiltUp?.invoke()
                } 
                // Inclinar para FRENTE -> Errado
                else if (screenZ < -0.7f) {
                    canProcess = false
                    onTiltDown?.invoke()
                }
            } else {
                // voltar à posição vertical
                if (screenZ < 0.3f && screenZ > -0.3f) {
                    canProcess = true
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
