package com.namnd.dto.request;

import com.namnd.dto.request.BaseSearchRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostSearchDTO extends BaseSearchRequest {
    private Long authorId;
}
