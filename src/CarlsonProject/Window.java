package CarlsonProject;

    public class Window{

    private boolean openFlag;
    private boolean holeFlag;
    private boolean speakFlag;
    private double speakChance;
    private Color color;

    public Window(boolean OF, boolean HF, boolean SF, double SC, Color clr) {
        this.openFlag = OF;
        this.holeFlag = HF;
        this.speakFlag = SF;
        this.speakChance = SC;
        this.color = clr;
    }

    public Window(Color color){
        this((Math.random()>0.5), (Math.random()>0.5), (Math.random()>0.5), 0.5, color);
    }

    public boolean isOpenFlag() {
            return this.openFlag;
    }

    public boolean isHoleFlag() {
            return this.holeFlag;
    }

    public boolean isSpeakFlag() {
            return this.speakFlag;
    }

    public Color getColor() {
            return this.color;
    }

    public boolean equals(Window w) {
        return (this.openFlag == w.openFlag) &
                (this.holeFlag == w.holeFlag) &
                (this.speakFlag == w.speakFlag) &
                (this.color == w.color);
    }

    @Override
    public int hashCode(){
        int count = 0;
        for (char c: this.color.toString().toCharArray()){
            count += (int)c;
        }
        boolean flags[] = {this.holeFlag, this.openFlag, this.speakFlag};
        for(boolean flag: flags){
            count += flag ? 1 : 0;
        }
        return count;
    }
}