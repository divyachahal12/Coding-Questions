   public class Main{
        static class Edge{
            int src;
            int nbr;
            int wt;
            
            Edge(int src, int nbr, int wt){
                this.src = src;
                this.nbr = nbr;
                this .wt = wt;
            }
        }
        public static void main(String[] args) throws Exception{
            int vces = 4; //0, 1, 2, 3
            ArrayList<Edge>[] graph = new ArrayList<Edge>[4];
            for(int i = 0; i < vces; i++){
                graph[i] = new ArrayList<Edge>();
            }
            
            graph[0].add(new Edge(0, 1, 20));
            graph[0].add(new Edge(0, 2, 50));
            graph[0].add(new Edge(0, 3, 10));
            
            graph[1].add(new Edge(1, 0, 20));
            
            graph[2].add(new Edge(2, 0, 50));
            graph[2].add(new Edge(2, 3, 20));
            
            graph[3].add(new Edge(3, 0, 10));
            graph[3].add(new Edge(3, 2, 20));
        }
   }
//hasPth
/*
 find if a path exists between src and dest. If it does, print true.>
 We know that if there is a path between src vertex and dest vertex then there must also exist a path between src's neighbor vertex and dest vertex too.
So we can develop some expectation and faith for this problem.
The expectation from the hasPath(src, dest) function is that it will return us a Boolean value, indicating whether the path exists between src and dest.
Keeping the faith> on the recursive call, hasPath(nbr, dest), 
that it works perfectly and will tell us whether there exists a path between src's neighbor and dest vertex.
And meet this expectation with faith by trusting the faith's result and returning the same result.
If hasPath(nbr, dest) returns false then no path exists between nbr and dest. 
So, it implies that no path exists between src and dest too. 
And if it returns true then it means path exists between nbr and dest which means path also exists between src and dest.
And if src has n number of neighbors then we call hasPath() for each of the neighboring vertex unless a true is returned, 
and if true is received through any call then the further iterations are halted and true is returned.
And the base case for this function will be when src becomes equal to dest. It means we have reached our destination and therefore we return true.
But if we notice, as this is a recursive function so we can get to see some redundant calls.
As src will make recursive calls to each of its neighbors, suppose that it made the first call to its neighbor 1, nbr1. 
So now this nbr1 will further make calls to each of its neighbors.
And not to forget, src is also one of nbr1's neighbors. So src will again make a recursive call to its neighbor and first of all nbr1 will be called.
And it will give us a run time error of stack overflow.
So, to handle this situation, we make use of the Boolean array, visited, 
which is already present in the signature of the hasPath() function in the code editor.
Every time we enter the hasPath() function corresponding to a certain src, we update the visited array's src index value to true.
And before making a call for any neighbor, we first check for the corresponding value of this neighbor's visited array.
And make a call only if it's false. As it implies that this vertex is being visited for the first time.

Time Complexity: O(V+E)
Where V is the number of vertices and E is the number of edges. In the worst case, all the vertices and all the edges will be travelled.
The time complexity of the while loop (k log(k)).Which sum up to O(n log(k)).

Space Complexity: O(V)
It will be the height of the recursion stack, which can be O(V) at max.


*/
 public static boolean hasPath(int src, int dest, ArrayList<Edge>[] graph, boolean[] visited){
        if(src == dest){
            return true;
        }
        
        visited[src] = true;
        for(Edge edge : graph[src]){
            if(visited[edge.nbr] == false){
                boolean hasNbrPath = hasPath(src.nbr, dest, graph, visited);
                if(hasNbrPath == true){
                    return true;
                }
            }
        }
        return false;
    }

//print all paths
/*
You are given a graph, a source vertex and a destination vertex.
You are required to find and print all paths between source and destination. Print 
     them in lexicographical order.
    
    E.g. Check the following paths
             012546
             01256
             032546
             03256

    The lexicographically smaller path is printed first.
    
In the hasPath() function, the return type is Boolean, which basically indicates whether there exists a path between source and destination.
But in this problem, instead of checking and returning some Boolean value we need to print the existing path.
And to achieve this, the first change that we make in printAllPaths() function is that we change the return type of this function to void.
And in addition, we add String in the argument of the function which will store the path covered so far.
Changing this return type to void also implies that printAllPaths() function returns nothing 
   therefore it makes no sense to capture the result of its recursive calls. (Statement - 5 in above code).
Which also implies the invalidation of statements 6 and 7 from above code for printAllPaths() function.
Moving to the most important point; even after doing the above changes, we may get a path or few printed but what about all possible paths.
To take care of this, it is really important that we explore all possibilities.
In the hasPath() function we used to set the corresponding value to src in the visited array as true, so that we can stop the redundant calls.
But now, to print all paths, we need to allow even the once visited vertex to contribute to other possible paths. 
For this, it's important to set the value, corresponding to src, in the visited array as false, after we explore all possibilities through the src vertex.
*/
public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf){
       if(src == dest){
           System.out.println(psf);
           return;
       }
       visited[src] = true;
       for(Edge edge: graph[src]){
           if(visited[edge.nbr] == false){
               printAllPaths(graph, edge.nbr, dest, visited, psf + edge.nbr);
           }
       }
       visited[src] = false;
   }

