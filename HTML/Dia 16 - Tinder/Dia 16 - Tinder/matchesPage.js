function renderMatchesPage() {
  const tableMatches = new MyTable("divMatchesPage");
  tableMatches.clearTable();
  tableMatches.initTable([
    "Cover",
    "Title",
    //"Description",
    "Rating",
    "Info Link",
    "Matches",
  ]);

  arrayFavouriteBooks.forEach((book) => {
    getUsersByBookID(book, tableMatches);
  });
}
