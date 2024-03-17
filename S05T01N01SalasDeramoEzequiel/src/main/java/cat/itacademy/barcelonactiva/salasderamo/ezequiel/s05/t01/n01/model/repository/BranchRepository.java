package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.repository;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch> findByBranchName(String branchName);

    void deleteByBranchName(String branchName);
}
