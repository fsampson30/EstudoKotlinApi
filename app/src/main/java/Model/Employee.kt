package Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Employee(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("jobTitle") val jobTitle : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("imageUrl") val imageUrl : String,
    @SerializedName("employeeCode") val employeeCode: String
) : Serializable




