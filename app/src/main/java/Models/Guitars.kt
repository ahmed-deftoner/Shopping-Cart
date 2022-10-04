package Models

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField

data class Guitars(
    val name: String,
    val imgSrc: Int,
    val price: Int,
    val description: String,
    val scaleLength: Double,
    val body: String,
    val rating: Double
) : java.io.Serializable