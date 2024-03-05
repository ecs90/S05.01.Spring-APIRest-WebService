package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.repository.BranchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
public class BranchController {
        private final BranchRepository branchRepository;

        public BranchController(BranchRepository branchRepository) {
            this.branchRepository = branchRepository;
        }

        @PostMapping("/add")
        public ResponseEntity<Branch> add(@RequestBody Branch branch){
            Branch _branch = branchRepository.save(new Branch(branch.getBranchName(),
                    branch.getBranchCountry()));
            return new ResponseEntity<>(_branch, HttpStatus.CREATED);
        }

        @PutMapping("/update")
        public ResponseEntity<Branch> update(@RequestBody Branch branch){
            Optional<Branch> branchData = branchRepository.findById(branch.getPk_branchID());

            if (branchData.isPresent()) {
                Branch _branch = branchData.get();
                _branch.setBranchName(branch.getBranchName());
                _branch.setBranchCountry(branch.getBranchCountry());
                return new ResponseEntity<>(branchRepository.save(_branch), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<HttpStatus> delete(@PathVariable String id){
            branchRepository.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/getOne/{id}")
        public ResponseEntity<Branch> getOne(@PathVariable String id){
            Optional<Branch> branchData = branchRepository.findById(Integer.valueOf(id));

            return branchData.map(branch -> new ResponseEntity<>(branch, HttpStatus.OK)).orElseGet(()
                    -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping("/getAll")
        public List<Branch> getAll(){
            return branchRepository.findAll();
        }
}
