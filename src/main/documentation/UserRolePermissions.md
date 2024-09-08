Public controllers, no login needed

Find books
All,
By title,
By ISBN,
By AuthorName
By subject

Registration
Login

READER

sendConfirmation
getOwnData

Update:

password
email
firstName
lastName

Order:

newOrder
updateOrder
getSelfOrderHistory

LIBRARIAN

findReader :

byId
byName,
byEmail,
byUserState
changeReaderStateToBanned

findOrder:
All
byId
byReader
byState
byDate
orderHistoryByReaderId

newOrder
updateOrder

addNewAuthor
addNewBook,
addNewSubject
changeBookState

ADMIN
All permissions of READER and LIBRARIAN

receives whole UserData
findUserByRole
updateUser
changeUserState
changeUserRole
setNewUserPassword
addNewUser
createNewConfirmationMessage