package mac.soubhagya.videoplayer;

public class Recyclerviewmodel {
    private String title;
    private String description;
    private String imageurl;

    public Recyclerviewmodel(String title,String description,String imageurl){
        this.title=title;
        this.description=description;
        this.imageurl=imageurl;
    }

    public String getTitle(){
        return  title;
    }
    public String getDescription(){
        return  description;
    }
    public String getImageurl(){
        return  imageurl;
    }
}
