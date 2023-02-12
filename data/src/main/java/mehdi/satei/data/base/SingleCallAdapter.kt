package mehdi.satei.data.base

import mehdi.satei.domain.util.Resource
import retrofit2.Response

class SingleCallAdapter<T>(
    private val apiCall: suspend () -> Response<T>,
) : CallAdapter<Resource<T>> {
    override suspend fun execute(): Resource<T> {
        return try {
            val execute = apiCall.invoke()
            if (execute.isSuccessful) {
                val response = execute.body()
                Resource.Success(response)
            } else {
                val errorBody = execute.errorBody()?.string()
                throw Exception(errorBody ?: "Unable to fetch data")
            }

        } catch (exception: Exception) {
            return Resource.Error(exception.message ?: "Error")
        }
    }
}