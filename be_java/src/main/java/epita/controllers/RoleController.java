package epita.controllers;

import epita.datamodel.Role;
import epita.exceptions.BadRequestAlertException;
import epita.exceptions.ResourceNotFoundException;
import epita.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/netflix")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) throws URISyntaxException {
        if (role.getId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID");
        }
        Role result = roleRepository.createRole(role);
        return ResponseEntity.created(new URI("/netflix/roles/" + result.getId()))
                .body(result);
    }


    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleRepository.getRoles();
    }



    //get roles by role id
    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long id) {
        Role role = roleRepository.getById(id);
        if(!role.getId().equals(id)){
            throw new ResourceNotFoundException("Not found Role with id = " + id);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        Role role = roleRepository.getById(id);
        if(!role.getId().equals(id)){
            throw new ResourceNotFoundException("Not found Role with id = " + id);
        }
        else{
            roleRepository.deleteByName(role.getName());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
