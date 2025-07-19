package com.namnd.dao.impl;


import com.namnd.dao.PostDAO;
import com.namnd.dto.request.GetPostsOfAuthor;
import com.namnd.dto.response.PageDTO;
import com.namnd.dto.response.PostManageDetailDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Repository
@Log4j2
public class PostDAOImpl extends AbstractBaseDAO implements PostDAO {


    @Override
    public PageDTO<PostManageDetailDTO> searchPostByAuthor(GetPostsOfAuthor searchDTO) {
        final StringBuilder sql = new StringBuilder();
        sql.append(" select p.id, ");
        sql.append(" p.content, ");
        sql.append(" p.title, ");
        sql.append(" p.created_at as createdAt, ");
        sql.append(" p.updated_at as updatedAt, ");
        sql.append(" u.username as authorName, ");
        sql.append(" (select count(*) from reaction as r where r.post_id = p.id and r.type = 'LIKE') as liked,");
        sql.append(" (select count(*) from reaction as r where r.post_id = p.id and r.type = 'DISLIKE') as disklike ");
        sql.append(" from post as p");
        sql.append(" join users as u ");
        sql.append(" on p.author_id = u.id ");
        sql.append(" where 1=1");

        Map<String, Object> params = new HashMap<>();

        if (StringUtils.hasText(searchDTO.getAuthorId())) {
            sql.append(" AND u.id =:user_id");
            params.put("user_id", Long.parseLong(searchDTO.getAuthorId()));
        }

        if (searchDTO.getOrders() != null && !searchDTO.getOrders().isEmpty()) {
            sql.append(" order by ");
            searchDTO.getOrders().forEach(order -> {
                String property = order.getProperty();
                switch (property) {
                    case "updatedAt":
                        sql.append(" p.updated_at ").append(getOrderBy(order.isAscending())).append(",");
                        break;

                    case "id":
                        sql.append(" p.id ").append(getOrderBy(order.isAscending())).append(",");
                        break;
                }
            });
            sql.deleteCharAt(sql.length() - 1);
        }

//        if (StringUtils.isNotBlank(searchDTO.getLocationName())) {
//            sql.append(" AND loc.location_name ILIKE :name");
//            params.put("name", "%" + searchDTO.getLocationName().trim() + "%");
//        }

        // SQL đếm tổng số bản ghi
        String countSql = "SELECT COUNT(*) FROM (" + sql + ") AS count_query";

        // Sắp xếp theo created_date, giảm dần
//        sql.append(" ORDER BY p.created_date DESC");

        log.info(sql);
        return queryPage(sql.toString(), countSql, params, PostManageDetailDTO.class, searchDTO.toPageable());
    }

}
