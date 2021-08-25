package com.library.service.service;

import com.library.service.bean.Book;
import com.library.service.bean.User;
import com.library.service.bean.UserBook;
import com.library.service.exception.ResourceNotFoundException;
import com.library.service.microservices.BookServiceProxy;
import com.library.service.microservices.UserServiceProxy;
import com.library.service.model.Library;
import com.library.service.repository.LibraryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mbeans.UserMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserServiceProxy userServiceProxy;

    @Autowired
    private BookServiceProxy bookServiceProxy;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<User> getAllUsers() {
        return userServiceProxy.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userServiceProxy.getUserById(id);
    }

    @Override
    public UserBook getUserBookAssociationByUserId(long id) {
        User user = userServiceProxy.getUserById(id);
        logger.info(user+" <<<<<<< user for user id "+id);
        if(user!=null){
            //fetches book ids on user_id from library table and calls GET /books/{book_id}
            // for all books. Displays consolidated data
            UserBook userBook = new UserBook(user);
            try {
                List<Library> libraryList = libraryRepository.findAllByUserId(id);
                for (Library library : libraryList) {
                    userBook.bookList.add(bookServiceProxy.getBookById(library.getBookId()));
                }
                //Here we can call signle native query to avoid individual database calls for each bookId
            /*String bookIds = libraryList.stream().map(l -> String.valueOf(l.getBookId())).collect(Collectors.joining(","));
            logger.info(bookIds+" <<<<<<< bookIds for user id "+id);*/
            }catch (Exception e) {
                logger.info("No books are associate with user");
            }
            return userBook;
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public User addUser(User user) {
        return userServiceProxy.addUser(user);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        User user = userServiceProxy.getUserById(id);
        // delete the user-book associations for this user. Then delete the user
        if(user!=null) {
            List<Library> libraryList = libraryRepository.findAllByUserId(id);
            libraryRepository.deleteAll(libraryList);
            userServiceProxy.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public User updateUser(Long id, User requestedUser) {
        User user = userServiceProxy.getUserById(id);
        if(user!=null) {
            user.setName(requestedUser.getName());
            user.setEmail(requestedUser.getEmail());
            user.setUserType(requestedUser.getUserType());
            return userServiceProxy.updateUser(id,user);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookServiceProxy.getAllBooks();
    }

    @Override
    public Book getBookById(long id) {
        return bookServiceProxy.getBookById(id);
    }

    @Override
    public Book addBook(Book book){
        return bookServiceProxy.addBook(book);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteBook(Long id) {
        // Before deleting book from book table, remove the association with user from library_table
        libraryRepository.deleteByBookId(id);
        return bookServiceProxy.deleteBook(id);
    }

    @Transactional
    @Override
    public UserBook issueBookToUser(Long userId, Long bookId) {
        User user = userServiceProxy.getUserById(userId);
        if(user!=null) {
            Book book = bookServiceProxy.getBookById(bookId);
            if(book!=null) {
                Library library = new Library(userId,bookId);
                libraryRepository.save(library);
                UserBook userBook = new UserBook(user);
                userBook.bookList.add(book);
                return userBook;
            } else {
                throw new ResourceNotFoundException(" bookId:"+bookId);
            }
        } else {
            throw new ResourceNotFoundException(" userId:"+userId);
        }
    }
    @Transactional
    @Override
    public UserBook releaseBookForUser(Long userId, Long bookId){
        User user = userServiceProxy.getUserById(userId);
        if(user!=null) {
            Book book = bookServiceProxy.getBookById(bookId);
            if(book!=null) {
                Library library = libraryRepository.findByUserIdAndBookId(userId,bookId);
                libraryRepository.delete(library);
                UserBook userBook = new UserBook(user);
                userBook.bookList.add(book);
                return userBook;
            } else {
                throw new ResourceNotFoundException(" bookId:"+bookId);
            }
        } else {
            throw new ResourceNotFoundException(" userId:"+userId);
        }
    }
    @Override
    public UserBook getBooksAssignedToUser(Long userId) {
        User user = userServiceProxy.getUserById(userId);
        if(user!=null) {
            UserBook userBook = new UserBook(user);
            List<Library> libraries = libraryRepository.findAllByUserId(userId);
            if(libraries.isEmpty()){
                throw new ResourceNotFoundException(" userId:"+userId);
            } else {
                for(Library library : libraries){
                    Book book = bookServiceProxy.getBookById(library.getBookId());
                    userBook.bookList.add(book);
                }
                return userBook;
            }
        } else {
            throw new ResourceNotFoundException(" userId:"+userId);
        }
    }
}
