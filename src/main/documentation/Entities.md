ENTITIES
Main task:
Book

      private Integer bookId;
      private String title;
      private Set<Author> authors;(many-to-many relation)
      private Subject subject;(many-to-one relation)
      private BookStatus status;
      private String codeISBN;
      private String imageURL;
      <!*
      private Integer publisherId;(many-to-one relation)
      private LocalDate yearOfIssue;
      private Integer numberOfPages;
      private string descriptionURL;
      *>

Author

      private Integer authorId;
      private String firstName;
      private String lastName;
      <!*
      private String additionalInfo;
      private String dateOfBirth;
      *>

<!* Publisher

       private String publisherID;
       private String publisherName;
       private String additionalInfo;
/*>
enum BookStatus

        AVAILABLE,
        NOT_AVAILABLE,
        OUT,
        ORDERED

Subject
        private Integer subjectId;
        private String subjectName;
        
User
        
       private Integer userId;
       private String firstName;
       private String lastName;
       private String userLogin;
       private String password;
       private String userEmail;
       private UserRole userRol;
       private UserStatus status;
       
enum UserStatus

        REGISTERED,
        CONFIRMED,
        RESTRICTED,
        DELETED   
        
enum UserRole

        ADMIN,
        READER,
        LIBRARIAN     


ConformationMessage

    private Integer id;
    private String codeMessage;
    private User user;
    private LocalDateTime expiredAt;

Additional task
<!*
Order

        private Integer orderId;
        private Reader reader (many-to-one relation)
        private LocalDateTime orderDate;
        private LocalDateTime readyAt;
        private LocalDateTime bookReturnDeadline;
        private Set<Book> orderedBooks;(one-to-many relation)
        private OrderStatus status;

enum OrderStatus

        RECEIVED,
        READY,
        OUT,
        RETURNED

User Entities

    There can be many different types of User. In this task given a case of Reader only.

abstract User

      private Integer userId;
      private String firstName;
      private String lastName;
      private String userLogin;
      private String password;
      private String userEmail;
      private UserRole userRol;


Reader extends User

      private Address readerHomeAddress; (nany-to-one relation)
      private LocalDate readerDateOfBirth;
      private String readerPhoneNumber;
      private LocalDate readerDateOfRegistration;
      private ReaderStatus status;


enum UserRole

      READER;

enum ReaderStatus

      ACTIVE,
      PAUSED,
      DISABLED;

Address

      private String streetName;
      private String houseNumber;
      private String addressExtension
      private Integer postalIndex;
      private String city;
*>