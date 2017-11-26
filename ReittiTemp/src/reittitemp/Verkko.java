package reittitemp;

import java.util.*;

public class Verkko {
    
    /*
    alustava korvike Graph-luokalle, sisällöltään vähän sekavahko nyt, kun koittanut sovitella Edgeä käyttöön myös
    */

    private Array<Array<Vertex>> list; //vieruslistan toteutus
    private int[][] grid;
    private Vertex[][] nGrid;
    private Vertex s;
    private Vertex u;
    private Array<Edge> edges;
    private Array<Vertex> vertexes;

    public Verkko(int[][] g) {
        nGrid = new Vertex[50][50];
//        adjList = new HashMap<>();
        list = new Array(2500);
        for (int i = 0; i < 2500; i++) {
            Array<Vertex> a = new Array(4);
            list.add(a);
        }
        edges = new Array(4 * (50 - 2) * (50 - 2) + 3 * (50 - 2) * 4 + 2 * 4);
        vertexes = new Array(2500); //ei ehkä tarvita

        grid = g;
    }

    public Verkko(Array<Edge> e, Array<Vertex> v) {
        vertexes = v;
        edges = e;
    }

    public Array<Vertex> getVertexes() {
        return vertexes;
    }

    public Array<Edge> getEdges() {
        return edges;
    }

    public Vertex[][] getNodeGrid() {
        return nGrid;
    }

    public Array<Array<Vertex>> getNodes() {
        return list;
    }

    public void initGraph() {
        int id = 0;
        int count = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (grid[i][j] == 1) {
                    Array<Vertex> a = list.get(id);
                    a = null;
                    nGrid[i][j] = null;
                } else {
                    Array<Vertex> a = list.get(id);
                    a = new Array(4);
                    count++;
                    nGrid[i][j] = new Vertex(id, i, j);
                    vertexes.add(nGrid[i][j]);
                    if (grid[i][j] == 2) {
                        u = nGrid[i][j];
                        System.out.println("u updated");
                    } else if (grid[i][j] == 3) {
                        s = nGrid[i][j];
                        System.out.println("s updated");
                    }
                }
                id++;
            }
        }
        connectNodes();
        System.out.println("count: " + count);
    }

    public void connectNodes() {
        int e = 0;
        Edge ed;
        edges = new Array(4 * (50 - 2) * (50 - 2) + 3 * (50 - 2) * 4 + 2 * 4);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (nGrid[i][j] != null) {
                    if (i + 1 < 50) {
                        if (nGrid[i + 1][j] != null) {
                            ed = new Edge(e, nGrid[i][j], nGrid[i + 1][j], 1);
                            edges.add(ed);
                            e++;
                            addEdge(nGrid[i][j], nGrid[i + 1][j]);
                        }
                    }
                    if (i - 1 >= 0) {
                        if (nGrid[i - 1][j] != null) {
                            ed = new Edge(e, nGrid[i][j], nGrid[i - 1][j], 1);
                            edges.add(ed);
                            e++;
                            addEdge(nGrid[i][j], nGrid[i - 1][j]);
                        }
                    }
                    if (j + 1 < 50) {
                        if (nGrid[i][j + 1] != null) {
                            ed = new Edge(e, nGrid[i][j], nGrid[i][j + 1], 1);
                            edges.add(ed);
                            e++;
                            addEdge(nGrid[i][j], nGrid[i][j + 1]);
                        }
                    }
                    if (j - 1 >= 0) {
                        if (nGrid[i][j - 1] != null) {
                            ed = new Edge(e, nGrid[i][j], nGrid[i][j - 1], 1);
                            edges.add(ed);
                            e++;
                            addEdge(nGrid[i][j], nGrid[i][j - 1]);
                        }
                    }
                }
            }
        }
        System.out.println("e: " + e);
    }

    public void addEdge(Vertex v, Vertex w) {

        list.get(v.id()).add(w);
        list.get(w.id()).add(v);
        v.connect(w);
//        System.out.println("added " + v.getId() + " and " + w.getId());

    }

    public Array<Vertex> getNeighbours(int v) {
        if (v < 2500) {
            return list.get(v);
        }
        return null;
    }

    public Vertex getS() {
        return s;
    }

    public Vertex getU() {
        return u;
    }

    public void updateU(Vertex i) {
        u = i;
    }

    public void updateS(Vertex i) {
        s = i;
    }

}