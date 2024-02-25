package lv.javaguru.travel.insurance.v2.core.repositories;

import lv.javaguru.travel.insurance.v2.core.domain.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    Optional<Classifier> findByTitle(String title);

}
