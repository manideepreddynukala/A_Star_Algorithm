public class Tile {
    int positionX, positionY, value;
    Tile(int x,int y,int value){
        positionX = x;
        positionY = y;
        this.value = value;
    }


    public Tile() {

    }
    // Getters and Setter for Value of the tile, Position of X and Position of Y
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPosition(int x, int y){

        this.positionX = x;
        this.positionY = y;
    }
    // it is used for deep copying instead of shollow copying
@Override
    public Tile clone()
    {
        Tile cloneTile = new Tile();
        cloneTile.positionX = this.positionX;
        cloneTile.positionY = this.positionY;
        cloneTile.value = this.value;
        return cloneTile;
    }
}


