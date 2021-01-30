package com.example.themoviedb_android.utils.helpers

import androidx.annotation.StringDef
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class DisposablesManager {

    private val disposables = CompositeDisposable()

    fun <T> addDisposable(observable: Observable<Response<T>>, liveData: MutableLiveData<Response<T>>, request: String?) {
        disposables.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveData.postValue(Response.loading())
            }
            .subscribe({
                liveData.postValue(it)
            }, {
                System.out.println("NEW ERROR: " + it.toString())
                val error =
                    parseErrorResponse(
                        it,
                        request ?: ""
                    )
                liveData.postValue(
                    Response.error(
                        error,
                        request
                    )
                )
            }))
    }

}

private fun parseErrorResponse(throwable: Throwable, request: String): ErrorType {
    return when (throwable) {
        is HttpException -> ErrorType(
            HTTP_ERROR,
            message = throwable.message ?: "",
            request = request
        )
        is UnknownHostException -> ErrorType(
            NETWORK_ERROR,
            message = throwable.message ?: "",
            request = request
        )
        is SocketTimeoutException -> ErrorType(
            NETWORK_ERROR,
            message = throwable.message ?: "",
            request = request
        )
        is ConnectException -> ErrorType(
            NETWORK_ERROR,
            message = throwable.message ?: "",
            request = request
        )
        else -> ErrorType(
            UNKNOWN,
            message = throwable.message ?: "",
            request = request
        )
    }
}

///////////////////////////////////

data class Response<out T>(@ResponseTypes val status: String, val data: T?, val error: ErrorType?, val request: String?) {
    companion object {
        fun <T> loading(): Response<T> {
            return Response(
                LOADING,
                null,
                null,
                null
            )
        }

        fun <T> loadingFirst(): Response<T> {
            return Response(
                LOADING_FIRST,
                null,
                null,
                null
            )
        }

        fun <T> success(data: T, request: String?): Response<T> {
            return Response(
                SUCCESS,
                data,
                null,
                request
            )
        }

        fun <T> error(error: ErrorType, request: String?): Response<T> {
            return Response(
                ERROR,
                null,
                error,
                request
            )
        }

        fun <T> successWithWarning(data: T, request: String?, error: ErrorType): Response<T> {
            return Response(
                SUCCESS,
                data,
                error,
                request
            )
        }

    }
}

//////////////////////////////////////

const val LOADING_FIRST: String = "loading_first"
const val LOADING: String = "data_loading"
const val SUCCESS: String = "data_success"
const val ERROR: String = "data_error"
const val COMPLETED: String = "data_completed"

annotation class ResponseTypes

/////////////////////////////////

data class ErrorType(
    @ErrorTypes val type: String = "",
    val message: String = "",
    val request: String = ""
) {
    override fun toString(): String {
        if (type != UNKNOWN) {
            return "Ha ocurrido un error: $type"
        } else {
            return "$message"
        }

    }
}

const val NETWORK_ERROR: String = "Error de red . Revisa tu conexión"
const val HTTP_ERROR: String = "Error HTTP. Contacta con soporte"
const val UNKNOWN: String = "Error desconocido"
const val VALIDATION_FAIL_PASSWORD: String = "Error en password: Verifique datos ingresados"
const val VALIDATION_FAIL_USER: String = "Error en usuario: Verifique datos ingresados"
const val BACK_RESPONSE_AUTH_FAIL: String = "Usuario o contraseña invalidos"

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    NETWORK_ERROR,
    HTTP_ERROR,
    UNKNOWN,
    VALIDATION_FAIL_USER,
    VALIDATION_FAIL_PASSWORD
)

annotation class ErrorTypes