package udacity.fwd.project1solution.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(
    val name: String = "",
    val size: Double? = null,
    val company: String = "",
    val description: String = "",
    val images: List<String> = mutableListOf()
) : Parcelable
