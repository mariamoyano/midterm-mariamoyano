package com.ironhack.midtermmariamoyano.repository;
import com.ironhack.midtermmariamoyano.models.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
}
