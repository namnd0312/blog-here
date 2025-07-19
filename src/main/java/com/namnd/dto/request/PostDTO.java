package com.namnd.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
public class PostDTO {

    @Schema(description = "ID bài viết", example = "1")
    private String id;

    @Schema(description = "Nội dung bài viết", example = "Bài viết về trải nghiệm du lịch của tôi ở Quy Nhơn")
    @NotEmpty
    private String content;

    @NotEmpty
    @Schema(description = "Tiêu đề bài viết", example = "Quy Nhơn có gì vui?")
    private String title;

    @NotEmpty
    @Schema(description = "ID tác giả", example = "1")
    private String authorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
