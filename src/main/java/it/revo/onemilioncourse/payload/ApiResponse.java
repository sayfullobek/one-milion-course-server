package it.revo.onemilioncourse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private Integer status;
    private Object user;

    public ApiResponse(String message, boolean success, Integer status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }
}
