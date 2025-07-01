class Book {
  constructor(
    id,
    title,
    authors, //array de autores
    publisher,
    publishedDate,
    description,
    averageRating,
    imageLinks, // [smallThumbnail : link, thumbnail : link ]
    infoLink
  ) {
    this.id = id;
    this.title = title;
    this.authors = authors;
    this.publisher = publisher;
    this.publishedDate = publishedDate;
    this.description = description;
    this.averageRating = averageRating;
    this.imageLinks = imageLinks;
    this.infoLink = infoLink;
  }
}
