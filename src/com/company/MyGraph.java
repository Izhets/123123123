package com.company;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.util.*;

public class MyGraph {


    private boolean[][] adjMatrix = null;
    private int vCount = 0;
    private int eCount = 0;
    boolean[] used = null; // массив пометок
    List<Integer> path = new ArrayList<>();

    /**
     * Конструктор
     *
     * @param vertexCount Кол-во вершин графа (может увеличиваться при
     *                    добавлении ребер)
     */
    public MyGraph(int vertexCount) {
        vCount = vertexCount;
        used = new boolean[vCount]; // массив пометок
        adjMatrix = new boolean[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                adjMatrix[i][j] = false;
            }

        }

    }

    public int vertexCount() {
        return vCount;
    }

    public int edgeCount() {
        return eCount;
    }

    public void addEdge(int v1, int v2) {
        int maxV = Math.max(v1, v2);
        /*if (maxV >= vertexCount()) {
            vCount = maxV + 1;
            adjMatrix = Arrays.copyOf(adjMatrix, maxV + 1);
            for (int i = 0; i < maxV; i++) {
                adjMatrix[i] = i < vCount ? Arrays.copyOf(adjMatrix[i], maxV + 1) : new boolean[maxV + 1];
            }

        }*/ // тупо не работает, а жаль
        if (!adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = true;
            //  adjMatrix[v2][v1] = true;
            eCount++;

        }
    }

    public void removeEdge(int v1, int v2) {
        if (adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = false;
            eCount--;
        }
    }


    public int[][] adjMatrixInt() {

        int[][] adjMatrixInt = new int[vCount][vCount];

        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                adjMatrixInt[i][j] = 0;
            }
        }
        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                adjMatrixInt[i][j] = adjMatrix[i][j] ? 1 : 0;
            }
        }
        return adjMatrixInt;
    }

    public void print() {
        int adjMatrixInt[][] = adjMatrixInt();

        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                System.out.print(adjMatrixInt[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void result(int a, int b, int[] vertexes) {
        removeVertexes(vertexes);
        justBFS(a, b);
        System.out.println(Arrays.toString(path.toArray()));
    }


    private void removeVertexes(int[] vertexes) { // удаляем ребра, содержащие указанные вершины
        for (int v : vertexes) {
            for (int i = 0; i < vCount; i++) {
                removeEdge(v, i);
                removeEdge(i, v);
            }

        }
    }


    private void justBFS(int v, int v2) { // обход в ширину, ищет просто путь, необязательно самый короткий

        if (!path.contains(v))
            path.add(v);// добавляем вершину в путь

        if (path.contains(v2)) {
            return; // выходим из рекурсии, если мы дошли до нужной вершины

        } else {

            //   <обработка вершины v>
            used[v] = true;
            for (int nv = 0; nv < vCount; nv++) { // перебираем вершины

                if (!used[nv] && adjMatrix[v][nv]) { // если nv не помечена и смежна с v
                    justBFS(nv, v2);

                    if (path.contains(v2)) {
                        return;  // выходим из рекурсии, если мы дошли до нужной вершины

                    }
                    path.remove(path.size() - 1); // удаляем те вершины, которые не ведут нас до нужной цели
                }
            }
            return;
        }
    }


}
