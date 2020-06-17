package mvcIntelliJIdea.model;

public class Picture implements Comparable<Picture> {
    public String name;
    public String file;
    public int score;
    int id;
    public Picture(int id,String name, String file){
        this.id = id;
        this.name = name;
        this.file = file;
        this.score = 0;
    }
    public Picture(int id,String name, String file, int score){
        this.id = id;
        this.name = name;
        this.file = file;
        this.score = score;
    }
    public String get_name(){
        return this.name;
    }
    public String get_file(){
        return this.file;
    }
    public int get_score(){
        return this.score;
    }

    public int get_id() {
        return this.id;
    }

    @Override
    public int compareTo(Picture o) {
        if(this.score < o.score){
            return 1;
        }
        else{
            return -1;
        }
    }
}
