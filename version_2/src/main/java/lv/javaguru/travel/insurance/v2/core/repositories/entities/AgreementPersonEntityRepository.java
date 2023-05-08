package lv.javaguru.travel.insurance.v2.core.repositories.entities;

import lv.javaguru.travel.insurance.v2.core.domain.entities.AgreementPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementPersonEntityRepository
        extends JpaRepository<AgreementPersonEntity, Long> {

}
