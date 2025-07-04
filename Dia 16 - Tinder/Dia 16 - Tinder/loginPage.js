function doLogin() {
  //getUsernameAPI(elementInputUsername.value, arrayUsers, renderMenuLogged);
  getUsernameAPI(elementInputUsername.value, currentUser);
  //await renderGamePage();
  //await renderMenuLogged();
}

function doLogout() {
  changePage("login");
}
