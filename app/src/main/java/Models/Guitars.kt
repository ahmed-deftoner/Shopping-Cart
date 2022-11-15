package Models

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField

data class Guitars(
    val name: String = "",
    val imgSrc: Int = 0,
    val price: Int = 0,
    val description: String = "",
    val scaleLength: Double = 0.0,
    val body: String = "",
    val rating: Double = 0.0
) : java.io.Serializable