//Multisolver - Smallest, Longest, Ceil, Floor, Kthlargest Path
/*
1. You are given a graph, a src vertex and a destination vertex.
2. You are give a number named "criteria" and a number "k".
3. You are required to find and print the values of 
3.1 Smallest path and it's weight separated by an "@"
3.2 Largest path and it's weight separated by an "@"
3.3 Just Larger path (than criteria in terms of weight) and it's weight separated by an "@"
3.4 Just smaller path (than criteria in terms of weight) and it's weight separated by an "@"
3.5 Kth largest path and it's weight separated by an "@"

Shortest path:
When src and destination vertices are equal, 
   if the weight so far (wsf) is less than the smallest path weight (spathwt) then the value of spathwt gets updated to the value of wsf. 
   Hence, our smallest path (spath) is the path so far (psf).


Largest path:
When src and destination vertices are equal, 
if the weight so far (wsf) is greater than the largest path weight (lpathwt) then the value of lpathwt gets updated to value of wsf. 
Hence, our largest path (spath) is now the path so far (psf).

Ceil path:
To find the ceil path, we need to find the smallest weight out of all the weights larger than the criteria. 
To do so, we first need to check if the weight so far (wsf) is greater than the given criteria. 
Also, wsf needs to be smaller than the current ceil path weight. 
If these conditions are fulfilled, then cpathwt is updated to this wsf and cpath becomes the path so far (psf).

Floor path:
To find the floor path, we need to find the largest weight out of all the weights smaller than the criteria. 
To do so, we first need to check if the weight so far (wsf) is smaller than the given criteria. 
Also, wsf needs to be larger than the current floor path weight. 
If these conditions are fulfilled, then fpathwt is updated to this wsf and fpath becomes the path so far (psf).

Kth largest path:
To find the kth largest path we make use of Priority Queue.
In a Priority Queue, the numbers can be added in any order but those numbers which have the highest priority are removed from first.
By default, priority queue is a minimum priority queue i.e. the minimum number out of all the elements in the queue will be removed first.
In our problem, if k=3, then the priority queue must always have 3 elements in it.
So, when we keep removing the minimum number from the priority queue while traversing through all the elements, 
at last the priority queue will store the 3 largest elements in it. 
At this point, the element that will be removed will be the smallest of the 3 largest elements, 
hence it is the 3rd largest number out of all the elements.

Kth LARGEST -> PRIORITY QUEUE(MIN) ALWAYS!
*/
   static String spath;
   static Integer spathwt = Integer.MAX_VALUE;
   static String lpath;
   static Integer lpathwt = Integer.MIN_VALUE;
   static String cpath;
   static Integer cpathwt = Integer.MAX_VALUE;
   static String fpath;
   static Integer fpathwt = Integer.MIN_VALUE;
   static PriorityQueue<Pair> pq = new PriorityQueue<>();
   public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k, String psf, int wsf) {
      if(src == dest){
          //smallest path
          if(wsf < spathwt){
              spathwt = wsf;
              spath = psf;
          }
          //largest path
          if(wsf > lpathwt){
              lpathwt = wsf;
              lpath = psf;
          }
          //just larger path a.k.a ceil
          if(wsf > criteria && wsf < cpathwt){
              cpathwt = wsf;
              cpath = psf;
          }
          //just smaller path a.k.a. floor
          if(wsf < criteria && wsf > fpathwt){
              fpathwt = wsf;
              fpath = psf;
          }
          //kth largest path
          if(pq.size() < k){
              pq.add(new Pair(wsf, psf));
          }else{
              if(wsf > pq.peek().wsf){
                  pq.remove();
                  pq.add(new Pair(wsf, psf));
              }
          }
          return;
      }
      
      visited[src] = true;
      for(Edge e : graph[src]){
          if(visited[e.src] == false){
              multisolver(graph, e.nbr, dest, visited, criteria, k, psf + e.nbr, wsf + e.wt);
          }
          
      }
      return false;
   }

   static class Pair implements Comparable<Pair> {
      int wsf;
      String psf;

      Pair(int wsf, String psf){
         this.wsf = wsf;
         this.psf = psf;
      }

      public int compareTo(Pair o){
         return this.wsf - o.wsf;
      }
   }

