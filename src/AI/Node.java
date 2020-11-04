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
    public int best;
    
    public Node(Board data,int depth,int mov) {
        this.data=data;
        this.depth=depth;
        this.mov=mov;
        this.best = -1;
        nodes = new ArrayList<Node>();
    }
    
    public void setBest(int a) {
    	this.best = a;
    }
    
    public Node() {
    	this.data=null;
        this.depth = 0;
        this.mov = -1;
    	this.factor = -1.0f;
    	this.nodes = new ArrayList<Node>();
    }
    
    public Node(float factor, int depth) {
    	this.data=null;
        this.depth = depth;
        this.mov = -1;
    	this.factor = factor;
    	this.nodes = new ArrayList<Node>();
    }
    
    
    public void setFactor(float factor) {
    	this.factor = factor;
    	
    }
    
    public void printResult(Node n) {
    	
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
    	//if(Evaluation.isMeta(n.data))
    		//System.out.println("#####ESTE ES META");
    	System.out.println("-+-+-+-+-+-+-+-+-+-+-+-");
    	System.out.println("Nodo: ");
    	System.out.println(n);
    	//System.out.println("Nodos hijos: " + n.nodes.size());
    	/*
    	for(int i = 0; i < n.nodes.size(); i++) {
    		System.out.println("[NODO HIJO No " + i + "]");
    		System.out.println(n.nodes.get(i));
    	}
    	*/
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
    
    public Node getMove(int column, char player) {
    	Board b_temp = new Board(copyBoard());
    	b_temp.addPiece(column, player);
    	Node temp = new Node(b_temp, 0, column);
    	return temp;
    }
    
    public void createTreeN(Node node, int depth, char player, int horizont) {
    	if(node.depth < horizont && !Evaluation.isMeta(node.data)) {
    		char c = (player == 'A') ? 'P' : 'A';
    		for (int column = 0; column < node.data.pieces.length; column++) {
				if(node.data.canAdd(column)) {
					Board temp = new Board(copyBoard(node.data));
					temp.addPiece(column, c);
					Node ntemp = new Node(temp, depth + 1, column); 
					node.nodes.add(ntemp);
					if(!Evaluation.isMeta(ntemp.data)) {
						createTreeN(ntemp, depth + 1, c, horizont);
					} 
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
    	return /*"Tablero: \n" + data.toString() + */ "\n Profundidad: " + this.depth + "\nFACTOR>" + factor; 
    }
}