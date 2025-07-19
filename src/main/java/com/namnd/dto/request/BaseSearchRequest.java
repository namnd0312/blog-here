package com.namnd.dto.request;

import com.namnd.dto.OrderDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
@ToString
public abstract class BaseSearchRequest {

    @Schema(description = "Index trang ", example = "0")
    protected Integer page;

    @Schema(description = "Tổng số bản ghi trên 1 trang, ví dụ 10 bản ghi / trang", example = "10")
    protected Integer pageSize;

    @ArraySchema(schema = @Schema(description = "Danh sách Sắp xếp theo tiêu chí"))
    protected List<OrderDTO> orders;

    public Pageable toPageable() {
        return PageRequest.of(page, pageSize);
    }
}
