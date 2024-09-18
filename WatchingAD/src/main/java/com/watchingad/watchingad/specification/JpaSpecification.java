package com.watchingad.watchingad.specification;


import java.time.LocalDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.watchingad.watchingad.api.signup.entity.SignUpEmailAuthEntity;

/**
 * JPA 공통 조건절 Class
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
public final class JpaSpecification {
	
	public static <T> Specification<T> equalObject(String equalObjectName, Object equalObject) {
        return new Specification<T>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 9059928830412258368L;

			@Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(equalObjectName), equalObject);
            }
        };
    }
	
	public static <T> Specification<T> greaterThanEqualDate(String equalObjectName, LocalDateTime equalObject) {
        return new Specification<T>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 9059928830412258368L;

			@Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(equalObjectName), equalObject);
            }
        };
    }

}
