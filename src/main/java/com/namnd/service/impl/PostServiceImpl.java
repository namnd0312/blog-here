package com.namnd.service.impl;

import com.namnd.dao.PostDAO;
import com.namnd.dto.request.PostDTO;
import com.namnd.dto.PostDetailDTO;
import com.namnd.dto.mapper.PostMapper;
import com.namnd.dto.request.GetPostsOfAuthor;
import com.namnd.dto.response.PageDTO;
import com.namnd.dto.response.PostManageDetailDTO;
import com.namnd.dto.response.ResponseDTO;
import com.namnd.model.Post;
import com.namnd.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.namnd.enums.MessageEnum.NOT_FOUND;


@Service
@Transactional
@Log4j2
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;
    private final PostMapper postMapper;

    public PostServiceImpl(PostDAO postDAO, PostMapper postMapper) {
        this.postDAO = postDAO;
        this.postMapper = postMapper;
    }

    @Override
    public ResponseDTO<Void> addPost(PostDTO postDTO) {
        Post entity = this.postMapper.toEntity(postDTO);
        this.postDAO.persist(entity);
        return ResponseDTO.ok();
    }

    @Override
    public ResponseDTO<Void> editPost(PostDTO postDTO) {
        Post post = this.postDAO.findById(postDTO.getId(), Post.class).orElse(null);
        if (post == null) {
            return ResponseDTO.error(NOT_FOUND);
        }

        Post entity = this.postMapper.toEntity(postDTO);
        this.postDAO.update(entity);
        return ResponseDTO.ok();
    }

    @Override
    public ResponseDTO<PostDetailDTO> getPostDetail(Long postId) {
        Post post = this.postDAO.findById(postId, Post.class).orElse(null);
        if (post == null) {
            return ResponseDTO.error(NOT_FOUND);
        }

        return ResponseDTO.ok();
    }

    @Override
    public List<PostDTO> getListPost() {
        return null;
    }

    @Override
    public ResponseDTO<PageDTO<PostManageDetailDTO>> getListPostByAuthor(GetPostsOfAuthor req) {
        return ResponseDTO.ok(this.postDAO.searchPostByAuthor(req));
    }

    @Override
    public ResponseDTO<Void> deletePost(Long postId) {
        Post post = this.postDAO.findById(postId, Post.class).orElse(null);
        if (post == null) {
            return ResponseDTO.error(NOT_FOUND);
        }

        this.postDAO.delete(post);

        return ResponseDTO.ok();
    }
}