//Get Connected Components Of A Graph
/*
You are required to find and print all connected components of the graph.

We will apply DFS for every unvisited vertex of the graph.
While we encounter any vertex that has not already been visited, we will mark it visited and add it to the ArrayList.
Once we complete the traversal of one component, we will add that component's arraylist to our answer ArrayList of ArrayList.

Time Complexity:
The time complexity of the above code is O(V) as we are going to visit every vertex exactly once.
Space Complexity:
The space complexity of the above code is O(h) where h is the height of the recursion stack.
*/
 public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      ArrayList<ArrayList<Integer>> comps = new ArrayList<>();//comps -> components
      boolean[] visited = new boolean[vtces];
      for(int v = 0; v < vtces; v++){
          if(visited[v] == false){
              ArrayList<Integer> comp = new ArrayList<>(); //comp -> component
              drawTreeAndGenerateComp(graph, v, comp, visited);
              comps.add(comp);
          }
      }
      System.out.println(comps);
   }
   
   public static void drawTreeAndGenerateComp(ArrayList<Edge>[] graph, int src, ArrayList<Edge> comp,boolean[] visited){
       visited[src] = true;
       comp.add(src);
       for(Edge e : graph[src]){
           if(visited[e.nbr] == false){
               drawTreeAndGenerateComp(graph, e.nbr, comp, visited);           }
       }
   }

//Is Graph Connected
/*
You are required to find and print if the graph is connected (there is a path from every vertex to every other).
As discussed in the previous question, we find out the number of connected components of a graph. 
If the number of components is 1, then the graph is said to be connected, else it is not connected.
*/
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int vtces = Integer.parseInt(br.readLine());
    ArrayList<Edge>[] graph = new ArrayList[vtces];
    for (int i = 0; i < vtces; i++) {
      graph[i] = new ArrayList<>();
    }

    int edges = Integer.parseInt(br.readLine());
    for (int i = 0; i < edges; i++) {
      String[] parts = br.readLine().split(" ");
      int v1 = Integer.parseInt(parts[0]);
      int v2 = Integer.parseInt(parts[1]);
      int wt = Integer.parseInt(parts[2]);
      graph[v1].add(new Edge(v1, v2, wt));
      graph[v2].add(new Edge(v2, v1, wt));
    }

    ArrayList<ArrayList<Integer>> comps = new ArrayList<>();//comps -> components
    boolean[] visited = new boolean[vtces];
    for (int v = 0; v < vtces; v++) {
      if (visited[v] == false) {
        ArrayList<Integer> comp = new ArrayList<>(); //comp -> component
        drawTreeAndGenerateComp(graph, v, comp, visited);
        comps.add(comp);
      }
    }
    System.out.println(comps.size() == 1);////check condition here
  }

  public static void drawTreeAndGenerateComp(ArrayList<Edge>[] graph, int src, ArrayList<Edge> comp, boolean[] visited) {
    visited[src] = true;
    comp.add(src);
    for (Edge e : graph[src]) {
      if (visited[e.nbr] == false) {
        drawTreeAndGenerateComp(graph, e.nbr, comp, visited);
      }
    }
  }

//Number Of Islands
/*
 You are given a 2d array where 0's represent land and 1's represent water. 
     Assume every cell is linked to it's north, east, west and south cell.
 You are required to find and count the number of islands.

Since the input is not given to us in a typical graph representation, we assume every cell of the 2d array as a vertex. 
We also assume that every cell is connected to its north, east, west and south cell.
Hence, each of these connections is considered an edge.

We create a new Boolean 2d array "visited" which stores whether a cell has been visited or not. 
Initially all the cells of this array are marked "false". 
We also initialize a "count" variable which stores the number of islands in the graph with 0.
For every cell of the input array, we check if that cell represents "land" and it hasn't been visited before. 
If the cell fulfills both these conditions, then we call the drawTreeforComponent() function on it and increase the "count" by 1.
The total "count" of islands in the graph is printed at the end of the main function.
BASE CASE: In the drawTreeforComponent() function, 
if we traverse out of the input grid (i.e. row<0 or column<0 or 
row>=arr.length or column>=arr[0].length)
or if we reach the "water" cell (arr[i][j]==1)
or if we have already visited that cell earlier (visited[i][j]==true),
then we simply return from the function.
Before visiting a cell, we mark it as "true" so that we don't visit it again.
We check whether cells to the north, east, west and south 
   to the given cell depict the required island or not by recursively calling drawTreeforComponent() function on them.

Dear reader, in this code, we made an intelligent base case for the drawTreeforComponent() function and made "reactive" recursion calls. 
This means we simply make the recursion calls and if faced by any obstacle, the base case deals with it.

Time Complexity:O(4*n2)
O(4*n2) which is simply written as O(n2)
This is because each cell of the matrix is processed at most 4 times. For Example, a particular cell can call a cell to its north, east, west or south.

Space Complexity:O(n2)
Since a 2D array is used to store "visited" elements hence the space complexity is quadratic.
*/
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int m = Integer.parseInt(br.readLine());
      int n = Integer.parseInt(br.readLine());
      int[][] arr = new int[m][n];

      for (int i = 0; i < arr.length; i++) {
         String parts = br.readLine();
         for (int j = 0; j < arr[0].length; j++) {
            arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
         }
      }

      // write your code here
      boolean[][] visited = new boolean[arr.length][arr[0].length];
      int count = 0;
      //traversing each cell in 2d array/matrix
      for(int i = 0; i < arr.length; i++){
          for(int j = 0; j < arr[0].length; j++){
              if(arr[i][j] == 0 && visited[i][j] == false){
                  drawTreeForComponent(arr, i, j, visited);
                  count++; 
              }
          }
      }
      System.out.println(count);
   }
   public static void drawTreeForComponent(int[][] arr, int i, int j; boolean[][] visited){
       if(i < 0 || j < 0 || i >= arr.length || j >= arr[0].length 
          || visited[i][j] == true || arr[i][j] == 1){
           return;
       }
       visited[i][j] = true;
       drawTreeForComponent(arr, i-1, j, visited);
       drawTreeForComponent(arr, i, j+1, visited);
       drawTreeForComponent(arr, i, j-1, visited);
       drawTreeForComponent(arr, i+1, j, visited);
   }

