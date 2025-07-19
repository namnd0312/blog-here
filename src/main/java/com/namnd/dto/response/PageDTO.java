package com.namnd.dto.response;


import com.namnd.dto.OrderDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class PageDTO<T>{

    @Schema(description = "Tồng số bản ghi", example = "100")
    protected Long totalRecords;

    @Schema(description = "Index trang hiện tại", example = "0")
    protected Integer page;

    @Schema(description = "Tổng số page", example = "10")
    protected Integer totalPages;

    @Schema(description = "Số bản ghi / page", example = "10")
    protected Integer pageSize;

//    @Schema(description = "Trạng thái response của API", example = "OK | FAIL")
//    protected List<OrderDTO> orders;

    @Schema(description = "Danh sách dữ liệu")
    protected List<T> rows;
}
