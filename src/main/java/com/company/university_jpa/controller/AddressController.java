package com.company.university_jpa.controller;

import com.company.university_jpa.entity.Address;
import com.company.university_jpa.entity.Subject;
import com.company.university_jpa.repository.AddressRepository;
import com.company.university_jpa.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/create")
    public String add(@RequestBody Address address){

        addressRepository.save(address);
        return "Address Saved";
    }
    @GetMapping
    private List<Address> getAll(){
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }
    @GetMapping("/{id}")
    private Address getAddressById(@PathVariable Integer id){
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {
            Address address = optional.get();
            return address;
        }
        return null;
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody Address address){
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()){
            Address address1 = optional.get();

            address1.setCity(address.getCity());
            address1.setDistrict(address.getDistrict());
            address1.setStreet(address.getStreet());

            return "Address is updated!";
        }
        return "Address is not exist";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        addressRepository.deleteById(id);
        return "Address is deleted!";
    }
}
