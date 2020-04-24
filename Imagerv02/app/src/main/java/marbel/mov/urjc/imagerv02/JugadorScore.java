package marbel.mov.urjc.imagerv02;

class JugadorScore implements Comparable<JugadorScore>{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }



    @Override
    public int compareTo(JugadorScore o) {
        if (score < o.score) {
            return -1;
        }
        if (score > o.score) {
            return 1;
        }
        return 0;
    }


    JugadorScore(){

    }

    public JugadorScore(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    private Integer score;

}