//Perfect Friends
/*
In this problem you are given a number n (representing the number of students). Each student will have an id from 0 to n - 1.
You are also given a number k (representing the number of pairs of students, clubbed together).
In the next k lines, two numbers are given separated by a space. The numbers are ids of students belonging to the same club.
You have to find in how many ways can we select a pair of students such that both students are from different clubs.

We start with making a graph. For making a graph, we need to define the edge.
In previous lectures of Graphs, we used to have 3 data members for edge, 
but here we can drop the idea of weight as the data member because it's not required.
So, in the new edge, we will only keep vertices and neighbors. And then define a constructor for this edge class.
After this in main, n, which represents the number of students, also represents the number of vertices in the graph.
We define an "n" length Array List of arrays (of type edge) and name it graph.
Then we apply a for loop on the graph's length to put an array list at each of it's index.
K, which represents the number of pairs, also represents the number of edges in the graph.
So, to set these edges in the graph, we need to run a for loop k times.
And in each iteration we collect and interpret the input from each line, which represents the pairs of students in the same club.
To do so we define an array of strings, parts and using the readline function of buffer reader, we split the line when we find a space.
Then we collect both the number representing vertices and which are now stored in the array parts, in some variables say v1 and v2.
Then finally we add the edges at v1 and v2 indices of the graph.
After that we use the same logic that we discussed in Get Connected Components (GCC).
After applying gcc logic we will get the connected components of the graph stored in an array list of arraylists, comps.
Each connected component represents each club. It means comps store all clubs and their members.
Now to get our final answer, we need to do some simple math in main.
Initialize a variable count to 0. 
Then we run a for loop on the array list storing connected components initializing i=0. And a nested for loop, initializing j with i+1.
In each iteration, we update count with the product of, sizes of clubs stored at index i and j.
Sizes of clubs are basically size of arraylist at ith index in components and size of arraylist at jth index in components.

Time Complexity:O(V+E)
Because the DFS approach has been used.
*/
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int n = Integer.parseInt(br.readLine());//n = no of vtces
      int k = Integer.parseInt(br.readLine());//k = no of edges
      
      // write your code here
      //making graph
      ArrayList<Edge>[] graph = new ArrayList[n];
      for(int i = 0; i < n; i++){
          graph[i] = new ArrayList<Edge>;
      }
      //adding elements in graph
      for(int e = 0; e < k; e++){
          String line = br.readLine();
          String[] parts = line.split(" ");
          
          int v1 = Integer.parseInt(parts[0]);
          int v2 = Integer.parseInt(parts[1]);
          
          graph[v1].add(new Edge(v1, v2));
          graph[v2].add(new Edge(v2, v1));
      }
      //getting connected components list
      boolean[] visited = new boolean[n];
      ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
      for(int v = 0; v < n; v++){
          if(visited[v] == false){
              ArrayList<Integer> comp = new ArrayList<>();
              getConnectedComp(graph, v, comp, visited);
              comps.add(comp);
          }
      }
      //calculating no. of pairs
      int pairs = 0;
      for(int i = 0; i < comps.size(); i++){
          for(int j = i + 1; j < comps.size(); j++){
              int count = comps.get(i).size() * comps.get(j).size();
              pair += count;
          }
      }
      System.out.println(pair);
   }
   public static void getConnectedComp(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited){
       visited[src] = true;
       comp.add(src);
       
       for(Edge e : graph[src]){
           if(e.nbr == false){
               getConnectedComp(graph, e.nbr, comp, visited);
           }
       }
   }


