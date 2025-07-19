package com.namnd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailDTO {

    private Long id;

    private String content;

    private String title;

    private String authorName;

    private Long like;

    private Long diskLike;

    List<CommentDTO> comments;
}
