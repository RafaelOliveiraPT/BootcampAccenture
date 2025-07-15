async function getUsernameAPI(username) {
  const url = `https://aai-books-852480dfe047.herokuapp.com/api/users/?filter={"where":{"username":"${username}"}}`;

  fetch(url)
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      throw new Error("Something went wrong");
    })
    .then((responseJSON) => {
      if (responseJSON.length === 0) {
        console.log("não encontrou o utilizador:" + username);
        postUsernameAPI(username);
      } else {
        console.log("encontrou o user: " + username);
        console.log(responseJSON);
        currentUser = responseJSON[0];
        arrayBooksTemp = currentUser.arrayBooks;

        if (currentUser.arrayBooks.length === 0) {
          getBooksFromGoogle(currentUser.filterWord, currentUser.filterAuthor);
        } else {
          changePage("loggedin");
        }
      }
      getArrayFavouriteBooksAPI();
    })
    .catch((error) => {
      console.log(error);
    });
}

function postUsernameAPI(username) {
  tempUser = {
    username: username,
    indexGlobal: 0,
    arrayBooks: [],
  };
  fetch("https://aai-books-852480dfe047.herokuapp.com/api/users", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(tempUser),
    // …
  })
    .then((response) => {
      return response.json();
    })
    .then((responseJSON) => {
      const newUser = new User(
        responseJSON.id,
        username,
        [],
        0,
        "J. K. Rowling",
        "harry"
      );
      arrayUsers.push(newUser);

      currentUser = newUser;
      getBooksFromGoogle(currentUser.filterWord, currentUser.filterAuthor);
    });
}

async function postBookAPI(user, book) {
  tempBook = {
    idBook: book.id,
    title: book.title,
    authors: book.authors,
    publisher: book.publisher,
    publishedDate: book.publishedDate,
    description: book.description,
    averageRating: book.averageRating,
    imageLinks: book.imageLinks,
    infoLink: book.infoLink,
  };
  const response = await fetch(
    `https://aai-books-852480dfe047.herokuapp.com/api/users/${user.id}/books`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(tempBook),
      // …
    }
  );
  if (response.ok) {
    console.log(response);
    const response_json = await response.json();
  }
}

async function getArrayFavouriteBooksAPI() {
  const url = `https://aai-books-852480dfe047.herokuapp.com/api/users/${currentUser.id}/books`;

  fetch(url)
    .then((response) => {
      if (response.ok) {
        const response_json = response.json();

        return response_json;
      }
      throw new Error("Something went wrong");
    })
    .then((responseJSON) => {
      arrayFavouriteBooks = responseJSON;
      return arrayFavouriteBooks;
    })
    .then((responseObject) => {
      // Do something with the response
      console.log(arrayFavouriteBooks);
      let strAux = "";
      let strPathFileAux = "";
      responseObject.forEach((book) => {
        if (book.imageLinks !== undefined)
          strPathFileAux = book.imageLinks.thumbnail;
        else strPathFileAux = "./inf.png";
        strAux = `
       <div "
          class="col-sm-12 col-md-6 col-lg-4 col-xl-4  p-3 d-flex justify-content-center align-items-center">
          <div class="flex-column text-center">
          <a href="${book.infoLink}" target="_blank"> <img  class="img-fluid" 
            src="${strPathFileAux}"
            alt="${book.title}"></a>
            <p><small>${book.title}</small></p>
          </div>
       </div>`;
        elementDivFavouriteBooks.insertAdjacentHTML("beforeend", strAux);
      });
      if (responseObject.length === 0) {
        strAux = "<p>ainda não gostou de nenhum livro!</p>";
        elementDivFavouriteBooks.insertAdjacentHTML("beforeend", strAux);
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

async function putUpdateUserAPI(user) {
  // console.log(user);
  tempUser = {
    id: user.id,
    username: user.username,
    arrayBooks: user.arrayBooks,
    indexGlobal: user.indexGlobal,
    filterAuthor: user.filterAuthor,
    filterWord: user.filterWord,
  };
  const response = await fetch(
    "https://aai-books-852480dfe047.herokuapp.com/api/users",
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(tempUser),
      // …
    }
  );
}

function getUsersByBookID(book, table) {
  let query = {
    where: {
      idBook: "" + book.idBook,
    },
    include: {
      relation: "user",
      scope: {
        fields: ["username"],
      },
    },
    fields: ["userId", "title", "id"],
  };
  fetch(
    "https://aai-books-852480dfe047.herokuapp.com/api/Books?filter=" +
      JSON.stringify(query)
  )
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      console.error("Erro : ", response.status);
    })
    .then((data) => {
      const arrayTemp = data.filter(
        (object) => object.user.username !== currentUser.username
      );
      const arrayMatchesUser = arrayTemp.map((object) => object.user.username);

      console.log(arrayTemp);
      let imgBook = "./inf.png";
      if (book.imageLinks !== undefined) {
        imgBook = book.imageLinks.thumbnail;
      }
      const imgBookHTMLElement = `<img width="100px" src='${imgBook}'> `;
      const linkBookHTMLElement = `<a href="${book.infoLink}">Link</a>`;
      table.insertRowMyTable([
        imgBookHTMLElement,
        book.title,
        //book.description,
        book.averageRating,
        linkBookHTMLElement,
        arrayMatchesUser.join(", "),
      ]);
    });
}
