///*
//package com.example.scratch
//
//import android.app.Service
//import android.content.Context
//import android.hardware.Sensor
//import android.hardware.SensorManager
//import android.hardware.SensorDirectChannel
//import android.os.Bundle
//import android.hardware.HardwareBuffer
//import android.os.MemoryFile
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var etFreq: EditText
//    private lateinit var etCount: EditText
//    private lateinit var btStart: Button
//    private lateinit var btStop: Button
//
//    private lateinit var sensorManager: SensorManager
////    private var accelerometer: Sensor = null
//    private lateinit var memFile: MemoryFile
////    private lateinit var hBuffer: HardwareBuffer
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        etFreq = findViewById(R.id.etFreq)
//        etCount = findViewById(R.id.etCount)
//        btStart = findViewById(R.id.btstart)
//        btStop = findViewById(R.id.btStop)
//
//        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//        val accelerometer: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//
//        try {
//            memFile = MemoryFile("AccMemoryFile", 1040)
//            Log.d("supp", "Memory buffer created")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//
////        try {
////            hBuffer = HardwareBuffer.create(1024,1,HardwareBuffer.BLOB,1,HardwareBuffer.USAGE_SENSOR_DIRECT_DATA)
////            Log.d("supp", "Hardware buffer created")
////        } catch (e: Exception){
////            e.printStackTrace()
////        }
//
//
////        var dc = sensorManager.createDirectChannel(hBuffer)
//        Log.d("supp", "Direct channel created")
////        dc.configure(accelerometer, SensorDirectChannel.RATE_FAST)
//
//        val isMemTypeSupported= accelerometer.isDirectChannelTypeSupported(SensorDirectChannel.TYPE_MEMORY_FILE)
//        val isHardTypeSupported= accelerometer.isDirectChannelTypeSupported(SensorDirectChannel.TYPE_HARDWARE_BUFFER)
//
//
//
//
//        btStart.setOnClickListener {
//            val freqStr = etFreq.text.toString()
////            if (freqStr.isNotEmpty()) {
////                val frequency = freqStr.toInt()
////                val delay = (1.0 / frequency * 1000000).toInt() // delay in microseconds
////                Log.d("SensorListener", "Accelerometer started with frequency: $delay")
////                etCount.text = Editable.Factory.getInstance().newEditable(delay.toString())
////
////            }
//
//            Log.d("supp", "CODE is working")
//            if (isMemTypeSupported){
//                Log.d("supp", "Memorytype is supported!!")
//            }
//
//            if (isHardTypeSupported){
//                Log.d("supp", "Hardwaretype is supported!!")
//            }
//
//            Log.d("supp", "If no logs above masla hai!!")
//            Log.d("supp", "MEM: $isMemTypeSupported")
//            Log.d("supp", "HARD: $isHardTypeSupported")
//
//
//
//        }
//    }
//
//
//}
//*/

package com.example.datalog

