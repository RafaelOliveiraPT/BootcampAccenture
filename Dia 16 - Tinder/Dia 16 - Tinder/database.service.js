function setArrayBooksDB(arrayBooks) {
  localStorage.setItem("arrayBooks", JSON.stringify(arrayBooks));
}

function getArrayBooksDB() {
  return JSON.parse(localStorage.getItem("arrayBooks"));
}

function setGlobalIndexDB(indexGlobal) {
  localStorage.setItem("indexGlobal", JSON.stringify(indexGlobal));
}

function getGlobalIndexDB() {
  return JSON.parse(localStorage.getItem("indexGlobal"));
}

function setArrayFavouriteBooksDB(arrayFavouriteBooksDB) {
  localStorage.setItem(
    "arrayFavouriteBooks",
    JSON.stringify(arrayFavouriteBooksDB)
  );
}

function getArrayFavouriteBooksDB() {
  return JSON.parse(localStorage.getItem("arrayFavouriteBooks"));
}

function setArrayUsersDB(arrayUsers) {
  localStorage.setItem("arrayUsers", JSON.stringify(arrayUsers));
}

function getArrayUsers() {
  return JSON.parse(localStorage.getItem("arrayUsers"));
}
