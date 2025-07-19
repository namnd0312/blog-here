package com.namnd.dto.mapper;


import com.namnd.dto.request.PostDTO;
import com.namnd.model.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PostMapper {

    public Post toEntity(PostDTO dto) {
        if (dto == null) {
            return null;
        }

        Post entity = new Post();
        BeanUtils.copyProperties(dto, entity);

        if(StringUtils.hasText(dto.getId())){
            entity.setId(Long.parseLong(dto.getId()));
        }

        if(StringUtils.hasText(dto.getAuthorId())){
            entity.setAuthorId(Long.parseLong(dto.getAuthorId()));
        }

        return entity;
    }

    public PostDTO toDTO(Post entity) {
        if (entity == null) {
            return null;
        }

        PostDTO dto = new PostDTO();

        BeanUtils.copyProperties(entity, dto);


        if(entity.getId() != null){
            dto.setId(entity.getId().toString());
        }

        if(entity.getAuthorId() != null){
            dto.setAuthorId(entity.getAuthorId().toString());
        }

        return dto;
    }
}
