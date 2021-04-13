package io.tanzu.labs.tddspringbootbooks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, Integer> {
}