//Hamiltonian Path And Cycle
/*
1. You are given a graph and a src vertex.
2. You are required to find and print all hamiltonian paths and cycles starting from src. The cycles must end with "*" and paths with a "."

Note -> A hamiltonian path is such which visits all vertices without visiting any twice.
        A hamiltonian path becomes a cycle if there is an edge between first and last vertex.
Note -> Print in lexicographically increasing order.

In Print All Paths, we used to find the path between a given source and destination vertex. 
Each time we visited any vertex, we used to set the value corresponding to the vertex as true in the visited array.
And when we were done exploring all neighbor's of this vertex then we again set the value as false corresponding to this vertex in the visited array.
Also if the base case was hit, that is when source becomes equal to destination, we printed the path so far.
We will follow a similar approach in this problem as well.
But this time the base case will change.
Since we need to visit each vertex of the graph this time, the base case will become the stage where all vertices have been visited.
Also, also, also, we need to check that the path obtained is Hamiltonian Path or Hamiltonian Cycle.
For doing so, we check whether there is an edge between source and last visited vertex.
If there is edge than it is Hamiltonian Cycle and we print, "*". And if there is no edge then it is the Hamiltonian Path and we print, ".".

Time Complexity:O(V+E)
Because of the DFS approach.
*/
public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      int src = Integer.parseInt(br.readLine());

      // write all your codes here
      HashSet<Integer> visited = new HashSet<>();
      pathAndCycle(graph, src, src + "", visited, src);
      
   }
    public static void pathAndCycle(ArrayList<Edge>[] graph, int src, String psf, HashSet<Integer> visited, int osrc){
        if(visited.size() == graph.length - 1){
            System.out.print(psf);
            boolean connected = false;
            for(Edge e : graph[src]){
                if(e.nbr == osrc){
                    connected = true;
                    break;
                }
            }
            if(connected == true){
                System.out.println("*");
            }else{
                System.out.println(".");
            }
            return;
        }
        visited.add(src);
        for(Edge e : graph[src]){
            if(visited.contains(e.nbr) == false){
                pathAndCycle(graph, e.nbr, psf + e.nbr, visited, osrc);
            }
        }
        visited.remove(src);
    }

//Knights Tour
/*
1. You are given a number n, the size of a chess board.
2. You are given a row and a column, as a starting point for a knight piece.
3. You are required to generate the all moves of a knight starting in (row, col) such that knight visits all cells of the board exactly once.
4. Complete the body of printKnightsTour function - without changing signature - to calculate and 
    print all configurations of the chess board representing the route of knight through the chess board. Use sample input and output to get more idea.

Note -> When moving from (r, c) to the possible 8 options give first precedence to (r - 2, c + 1) and move in clockwise manner to explore other options.

We will use the levels and options technique that we have used in many problems earlier as well.

Since we are using the levels and options method, we have to decide what options we have at a particular level.
A knight can move in 8 possible directions if those moves stay inside the chess board as shown in fig-1 and fig-2.
We will apply the recursive calls at each of these levels and the further exploration will be carried out by recursion.
We will also have to think about the base cases. 
Obviously, when we move out of the chess board then we have to return from that particular recursive call. 
Also, we have to return from the recursive call if we come across a box which has already been visited

How many moves will be there? We have to visit every block of the chessboard of size nxn. So, there will be nxn moves. 
So, when we reach nxn move, we will put the move value in the corresponding row and column (that we have been doing so far) 
and then we will print the board.

So, the base cases or the exceptional cases that we handled are:
If we reach out of the chess board, return
If we reach an already visited block, return
If we are at the last move, put the move in the chessboard and display the board.

Time Complexity:
The time complexity of this solution is O(8n^2) since there are n2 cells and for each we have a maximum of 8 possible moves.
Space Complexity:
The space complexity is O(1) without considering the recursion space and it is O(h) if we consider the recursion space, 
here h is the height of the stack (max height of the recursion stack).
*/
public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        
        int[][] chess = new int[n][n];
        printKnightsTour(chess, r, c, 1);
    }

    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {
        if(r < 0 || c < 0 || r >= chess.length || c >= chess.length || chess[r][c] > 0){
            return;
        }else if(upcomingMove == chess.length * chess.length){
            chess[r][c] = move;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }
        chess[r][c] = upcomingMove;
        printKnightsTour(chess, r - 2, c + 1, upcomingMove + 1);
        printKnightsTour(chess, r - 1, c + 2, upcomingMove + 1);
        printKnightsTour(chess, r + 1, c + 2, upcomingMove + 1);
        printKnightsTour(chess, r + 2, c + 1, upcomingMove + 1);
        printKnightsTour(chess, r + 2, c - 1, upcomingMove + 1);
        printKnightsTour(chess, r + 1, c - 2, upcomingMove + 1);
        printKnightsTour(chess, r - 1, c - 2, upcomingMove + 1);
        printKnightsTour(chess, r - 2, c - 1, upcomingMove + 1);
        chess[r][c] = 0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

//Breadth First Traversal
/*
REMOVE MARK WORK ADD

1. You are given a graph, and a src vertex.
2. You are required to do a breadth first traversal and print which vertex is reached via which path, 
     starting from the src.

 Which data structure we used to print the level-order of a generic tree and a binary tree? 
 Yes, we used the queue data structure and we implemented the algorithm "remove-print-add".
 Here we will perform the following steps: r m* w a*

r --> remove w --> work

m --> mark a --> add
Now, the first step from r m* w a* i.e. remove will be carried out. 
So, we will remove the element from the queue and we mark the vertex 2 with a star depicting that we have visited it.

The next step is w i.e. work. What is the work that we have to do here? We have to print. 
So, that is what we will do. Now, the last step is to add. 
We have to add the neighbors of this vertex i.e. vertex 2 to the queue and we have to add only those vertices which are not marked. 
The thing which we want to convey is that if we do not take care of the order in which we are inserting the vertices into the queue, 
we will still get Breadth First Traversal of the tree, but it would be different.

Time Complexity:
The adding and removing of elements from the queue takes O(1) time. 
Since we are traversing every vertex of the graph to print its breadth first traversal, the time taken will be O(n).

Space Complexity:
Since we have used a queue data structure, the space complexity of the above method is O(n)
*/
static class Pair{
       int v;
       String psf;
       
       Pair(int v, String psf){
           this.v = v;
           this.psf = psf;
       }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         graph[v1].add(new Edge(v1, v2));
         graph[v2].add(new Edge(v2, v1));
      }

      int src = Integer.parseInt(br.readLine());

      // write your code here  
      boolean[] visited = new boolean[vtces];
      ArrayDeque<Pair> queue = new ArrayDeque<>();
      queue.add(new Pair(src, src + ""));
      
      while(queue.size() > 0){
          Pair rem = queue.removeFirst();
          if(visited[rem.v] == true){
              continue;
          }
          visited[rem.v] = true;
          System.out.println(rem.v + "@" + rem.psf);
          for(Edge e : graph[rem.v]){
              if(visited[e.nbr] == false){
                  queue.add(new Pair(e.nbr, rem.psf + e.nbr));
              }
          }
      }
   }

