package com.nadhif.quran.core.data.network

import com.yus.quran.core.data.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is NetworkResponse.Success -> {
                emitAll(fetchFromNetwork(apiResponse.data).map {
                    Resource.Success(it)
                })
            }

            is NetworkResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    protected abstract fun fetchFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<NetworkResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}