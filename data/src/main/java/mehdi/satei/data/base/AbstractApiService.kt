package mehdi.satei.data.base

import mehdi.satei.domain.util.Resource
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

abstract class AbstractApiService<S> constructor(
    private val serviceClass: Class<S>,
) : API<S> {

    @Inject
    lateinit var retrofit: Retrofit

    override val apiService: S by lazy {
        create()
    }

    protected suspend fun <T> execution(apiCall: suspend () -> Response<T>): Resource<T> {
        return SingleCallAdapter(apiCall).execute()
    }

    private fun create(): S {
        return retrofit.create(serviceClass)
    }
}