//Is Graph Cyclic
/*
You are required to find and print if the graph is cyclic.
Print true if there exists a cycle, else false.
Note: Input is given in the form of adjacency list.
Note: Graph may or may not be connected.

What does it mean, when we say that there is a cycle present in the graph?
It means that there exists a pair of vertices (u, v) such that there is not an unique path from node u to node v. 
In simple words, there exists at least two different paths of reaching node v from the node u.
To find if there exists such two paths, we can do a BFS traversal of the given graph. For every visited vertex "v", we will push all the neighbouring vertices "u" into the queue.
Now, here is the crux of the algorithm. 
If the neighboring vertex is already visited, then there exists at least two different paths from node v to node u. 
It is because we were able to visit the vertex u, as it must have been popped out from the queue earlier.
Now, since there exists two different paths from node v to node u, there is a cycle present in the graph, (and nodes u and v must be part of that cycle).

Time Complexity:
We are doing a simple BFS traversal of the graph. Hence the time complexity will be O(N + E) where N = number of vertices and E = number of edges.

Space Complexity:
We are building a visited array and a parent array of size equal to the number of vertices. Also, we are taking the queue data structure to perform BFS traversal,
which will store at max n nodes.
Hence, the total space complexity will be O(n + n + n) = O(n) only.
Please note that we are not taking into account the space taken to build the adjacency list, as it was given to us as an input.
*/
      public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      // write your code here
      boolean[] visited = new int[vtces];
      for(int i = 0; i < vtces; i++){
          if(visited[i] == false){
              boolean cycle = isCyclic(graph, i, visited);
              if(cycle == true){
                  System.out.println("true");
                  return;
              }
          }
      }
      System.out.println("false");
   }
   static class Pair{
       int v;
       String psf;
       
       Pair(int v, String psf){
           this.v = v;
           this.psf = psf;
       }
   }
   public static boolean isCyclic(ArrayList<Edge> graph, int src, boolean[] visited){
       ArrayDeque<Pair> q = new ArrayDeque<>();
       q.add(new Pair(src, src + ""));
       
       while(q.size() > 0){
           Pair rem = q.removeFirst();
           if(visited[rem.v] == true){
               return true;
           }
           visited[rem.v] = true;
           for(Edge e : graph[rem.v]){
               if(visited[e.nbr] == false){
                    q.add(new Pair(e.nbr, rem.psf + e.nbr));
               }
           }
       }
       return false;
   }

