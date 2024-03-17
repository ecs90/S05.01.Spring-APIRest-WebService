package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.repository.BranchRepository;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping("/add")
    public ResponseEntity<BranchDTO> add(@RequestBody Branch branch){
        BranchDTO branchDTO = branchService.addBranch(branch);
        return new ResponseEntity<>(branchDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BranchDTO> update(@RequestBody Branch branch) throws ChangeSetPersister.NotFoundException {
        BranchDTO branchDTO = branchService.updateBranch(branch.getPk_branchID(),branch);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        branchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDTO> getOne(@PathVariable int id){
        BranchDTO branchDTO = branchService.getOneBranch(id);
        if (branchDTO != null) {
            return new ResponseEntity<>(branchDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll")
    public List<BranchDTO> getAll(){
        return branchService.getAllBranches();
    }
}
