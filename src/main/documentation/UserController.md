PublicController(Available for unregistered user)
@RequestMapping("/api/users")

@PostMapping("/register)
ResponseEntity<UserDataResponse> registerNewReaderByReader(@RequestBody NewUserRequestFromReader dto);

@PostMapping("/login")
ResponseEntity<UserDataResponse> loginUser(@RequestBody UserLoginRequest dto)

ReaderController(Available for registered user)
@RequestMapping("/api/users)

@PostMapping("/confirmation")
ResponseEntity<ConfirmedUserResponse> confirmRegistration(@RequestBody ConfirmationRequest dto)



@PutMapping()
ResponseEntity<UserResponseForReader> updateSelfDataByReader(@RequestBody ReaderDataSelfUpdateRequest dto)

AdminController
@RequestMapping("/api/users/admin")

@PostMapping("/user")
ResponseEntity<UserDataResponseForAdmin> registerUserByAdmin(@RequestBody NewUserRequestFromAdmin dto)

@PutMapping("/user")
ResponseEntity<UserDataResponseForAdmin> updateUserDataByAdmin(@RequestBody UserDataUpdateByAdminRequest dto)

@GetMapping("/user")
ResponseEntity<List<UserDataResponseForAdmin>> getAllUsers()

@GetMapping("/user/{role}")
ResponseEntity<List<UserDataResponseForAdmin>> getUsersByRole(@PathVariable String role)

@GetMapping("/user/{state}")
ResponseEntity<List<UserDataResponseForAdmin>> getUsersByState(@PathVariable String state)

@GetMapping("/user/{id}")
ResponseEntity<UserDataResponseForAdmin> getUserById(@PathVariable Integer id)

@GetMapping("/user/{lastName}")
ResponseEntity<List<UserDataResponseForAdmin>> getUsersById(@PathVariable Integer id)

@PostMapping("/user/confirmation")
ResponseEntity<SentConfirmationForAdmin> sendNewConfirmationToUser(@RequestBody NewConfirmationRequest dto)