//Is Graph Bipartite
/*
You are required to find and print if the graph is bipartite
Note -> A graph is called bipartite if it is possible to split it's vertices in two sets of mutually exclusive and exhaustive vertices such that all edges are across sets.

According to the definition, we need to distribute vertices into two subsets which are mutually exclusive:

No vertex should appear in both the sets, or the intersection of sets should be empty.
Also, they need to be exhaustive:

Every vertex should appear in either one of the two sets, or the union of sets should be the entire set {0, 1, 2, ... n-1}.
Let us first analyze WHAT we will have to do in the algorithm.

If the graph is acyclic, i.e. there is no cycle present in the graph, then the graph is always bipartite.
If there exists any one cycle of odd length, i.e. the number of vertices in the cycle are odd in counting, then the graph is not bipartite.
If all the cycles (if present) are of even length, i.e. the number of vertices in each cycle are even in counting, then the graph is bipartite.
The algorithm, which we are going to use to check bipartiteness of a graph is known as graph-coloring algorithm, which uses BFS traversals. 
A graph is bipartite if and only if it is two-colorable.
We will start a breadth-first traversal, starting from each vertex which hasn't been visited yet. 
But, instead of just pushing the node's value into the queue, we will also push a visiting time of the node.
We will try to add the nodes with even visiting time in the first set, and the nodes with odd visiting time in the second set.
We know already that during the bfs traversal, if we find any node which is already visited, then there exists a cycle.
If the length of cycle is even, then the visiting time of the last node will be the same from both the paths 
   (if even from the first path, then it will be even from the second path as well, and vice-versa).
Else, since the length of cycle is odd, the visiting time of the last node in the cycle will be different from both the paths 
   (if even from the first path, then it will be odd from the second path, and vice-versa).
Hence, we cannot put the last node in either the first set or the second set. Thus, the graph will not be bipartite in an odd-length cycle case.

Time Complexity:
We are simply doing a BFS traversal of the entire graph, which will take O(N + E) time, where N = number of vertices, and E = number of edges.
Space Complexity:
We are using a queue data structure for the BFS traversal, which will store at max N vertices. Hence, the space complexity is O(N).
Please note that we are not taking into account the space taken to build the adjacency list, as it was given to us as an input.
*/
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      // write your code here
      int visited[] = new int[vtces];
      Arrays.fill(visited, -1);
      for(int v = 0; v < vtces; v++){
          if(visited[v] == -1){//means not visited
              boolean isComponentBipartite = isGraphBipartite(graph, v,  visited);
              if(isComponentBipartite == false){
                  System.out.println(false);
                  return;
              }
          }
      }
      System.out.println(true);
   }
   static class Pair{
       int v;
       String psf;
       int level;
       Pair(int v, String psf, int level){
           this.v = v;
           this.psf = psf;
           this.level = level;
       }
   }
   public static boolean isGraphBipartite(ArrayList<Edge>[] graph, int src, int[] visited){
       ArrayDeque<Pair> q = new ArrayDeque<>();//bfs uses queue
       q.add(new Pair(src, src + "", 0));
       
       while(q.size() > 0){//traversing bfs using queue
           Pair rem = q.removeFirst();
           
           if(visited[rem.v] != -1){//means already visited
               if(rem.level != visited[rem.v]){//coming level not same as previous level; i.e. odd size of cycle, in which bipartite not possible
                   return false;
               }
           }else{
               visited[rem.v] = rem.level;
           }
           for(Edge e: graph[rem.v]){
               if(visited[e.nbr] == -1){//nbr not visited
                   q.add(new Pair(e.nbr, rem.psf + e.nbr, rem.level + 1));
               }
           }
       }
       return true;
   }

//Spread Of Infection
/*
Given an undirected graph representing people and their connectivity. 
Vertices 0 to n-1 represent different people and edge (u, v) represents that there can be a direct contact between persons u and v.
You are given a src person, who got infected by a disease at time t = 0.
You are also given the total time t during which the infection can spread among the people.
If a person is infected, then it takes 1 unit of time to make all of it's connections get infected.
You need to find the count of people who can get infected by the disease within the time t.
Note: Input is given in the form of adjacency list.

The idea is to use a BFS traversal algorithm with the source node as the src.
Along with the source node's value, we will also push the visiting time t = 0.
We will maintain a count integer variable set to 0 initially, which will finally store the result, i.e. number of infected people within the total time of spread of infection.
We will run the BFS, until the queue becomes empty (all nodes get visited), or the visiting time of the front element becomes greater than the total time given.
If the node is already visited (cycle exists and the node was visited with a smaller visiting time), 
   then we will simply pop and continue for the next element (do not visit it's neighbours).
Otherwise, we will mark the current node as visited. 
For each element, who's visiting time < total time, which gets removed (popped) from the queue, we will increment the count by 1.
Also, we will break from the BFS traversal, if the front element's visiting time is greater than the total time of spread.
Note: We can simply break because all the nodes after the front element present in the queue, 
   will have visiting time greater than (or equal to) the visiting time of the front element. 
   Since, the front element has time > t, hence all the elements after it will also have time > t.
Now, we will push all the unvisited neighbours of the current node with visiting time one more than the visiting time of the current node. 
   (If visiting time of src is x, then it's neighbours will have visiting time x + 1).
Finally, when the BFS algorithm is successfully completed, we can simply print the value of count.

Time Complexity:
We are simply doing a BFS traversal, hence the time complexity will be O(N + E) where N = number of vertices in the graph and E = number of edges in the graph.
Space Complexity:
To perform BFS, we use queue data structure, which will take O(N) auxiliary space.
Please note that we are not taking into account the space taken to build the adjacency list, as it was given to us as an input.
*/
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         graph[v1].add(new Edge(v1, v2));
         graph[v2].add(new Edge(v2, v1));
      }

      int src = Integer.parseInt(br.readLine());
      int t = Integer.parseInt(br.readLine());
      
      // write your code here
      int[] vis = new int[vtces];
      Arrays.fill(vis, 0);
      ArrayDeque<Pair> q = new ArrayDeque<>();
      q.add(new Pair(src, 1));
      int count = 0;
      
      while(q.size() > 0){
          Pair rem = q.removeFirst();
          if(vis[rem.v] != 0){//already vis;
              continue;
          }
          vis[rem.v] = rem.time;
          if(rem.time > t){//time for current pair exceeds given 't' time, then break from loop
              break;
          }
          count++;
          for(Edge e, graph[rem.v]){
              if(vis[e.nbr] == 0){//i.e. not visited nbr
                  q.add(new Pair(e.nbr, rem.time + 1));
              }
          }
      }
      System.out.println(count);
   }
   static class Pair{
       int v;
       int time;
       
       Pair(int v, int time){
           this.v = v;
           this.time = time;
       }
   }

