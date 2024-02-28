package com.enigma.enigma_shop.spesification;

import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getSpecification(SearchProductRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
