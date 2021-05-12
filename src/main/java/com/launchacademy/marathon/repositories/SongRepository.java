package com.launchacademy.marathon.repositories;

import com.launchacademy.marathon.models.Song;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SongRepository extends PagingAndSortingRepository<Song,Integer> {
  List<Song> findAll();
}
