package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService{
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private MappingService mappingService;

    @Override
    public BranchDTO addBranch(Branch branch) {
        return mappingService.convertDataIntoDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO getOneBranch(Integer idBranch) {
        Optional<Branch> branchData = branchRepository.findById(idBranch);

        BranchDTO branchDTO;
        if (branchData.isPresent()) {
            branchDTO = mappingService.convertDataIntoDTO(branchData.get());
            return branchDTO;
        }
        return null;
    }

    @Override
    public BranchDTO getOneBranch(String branchName) {
        Optional<Branch> branchData = branchRepository.findByBranchName(branchName);

        BranchDTO branchDTO;
        if (branchData.isPresent()) {
            branchDTO = mappingService.convertDataIntoDTO(branchData.get());
            return branchDTO;
        }
        return null;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(mappingService::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BranchDTO updateBranch(int id, Branch branch) throws ChangeSetPersister.NotFoundException {
        Optional<Branch> branchData = branchRepository.findById(branch.getPk_branchID());

        if (branchData.isPresent()) {
            Branch _branch = branchData.get();
            _branch.setBranchName(branch.getBranchName());
            _branch.setBranchCountry(branch.getBranchCountry());
            branchRepository.save(_branch);
            return mappingService.convertDataIntoDTO(_branch);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void deleteById(Integer idBranch) {
        branchRepository.deleteById(idBranch);
    }

    @Override
    public void deleteByName(String branchName) {
        branchRepository.deleteByBranchName(branchName);
    }
}
