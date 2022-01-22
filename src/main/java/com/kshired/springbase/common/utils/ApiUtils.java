package com.kshired.springbase.common.utils;

import lombok.Getter;
import lombok.Setter;

public class ApiUtils {
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(ApiStatus.SUCCESS, data, null);
    }

    public static ApiResult<?> error(String message){
        return new ApiResult<>(ApiStatus.ERROR, null, message);
    }

    @Getter
    @Setter
    public static class ApiResult<T> {
        private final String status;
        private final T data;
        private final String message;

        public ApiResult(ApiStatus status, T data, String message) {
            this.status = status.getValue();
            this.data = data;
            this.message = message;
        }
    }
}
