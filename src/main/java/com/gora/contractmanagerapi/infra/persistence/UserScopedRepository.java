package com.gora.contractmanagerapi.infra.persistence;

import com.gora.contractmanagerapi.infra.authorization.component.CustomUserDetails;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@NoRepositoryBean
public interface UserScopedRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    default String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (nonNull(auth) && auth.getPrincipal() instanceof CustomUserDetails userDetails)
            return userDetails.getUsername();

        throw new IllegalStateException("User not authenticated");
    }

    /**
     * Override findAll() to apply filter on logged user
     */
    @Override
    default List<T> findAll() {
        return findAll(userScope());
    }

    /**
     * Override findById() to apply filter on logged user
     */
    @Override
    default Optional<T> findById(ID id) {
        return findOne((root, query, cb) -> cb.and(
                cb.equal(root.get("id"), id),
                cb.equal(root.get("createdBy"), getCurrentUsername())
        ));
    }


    /**
     * Specification that filter by logged user
     */
    private Specification<T> userScope() {
        String username = getCurrentUsername();
        return (root, query, cb) -> cb.equal(root.get("createdBy"), username);
    }
}
