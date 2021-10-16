package net.vpein.timer.controller;

import net.vpein.timer.domain.Metimer;
import net.vpein.timer.repo.MetimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class MetimerController {
    @Autowired
    MetimerRepository metimerRepository;

    @GetMapping("/metimers")
    public ResponseEntity<List<Metimer>> getAllTimers(@RequestParam(required = false) String title) {
        try {
            List<Metimer> metimers = new ArrayList<Metimer>();

            if (title == null)
                metimerRepository.findAll().forEach(metimers::add);
            else
                metimerRepository.findByTitleContaining(title).forEach(metimers::add);

            if (metimers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(metimers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/metimers/{id}")
    public ResponseEntity<Metimer> getMetimerById(@PathVariable("id") long id) {
        Optional<Metimer> metimerData = metimerRepository.findById(id);

        if (metimerData.isPresent()) {
            return new ResponseEntity<>(metimerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/metimers")
    public ResponseEntity<Metimer> createMetimer(@RequestBody Metimer metimer) {
        try {
            Metimer _metimer = metimerRepository
                    .save(new Metimer(0L, metimer.getTitle(), metimer.getBeschreibung(),metimer.getSprecher(), metimer.getZeitpunkt()));
            return new ResponseEntity<>(_metimer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/metimers/{id}")
    public ResponseEntity<Metimer> updateMetimer(@PathVariable("id") long id, @RequestBody Metimer metimer) {
        Optional<Metimer> metimerData = metimerRepository.findById(id);

        if (metimerData.isPresent()) {
            Metimer _metimer = metimerData.get();
            _metimer.setTitle(metimer.getTitle());
            _metimer.setBeschreibung(metimer.getBeschreibung());
            _metimer.setSprecher(metimer.getSprecher());
            _metimer.setZeitpunkt(metimer.getZeitpunkt());
            return new ResponseEntity<>(metimerRepository.save(_metimer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/metimers/{id}")
    public ResponseEntity<Metimer> deleteMetimer(@PathVariable("id") long id) {
        try {
            metimerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/metimers")
    public ResponseEntity<HttpStatus> deleteAllMetimers() {
        try {
            metimerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/metimers/sprecher")
    public ResponseEntity<List<Metimer>> findBySprecher(@RequestParam(required = false) String sprecher) {
        try {
            List<Metimer> metimers = metimerRepository.findBySprecher(sprecher);

            if (metimers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(metimers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
