package com.namnd.dto.response;


import com.namnd.enums.MessageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

    @Schema(description = "Trạng thái response của API", example = "OK | FAIL")
    private String status;

    @Schema(description = "Mã code của kết quả", example = "200 -> OK | Khác 200 -> ERROR")
    private String code;

    @Schema(description = "Thông báo lỗi trả về nếu có", example = "Data not found.")
    private String errorMessage;

    @Schema(description = "Dữ liệu trả về nếu có", example = "{} | []")
    private T data;

    public static <T> ResponseDTO<T> ok(T data) {
        return ResponseDTO.<T>builder()
                .status(MessageEnum.SUCCESS.getStatus())
                .code(MessageEnum.SUCCESS.getCode())
                .errorMessage("")
                .data(data)
                .build();
    }

    public static <T> ResponseDTO<T> ok() {
        return ResponseDTO.<T>builder()
                .status(MessageEnum.SUCCESS.getStatus())
                .code(MessageEnum.SUCCESS.getCode())
                .errorMessage("")
                .data(null)
                .build();
    }


    public static <T> ResponseDTO<T> error(String code, String errorMessage) {
        return ResponseDTO.<T>builder()
                .status("FAIL")
                .code(code)
                .errorMessage(errorMessage)
                .data(null)
                .build();
    }

    public static <T> ResponseDTO<T> error(MessageEnum messageEnum) {
        return ResponseDTO.<T>builder()
                .status(messageEnum.getStatus())
                .code(messageEnum.getCode())
                .errorMessage(messageEnum.getMessage())
                .data(null)
                .build();
    }
}
