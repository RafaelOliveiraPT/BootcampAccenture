function renderProfilePage() {
  document.getElementById("choosedAuthorID").value = currentUser.filterAuthor;

  document.getElementById("choosedWordID").value = currentUser.filterWord;
}

function changeFilters() {
  const author = document.getElementById("choosedAuthorID").value;

  const word = document.getElementById("choosedWordID").value;

  currentUser.filterAuthor = author;
  currentUser.filterWord = word;
  currentUser.indexGlobal = 0;
  currentUser.arrayBooks = [];
  arrayBooksTemp = [];
  getBooksFromGoogle(currentUser.filterWord, currentUser.filterAuthor);
  changePage("game");
}
