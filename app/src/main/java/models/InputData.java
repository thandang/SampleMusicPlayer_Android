package models;

/**
 * Created by thandang on 7/12/16.
 */
public class InputData {
    public float positionX;
    public float positionY;
    public float sizeStart;
    public float sizeEnd;
    public float delta;
    public int   isDown; // 1 is true and 0 is false
    public float delta2;
    public float currentPositionY;
    public float secondPostionY;
    public int   numberOfStepItem;
    public float nextPosition;

    public InputData() {
        positionX = 20.0f;
        positionY = 1.5f;
        sizeStart = 32.0f;
        sizeEnd = 32.0f;
        delta = 0.0f;
        delta2 = 0.0f;
        isDown = 0;
        currentPositionY = 0.0f;
        secondPostionY = 0.0f;
        numberOfStepItem = 5;
        nextPosition = 0.0f;
    }
}
