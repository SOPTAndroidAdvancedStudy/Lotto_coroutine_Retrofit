package com.example.lotto.utills

data class Resource<out T>(val status : Status , val data : T? , val message : String?) {
    companion object{
        fun <T> success(data : T?) : Resource<T> = Resource(status = Status.SUCCESS , data = data , message = null)

        fun <T> error(msg : String , data : T?) : Resource<T> =
            Resource(status = Status.ERROR , data = data , message = msg)

        fun <T> loading(data : T?) : Resource<T> =
            Resource(status = Status.LOADING,data = data , message = null)
    }
}