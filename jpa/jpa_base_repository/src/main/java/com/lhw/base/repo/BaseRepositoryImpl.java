package com.lhw.base.repo;

import com.lhw.base.annotation.NeedFilterField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author ：linhw
 * @date ：21.6.25 11:20
 * @description：基础数据访问实现
 * @modified By：
 */
public class BaseRepositoryImpl<ENTITY,ID> extends SimpleJpaRepository<ENTITY,ID> implements BaseRepository<ENTITY,ID> {

    private static final Logger log = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    private JpaEntityInformation<ENTITY,ID> jpaEntityInformation;

    private EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.jpaEntityInformation = entityInformation;
        this.entityManager = entityManager;
    }

//    public BaseRepositoryImpl(Class<ENTITY> domainType, EntityManager entityManager) {
//        super(domainType,entityManager);
//        this.entityManager = entityManager;
//    }

    @Override
    public ENTITY find(ID id) {
        System.out.println("id = 【" + id + "】");
        if (jpaEntityInformation.hasCompositeId()){
            jpaEntityInformation.getCompositeIdAttributeValue("11","11");
            jpaEntityInformation.getIdAttribute();
            jpaEntityInformation.getIdAttributeNames();
            jpaEntityInformation.getRequiredIdAttribute();
//            jpaEntityInformation.getRequiredId(null);
        }
        System.out.println(jpaEntityInformation.getJavaType());
        System.out.println(jpaEntityInformation.getEntityName());
//        ID tempId =jpaEntityInformation.getId();
        System.out.println(jpaEntityInformation.getIdType());
//        if (jpaEntityInformation.isNew(null)){
//            System.out.println("该对象是一个新的实体");
//        }
        return null;
    }

    /**
     * 测试方法，业务中不需要调这个，只需要调jpaRepository中写好的findById即可
     *      根据ID和age查找，ID满足且age大于100的会被查询出来
     * @param id
     * @return
     */
    @Override
    public ENTITY findOne(ID id) {
        //设置规格，查询code等于id的以及age大于100的对象
//        Specification<ENTITY> specification = (Specification<ENTITY>) (root, query, criteriaBuilder) -> {
//            Predicate predicate = criteriaBuilder.equal(root.get("code"),id);
//            Predicate predicate2 = criteriaBuilder.greaterThan(root.get("age"),100);
//            query.where(predicate,predicate2);
//            return null;
//        };
        System.out.println("自定义的findOne方法");
        Specification<ENTITY> specification = new NormalFieldSpecification<>(jpaEntityInformation,id);
        TypedQuery<ENTITY> query = super.getQuery(specification, Sort.unsorted());
        if (query.getResultList().size() == 1){
            return query.getSingleResult();
        }
        log.warn("result size more than 1");
        return null;
    }

    @Override
    public void deleteOne(ID id) {

    }

    @Override
    public int updateOne(ENTITY entity) {
        return 0;
    }

    /**
     * 自定义查找
     * @param ids id 列表
     * @param sort 排序方式
     * @return 返回实体列表
     */
    @Override
    public List<ENTITY> findAllByIds(Collection<ID> ids, Sort sort) {
        if (!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        if (jpaEntityInformation.hasCompositeId()) {
            List<ENTITY> results = new ArrayList<>();
            ids.forEach(id -> super.findById(id).ifPresent(results::add));
            return results;
        }

        ByIdsSpecification<ENTITY> specification = new ByIdsSpecification<>(jpaEntityInformation);
        TypedQuery<ENTITY> query = super.getQuery(specification, sort);
        return query.setParameter(specification.parameter, ids).getResultList();
    }

    /**
     * 自定义查找
     * @param ids id 列表
     * @param pageable 分页方式
     * @return 返回实体列表
     */
    @Override
    public Page<ENTITY> findAllByIds(Collection<ID> ids, Pageable pageable) {
        if (!ids.iterator().hasNext()) {
            return new PageImpl<>(Collections.emptyList());
        }

        if (jpaEntityInformation.hasCompositeId()) {
            throw new UnsupportedOperationException("Unsupported find all by composite id with page info");
        }

        ByIdsSpecification<ENTITY> specification = new ByIdsSpecification<>(jpaEntityInformation);
        TypedQuery<ENTITY> query = super.getQuery(specification, pageable)
                .setParameter(specification.parameter, ids);
        TypedQuery<Long> countQuery = getCountQuery(specification, getDomainClass())
                .setParameter(specification.parameter, ids);

        return pageable.isUnpaged() ?
                new PageImpl<>(query.getResultList())
                : readPage(query, getDomainClass(), pageable, countQuery);
    }


    protected <S extends ENTITY> Page<S> readPage(TypedQuery<S> query,
                                                  Class<S> domainClass,
                                                  Pageable pageable,
                                                  TypedQuery<Long> countQuery) {

        if (pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        return PageableExecutionUtils.getPage(query.getResultList(), pageable,
                () -> executeCountQuery(countQuery));
    }

    private static long executeCountQuery(TypedQuery<Long> query) {

        Assert.notNull(query, "TypedQuery must not be null!");

        List<Long> totals = query.getResultList();
        long total = 0L;

        for (Long element : totals) {
            total += element == null ? 0 : element;
        }

        return total;
    }

    private static final class ByIdsSpecification<T> implements Specification<T> {
        private static final long serialVersionUID = 1L;
        private final JpaEntityInformation<T, ?> entityInformation;
        @Nullable
        ParameterExpression<Collection> parameter;

        ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
            this.entityInformation = entityInformation;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Path<?> path = root.get(this.entityInformation.getIdAttribute());
            this.parameter = cb.parameter(Collection.class);
            return path.in(this.parameter);
        }
    }

    private static final class NormalFieldSpecification<T,ID> implements Specification<T> {

        private static final long serialVersionUID = 1L;
        private final JpaEntityInformation<T,?> entityInformation;
        @Nullable
        ParameterExpression<Collection> parameter;
        private final ID id;

        private NormalFieldSpecification(JpaEntityInformation<T, ?> entityInformation, ID id) {
            this.entityInformation = entityInformation;
            this.id = id;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Class<T> clazz = entityInformation.getJavaType();
            Field[] fields = clazz.getDeclaredFields();
            List<Predicate> predicates = new LinkedList<>();
            Path<?> path = null;
            for (Field field : fields) {
                Annotation needFilterField = field.getAnnotation(NeedFilterField.class);
                Annotation idField = field.getAnnotation(Id.class);
                //这个是判断主键的值
                if (Objects.nonNull(idField)){
                    path = root.get(field.getName());
                    predicates.add(criteriaBuilder.equal(path,id));
                }
                // 这个只是用于测试用的，过滤出带有NeedFilterField注解的属性的值是否大于100，
                // 这样写肯定不是正常的，但是我这里也只是用作测试，具体的可以根据业务进行抽取
                if (Objects.nonNull(needFilterField)){
                    path = root.get(field.getName());
                    predicates.add(criteriaBuilder.greaterThan((Expression<? extends Integer>) path,100));
                }
            }
            //不能这么遍历查询，否则有多少个条件就会查询多少次，最后的结果就是最后一次查询的结果
//            predicates.forEach(query::where);
            Predicate[] arr = new Predicate[predicates.size()];
            query.where(predicates.toArray(arr));
            return null;
        }
    }

}
