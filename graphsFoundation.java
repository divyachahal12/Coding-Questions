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

//
