function renderMenuLogged() {
  let strHTML = `
    <button class="btn btn-secondary dropdown-toggle btn-menu me-5" type="button" data-bs-toggle="dropdown"
          aria-expanded="false">
          Menu
        </button>
        <ul class="dropdown-menu btn-dropdown-bg">
          <li><a onclick="changePage('profile')" class="dropdown-item">Profile</a></li>
          <li><a onclick="changePage('favouriteBooks')" class="dropdown-item">Favourite Books</a></li>
          <li><a onclick="changePage('matchesPage')" class="dropdown-item" >Matches</a></li>
          <li><a onclick="changePage('game')" class="dropdown-item">Game</a></li>
          <li><a onclick="doLogout()" class="dropdown-item">${currentUser.username} (logout)</a></li>
        </ul>
    `;
  elementDivMenu.innerHTML = strHTML;
  elementDivMenu.style.display = "inline";
  elementDivLogin.style.display = "none";
  elementDivFavouriteBooks.style.display = "none";
}
