package com.example.frequenc.serial

import android.content.Context
import android.hardware.usb.UsbManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.hoho.android.usbserial.driver.UsbSerialDriver
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber

class SerialManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private var usbPort: UsbSerialPort? = null

    suspend fun connect(): Result<Unit> = withContext(dispatcher) {
        try {
            val usbManager = context.getSystemService(UsbManager::class.java)
                ?: return@withContext Result.failure(IllegalStateException("USB Manager not available"))

            val device = usbManager.deviceList.values.firstOrNull()
                ?: return@withContext Result.failure(NoDeviceException())

            val driver: UsbSerialDriver = UsbSerialProber.getDefaultProber().probeDevice(device)
                ?: return@withContext Result.failure(IllegalStateException("No USB Serial Driver found"))

            usbPort = driver.ports.firstOrNull() ?: return@withContext Result.failure(IllegalStateException("No USB Port available"))

            val connection = usbManager.openDevice(device)
                ?: return@withContext Result.failure(IllegalStateException("Could not open USB device"))

            usbPort?.apply {
                open(connection)
                setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendCommand(command: String): Result<Unit> = withContext(dispatcher) {
        try {
            usbPort?.write(command.toByteArray(), 1000)
                ?: return@withContext Result.failure(IllegalStateException("USB Port not connected"))

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun disconnect() {
        usbPort?.close()
        usbPort = null
    }
}

// Custom exception for no device found
class NoDeviceException : Exception("No USB device found")