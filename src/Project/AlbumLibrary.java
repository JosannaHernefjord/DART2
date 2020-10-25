package Project;

public class AlbumLibrary extends ItemLibrary
{
	//-----Constructor----
	public AlbumLibrary()
	{
		super();
	}

	public void addAlbum(int id, String title, String artist, int releaseYear, double rentPerDay)
	{
		addItem(new Album(id, title, artist, releaseYear, rentPerDay));
	}

	public void removeAlbum(int idToRemove)
	{
		removeItem(idToRemove);
	}

	public void rentAlbum(int id)
	{
		rentItem(id);
	}

	public void printAllAlbums()    // view all albums
	{
		printAllItems();
	}

	public void returnAlbum(int id)
	{
		returnItem(id);
	}

	public void printByYear(int year)
	{
		for (Item item : itemList) {
			Album album = (Album) item;

			if(year == (album.getReleaseYear()))
			{
				System.out.println(album.toString());
			}
		}
	}

	public void printAlbumsHistory()
	{
		printItemHistory();
	}
}