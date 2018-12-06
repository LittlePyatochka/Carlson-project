package CarlsonProject;

public class Carlson extends Person implements Talkable{

    private int WindowMayChoose;
    private int jamWasEaten;
    // Счётчик ходов до возможности употребить джем
    final private int JAMTURN = 6;
    // Количество ходов, которые должны пройти между проеданием джема

    public Carlson(String name){
        super(name, 60);
    }

    public void chooseWindow(Window windows[]) {
        //принимает массив всех окон и выбирает из него одно
        // Значение выбранного окна присваивается полю в классе Move
        int currentLen = 0;
        for (int i = 0; i < windows.length; i++) {
            if(windows[i].isSpeakFlag()) {
                WindowMayChoose = i;
                printStatus();

                if (currentLen == 0) {
                    currentLen = Math.abs(i - Move.getCurrWindowID());
                }

                if (Math.abs(Move.getCurrWindowID() - i) <= currentLen) {
                    Move.setTargetWindowID(i);
                    currentLen = Math.abs(i - Move.getCurrWindowID());
                    printStatus();
                    say("chooseWindow");
                }
            }
        }
    }



    public int getJamWasEaten(){
        return this.jamWasEaten;
    }

    //метод, осуществляющий движение между окнами
    public void move(){
        int change = 0;
        if (Move.getCurrWindowID() != Move.getTargetWindowID()) {
            change = (Move.getCurrWindowID() < Move.getTargetWindowID()) ? 1 : -1;
            Move.setCurrWindowID(Move.getCurrWindowID() + change);
        }
    }
    public void eatJam(Baby baby){
        if (jamWasEaten == 0) {
            if (baby.hasJam()) {
                baby.decJam();
                say("jam");
                this.jamWasEaten = JAMTURN;
            }
        }
    }

    public void checkOpenWindow(Window window){
        if (window.isOpenFlag()){

        }
    }

    // Проверяет есть ли дыра в окне
    public boolean searchHole(Window window){
        if (window.isHoleFlag()){
            System.out.println(this.toString() + " нашёл дыру в занавесках " + window.getColor().toString() + "го окна");
        } else {
            System.out.println("Чёрт, дыры в " + window.getColor().toString() + "м окне нет");
        }
        return window.isHoleFlag();
    }

    @Override
    public void printStatus(){
        System.out.printf("Из #s го окна доносится крик", Move.getWindows()[WindowMayChoose].getColor().toString());
    }

    public void rest(){
        if (this.getEndurance()<=55){ this.setEndurance(this.getEndurance()+5); }
        else{ setEndurance(60); }
    }

    public void say(String what){
        if (what == "chooseWindow"){
            System.out.println("Малыш и Карлсон ползут к " + Move.getWindows()[Move.getTargetWindowID()].getColor().toString() + "му окну");
        } else if (what == "jam"){
            System.out.println("Карлсон съел банку варенья");
            System.out.printf("У малыша осталось %d банок варенья",  baby.getJamCounter());
        } else if (what == "sad"){
            System.out.println("Карлсон " + this.getName() + " расстроен");
        }
    }

    public String toString() {
        return "Карлсон " + this.getName();
    }
}

