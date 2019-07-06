package inc.grayherring.com.core.models

import com.squareup.moshi.Json

data class NasaPlanetary (
	val date : String,
	val explanation : String,
	val hdurl : String,
	@field:Json(name = "media_type") val mediaType : String, //todo change to enum video / image
	@field:Json(name = "service_version")	val serviceVersion : String,
	val title : String,
	val url : String
)