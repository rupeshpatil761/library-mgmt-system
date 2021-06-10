package com.library.service.repository;

import com.library.service.model.Library;
import com.library.service.model.LibraryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, LibraryId> {
    long deleteByBookId(long id);
    List<Library> findAllByUserId(long id);
    Library findByUserIdAndBookId(long userId, long bookId);
}
