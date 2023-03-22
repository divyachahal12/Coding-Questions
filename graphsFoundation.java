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

