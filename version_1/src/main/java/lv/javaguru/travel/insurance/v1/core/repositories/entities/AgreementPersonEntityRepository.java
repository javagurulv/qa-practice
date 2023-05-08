package lv.javaguru.travel.insurance.v1.core.repositories.entities;

import lv.javaguru.travel.insurance.v1.core.domain.entities.AgreementPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementPersonEntityRepository
        extends JpaRepository<AgreementPersonEntity, Long> {

}
