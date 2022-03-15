package de.fhkiel.srcms.apps.therapy.physical._database;

public class UserModal {

    // variables for user specification,
    private String userName;
    private String iteration;
    private String animationOne;
    private String animationTwo;
    private int id;

    // creating getter and setter methods
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIteration() {
        return iteration;
    }

    public void setIteration(String iteration) {
        this.iteration = iteration;
    }

    public String getAnimationOne() {
        return animationOne;
    }

    public void setAnimationOne(String animationOne) {
        this.animationOne = animationOne;
    }

    public String getAnimationTwo() {
        return animationTwo;
    }

    public void setAnimationTwo(String animationTwo) {
        this.animationTwo = animationTwo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public UserModal(String userName, String iteration, String animationOne, String animationTwo) {
        this.userName = userName;
        this.iteration = iteration;
        this.animationOne = animationOne;
        this.animationTwo = animationTwo;
    }
}
