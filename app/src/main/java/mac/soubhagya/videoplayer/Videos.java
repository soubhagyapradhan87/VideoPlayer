package mac.soubhagya.videoplayer;



public class Videos {
    private String description;
    private int id;
    private String thumb;
    private String title;
    private String url;


    public Videos(String description, int id, String thumb, String title, String url){
        this.description=description;
        this.id=id;
        this.thumb=thumb;
        this.title=title;
        this.url=url;
    }


    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }



}
