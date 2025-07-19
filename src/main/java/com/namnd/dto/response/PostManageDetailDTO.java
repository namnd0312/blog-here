package com.namnd.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class PostManageDetailDTO {

    @Schema(description = "ID bài viết", example = "1")
    private Long id;

    @Schema(description = "Nội dung bài viết ", example = "...")
    private String content;

    @Schema(description = "Tiêu đề bài viết", example = "Quy nhơn có gì vui?")
    private String title;

    @Schema(description = "Tên tác giả bài viết", example = "Nghiêm Đức Nam")
    private String authorName;

    @Schema(description = "Số lượng người yêu thích bài viết", example = "100")
    private Long liked;

    @Schema(description = "Số lượng người ko thích bài viết", example = "3")
    private Long diskLike;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss - dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Thời gian tạo bài viết", example = "19/07/2025 12:12:20")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Thời gian update gần nhất - dd/MM/yyyy HH:mm:ss", example = "19/07/2025 16:12:44")
    private LocalDateTime updatedAt;
}
