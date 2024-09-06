BooksRepositoryInterface

    Optional<Book> addOrUpdateBook(Book book);
    Book[] findAllBooks();
    Optional<Book> findBookById(Integer id);
    Book[] findBooksByTitle(String title);
    Book[] findBooksByAuthorId(Integer id);
    Book[] findBooksByStatus(BookStatus status);
    Book[] findBooksBySubjectName(String subjectName);
    Book[] findBooksByCodeISBN(String codeISBN);

    Boolean deleteBookById(Integer id);

AuthorsRepositoryInterface

   Optional<Author> addOrUpdateAuthor(Author newAuthor);
   Author[] findAllAuthors();
   Optional<Author> findAuthorById(Integer id);
   Author[] findAuthorsByName(String firstName, String lastName);

SubjectsRepositoryInterface

   Optional<Subject> addOrUpdateSubject(Subject newSubject);
   Optional<Subject> findSubjectById(String id);
   Subject[] findAllSubjects();
   Optional<Subject> findSubjectByName(String name);
   Boolean deleteSubjectById(Integer id);

UserService

    UserResponseDto addUser(NewUserRequestDto dto);
    UserResponseDto updateUserData(UpdateUserRequestDto dto);
    String deleteUserById(Integer id);
    UserResponseDro userLogin(UserLoginDto dto);
    List<UserResponseDto> findAllUsers();
    List<UserResponseDto> findUserByFirstAndLastName(String firstName, String lastName);
    UserResponseDto findUserById(Integer Id);
    UserResponseDto findUserByEmail(String email);
    UserResponseDto findUserByLogin(String login);
    String changePassword(ChangeUserPasswordRequestDto dto);
    String getUserRole(Integer userId);

UserRoleService

    UserRoleDto addUserRole(String newUserRole);
    UserRoleDto updateUserRole(UserRoleDto dto);
    List<UserRoleDto> findAllRoles();
    UserRoleDto findRoleByName(String role);