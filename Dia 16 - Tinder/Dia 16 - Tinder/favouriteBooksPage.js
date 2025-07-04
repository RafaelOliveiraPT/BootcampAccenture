function renderFavouriteBooksPage(visibility = false) {
  arrayFavouriteBooks = getArrayFavouriteBooksAPI();

  elementDivFavouriteBooks.innerHTML = "";
  elementDivProfile.style.display = "none";
  elementDivGame.style.display = "none";
  elementDivMatchesPage.style.display = "none";

  if (visibility) {
    elementDivFavouriteBooks.style.display = "flex";
  }
}
