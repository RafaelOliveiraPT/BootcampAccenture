function showGamePage() {
  elementDivGame.style.display = "inline";
  elementDivFavouriteBooks.style.display = "none";
  elementDivProfile.style.display = "none";
}

function createAuthors(authors) {
  elementDivAuthors.innerHTML = "";
  let strAux = "";
  if (authors !== undefined) {
    authors.forEach((author) => {
      strAux += `<p> <strong> Author: </strong> ${author} </p>`;
    });
  }
  elementDivAuthors.innerHTML = strAux;
}

function createDescription(description) {
  const elementFirst50Chars = document.getElementById("first50Chars");
  const elementDots = document.getElementById("dots");
  const elementMoreText = document.getElementById("moreText");
  const elementBtnReadMore = document.getElementById("btnReadMore");
  elementFirst50Chars.textContent = "";
  elementMoreText.textContent = "";

  let str1 = "";
  let str2 = "";
  if (description !== undefined && description.length > 150) {
    str1 = description.substr(0, 150);
    str2 = description.substr(150);
    elementFirst50Chars.textContent = str1;
    elementMoreText.textContent = str2;
    elementMoreText.style.display = "none";
    elementDots.style.display = "inline";
    elementBtnReadMore.style.display = "inline";
  } else {
    elementFirst50Chars.textContent = description;
    elementMoreText.style.display = "none";
    elementDots.style.display = "none";
    elementBtnReadMore.style.visibility = "inline";
  }
}

function readMore() {
  const elementFirst50Chars = document.getElementById("first50Chars");
  const elementDots = document.getElementById("dots");
  const elementMoreText = document.getElementById("moreText");
  const elementBtnReadMore = document.getElementById("btnReadMore");

  if (elementMoreText.style.display === "none") {
    elementDots.style.display = "none";
    elementMoreText.style.display = "inline";
    elementBtnReadMore.innerHTML = "Read less";
  } else {
    elementDots.style.display = "inline";
    elementMoreText.style.display = "none";
    elementBtnReadMore.innerHTML = "Read more";
  }
}

function createDetails(infoLink, publisher, publishedDate, averageRating) {
  let strAux = "";
  elementDivDetais.innerHTML = "";

  strAux += `<p class="mb-0"> <strong> Publisher: </strong> ${publisher} </p>`;
  strAux += `<p class="mb-0"> <strong> Publisher date: </strong> ${publishedDate} </p>`;
  strAux += `<p class="mb-0"> <strong> Rate: </strong> ${averageRating} </p>`;
  strAux += ` <button class="btn btn-success" id="btnReadMore" id="btnInfoLink"
                  onclick="document.location='${infoLink}'"> Info
                </button>`;
  elementDivDetais.innerHTML = strAux;
}

async function renderGamePage() {
  if (currentUser.indexGlobal !== 0 && currentUser.indexGlobal % 10 === 0) {
    getBooksFromGoogle(currentUser.filterWord, currentUser.filterAuthor);
    renderPageAux();
  } else {
    renderPageAux();
  }
}

function renderPageAux() {
  /*if (arrayBooksTemp.length === 0) {
    return;
  }*/

  //console.log("aquiii", arrayBooksTemp);
  elementCardTitle.textContent = arrayBooksTemp[currentUser.indexGlobal].title;

  createDescription(arrayBooksTemp[currentUser.indexGlobal].description);

  if (arrayBooksTemp[currentUser.indexGlobal].imageLinks) {
    elementCardImage.src =
      arrayBooksTemp[currentUser.indexGlobal].imageLinks.thumbnail;
  } else {
    elementCardImage.src = "./inf.png";
  }

  createAuthors(arrayBooksTemp[currentUser.indexGlobal].authors);
  createDetails(
    arrayBooksTemp[currentUser.indexGlobal].infoLink,
    arrayBooksTemp[currentUser.indexGlobal].publisher,
    arrayBooksTemp[currentUser.indexGlobal].publishedDate,
    arrayBooksTemp[currentUser.indexGlobal].averageRating
  );
}

function nextBook(like) {
  if (like) {
    arrayFavouriteBooks.push(arrayBooksTemp[currentUser.indexGlobal]);
    postBookAPI(currentUser, arrayBooksTemp[currentUser.indexGlobal]); // alterar aqui criar postBook
  }
  currentUser.indexGlobal++;

  putUpdateUserAPI(currentUser);

  renderGamePage();
}
