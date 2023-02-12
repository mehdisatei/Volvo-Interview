package mehdi.satei.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

}

fun <T, R> Resource<T>.map(isSuccess: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Success -> {
            data?.let { Resource.Success(isSuccess.invoke(data)) }
                ?: Resource.Error("Data is null")
        }
        is Resource.Error -> {
            Resource.Error(this.message ?: "")
        }
    }
}