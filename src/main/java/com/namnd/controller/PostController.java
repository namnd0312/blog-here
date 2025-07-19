package com.namnd.controller;


import com.namnd.dto.request.PostDTO;
import com.namnd.dto.request.GetPostsOfAuthor;
import com.namnd.dto.response.PageDTO;
import com.namnd.dto.response.PostManageDetailDTO;
import com.namnd.dto.response.ResponseDTO;
import com.namnd.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post")
@Tag(name = "Post API", description = "API quản lý bài đăng của người dùng")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @Operation(summary = "Thêm mới bài viết")
    @ApiResponse(responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDTO.class)))
    )
    public ResponseEntity<?> addPost(@RequestBody @Valid PostDTO req){
        ResponseDTO<Void> res = this.postService.addPost(req);
        return  ResponseEntity.ok(res);
    }


    @PutMapping
    @Operation(summary = "Chỉnh sửa bài viết", description = "Bắt buộc phải truyền id của bài viết")
    @ApiResponse(responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDTO.class)))
    )
    public ResponseEntity<?> editPost(@RequestBody @Valid PostDTO req){
        ResponseDTO<Void> res = this.postService.editPost(req);
        return  ResponseEntity.ok(res);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Xoá bài viết theo id bài viết")
    @ApiResponse(responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDTO.class)))
    )
    public ResponseEntity<?> deletePost(@PathVariable @Valid @NotEmpty @Parameter(description = "ID của bài viết", example = "1001") String id){
        ResponseDTO<Void> res = this.postService.deletePost(Long.parseLong(id));
        return  ResponseEntity.ok(res);
    }


    @PostMapping("search-by-author")
    @Operation(summary = "Tìm kiếm danh sách bài viết theo tác giả", description = "Có sử dụng phân trang và sắp xếp theo tiêu chí")
    @ApiResponse(responseCode = "200", description = "Danh sách bài viết có phân trang",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostManageDetailDTO.class)))
    )
    public ResponseEntity<?> searchByAuthor(@RequestBody @Valid GetPostsOfAuthor req){
        ResponseDTO<PageDTO<PostManageDetailDTO>> res = this.postService.getListPostByAuthor(req);
        return  ResponseEntity.ok(res);
    }
}
