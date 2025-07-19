package com.namnd.model;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Auditable {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Comment("Thời gian insert bản ghi")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Comment("Thời gian update bản ghi gần nhất")
    private LocalDateTime updatedAt;

}
