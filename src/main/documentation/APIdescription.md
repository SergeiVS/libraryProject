addNewAuthor

Request:

    {
        "id":"..."
        "firstName": "...",
        "lastName":"..."
    }

Response:

    {
        "id": ...,
        "firstName": "....",
        "lastName": "...."
    }

addNewBook

Request

    {
        "bookTitle":"Clean code",
        "authorsIds":[1],
        "codeISBN":"978-0132350884",
        "bookSubject":"Computer sience"
    }

Response

    {
        "id": 2,
        "bookTitle": "Clean code",
        "authors": [
            {
                "id": 1,
                "firstName": "Robert",
                "lastName": "Martin"
            }
        ],
        "codeISBN": "978-0132350884",
        "bookSubject": "Computer science",
        "bookStatus": "AVAILABLE"
    }
