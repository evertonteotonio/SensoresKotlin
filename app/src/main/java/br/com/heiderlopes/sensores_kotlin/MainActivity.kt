package br.com.heiderlopes.sensores_kotlin

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SensorEventListener {


    private lateinit var mSensorManager: SensorManager
    private lateinit var mAccelerometer: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val x = event?.values[0]
        val y = event.values[1]
        val z = event.values[2]

        textViewX.text = "Posição X: " + x!!.toInt() + " Float: " + x
        textViewY.text = "Posição Y: " + y!!.toInt() + " Float: " + y
        textViewZ.text = "Posição Z: " + z!!.toInt() + " Float: " + z

        if (y < 0) { // O dispositivo esta de cabeça pra baixo
            if (x > 0)
                textViewDetail.text = "Virando para ESQUERDA ficando INVERTIDO"
            if (x < 0)
                textViewDetail.text = "Virando para DIREITA ficando INVERTIDO"
        } else {
            if (x > 0)
                textViewDetail.text = "Virando para ESQUERDA "
            if (x < 0)
                textViewDetail.text = "Virando para DIREITA "
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
