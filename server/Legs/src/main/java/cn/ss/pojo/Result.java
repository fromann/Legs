package cn.ss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer status;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return new Result(1, null, data);
    }
    public static Result success() {
        return new Result(1, null, "success");
    }

    public static Result err(String message) {
        return new Result(0, message, null);
    }
}
