package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/songs")
public class SongsController {
  private SongRepository songRepository;

  @Autowired SongsController(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  @GetMapping
  public String songList(Model model, Pageable pageable) {
    model.addAttribute("songs", songRepository.findAll(pageable));
    return "songs/index";
  }

  @GetMapping("/new")
  public String newSong(@ModelAttribute("song") Song song) {
    return "songs/new";
  }

  @PostMapping
  public String addSong(@ModelAttribute("song") @Valid Song song, BindingResult bindingResult, Model model) {
    if(bindingResult.hasErrors()) {
      return "songs/new";
    }
    else{
      songRepository.save(song);
      return "redirect:/songs";
    }
  }

}