//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.hardware.Sensor
//import android.hardware.SensorEvent
//import android.hardware.SensorEventListener
//import android.hardware.SensorManager
//import android.os.Bundle
//import android.os.Environment
//import android.os.Handler
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import java.io.BufferedWriter
//import java.io.File
//import java.io.FileOutputStream
//import java.io.OutputStreamWriter
//import java.text.SimpleDateFormat
//import java.util.*
//
//var counter = 0
//
//class MainActivity : AppCompatActivity(), SensorEventListener {
//    private lateinit var sensorManager: SensorManager
//    private lateinit var Gyromanager: SensorManager
//    private lateinit var Magmanager: SensorManager
//    private lateinit var GravManager: SensorManager
//
//    private var accelerometer: Sensor? = null
//    private var gyroscope: Sensor? = null
//    private var magnet: Sensor? = null
//    private var gravity: Sensor? = null
//
//    private lateinit var btStart: Button
//    private lateinit var btStop: Button
//
////    private lateinit var fileWriter: FileWriter
////    private var handler: Handler = Handler();
//
//    private lateinit var fileOutputStream: FileOutputStream
//    private lateinit var bufferedWriter: BufferedWriter
//
//    private lateinit var fileOutputStream_gyro: FileOutputStream
//    private lateinit var bufferedWriter_gyro: BufferedWriter
//
//    private lateinit var fileOutputStream_mag: FileOutputStream
//    private lateinit var bufferedWriter_mag: BufferedWriter
//
//    private lateinit var fileOutputStream_grav: FileOutputStream
//    private lateinit var bufferedWriter_grav: BufferedWriter
//
//    private val REQUEST_WRITE_EXTERNAL_STORAGE = 1
//
//
//
//    //    ------------------------------------------------------------------------------------------------------------------------------------ //
//    private val listenerAcc = object : SensorEventListener {
//        override fun onSensorChanged(event: SensorEvent) {
//            if (event.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
//                val x = event.values[0]
//                val y = event.values[1]
//                val z = event.values[2]
//
//                val tm = event.timestamp
//
//                val calendar = Calendar.getInstance()
//                val year = calendar.get(Calendar.YEAR)
//                val month = calendar.get(Calendar.MONTH)
//                val day = calendar.get(Calendar.DAY_OF_MONTH)
//                val hour = calendar.get(Calendar.HOUR_OF_DAY)
//                val minute = calendar.get(Calendar.MINUTE)
//                val second = calendar.get(Calendar.SECOND)
//                val unixTime = System.nanoTime()
//
//                val data = "ACC,$year-$month-$day $hour:$minute,$second,$tm,$x,$y,$z"
//
//                Log.d("SensorListener", " ACC Data is: $data")
//                if (::bufferedWriter.isInitialized) {
//                    writeCsvData(data)
//                }
//            }
//        }
//
//        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
//            // Handle accuracy changes for listener 1
//        }
//    }
//
//    private val listenerGyro = object : SensorEventListener {
//        override fun onSensorChanged(event: SensorEvent) {
////            val timestamp = System.currentTimeMillis()
//
//            if (event.sensor?.type == Sensor.TYPE_GYROSCOPE) {
//                val x = event.values[0]
//                val y = event.values[1]
//                val z = event.values[2]
//
//                val tm = event.timestamp
//
//
//                val calendar = Calendar.getInstance()
//                val year = calendar.get(Calendar.YEAR)
//                val month = calendar.get(Calendar.MONTH)
//                val day = calendar.get(Calendar.DAY_OF_MONTH)
//                val hour = calendar.get(Calendar.HOUR_OF_DAY)
//                val minute = calendar.get(Calendar.MINUTE)
//                val second = calendar.get(Calendar.SECOND)
//                val unixTime = System.nanoTime()
////                ${event.timestamp}
//                val data = "GYRO,$year-$month-$day $hour:$minute,$second,$tm,$x,$y,$z"
////                Log.d("SensorListener", " Gyro Data is: $data")
//                if (::bufferedWriter_gyro.isInitialized) {
//                    writeCsvDataG(data)
//                }
//
//            }
//        }
//
//        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
//            // Handle accuracy changes for listener 2
//        }
//    }
//
//
//
//    private val listenerMag = object : SensorEventListener {
//        override fun onSensorChanged(event: SensorEvent) {
////            val timestamp = System.currentTimeMillis()
//
//            if (event.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD) {
//                val x = event.values[0]
//                val y = event.values[1]
//                val z = event.values[2]
//
//                val tm = event.timestamp
//
//
//                val calendar = Calendar.getInstance()
//                val year = calendar.get(Calendar.YEAR)
//                val month = calendar.get(Calendar.MONTH)
//                val day = calendar.get(Calendar.DAY_OF_MONTH)
//                val hour = calendar.get(Calendar.HOUR_OF_DAY)
//                val minute = calendar.get(Calendar.MINUTE)
//                val second = calendar.get(Calendar.SECOND)
//                val unixTime = System.nanoTime()
////                ${event.timestamp}
//                val data = "MAG,$year-$month-$day $hour:$minute,$second,$tm,$x,$y,$z"
//                Log.d("SensorListener", " MAG Data is: $data")
//                if (::bufferedWriter_mag.isInitialized) {
//                    writeCsvDataM(data)
//                }
//            }
//        }
//
//        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
//            // Handle accuracy changes for listener 2
//        }
//    }
//
//    private val listenerGrav = object : SensorEventListener {
//        override fun onSensorChanged(event: SensorEvent) {
////            val timestamp = System.currentTimeMillis()
//
//            if (event.sensor?.type == Sensor.TYPE_GRAVITY) {
//                val x = event.values[0]
//                val y = event.values[1]
//                val z = event.values[2]
//
//                val tm = event.timestamp
//
//
//                val calendar = Calendar.getInstance()
//                val year = calendar.get(Calendar.YEAR)
//                val month = calendar.get(Calendar.MONTH)
//                val day = calendar.get(Calendar.DAY_OF_MONTH)
//                val hour = calendar.get(Calendar.HOUR_OF_DAY)
//                val minute = calendar.get(Calendar.MINUTE)
//                val second = calendar.get(Calendar.SECOND)
//                val unixTime = System.nanoTime()
////                ${event.timestamp}
//                val data = "GRA,$year-$month-$day $hour:$minute,$second,$tm,$x,$y,$z"
//                Log.d("SensorListener", " GRA Data is: $data")
//                if (::bufferedWriter_mag.isInitialized) {
//                    writeCsvDataGrav(data)
//                }
//
//            }
//        }
//
//        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
//            // Handle accuracy changes for listener 2
//        }
//    }
////    ------------------------------------------------------------------------------------------------------------------------------------ //
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//
//        Magmanager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        magnet = Magmanager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
//
//        Gyromanager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        gyroscope = Gyromanager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
//
//        GravManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        gravity = GravManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
//
//        btStart = findViewById(R.id.btstart)
//        btStop = findViewById(R.id.btStop)
//
//        btStart.setOnClickListener {
//
//            val frequency = 200
//            val gyrofreq = 200
//
//            val delay = (1.0 / frequency * 1000000).toInt() // delay in microseconds
//            val delayG = (1.0 / gyrofreq * 1000000).toInt() // delay in microseconds
//
//            Log.d("SensorListener", "Accelerometer started with frequency: $delay")
//            Log.d("SensorListener", "Gyroscope started with frequency: $delayG")
//
//            Log.d("Storage", "Permission for access asking")
//            checkWriteExternalStoragePermission()
//
////            var sensorList: List<Sensor?>
////            sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
////
////            Log.d("sensorList", sensorList.toString());
//
//            Log.d("delay", "Magnet delay finding 1111111")
//            if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
//                Log.d("delay", "Magnet delay finding")
//                val gravSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD)
//                // Use the version 3 gravity sensor.
//                var magnet: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
//                var rate = magnet?.minDelay;
//                Log.d("delay", "Magnet has the mindelay of $rate\n")
//            }
//
//
//            // Register the sensor listeners
//            sensorManager.registerListener(listenerAcc, accelerometer, delay)
//            Magmanager.registerListener(listenerMag, magnet, 5001)
////            sensorManager.registerListener(listenerAcc, gyroscope, delayG)
////            GravManager.registerListener(listenerGrav, gravity, delay)
////            handler.postDelayed({
////                sensorManager.registerListener(listenerGyro, gyroscope, delayG)
////            }, 2)
//            Gyromanager.registerListener(listenerGyro, gyroscope, delayG)
//        }
//
//        btStop.setOnClickListener {
//
//            sensorManager.unregisterListener(listenerAcc)
//            Gyromanager.unregisterListener(listenerGyro)
//            Log.d("mag", "listener wast here")
//            Magmanager.unregisterListener(listenerMag)
//            GravManager.unregisterListener(listenerGrav)
//
//
//            Log.d("SensorListener", String.format("Accelerometer event Listener stopped: %d", counter))
//
//            counter = 0
//            closeFileWriter()
//            val c =
//                String.format("Data has been written to %s", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
//            Log.d("File", c)
//        }
//    }
//
//
//
//
//
//
//
//
//    // ------------------------------------------------------------------------------------------------
//    private fun openFileWriter() {
//        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
//        val timestamp = dateFormat.format(Date())
//        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//        dir.mkdirs()
//
//        val str = android.os.Build.MODEL
//        val fileName = "Accelerometer_$timestamp,$str.csv"
//        val path = File(dir, fileName).absolutePath
//        fileOutputStream = FileOutputStream(path)
//        bufferedWriter = BufferedWriter(OutputStreamWriter(fileOutputStream))
//
//        val fileNameg = "Gyroscope_$timestamp,$str.csv"
//        val pathg = File(dir, fileNameg).absolutePath
//        fileOutputStream_gyro = FileOutputStream(pathg)
//        bufferedWriter_gyro = BufferedWriter(OutputStreamWriter(fileOutputStream_gyro))
//
//
//        val fileNamem = "Magnet_$timestamp,$str.csv"
//        val pathm = File(dir, fileNamem).absolutePath
//        fileOutputStream_mag = FileOutputStream(pathm)
//        bufferedWriter_mag = BufferedWriter(OutputStreamWriter(fileOutputStream_mag))
//
//        val fileNamegrav = "Grav_$timestamp,$str.csv"
//        val pathgrav = File(dir, fileNamegrav).absolutePath
//        fileOutputStream_grav = FileOutputStream(pathgrav)
//        bufferedWriter_grav = BufferedWriter(OutputStreamWriter(fileOutputStream_grav))
//    }
//
//
//
//    private fun closeFileWriter() {
//        try {
//            bufferedWriter.close()
//            fileOutputStream.close()
//
//            bufferedWriter_gyro.close()
//            fileOutputStream_gyro.close()
//
//            bufferedWriter_mag.close()
//            fileOutputStream_mag.close()
//
//            bufferedWriter_grav.close()
//            fileOutputStream_grav.close()
//        } catch (e: Exception) {
//            Log.d("FILE", "Failed to close file writer", e)
//        }
//    }
//
//    private fun writeCsvData(data: String) {
//        bufferedWriter.write(data)
//        bufferedWriter.newLine()
//    }
//
//    private fun writeCsvDataG(data: String) {
//        bufferedWriter_gyro.write(data)
//        bufferedWriter_gyro.newLine()
//    }
//
//    private fun writeCsvDataM(data: String) {
//        bufferedWriter_mag.write(data)
//        bufferedWriter_mag.newLine()
//    }
//
//    private fun writeCsvDataGrav(data: String) {
//        bufferedWriter_grav.write(data)
//        bufferedWriter_grav.newLine()
//    }
//
//    private fun checkWriteExternalStoragePermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            Log.d("Storage", "Permission for access asking2")
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_EXTERNAL_STORAGE)
//            Log.d("Storage", "Permission for access asked")
//        } else {
//            // Permission already granted
//            openFileWriter()
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_WRITE_EXTERNAL_STORAGE -> {
//                Log.d("Storage", "Permission was here")
//                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//
//
//                    Log.d("Storage", "Permission for access granted")
//                    // Permission granted
//                    openFileWriter()
////                    openFileWriterG()
//                } else {
//                    // Permission denied
//                    Log.d("Storage", "Permission denied")
//                    Toast.makeText(this, "Permission denied for some reason", Toast.LENGTH_SHORT).show()
//                }
//                return
//            }
//        }
//    }
//
////    -----------------------------------------------------------------------------------------------------------------------
//
//    override fun onSensorChanged(event: SensorEvent?) {
////        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
////            val x = event.values[0]
////            val y = event.values[1]
////            val z = event.values[2]
////
////            val calendar = Calendar.getInstance()
////            val year = calendar.get(Calendar.YEAR)
////            val month = calendar.get(Calendar.MONTH)
////            val day = calendar.get(Calendar.DAY_OF_MONTH)
////            val hour = calendar.get(Calendar.HOUR_OF_DAY)
////            val minute = calendar.get(Calendar.MINUTE)
////            val second = calendar.get(Calendar.SECOND)
////
////
////            val data = "ACC,$year-$month-$day $hour:$minute,$second,${event.timestamp},$x,$y,$z\n"
////            Log.d("SensorListener", "Data is: $data")
//////            fileWriter.write(data)
////            if (::bufferedWriter.isInitialized) {
////                writeCsvData(data)
////            }
////            counter++;
////        }
////        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
////            val x = event.values[0]
////            val y = event.values[1]
////            val z = event.values[2]
////
////            val calendar = Calendar.getInstance()
////            val year = calendar.get(Calendar.YEAR)
////            val month = calendar.get(Calendar.MONTH)
////            val day = calendar.get(Calendar.DAY_OF_MONTH)
////            val hour = calendar.get(Calendar.HOUR_OF_DAY)
////            val minute = calendar.get(Calendar.MINUTE)
////            val second = calendar.get(Calendar.SECOND)
////
////
////            val data = "GYRO,$year-$month-$day $hour:$minute,$second,${event.timestamp},$x,$y,$z\n"
////            Log.d("SensorListener", "Data is: $data")
//////            fileWriter.write(data)
////            if (::bufferedWriter.isInitialized) {
////                writeCsvData(data)
////            }
////            counter++;
////        }
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
//
//    override fun onDestroy() {
//        super.onDestroy()
//        sensorManager.unregisterListener(this)
//        Gyromanager.unregisterListener(this)
//        Magmanager.unregisterListener(this)
//
//    }
//
//}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////-------------------------------------------/////////////////////////////////////
////////////////////////////// START OF THREADING APPROACH USING HANDLER /////////////////////////////////////
//////////////////////////////-------------------------------------------/////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var gyroscope: Sensor
    private lateinit var magnetometer: Sensor

    private lateinit var accelerometerListener: SensorEventListener
    private lateinit var gyroscopeListener: SensorEventListener
    private lateinit var magnetometerListener: SensorEventListener

    private lateinit var accelerometerFile: File
    private lateinit var gyroscopeFile: File

    private lateinit var accelerometerWriter: FileWriter
    private lateinit var gyroscopeWriter: FileWriter

    private var isRecording = false

    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private val requestCode = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkAndRequestPermissions()

        // Initialize sensor manager and sensors
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        // Set up listeners
        setupAccelerometerListener()
        setUpMagListener()
        setupGyroscopeListener()

        // Set up buttons
        val startButton: Button = findViewById(R.id.btstart)
        val stopButton: Button = findViewById(R.id.btStop)

        startButton.setOnClickListener {
            startRecording()
        }

        stopButton.setOnClickListener {
            stopRecording()
        }
    }

    private fun checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val notGrantedPermissions = permissions.filter {
                ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
            }.toTypedArray()

            if (notGrantedPermissions.isNotEmpty()) {
                requestPermissions(notGrantedPermissions, requestCode)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == this.requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with your code
            } else {
                // Permission denied, handle accordingly (e.g., show a message)
                Toast.makeText(
                    this,
                    "Permissions are required for the app to function properly.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }


    private fun setupAccelerometerListener() {
        accelerometerListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Do nothing
            }

            override fun onSensorChanged(event: SensorEvent) {
                if (isRecording) {
                    val timestamp = event.timestamp
                    val values = event.values
                    writeDataToCSV(accelerometerWriter, Sensor.TYPE_ACCELEROMETER, timestamp, values)
                }
            }
        }
    }

    private fun setUpMagListener() {
        magnetometerListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Do nothing
            }

            override fun onSensorChanged(event: SensorEvent) {
                if (isRecording) {
                    val timestamp = event.timestamp
                    val values = event.values
                    Log.d("sensor", "mag sensor value is : $values, at timestamp $timestamp")
                }
            }
        }
    }

    private fun setupGyroscopeListener() {
        gyroscopeListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Do nothing
            }

            override fun onSensorChanged(event: SensorEvent) {
                if (isRecording) {
                    val timestamp = event.timestamp
                    val values = event.values
//                    CHANGED
                    writeDataToCSV(gyroscopeWriter, Sensor.TYPE_GYROSCOPE, timestamp, values)
                }
            }
        }
    }

    private fun startRecording() {
        if (!isRecording) {
            isRecording = true

            // Create CSV files and writers
            accelerometerFile = createCSVFile("Accelerometer_data.csv")
            gyroscopeFile = createCSVFile("Gyroscope_data.csv")

            accelerometerWriter = FileWriter(accelerometerFile)
            gyroscopeWriter = FileWriter(gyroscopeFile)

            // Start accelerometer listener with a delay
            sensorManager.registerListener(
                accelerometerListener,
                accelerometer,
                5000
            )
            sensorManager.registerListener(
                magnetometerListener,
                magnetometer,
                5000000
            )
            sensorManager.registerListener(
                gyroscopeListener,
                gyroscope,
                5000
            )
        }
    }

    private fun stopRecording() {
        if (isRecording) {
            isRecording = false

            // Unregister sensor listeners
            sensorManager.unregisterListener(accelerometerListener)
            sensorManager.unregisterListener(gyroscopeListener)
            sensorManager.unregisterListener(magnetometerListener)


            // Close writers
            accelerometerWriter.close()
            gyroscopeWriter.close()
        }
    }

    private fun createCSVFile(fileName: String): File {
        val directory =
            File(Environment.getExternalStorageDirectory().toString() + "/Download")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        return File(directory, fileName)
    }

    private fun writeDataToCSV(
        writer: FileWriter,
        sensorType: Int,
        timestamp: Long,
        values: FloatArray
    ) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(timestamp / 1000000)) // Convert nanoseconds to milliseconds

        var sensorName = "ACC"
        if (sensorType == Sensor.TYPE_GYROSCOPE)
            sensorName = "GYRO"

        val data = "$sensorName,$formattedDate,${timestamp},${values[0]},${values[1]},${values[2]}\n"

        try {
            writer.append(data)
            writer.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
