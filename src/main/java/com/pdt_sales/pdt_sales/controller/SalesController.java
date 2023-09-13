package com.pdt_sales.pdt_sales.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import com.pdt_sales.pdt_sales.entity.Interaction;
import com.pdt_sales.pdt_sales.service.InteractionService;

@RestController
@RequestMapping("/interactions")
@AllArgsConstructor
public class InteractionController {

  private InteractionService interactionService;

  // Create
  @PostMapping("")
  public ResponseEntity<Interaction> createInteraction(@RequestBody Interaction interaction) {
    Interaction newInteraction = interactionService.saveInteraction(interaction);
    return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
  }

  // Read All
  @GetMapping("")
  public ResponseEntity<List<Interaction>> getAllInteractions() {
    List<Interaction> allInteractions = interactionService.getAllInteractions();
    return new ResponseEntity<>(allInteractions, HttpStatus.OK);
  }

  // Read One
  @GetMapping("{id}")
  public ResponseEntity<Interaction> getInteraction(@PathVariable Long id) {
    Interaction foundInteraction = interactionService.getInteraction(id);
    return new ResponseEntity<>(foundInteraction, HttpStatus.OK);

  }

  // Update
  @PutMapping("{id}")
  public ResponseEntity<Interaction> updateInteraction(@PathVariable Long id, Interaction interaction) {
    Interaction updatedInteraction = interactionService.updateInteraction(id, interaction);
    return new ResponseEntity<>(updatedInteraction, HttpStatus.OK);

  }

  // Delete
  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteInteraction(@PathVariable Long id) {
    interactionService.deleteInteraction(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
