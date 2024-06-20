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

        // device info
        dataInfo["Manufacturer"] = Build.MANUFACTURER
        dataInfo["Model"] = Build.MODEL
        dataInfo["Product"] = Build.PRODUCT
        dataInfo["Hardware"] = Build.HARDWARE
        dataInfo["User"] = Build.USER
        dataInfo["Host"] = Build.HOST
        dataInfo["Type"] = Build.TYPE
        dataInfo["Brand"] = Build.BRAND
        dataInfo["Id"] = Build.ID
        dataInfo["Board"] = Build.BOARD
        dataInfo["BootLoader"] = Build.BOOTLOADER
        dataInfo["Display"] = Build.DISPLAY
        dataInfo["FingerPrint"] = Build.FINGERPRINT
        dataInfo["ODM_SKU"] = Build.ODM_SKU ?: "N/A"
        dataInfo["SOC_Manufacturer"] = Build.SOC_MANUFACTURER ?: "N/A"
        dataInfo["SOC_Model"] = Build.SOC_MODEL ?: "N/A"
        dataInfo["TAGS"] = Build.TAGS
        dataInfo["Radio version"] = Build.getRadioVersion() ?: "N/A"
        //dataInfo["Serial"] = Build.getSerial()  // require specials permissions
        dataInfo["Time"] = Build.TIME.toString()
        dataInfo["ABIS"] = Build.SUPPORTED_ABIS.contentToString()
        dataInfo["32 bits"] = Build.SUPPORTED_32_BIT_ABIS.contentToString()
        dataInfo["64 bits"] = Build.SUPPORTED_64_BIT_ABIS.contentToString()
        dataInfo["Partition Name System"] = Build.Partition.PARTITION_NAME_SYSTEM ?: "N/A"

        // Android info
        dataInfo["Release"] = Build.VERSION.RELEASE
        dataInfo["Codename"] = Build.VERSION.CODENAME
        dataInfo["Incremental"] = Build.VERSION.INCREMENTAL
        dataInfo["SDK_INT"] = Build.VERSION.SDK_INT.toString()
        dataInfo["Base_OS"] = Build.VERSION.BASE_OS ?: "N/A"
        dataInfo["Security_patch"] = Build.VERSION.SECURITY_PATCH ?: "N/A"
        dataInfo["Media performance class"] = Build.VERSION.MEDIA_PERFORMANCE_CLASS.toString()
        dataInfo["Upside down cake"] = Build.VERSION_CODES.UPSIDE_DOWN_CAKE.toString()

        // Display info
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        dataInfo["Screen Width"] = "${displayMetrics.widthPixels}px"
        dataInfo["Screen Height"] = "${displayMetrics.heightPixels}px"
        dataInfo["Screen Density"] = "${displayMetrics.densityDpi} dpi"
        dataInfo["Screen Density (scaled)"] = "${displayMetrics.density}"
        dataInfo["Scaled Density"] = "${displayMetrics.scaledDensity}"

        return dataInfo
    }
}