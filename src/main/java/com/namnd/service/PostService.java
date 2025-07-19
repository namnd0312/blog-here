package com.namnd.service;

import com.namnd.dto.request.PostDTO;
import com.namnd.dto.PostDetailDTO;
import com.namnd.dto.request.GetPostsOfAuthor;
import com.namnd.dto.response.PageDTO;
import com.namnd.dto.response.PostManageDetailDTO;
import com.namnd.dto.response.ResponseDTO;

import java.util.List;

public interface PostService {

    ResponseDTO<Void> addPost(PostDTO postDTO);

    ResponseDTO<Void> editPost(PostDTO postDTO);

    ResponseDTO<PostDetailDTO> getPostDetail(Long postId);

    List<PostDTO> getListPost();

    ResponseDTO<PageDTO<PostManageDetailDTO>> getListPostByAuthor(GetPostsOfAuthor req);


    ResponseDTO<Void> deletePost(Long postId);
}
