package lv.javaguru.travel.insurance.v2.core.repositories.entities;

import lv.javaguru.travel.insurance.v2.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.v2.core.domain.entities.AgreementPersonRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreementPersonRiskEntityRepository
        extends JpaRepository<AgreementPersonRiskEntity, Long> {

    List<AgreementPersonRiskEntity> findByAgreementPerson(AgreementPersonEntity agreementPerson);

}
