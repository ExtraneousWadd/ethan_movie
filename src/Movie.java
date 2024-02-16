public class Movie {
    private String title;
    private String[] cast;
    private String director;
    private String overview;
    private int runtime;
    private double userRating;
    public Movie(String title, String[] cast, String director, String overview, int runtime,int userRating){
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.userRating = userRating;
        this.overview = overview;
        this.runtime = runtime;

    }

    public String getTitle(){
        return title;
    }

    public String[] getCast(){
        return cast;
    }
    public String getOverview(){
        return overview;
    }
    public String getDirector(){
        return director;
    }
    public int getRuntime(){
        return runtime;
    }
    public double getRating(){
        return userRating;
    }

    public void getInfo(){
        String str = "Title: " + title + "\n" + "Cast: ";
        for(int i = 0; i < cast.length; i++){
            str += cast[i] + "|";
        }
        str += "\n" + "Overview: " + overview + "\n";
        str += "Runtime: " + runtime + "\n";
        str += "User Ratings: " + userRating + "\n";
        System.out.println(str);
    }


}
