package sample;

public class Poem {
    private String date;
    private String poemName;
    private String writer;
    private int number;

    public Poem(int number,String poemName,String writer,String date){
        setNumber(number);
        setPoemName(poemName);
        setWriter(writer);
        setDate(date);
    }

    public void setDate(String date) {
        this.date=date;
    }
    public String getDate(){
        return date;
    }

    public Poem() {

    }

    public String getPoemName() {
        return poemName;
    }

    public String getWriter() {
        return writer;
    }
    public  int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number=number;
    }

    public void setPoemName(String poemName) {
        this.poemName = poemName;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
