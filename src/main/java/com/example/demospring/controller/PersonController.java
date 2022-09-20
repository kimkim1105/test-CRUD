package com.example.demospring.controller;

import com.example.demospring.model.Classify;
import com.example.demospring.model.Person;
import com.example.demospring.model.dto.PersonDTO;
import com.example.demospring.service.IClassifyService;
import com.example.demospring.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    IPersonService iPersonService;
    @Autowired
    IClassifyService iClassifyService;

    @GetMapping("/test")
    public String getListPersontest(@RequestParam(required = false, name = "key") String key, Model model, @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("classifies", iClassifyService.findAll());
        model.addAttribute("persons", iPersonService.findAllWithKey(key, pageable));
        return "test";
    }
    @GetMapping
    public String getListPerson(@RequestParam(required = false, name = "key") String key, Model model, @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("classifies", iClassifyService.findAll());
        model.addAttribute("persons", iPersonService.findAllWithKey(key, pageable));
        return "person/peopleList";
    }
    @GetMapping("/listPerson")
    public ResponseEntity<?> getAllListPerson(@RequestParam(required = false, name = "key") String key){
        return new ResponseEntity<>(iPersonService.findPersonWithKey(key),HttpStatus.OK);
    }
    @GetMapping("/free")
    public ResponseEntity<?> getAllPersonInFree(@RequestParam(required = false, name = "key") String key){
        return new ResponseEntity<>(iPersonService.findPersonWithKeyBorroed(key),HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getPersonByPhone(@PathVariable String phone){
        if (iPersonService.findPersonByPhone(phone).isPresent()){
            return new ResponseEntity<>("existed",HttpStatus.OK);
        }
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
    @GetMapping("/phone/{phone}/{id}")
    public ResponseEntity<?> getPersonByPhoneAndId(@PathVariable String phone,@PathVariable Long id){
        if (iPersonService.findById(id).isPresent()){
            if (iPersonService.findById(id).get().getPhone().equals(phone)){
                return new ResponseEntity<>("ok",HttpStatus.OK);
            }else {
                return new ResponseEntity<>("existed",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("existed",HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id){
        return new ResponseEntity<>(iPersonService.findById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addNewPerson(@RequestBody PersonDTO personDTO){
        if (iPersonService.findPersonByPhone(personDTO.getPhone()).isPresent()){
            return new ResponseEntity<>("phone existed, pls try again",HttpStatus.OK);
        }
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setGender(personDTO.isGender());
        person.setStatus(true);
        person.setAddress(personDTO.getAddress());
        person.setPhone(personDTO.getPhone());
        person.setDateOfBirth(personDTO.getDateOfBirth());
        person.setAvatar("");
        person.setTypeAction(false);
        Optional<Classify> classifyOptional = iClassifyService.findById(personDTO.getClassify().getId());
        person.setClassify(classifyOptional.get());
        Optional<Person> personOptional = iPersonService.getLastestPerson();
        StringBuffer personCode = new StringBuffer();
        Long lastId = personOptional.get().getId();
        String format = String.format("%05d",(lastId+1));
        String month = String.format("%02d",LocalDate.now().getMonth().getValue());
        String year = String.valueOf(LocalDate.now().getYear()).substring(2);
        String classifyCode = classifyOptional.get().getCode();
        personCode.append(year);
        personCode.append(month);
        personCode.append(classifyCode);
        personCode.append(format);
        person.setCode(personCode.toString());
        return new ResponseEntity<>(iPersonService.save(person),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editPerson(@RequestBody Person person){
        Person personOptional = iPersonService.findById(person.getId()).get();
        if (personOptional.getPhone().equals(person.getPhone())){
            personOptional.setPhone(person.getPhone());
        }else {
            if (iPersonService.findPersonByPhone(person.getPhone()).isPresent()){
                return new ResponseEntity<>("existed", HttpStatus.OK);
            }
        }
        personOptional.setName(person.getName());
        personOptional.setGender(person.isGender());
        personOptional.setStatus(true);
        personOptional.setAddress(person.getAddress());
        personOptional.setPhone(person.getPhone());
        personOptional.setDateOfBirth(person.getDateOfBirth());
        personOptional.setAvatar("");
        personOptional.setTypeAction(false);
        Optional<Classify> classifyOptional = iClassifyService.findById(person.getClassify().getId());
        personOptional.setClassify(classifyOptional.get());
        return new ResponseEntity<>(iPersonService.save(personOptional),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        Person personOptional = iPersonService.findById(id).get();
        iPersonService.remove(personOptional.getId());
        return new ResponseEntity<>(iPersonService.save(personOptional),HttpStatus.OK);
    }
}
