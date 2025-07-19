package com.namnd.dao;

import com.namnd.dto.request.GetPostsOfAuthor;
import com.namnd.dto.response.PageDTO;
import com.namnd.dto.response.PostManageDetailDTO;

import java.io.Serializable;

public interface PostDAO extends Serializable, BaseDAO {

    PageDTO<PostManageDetailDTO> searchPostByAuthor(GetPostsOfAuthor searchDTO);
}
