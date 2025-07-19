package com.namnd.dao.impl;

import com.namnd.dao.BaseDAO;
import com.namnd.dto.OrderDTO;
import com.namnd.dto.response.PageDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractBaseDAO implements BaseDAO {
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    /**
     * Thực hiện query với phân trang bằng SQL native.
     *
     * @param baseSql   Câu SQL gốc (không có LIMIT/OFFSET)
     * @param countSql  Câu SQL dùng đếm tổng số bản ghi
     * @param params    Các parameter truyền vào query
     * @param dtoClass  Class của DTO cần ánh xạ
     * @param pageable  Thông tin phân trang
     * @param <T>       Kiểu DTO
     * @return          Trang kết quả dạng PageDTO<T>
     */
    protected <T> PageDTO<T> queryPage(
            String baseSql,
            String countSql,
            Map<String, Object> params,
            Class<T> dtoClass,
            Pageable pageable
    ) {

        // 1. Query tổng số bản ghi
        Long total = Optional.of(
                namedParameterJdbcTemplate.queryForObject(countSql, params, Long.class)
        ).orElse(0L);

        // 2. Thêm LIMIT và OFFSET vào câu SQL gốc
        String pagedSql = baseSql + " LIMIT :limit OFFSET :offset";
        params.put("limit", pageable.getPageSize());
        params.put("offset", pageable.getOffset());

        // 3. Thực thi query và ánh xạ kết quả tự động qua BeanPropertyRowMapper, tên của sql alias phải map với tên fields của DTO
        List<T> rowsData = namedParameterJdbcTemplate.query(
                pagedSql,
                params,
                BeanPropertyRowMapper.newInstance(dtoClass)
        );

        int totalPages = (int) Math.ceil((double) total / pageable.getPageSize());

        return PageDTO.<T>builder()
                .rows(rowsData)
                .page(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalRecords(total)
                .totalPages(totalPages)
                .build();
    }


    /**
     * Find all entity
     * @param clazz
     * @param <T>  clazz
     */
    public <T> List<T> findAll(Class<T> clazz){
        return getSession().createQuery("select t from " + clazz.getName() + " t", clazz).getResultList();
    }

    /**
     * find entity by ID
     * @param id
     * @param <T>  clazz
     */
    public <T> Optional<T> findById(Serializable id, Class<T> clazz){
        return Optional.ofNullable(getSession().get(clazz, id));
    }

    /**
     * Tạo mới entity
     * @param entity
     * @param <T>
     */
    public <T> void persist(T entity) {
        getSession().persist(entity);
    }

    /**
     * Cập nhật entity
     * @param entity
     * @param <T>
     */
    public <T> void update(T entity){
        getSession().merge(entity);
    }

    /**
     * Xoá entity
     * @param entity
     * @param <T>
     */
    public <T> void delete(T entity){
        getSession().remove(entity);
    }

    protected String getOrderBy(boolean ascending) {
        return ascending ? OrderDTO.ASC : OrderDTO.DESC;
    }
}
