package mehdi.satei.data.base

interface CallAdapter<R> {
    suspend fun execute(): R
}