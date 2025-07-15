async function getBooksFromGoogle(wordSearch = "", author) {
  //const url = `https://www.googleapis.com/books/v1/volumes?q=${search}`;
  const url = `https://www.googleapis.com/books/v1/volumes?q=${wordSearch}+inauthor:${author}&orderBy=relevance&startIndex=${currentUser.indexGlobal}&maxResults=10`;

  fetch(url)
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      throw new Error("Something went wrong");
    })
    .then((dataFromGoogle) => {
      // Do something with the response
      dataFromGoogle.items.forEach((bookData) => {
        const id = bookData.id;
        const title = bookData.volumeInfo.title;
        const authors = bookData.volumeInfo.authors;
        const publisher = bookData.volumeInfo.publisher;
        const publishedDate = bookData.volumeInfo.publishedDate;
        const description = bookData.volumeInfo.description;
        const averageRating = bookData.volumeInfo.averageRating;
        const imageLinks = bookData.volumeInfo.imageLinks;
        const infoLink = bookData.volumeInfo.infoLink;

        const book = new Book(
          id,
          title,
          authors,
          publisher,
          publishedDate,
          description,
          averageRating,
          imageLinks,
          infoLink
        );

        arrayBooksTemp.push(book);
      });
      currentUser.arrayBooks = arrayBooksTemp;
      putUpdateUserAPI(currentUser);

      changePage("loggedin");
    })
    .catch((error) => {
      console.log(error);
    });
}