//Shortest Path In Weights Dijkstra's Algorithm
/*
The algorithm is very intuitive, as it follows a Greedy Approach. The algorithm is also known as "Single Source Shortest Paths Algorithm".

You are given an undirected graph and a source vertex. The vertices represent cities and the edges represent distance in kms.
The graph is weighted in nature. The weights of all edges are non-negative.
You are required to find the shortest path to each city (in terms of kms) from the source city along with the total distance on the path from source to destinations.
Note: Input is given in the form of adjacency list. 

Please note that this problem is different from Single Source Source Shortest Path in terms of count of edges (in unweighted graph).
For the shortest path in terms of edge count, we had used BFS traversal in the previous problem "Spread of Infection" .
Here, we need to find the shortest path in terms of weight. The shortest path may (or may not) be longer in terms of edge count.
In the above example, the shortest weighted path from 0 to 1 is equal to 10 via "02341", which is longer from the path "01".
You will be amazed to read that, we just need a slight modification to our BFS algorithm, so that it can find the shortest path in terms of weight.
Can you guess where we need to tweak?
In BFS traversal, we used a queue data structure. But since we can only maintain FIFO order in the queue, we cannot get the shortest path in terms of weight.
For Dijkstra's algorithm, we just need to replace the queue with a priority queue, and that's it.
You should be able to analyze that the Priority Queue pops out the element with higher priority among all the candidates.
We should have a path with the shortest distance as the highest priority. 
Hence, we will declare a Pair class, which will have a weight so far, storing the maximum distance of that node from source vertex.
But, how will our priority queue be able to decide which Pair object has higher priority?
So, our Pair class must implement a comparable interface. 
Please refer to the "Comparable vs Comparator" video of "Priority Queue" if you do not know how to define a custom comparator for user defined classes. 
We will define the compareTo function which compares the values of weight so far (wsf).

Time Complexity:
This is an interesting analysis. The time complexity of Dijkstra's algorithm will be O(E + V logV) where V = number of vertices and E = number of edges.
This is because, we are iterating over all the edges once during the entire run of the algorithm 
In each iteration, we are popping one node and pushing the unvisited neighbour nodes. 
Since the priority queue can contain all the vertices, the push or pop operation will be O(log V).
Hence the total time complexity will be O(E) + O(V) * O(log V) = O(E + V log V).
Note: You can argue that we might be having multiple Pairs having the same node's value. So, the maximum size of the priority queue will be not O(N) but O(E).
But, even if you replace log V with Log E (cost of one push/pop operation), then there will be no difference in the time complexity as:

O(E + VlogE) = O(E + Vlog(V^2)) = O(E + 2V logV) = O(E + V logV) only.
Space Complexity:
We are taking a priority queue of Pair nodes. Hence, the space complexity will be O(N) where N = maximum Pair nodes in the queue, which is equivalent to O(V).
*/
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      int src = Integer.parseInt(br.readLine());
      // write your code here
      
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      pq.add(new Pair(src, src + "", 0));
      boolean[] vis = new boolean[vis];
      
      while(pq.size() > 0){
          Pair rem = pq.remove();
          
          if(vis[rem.v] == true){//already visited
              continue;
          }
          vis[rem.v] = true;
          
          System.out.pritln(rem.v + " via " + rem.psf + " @ " + rem.wsf);
          
          for(Edge e: graph[rem.v]){
              if(vis[e.nbr] == false){//unvisited nbrs
                  pq.add(new Pair(e.nbr, rem.psf + e.nbr, rem.wsf + e.wt));
              }
          }
      }
      
   }
   static class Pair implements Comparable<Pair>{
       int v;
       String psf;
       int wsf;
       Pair(int v, String psf, int wsf){
           this.v = v;
           this.psf = psf;
           this.wsf = wsf;
       }
       public int compareTo(Pair o){
           return this.wsf - o.wsf;
       }
   }

//
/*

*/





