package com.ironhack.midtermmariamoyano.repository;

import com.ironhack.midtermmariamoyano.models.ThirdParty;
import com.ironhack.midtermmariamoyano.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {

}
