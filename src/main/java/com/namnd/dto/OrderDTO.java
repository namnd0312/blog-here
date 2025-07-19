package com.namnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
    public static final String DESC = "desc";
    public static final String ASC = "asc";

    @Schema(description = "Tiêu chí sắp xếp", example = "createdAt")
    private String property;

    @JsonProperty
    @Schema(description = "Sắp xếp tăng, giảm (boolean)", example = "true")
    private boolean ascending;
}
