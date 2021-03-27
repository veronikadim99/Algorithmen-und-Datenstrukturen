import java.util.ArrayList;
import java.util.Stack;

public class ContentAwareImageResizing {
    public int sx, sy;
    public Image image;

    public ContentAwareImageResizing(Image image) {
        sx = image.sizeX();
        sy = image.sizeY();
        this.image = image;
    }

    // convert (x,y) pixel coordinate into index of node in the graph
    public int coordinateToNode(int x, int y) {
        return x + y * sx;
    }

    // convert pixel coordinate into index of node in the graph
    public int coordinateToNode(Coordinate p) {
        return coordinateToNode(p.x, p.y);
    }

    // convert index of node in the graph to a pixel coordinate
    public Coordinate nodeToCoordinate(int v) {
        int y = v / sx;
        return new Coordinate(v - y * sx, y);
    }

    public WeightedDigraph makeVGraph() {
        WeightedDigraph graph = new WeightedDigraph(sx * sy + 2);
        final int delta = 1;
        for (int y = 1; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                Coordinate pFrom = new Coordinate(x, y - 1);
                int vFrom = coordinateToNode(pFrom);
                for (int d = -delta; d <= delta; d++) {
                    if (x + d >= 0 && x + d < sx) {
                        Coordinate pTo = new Coordinate(x + d, y);
                        int vTo = coordinateToNode(pTo);
                        graph.addEdge(vFrom, vTo, image.contrast(pFrom, pTo));
                    }
                }
            }
        }
        int vSource = sx * sy;
        int vTarget = sx * sy + 1;
        for (int x = 0; x < sx; x++) {
            int vTo = coordinateToNode(x, 0);
            graph.addEdge(vSource, vTo, 0);
            int vFrom = coordinateToNode(x, sy - 1);
            graph.addEdge(vFrom, vTarget, 0);
        }
        return graph;
    }

    public int[] leastContrastImageVPath() {
        // TODO

        int nodeV = sx*sy+1;
        ShortestPathsTopological getStack = new ShortestPathsTopological(makeVGraph(),nodeV-1);
        int sizeofPath = getStack.pathTo(nodeV).size()-2;
        int[] leastContrastPath = new int[sizeofPath];

        Stack<Integer> pathStack = getStack.pathTo(nodeV);
        if(pathStack.size() != 0){ pathStack.pop();}


            int check = (pathStack.size() == 1) ? 1 : 0;
            if(check == 1){ pathStack.pop(); }
            if(check == 0) {
                for(int i = 0; i < sizeofPath; i++) {
                    Coordinate node = nodeToCoordinate(pathStack.pop());
                    leastContrastPath[i] = node.x % sx;
                }
            }
            return leastContrastPath;

    }

    public void removeVPath(int[] path) {
        image.removeVPath(path);
        sx--;
    }

    public static void demoMatrixImage(String filename) throws java.io.FileNotFoundException {
        MatrixImage image = new MatrixImage(filename);
        ContentAwareImageResizing cair = new ContentAwareImageResizing(image);
        System.out.println("Original:");
        image.render();
        for (int k = 0; k < 2; k++) {
            cair.removeVPath(cair.leastContrastImageVPath());
            System.out.println("After removing one path:");
            image.render();
        }
    }

    public static void demoPictureImage(String filename) {
        PictureImage image = new PictureImage(filename);
        ContentAwareImageResizing cair = new ContentAwareImageResizing(image);
        int nDeletions = image.sizeX()/2;
        for (int k = 0; k < nDeletions; k++) {
            System.out.println("removing path " + k);
            cair.removeVPath(cair.leastContrastImageVPath());
            image.render();
        }
    }

    public static void main(String[] args) throws java.io.FileNotFoundException {

    demoPictureImage("640px-foto_2017_07_30_202914-cropped.jpg");
       demoMatrixImage("testImage.txt");












    }
}

