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

//
