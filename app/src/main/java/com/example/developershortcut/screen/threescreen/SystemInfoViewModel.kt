package com.example.developershortcut.screen.threescreen

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SystemInfoViewModel() : ViewModel() {

    /*
        var cpuUsage = mutableFloatStateOf(0f)
            private set

        var ramUsage = mutableFloatStateOf(0f)
            private set

        var storageUsage = mutableFloatStateOf(0f)
            private set




        init {

            // Simulating real-time data updates
            viewModelScope.launch {
                while (true) {
                    updateSystemMetrics()
                    delay(500)
                }
            }
        }

        private fun updateSystemMetrics() {
            // cpuUsage.floatValue = getCpuUsage()
            ramUsage.floatValue = getRamUsage()
            storageUsage.floatValue = getStorageUsage()
        }

        private fun getCpuUsage(): Float {

            return 0f
        }


        private fun getRamUsage(): Float {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memoryInfo = ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(memoryInfo)
            return (memoryInfo.totalMem - memoryInfo.availMem).toFloat() / memoryInfo.totalMem.toFloat()
        }

        private fun getStorageUsage(): Float {
            val stat = android.os.StatFs(context.filesDir.path)
            val totalBytes: Long
            val availableBytes: Long
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                totalBytes = stat.totalBytes
                availableBytes = stat.availableBytes
            } else {
                totalBytes = stat.blockCountLong * stat.blockSizeLong
                availableBytes = stat.availableBlocksLong * stat.blockSizeLong
            }
            return (totalBytes - availableBytes).toFloat() / totalBytes.toFloat()
        }
    */
    //var systemInfo by mutableStateOf(mapOf<String, String>())

    // Ejemplo de estado inmutable que puede ser observado
    private val _systemInfo = MutableStateFlow<Map<String, String>>(emptyMap())
    var systemInfo = _systemInfo

    fun fetchSystemInfo(context: Context) {
        viewModelScope.launch {
            val infoMap = getSystemInfo(context)
            _systemInfo.value = infoMap
        }
    }


    private fun getSystemInfo(context: Context): Map<String, String> {
        val dataInfo = mutableMapOf<String, String>()

        // Device Info

        // Información del dispositivo
        dataInfo["Fabricante"] = Build.MANUFACTURER
        dataInfo["Modelo"] = Build.MODEL
        dataInfo["Producto"] = Build.PRODUCT
        dataInfo["Hardware"] = Build.HARDWARE

        // Información de la versión de Android
        dataInfo["Versión de Android"] = "${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
        dataInfo["Versión de la base de banda"] = Build.VERSION.INCREMENTAL
        dataInfo["Código de versión"] = Build.VERSION.CODENAME

        // Información de la pantalla
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        dataInfo["Ancho de pantalla"] = "${displayMetrics.widthPixels}px"
        dataInfo["Alto de pantalla"] = "${displayMetrics.heightPixels}px"
        dataInfo["Densidad de pantalla"] = "${displayMetrics.densityDpi} dpi"
        dataInfo["Densidad de pantalla (scaled)"] = "${displayMetrics.density}"
        dataInfo["Escala de densidad"] = "${displayMetrics.scaledDensity}"

        // Otra información del sistema
        dataInfo["Nombre del usuario"] = Build.USER
        dataInfo["Nombre del host"] = Build.HOST
        dataInfo["Tipo de compilación"] = Build.TYPE
        dataInfo["Marca"] = Build.BRAND
        dataInfo["Identificador del producto"] = Build.ID

        return dataInfo
    }
}