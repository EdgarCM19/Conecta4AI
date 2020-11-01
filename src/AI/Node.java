package AI;
import java.util.ArrayList;
import java.util.Collection;

 

import logic.Board;

 


public class Node {
    public ArrayList<Node> nodes;
    public Board data;
    public float factor;
    public int mov;
    public int depth;
    
    public Node(Board data,int depth,int mov) {
        this.data=data;
        this.depth=depth;
        this.mov=mov;
        nodes = new ArrayList<Node>();
    }
    
    public void createChild(char player,int depth) {
        for (int i =0;i<7; i++) {
        	if(data.canAdd(i)) {
                char[][] newBoard = copyBoard();
                Board tem = new Board(newBoard,data.pieces);
                tem.addPiece(i, player);
                nodes.add(new Node(tem,depth,-1));
            }
        }
        //System.out.print("A quie se queda");
    }
    
    public void imprimmirHijos() {
    	for(int i=0;i<nodes.size();i++) {
    		System.out.println("Hijo: "+i);
    		nodes.get(i).print();
    	}
    }
    
    public void printTree() {
    	printNodeTree(this);
    }
    
    public void printNodeTree(Node n) {
    	if(Evaluation.isMeta(n.data))
    		System.out.println("#####ESTE ES META");
    	System.out.println("-+-+-+-+-+-+-+-+-+-+-+-");
    	System.out.println("Nodo Padre: ");
    	System.out.println(n);
    	System.out.println("Nodos hijos: ");
    	for(int i = 0; i < n.nodes.size(); i++) {
    		System.out.println("[NODO HIJO No " + i + "]");
    		System.out.println(n.nodes.get(i));
    	}
    	for(Node node : n.nodes)
    		printNodeTree(node);
    }
    
    public void selectChildren() {
        
    }
    
    public void createTree(Node nodo,int depth,char player,int horizon) {
        if(nodo.depth != horizon /*&& !Evaluation.isMeta(nodo.data)  */ ) {
        	char c = (player == 'A') ? 'P' : 'A';
            nodo.createChild(c,nodo.depth+1,nodo.data);        	 
             for(int i=0;i<nodo.nodes.size();i++){
            	 System.out.println("Siguen en el for");
                 createTree(nodo.nodes.get(i),nodo.nodes.get(i).depth,c,horizon);    
             }
        }
    }
    
    public void createTreeN(Node node, int depth, char player, int horizont) {
    	if(node.depth < horizont && !Evaluation.isMeta(node.data)) {
    		System.out.println("holis");
    		char c = (player == 'A') ? 'P' : 'A';
    		for (int column = 0; column < node.data.pieces.length; column++) {
				if(node.data.canAdd(column)) {
					System.out.println("Alooo?");
					Board temp = new Board(copyBoard(node.data));
					temp.addPiece(column, c);
					Node ntemp = new Node(temp, depth + 1, column); 
					nodes.add(ntemp);
					if(!Evaluation.isMeta(ntemp.data)) {
						createTreeN(ntemp, depth + 1, c, horizont);
					} /* else {
						System.out.println("}{}}{}{{}{{}{}{}{}{}}{{}{}");
						System.out.println(ntemp);
						for (int columnb = 0; columnb < node.data.pieces.length; columnb++) {
							if(node.data.canAdd(columnb)) {
								System.out.println("La columna: " + columnb + " entro a la creacion");
								Board temp2 = new Board(copyBoard(ntemp.data), ntemp.data.pieces);
								temp2.addPiece(columnb, player);
								temp2.print();
							}
						}
						System.out.println("}{}}{}{{}{{}{}{}{}{}}{{}{}");
					} */
				}
			}
    	}
    }
    
    public void createChild(char player,int depth,Board datas) {
    	for (int i =0;i<7; i++) {
            if(data.canAdd(i)) {
                char[][] newBoard = copyBoard(datas);
                Board tem = new Board(newBoard,data.pieces);
                tem.addPiece(i, player);
                nodes.add(new Node(tem,depth,-1));
            }
        }
    }
    
    private char[][] copyBoard(){
        char[][] aux = new char[6][7];
        for(int i=0;i<6;i++) {
            for(int j=0;j<7;j++) {
                aux[i][j] = data.board[i][j];
            }
        }
        return aux;
    }
    
    private char[][] copyBoard(Board matriz){
        char[][] aux = new char[6][7];
        for(int i=0;i<6;i++) {
            for(int j=0;j<7;j++) {
                aux[i][j] = matriz.board[i][j];
            }
        }
        return aux;
    }
    
    public void print() {
    	System.out.println(toString());
    }
    
    @Override
    public String toString() {
    	return "Tablero: \n" + data.toString() + "Factor: \n" + this.factor + "\n Profundidad: " + this.depth; 
    }
}