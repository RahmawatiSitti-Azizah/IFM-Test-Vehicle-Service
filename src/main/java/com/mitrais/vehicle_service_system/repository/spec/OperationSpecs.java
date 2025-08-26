package com.mitrais.vehicle_service_system.repository.spec;

import com.mitrais.vehicle_service_system.entity.Operation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class OperationSpecs {
    public static Specification<Operation> hasFieldLike(String field, String keyword){
        return (root, query, criteriaBuilder) -> {
            if(!StringUtils.hasText(keyword)){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(field)),"%"+keyword+"%");
        };
    }

    public static Specification<Operation> hasFieldEqual(String field, Object data) {
        return (root, query, criteriaBuilder) -> {
            if (data == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get(field), data);
        };
    }
}
