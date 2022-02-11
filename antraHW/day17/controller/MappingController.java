package homework.week4.day17.controller;

import homework.week4.day17.domain.dto.BookResponseDTO;
import homework.week4.day17.domain.dto.BooksLikesResponseDTO;
import homework.week4.day17.domain.dto.PersonResponseDTO;
import homework.week4.day17.service.BookService;
import homework.week4.day17.service.BooksLikesService;
import homework.week4.day17.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MappingController {

    Logger logger = LoggerFactory.getLogger(MappingController.class);

    private final BookService bs;
    private final PersonService ps;
    private final BooksLikesService bls;


    @Autowired
    public MappingController(BookService bs, PersonService ps, BooksLikesService bls){
        this.bs = bs;
        this.ps = ps;
        this.bls = bls;
    }

/*
 * homework:
 *      create rest api for previous orm project
 *      1. write Readme
 *      2. many - many
 *        endpoint to create Person
 *        endpoint to create Book
 *        endpoint to register Person with Book
 *        endpoint to update Person
 *        endpoint to get Persons(consider pagination)
 *      3. exception handling
 *      4. log
 */
    //endpoint to get Persons
    @GetMapping("/people")
    public ResponseEntity<List<PersonResponseDTO>> getAllPeople(){
        logger.info("Retrieving all People.");
        return new ResponseEntity<>(ps.findAllPeople(), HttpStatus.OK);
    }

    //endpoint to get Books
    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        logger.info("Retrieving all Books.");
        return new ResponseEntity<>(bs.findAllBooks(), HttpStatus.OK);
    }

    //Register Person with Book
    @GetMapping("/register")
    public ResponseEntity<List<BooksLikesResponseDTO>> registerPersonWithBook(
            @RequestParam String personId,
            @RequestParam String bookTitle){
        logger.info("Registering Person with book: " + personId + " likes "+ bookTitle);
        //TODO
        return new ResponseEntity<>(bls.findAllBooksLikes(), HttpStatus.OK);
    }

    //Add someone to the list
    @PutMapping("/update-people")
    public void updatePeople(@RequestParam String personId, @RequestParam String name){
        //xxException();
        logger.info("Updating people data: " + name);
        ps.setPerson(personId, name);
        logger.info(name + " successfully added/updated to people.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> xxException(){
        logger.error("An Exception happened.");
        return new ResponseEntity<>("Exception happened ", HttpStatus.BAD_REQUEST);
    }

}
