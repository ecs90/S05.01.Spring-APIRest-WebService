package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.dto.BranchDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface BranchService {
    BranchDTO addBranch (Branch branch);
    BranchDTO getOneBranch (Integer idBranch);
    BranchDTO getOneBranch (String branchName);
    List<BranchDTO> getAllBranches();
    BranchDTO updateBranch(int id, Branch branch) throws ChangeSetPersister.NotFoundException;
    void deleteById(Integer idBranch);
    void deleteByName (String branchName);
}
