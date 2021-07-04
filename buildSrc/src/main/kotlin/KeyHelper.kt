import java.io.File
import java.io.FileInputStream
import java.util.*

object KeyHelper {

    const val KEY_STORE_FILE = "storeFile"
    const val KEY_STORE_PASS = "storePassword"
    const val KEY_ALIAS = "keyAlias"
    const val KEY_PASS = "keyPassword"
    const val KEY_ONESIGNAL_APP_ID_DEV = "oneSignalAppId"
    const val KEY_ONESIGNAL_APP_ID_TEST = "oneSignalAppId"
    const val KEY_ONESIGNAL_APP_ID_PROD = "oneSignalAppId"

    private val properties by lazy {
        Properties().apply { load(FileInputStream(File("key.properties"))) }
    }

    fun getValue(key: String): String {
        return properties.getProperty(key)
    }
}