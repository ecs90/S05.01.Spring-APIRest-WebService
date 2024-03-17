package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.EUCountries;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingService{
    @Autowired
    private BranchRepository branchRepository;

    public List<BranchDTO> getAllUsersLocation() {
        return ((List<Branch>) branchRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    public BranchDTO convertDataIntoDTO (Branch branchData) {

        BranchDTO dto = new BranchDTO();

        dto.setPk_branchID(branchData.getPk_branchID());
        dto.setBranchName(branchData.getBranchName());
        dto.setBranchCountry(branchData.getBranchCountry());
        dto.setBranchType(isEU(dto.getBranchCountry())?"EU":"not EU");

        return dto;
    }

    public Branch convertDTOIntoData (BranchDTO branchDTO) {
        Branch branch = new Branch();

        branch.setPk_branchID(branchDTO.getPk_branchID());
        branch.setBranchName(branchDTO.getBranchName());
        branch.setBranchCountry(branchDTO.getBranchCountry());

        return branch;
    }

    private boolean isEU(String country){
        try{
            EUCountries.valueOf(country.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
