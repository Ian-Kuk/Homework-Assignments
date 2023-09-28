import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

public class GraphTest {


    /* loadGraphFromFile(dir,fname):
        auxiliary helper method to load a graph from a text file

        since Windows uses '\' for specifying directory structure
        and Linux/Mac uses '/',
        this method first tries loading from directory: "resources\fname"
        and if it fails with file not found,
        will try again using directory: "resources/fname"
        so that the code will not need to be changed depending on the OS used
     */
    private Graph loadGraphFromFile(String dir, String fname) {
        Graph<Integer> g = new Graph<>();
        String fdir = "resources"+dir+""+fname; //"resources/GraphTest-Integer.txt";
        Scanner scnr = null;
        try {
            scnr = new Scanner(new File(fdir));
        }
        catch (Exception e) {
            System.out.println("Cannot open file "+fdir);
            if (dir.equals("/"))
                System.exit(1);
            return loadGraphFromFile("/",fname);
        }
        ScanGraph<Integer> sg = new ScanGraph<Integer>(new ScanInteger(scnr));
        sg.skipComment();
        if ( sg.hasNext() )
            return sg.next();
        return null;
    }

    /* testBFSTree():
        tests that the Graph.bfs(start) method works
        on the undirected graph given in code-example1.pdf
        (loaded from GraphTest-Integer.txt)
     */
    @Test
    public void testBFSTree()  {
        try {
            Graph<Integer> g =  loadGraphFromFile("\\","GraphTest-Integer.txt");
            g.makeUndirected();
            List<GraphNode<Integer>> bfslist = g.bfs(2);
            HashMap<GraphNode<Integer>,GraphNode<Integer>> prevmap = g.bfsPrev(2);

            List<GraphNode<Integer>> bfslistExpected = new ArrayList<>();
            int[] nodeOrder = {2,4,1,13,7,5,8,11,12,6};
            for (int x : nodeOrder)
                bfslistExpected.add(new GraphNode<>(x));
            for (GraphNode<Integer> node : bfslistExpected)
                assertEquals(node, bfslist.remove(0));
            assertTrue(bfslist.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    /* testBFSTreeDirected():
        tests that the Graph.bfs(start) method works
        on the directed form of the graph given in code-example1.pdf
        (loaded from GraphTest-Integer.txt)
     */
    @Test
    public void testBFSTreeDirected()  {
        try {
            Graph<Integer> g =  loadGraphFromFile("\\","GraphTest-Integer.txt");
           // g.makeUndirected();
            List<GraphNode<Integer>> bfslist = g.bfs(1);
            HashMap<GraphNode<Integer>,GraphNode<Integer>> prevmap = g.bfsPrev(2);

            List<GraphNode<Integer>> bfslistExpected = new ArrayList<>();
            int[] nodeOrder = {1,2,7,4};
            for (int x : nodeOrder)
                bfslistExpected.add(new GraphNode<>(x));
            for (GraphNode<Integer> node : bfslistExpected)
                assertEquals(node, bfslist.remove(0));
            assertTrue(bfslist.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /* testBFSGraph():
        tests that the Graph.bfs(start) method works
        on the undirected graph given in GraphTestWithCycles.txt

        this graph is similar to the one in GraphTest-Integer.txt,
        but a few additional edges have been added to create cycles in the graph,
        and the BFS will result in a different order of nodes visited
     */
    @Test
    public void testBFSGraph()  {
        try {
            Graph<Integer> g =  loadGraphFromFile("\\","GraphTestWithCycles.txt");
            g.makeUndirected();
            List<GraphNode<Integer>> bfslist = g.bfs(2);

            List<GraphNode<Integer>> bfslistExpected = new ArrayList<>();
            int[] nodeOrder = {2,4,5,6,1,13,8,7,11,12};
            for (int x : nodeOrder)
                bfslistExpected.add(new GraphNode<>(x));
            for (GraphNode<Integer> node : bfslistExpected)
                assertEquals(node, bfslist.remove(0));
            assertTrue(bfslist.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /* testBFSGraphDirected():
        tests that the Graph.bfs(start) method works
        on the directed form of the graph given in GraphTestWithCycles.txt
     */
    @Test
    public void testBFSGraphDirected()  {
        try {
            Graph<Integer> g =  loadGraphFromFile("\\","GraphTestWithCycles.txt");
            //g.makeUndirected();
            List<GraphNode<Integer>> bfslist = g.bfs(2);

            List<GraphNode<Integer>> bfslistExpected = new ArrayList<>();
            int[] nodeOrder = {2,4,5,6,13,8};
            for (int x : nodeOrder)
                bfslistExpected.add(new GraphNode<>(x));
            for (GraphNode<Integer> node : bfslistExpected)
                assertEquals(node, bfslist.remove(0));
            assertTrue(bfslist.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /* testBFSPrev():
        tests that the Graph.bfsPrev(start) method works
        on the undirected graph given in GraphTestWithCycles.txt

        checks if Graph.bfsPrev(start) contains all of
        the proper edges that are in the resulting BFS tree
     */
    @Test
    public void testBFSPrev()  {
        try {
            Graph<Integer> g =  loadGraphFromFile("\\","GraphTestWithCycles.txt");
            g.makeUndirected();
            HashMap<GraphNode<Integer>,GraphNode<Integer>> bfsprev = g.bfsPrev(2);

            int[] edgeOrder = {6,2,13,4,12,13,8,5,5,2,11,13,4,2,7,1,2,2,1,2};
            List<GraphEdge<Integer>> expectedEdges = new ArrayList<>();
            List<GraphEdge<Integer>> foundEdges = new ArrayList<>();
            List<GraphEdge<Integer>> matchedEdges = new ArrayList<>();
            for (int i=0; i<edgeOrder.length; i+=2) {
                int a = edgeOrder[i];
                int b = edgeOrder[i+1];
                GraphEdge<Integer> edge = new GraphEdge(new GraphNode(a),new GraphNode(b));
                expectedEdges.add(edge);
            }

            for (GraphNode<Integer> key : bfsprev.keySet()) {
                int a = key.element();
                int b = bfsprev.get(key).element();
                GraphEdge<Integer> edge = new GraphEdge(new GraphNode(a),new GraphNode(b));
                foundEdges.add(edge);
            }

            for (GraphEdge<Integer> edge : foundEdges) {
                assertFalse(expectedEdges.isEmpty());
                assertTrue(expectedEdges.contains(edge));
                expectedEdges.remove(edge);
                matchedEdges.add(edge);
            }
            assertTrue(expectedEdges.isEmpty());
            assertEquals(matchedEdges.size(),foundEdges.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}