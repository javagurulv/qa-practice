package lv.javaguru.travel.insurance.v1.core.repositories;

import lv.javaguru.travel.insurance.v1.core.domain.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDefaultDayRateRepository
        extends JpaRepository<CountryDefaultDayRate, Long> {

    Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);

}
