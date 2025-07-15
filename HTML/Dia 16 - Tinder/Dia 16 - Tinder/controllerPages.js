function changePage(page) {
  switch (page) {
    case "loggedin":
      renderGamePage();
      renderMenuLogged();
      showGamePage();
      break;
    case "login":
      elementDivMenu.innerHTML = "";
      elementDivMenu.style.display = "none";
      elementDivLogin.style.display = "inline";
      elementDivGame.style.display = "none";
      elementDivFavouriteBooks.style.display = "none";
      elementDivProfile.style.display = "none";
      elementDivMatchesPage.style.display = "none";

      break;
    case "game":
      showGamePage();
      break;
    case "favouriteBooks":
      renderFavouriteBooksPage("true");
      break;
    case "profile":
      renderProfilePage();
      elementDivMenu.style.display = "none";
      elementDivLogin.style.display = "none";
      elementDivGame.style.display = "none";
      elementDivFavouriteBooks.style.display = "none";
      elementDivProfile.style.display = "inline";
      elementDivMachesPage.style.display = "none";

      break;
    case "matchesPage":
      renderMatchesPage();
      elementDivMenu.style.display = "none";
      elementDivLogin.style.display = "none";
      elementDivGame.style.display = "none";
      elementDivFavouriteBooks.style.display = "none";
      elementDivProfile.style.display = "none";
      elementDivMatchesPage.style.display = "inline";
      break;
    default:
      break;
  }
}
