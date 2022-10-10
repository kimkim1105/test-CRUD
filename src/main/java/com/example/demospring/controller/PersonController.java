package com.example.demospring.controller;

import com.example.demospring.model.dto.PersonDTO;
import com.example.demospring.model.view.PersonHistory;
import com.example.demospring.repository.IPersonHistoryDetailRepository;
import com.example.demospring.repository.IPersonHistoryRepository;
import com.example.demospring.service.IClassifyService;
import com.example.demospring.service.IOrderService;
import com.example.demospring.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    IPersonService iPersonService;
    @Autowired
    IClassifyService iClassifyService;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    IPersonHistoryRepository personHistoryRepository;
    @Autowired
    IPersonHistoryDetailRepository iPersonHistoryDetailRepository;

    @GetMapping("/person-history")
    public String getListPersonHistory(@RequestParam(required = false, name = "key") String key, Model model,
                                    @PageableDefault(value = 5) Pageable pageable){
        if (key==null){
            key="";
        }
        model.addAttribute("key", key);
        personHistoryRepository.paramSetKeyPerson('%'+key+'%');
        model.addAttribute("persons", iPersonService.findAllPage(pageable));
        return "person/person-history";
    }
    @GetMapping("/person-history-list")
    public ResponseEntity<?> getListHistoryPerson(@RequestParam(required = false, name = "person_key") String key){
        if (key==null){
            key="";
        }
        personHistoryRepository.paramSetKeyPerson('%'+key+'%');
        Iterable<PersonHistory> personHistories = personHistoryRepository.findAll();
        return new ResponseEntity<>(personHistoryRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping("/person-history-detail/")
    public String getListPersonHistoryDetail(@RequestParam Long id, @RequestParam(required = false, name = "key") String key,
                                             Model model,
                                       @PageableDefault(value = 5) Pageable pageable){
        if (key==null){
            key="";
        }
        model.addAttribute("key", key);
        model.addAttribute("id", id);
        iPersonHistoryDetailRepository.paramSetKeyBook('%'+key+'%');
        iPersonHistoryDetailRepository.paramSetOrderId(id);
        model.addAttribute("person", iPersonService.findById(id).get());
        model.addAttribute("persons", iPersonService.findAllPageDetail(pageable));
        return "person/person-history-detail";
    }

    @GetMapping
    public String getListPerson(@RequestParam(required = false, name = "key") String key, Model model,
                                @RequestParam(required = false, name = "from") String from,
                                @RequestParam(required = false, name = "to") String to,
                                @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("classifies", iClassifyService.findAll());
        if (key==null){
            key="";
        }
        if (from==null){
            from="";
        }
        if (to==null){
            to="";
        }
        model.addAttribute("from", from);
        model.addAttribute("key", key);
        model.addAttribute("to",to);
        model.addAttribute("persons", iPersonService.findAllWithKey(key, from, to, pageable));
        return "person/peopleList";
    }

    @GetMapping("/listPerson")
    public ResponseEntity<?> getAllListPerson(@RequestParam(required = false, name = "key") String key,
                                              @RequestParam(required = false, name = "from") String from,
                                              @RequestParam(required = false, name = "to") String to){
        if (!iPersonService.findPersonWithKey(key, from, to).iterator().hasNext()){
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iPersonService.findPersonWithKey(key,from,to),HttpStatus.OK);
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id){
        if (!iPersonService.findById(id).isPresent()){
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
        if (!iPersonService.findById(id).get().isStatus()){
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iPersonService.findById(id),HttpStatus.OK);
    }
//    @PostMapping
//    public ResponseEntity<?> addNewPerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            Map<String, String> errors= new HashMap<>();
//
//            bindingResult.getFieldErrors().forEach(
//                    error -> errors.put(error.getField(), error.getDefaultMessage())
//            );
//
//            String errorMsg= "";
//
//            for(String key: errors.keySet()){
//                errorMsg+= "error in: " + key + ", by: " + errors.get(key) + "\n";
//            }
////            if (iPersonService.findPersonByPhone(personDTO.getPhone()).isPresent()){
////                errorMsg+= "phone existed";
////            }
//            return new ResponseEntity<>(errorMsg,HttpStatus.OK);
//        }
//        if (iPersonService.findPersonByPhone(personDTO.getPhone()).isPresent()){
//            return new ResponseEntity<>("phone existed", HttpStatus.OK);
//        }
//        Person person = new Person();
//        person.setName(personDTO.getName());
//        person.setGender(personDTO.isGender());
//        person.setStatus(true);
//        person.setAddress(personDTO.getAddress());
//        person.setPhone(personDTO.getPhone());
//        person.setDateOfBirth(personDTO.getDateOfBirth());
//        person.setAvatar("");
////        person.setTypeAction(false);
//        Optional<Classify> classifyOptional = iClassifyService.findById(personDTO.getClassify().getId());
//        person.setClassify(classifyOptional.get());
//        Optional<Person> personOptional = iPersonService.getLastestPerson();
//        StringBuffer personCode = new StringBuffer();
//        Long lastId;
//        if (personOptional.isPresent()){
//            lastId = personOptional.get().getId();
//        }else {
//            lastId = 0L;
//        }
//        String format = String.format("%05d",(lastId+1));
//        String month = String.format("%02d",LocalDate.now().getMonth().getValue());
//        String year = String.valueOf(LocalDate.now().getYear()).substring(2);
//        String classifyCode = classifyOptional.get().getCode();
//        personCode.append(year);
//        personCode.append(month);
//        personCode.append(classifyCode);
//        personCode.append(format);
//        person.setCode(personCode.toString());
//        Order order = new Order();
//        order.setBookSize(0);
//        order.setBookReturn(0);
//        order.setPerson(iPersonService.save(person));
//        iOrderService.save(order);
//        return new ResponseEntity<>(iPersonService.save(person),HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<?> addNewPerson (@RequestBody PersonDTO personDTO){
        String rs = iPersonService.addNewPerson(personDTO);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iPersonService.findPersonByPhone(personDTO.getPhone()),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editPerson(@RequestParam Long id, @RequestBody PersonDTO personDTO){
        String rs = iPersonService.updatePerson(id, personDTO);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iPersonService.findById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        String rs = iPersonService.deletPerson(id);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iPersonService.findById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

//    @PutMapping
//    public ResponseEntity<?> editPerson(@RequestParam Long id, @RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
//        if (!iPersonService.findById(id).isPresent()||!iPersonService.findById(id).get().isStatus()){
//            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
//        }
//        Person personOptional = iPersonService.findById(id).get();
////        if (personOptional.isTypeAction()){
////            return new ResponseEntity<>("Person in borrowing, can't edit",HttpStatus.OK);
////        }
//        if (bindingResult.hasErrors()){
//            Map<String, String> errors= new HashMap<>();
//
//            bindingResult.getFieldErrors().forEach(
//                    error -> errors.put(error.getField(), error.getDefaultMessage())
//            );
//
//            String errorMsg= "";
//
//            for(String key: errors.keySet()){
//                errorMsg+= "error in: " + key + ", by: " + errors.get(key) + "\n";
//            }
//
//            return new ResponseEntity<>(errorMsg,HttpStatus.OK);
//        }
//        if (personOptional.getPhone().equals(personDTO.getPhone())){
//            personOptional.setPhone(personDTO.getPhone());
//        }else {
//            if (iPersonService.findPersonByPhone(personDTO.getPhone()).isPresent()){
//                return new ResponseEntity<>(" phone existed", HttpStatus.OK);
//            }
//        }
//        personOptional.setName(personDTO.getName());
//        personOptional.setGender(personDTO.isGender());
//        personOptional.setStatus(true);
//        personOptional.setAddress(personDTO.getAddress());
//        personOptional.setPhone(personDTO.getPhone());
//        personOptional.setDateOfBirth(personDTO.getDateOfBirth());
//        personOptional.setAvatar("");
////        personOptional.setTypeAction(false);
//        Optional<Classify> classifyOptional = iClassifyService.findById(personDTO.getClassify().getId());
//        personOptional.setClassify(classifyOptional.get());
//        return new ResponseEntity<>(iPersonService.save(personOptional),HttpStatus.OK);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> deletePerson(@PathVariable Long id){
//        Person personOptional = iPersonService.findById(id).get();
////        if (personOptional.isTypeAction()){
////            return new ResponseEntity<>("Person in borrowing, can't delete",HttpStatus.OK);
////        }
//        iPersonService.remove(personOptional.getId());
//        return new ResponseEntity<>(iPersonService.save(personOptional),HttpStatus.OK);
//    }
}
