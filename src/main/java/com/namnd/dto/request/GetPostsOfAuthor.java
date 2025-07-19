package com.namnd.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPostsOfAuthor extends BaseSearchRequest{

    @NotEmpty
    @Schema(description = "Mã tác giả", example = "1")
    private String authorId;
}
