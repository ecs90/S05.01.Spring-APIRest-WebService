package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.services.BranchService;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.services.MappingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class WebController {

    @Autowired
    private BranchService branchService;
    @Autowired
    private MappingService mappingService;

    /*@GetMapping("/")
    public String index(){
        return "home";
    }*/

    @GetMapping({"", "/", "branches" })
    public String listBranches(Model model) {
        List<BranchDTO> branches = mappingService.getAllUsersLocation();
        model.addAttribute("branches", branches);
        return "listBranches";
    }

    @GetMapping("/search/{id}")
    public String searchById(@RequestParam("id") String id, Model model) {

        try {
            int branchID = Integer.parseInt(id);
            BranchDTO branchDTO = branchService.getOneBranch(branchID);

            if (branchDTO != null) {
                model.addAttribute("branches", Arrays.asList(branchDTO));
                return "listBranches";
            } else {
                model.addAttribute("message", "Branch ID not found.");
                return "listBranches";
            }

        } catch (NumberFormatException e) {
            model.addAttribute("message", "Insert a valid ID please.");
            return "redirect:/branches/";
        } catch (Exception e) {
            model.addAttribute("message", "Error searching branch. " + e.getMessage());
            return "redirect:/branches/";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("branchDTO", new BranchDTO());
        return "addBranch";
    }

    @PostMapping("/add")
    public String addBranch(@Valid @ModelAttribute BranchDTO branchDTO, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("branchDTO", new BranchDTO());
            model.addAttribute("error", "Every field is obligatory. " +
                    "Complete every field please.");
            return "addBranch";

        }  else {

            try {
                branchService.addBranch(mappingService.convertDTOIntoData(branchDTO));
                return "redirect:/branches";

            } catch (RuntimeException e) {
                model.addAttribute("message", "Error creating branch. " + e.getMessage());
                return "addBranch";
            }
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        BranchDTO branchDTO = branchService.getOneBranch(id);
        if (branchDTO != null) {
            model.addAttribute("branchDTO", branchDTO);
            return "editBranch";

        } else {
            return "Error editing branch.";
        }
    }

    @PostMapping("/edit/{id}")
    public String editBranch(@Valid @ModelAttribute BranchDTO branchDTO, BindingResult result, @PathVariable int id, Model model) {

        if(result.hasErrors()){
            model.addAttribute("error", "Every field is mandatory. " +
                    "Complete every field please.");
            return "editBranch";

        } else{
            try {
                branchService.updateBranch(id, mappingService.convertDTOIntoData(branchDTO));
                return "redirect:/branches";

            } catch (Exception e) {
                return "Error editing branch." + e.getMessage();
            }
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable int id) {
        try {
            branchService.deleteById(id);
            return "redirect:/branches";

        } catch (Exception e) {
            return "Error deleting branch." + e.getMessage();
        }
    }
}
