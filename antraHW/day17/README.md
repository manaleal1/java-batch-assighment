Rest API for many to many relation

Tables: people, books, booksLikes

One book can be liked by many people. One person can like many books.

Mappings:
- <code>/people</code> - endpoint to get all entries in <code>people</code> table<br>
- <code>/books</code> - endpoint to get all entries in <code>books</code> table<br>
- <code>/register</code> - endpoint to register <code>person</code> with <code>book</code> <br>
- <code>/update-people</code> - endpoint to update <code>person</code> table. It adds/updates the person in the <code>person</code> table.
