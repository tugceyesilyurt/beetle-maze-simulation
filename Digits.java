
public class Digits {

    public static void main(String[] args) {
        startSimulation();
    }

    public static void startSimulation() {
        int[][] matrix = new int[8][8];

        int[][] directions = {
            {-1, 0}, // left
            {1, 0}, // right
            {0, -1}, // up
            {0, 1}, // down
            {-1, -1}, // up-left
            {-1, 1}, // up-right
            {1, -1}, // down-left
            {1, 1} // down-right
        };

        int moves = 0;
        int borderAttempts = 0;
        int i = (int) (Math.random() * 8);
        int j = (int) (Math.random() * 8);

        visitCell(matrix, i, j);

        while (!isAllCellsVisited(matrix)) {
            int[] direction = directions[(int) (Math.random() * 8)];
            int newI = i + direction[0];
            int newJ = j + direction[1];

            if (isMoveValid(newI, newJ)) {
                i = newI;
                j = newJ;
                visitCell(matrix, i, j);
            } else {
                borderAttempts++;
            }

            moves++;
        }

        printProbabilities(matrix, moves, borderAttempts);
        printMatrix(matrix);
    }

    public static boolean isMoveValid(int i, int j) {
        return i >= 0 && i < 8 && j >= 0 && j < 8;
    }

    public static void visitCell(int[][] matrix, int i, int j) {
        matrix[i][j]++;
    }

    public static boolean isAllCellsVisited(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[i].length; j++) {
                if(j != matrix.length-1)    System.out.print(matrix[i][j] + ", ");
                else    System.out.print(matrix[i][j]);
            }
            System.out.println("]");
        }
    }
    
    public static void printProbabilities(int[][] matrix, int moves, int borderAttempts) {
        int leftMoves = matrix[0][1];
        int rightMoves = matrix[0][2];
        int upMoves = matrix[1][0];
        int downMoves = matrix[2][0];
        int allMoves = moves;

        double leftProbability = (double) leftMoves / allMoves * 100;
        double rightProbability = (double) rightMoves / allMoves * 100;
        double upProbability = (double) upMoves / allMoves * 100;
        double downProbability = (double) downMoves / allMoves * 100;
        double borderProbability = (double) borderAttempts / allMoves * 100;

        System.out.println("Probability of moving left: " + leftProbability + "%");
        System.out.println("Probability of moving right: " + rightProbability + "%");
        System.out.println("Probability of moving up: " + upProbability + "%");
        System.out.println("Probability of moving down: " + downProbability + "%");
        System.out.println("Probability reaching the border: " + borderProbability + "%");
    }
}
