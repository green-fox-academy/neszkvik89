public class SymmetricMatrix {

  public boolean isToReturn() {
    return toReturn;
  }

  public void setToReturn(boolean toReturn) {
    this.toReturn = toReturn;
  }

  boolean toReturn = true;

  public SymmetricMatrix() {
  }

  public boolean isSymmetric(int [][] my2dArray) {
    for (int i = 1; i < my2dArray.length; i++) {
      if (my2dArray[i][i-1] != my2dArray[i-1][i]) {
        this.setToReturn(false);
        break;
      } else if (my2dArray[my2dArray.length - i][my2dArray.length - i - 1]
          != my2dArray[my2dArray.length - i - 1][my2dArray.length - i]) {
        this.setToReturn(false);
        break;
      }
    } return this.isToReturn();
  }

  public static void main(String[] args) {
    SymmetricMatrix myMatrix = new SymmetricMatrix();
    int [][] testMatrix = new int [4][4];
    testMatrix[0][0] = 1;
    testMatrix[0][1] = 1;
    testMatrix[0][2] = 1;
    testMatrix[0][3] = 1;
    testMatrix[1][0] = 1;
    testMatrix[1][1] = 1;
    testMatrix[1][2] = 1;
    testMatrix[1][3] = 1;
    testMatrix[2][0] = 1;
    testMatrix[2][1] = 5;
    testMatrix[2][2] = 3;
    testMatrix[2][3] = 1;
    testMatrix[3][0] = 1;
    testMatrix[3][1] = 1;
    testMatrix[3][2] = 1;
    testMatrix[3][3] = 1;


    System.out.println(myMatrix.isSymmetric(testMatrix));
  }
}
