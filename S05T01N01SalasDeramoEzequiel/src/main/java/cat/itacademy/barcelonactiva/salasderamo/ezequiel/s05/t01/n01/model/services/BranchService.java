package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO addBranch (BranchDTO branchDTO);
    BranchDTO getOneBranch (Integer idBranch);
    BranchDTO getOneBranch (String branchName);
    List<BranchDTO> getAllBranches();
    BranchDTO updateBranch(int id, BranchDTO branchDTO);
    void deleteById(Integer idBranch);
    void deleteByName (String branchName);
}
