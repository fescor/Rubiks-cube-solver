import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
// vgale to clone 
// tsekare ta moves pali 

public class Cube implements Comparable<Cube>{
    static int SIZE = 3;
    static String moves[] = {"U", "U'", "D", "D'", "F", "F'", "B", "B'", "R", "R'", "L", "L'"};
    HashMap<String,char[][]> sides;
    private int score;
    private Cube father = null;
    private int cost = 0;
    
    private String prevMove;
    

    Cube(){

        this.sides = new HashMap<String,char[][]>() ;
        this.sides.put("A", new char[3][3]);
        this.sides.put("B", new char[3][3]);
        this.sides.put("C", new char[3][3]);
        this.sides.put("D",  new char[3][3]);
        this.sides.put("Up", new char[3][3]);
        this.sides.put("Down", new char[3][3]);
        this.SetColors();
        
    }
    public String[] Randomaise(int numOfMoves){
        Random rand = new Random();
        String randomMoves[] = new String[numOfMoves];
        int random = 0;
        String move = moves[random];
        for(int i = 0 ; i < numOfMoves; i++){
            do{
                random = rand.nextInt(moves.length);
                move = moves[random];
            } while (i > 0 && randomMoves[i-1].equals(getOppositeMove(move)));
            Move(move);
            randomMoves[i]= move;
        }
        return randomMoves;
    }
    void SetColors(){
        for (int i = 0 ; i < 3; i++){ // side A main side
            for (int j = 0 ; j < 3; j++){
                this.sides.get("A")[i][j] = 'W';
            }
        }
        for (int i = 0 ; i < 3; i++){ // side B
            for (int j = 0 ; j < 3; j++){
                this.sides.get("B")[i][j] = 'O';
            }
        }
        for (int i = 0 ; i < 3; i++){ // side C
            for (int j = 0 ; j < 3; j++){
                this.sides.get("C")[i][j] = 'Y';
            }
        }
        for (int i = 0 ; i < 3; i++){ // side D
            for (int j = 0 ; j < 3; j++){
                this.sides.get("D")[i][j] = 'R';
            }
        }
        for (int i = 0 ; i < 3; i++){ // side Up
            for (int j = 0 ; j < 3; j++){
                this.sides.get("Up")[i][j] = 'G';
            }
        }
        for (int i = 0 ; i < 3; i++){ // side Down
            for (int j = 0 ; j < 3; j++){
                this.sides.get("Down")[i][j] = 'B';
            }
        }
    }
    Cube getFather()
	{
        return this.father;
    }

    void setFather(Cube father)
	{
        this.father = father;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Cube(HashMap<String,char[][]> sides){
        this.sides = new HashMap<String, char[][]>();
        for(String key : sides.keySet()){
            char side[][] = new char[3][3];
            for(int i = 0; i<3 ; i++){
                for(int j=0; j<3; j++){
                    side[i][j] = sides.get(key)[i][j];
                }
            }
            this.sides.put(key,side);
        }
    }
    public void PrintSides(){
        for(String key : sides.keySet()){
            char[][] side = sides.get(key);
            System.out.println("Side : " + key);
            for(int i = 0 ; i <3; i++){
                
                for(int j = 0 ; j <3; j++){
                    System.out.print(side[i][j]);

                
                }
                System.out.println();
            }
            System.out.println();
        }

    }
    public char[][] getSide(String side){
        return sides.get(side);
    }

    public char[] reversearray(char[] col){
        char[] temp;
        temp = new char[3];
        int j = 3;
        for(int i = 0 ; i < 3 ; i++){
            temp[j-1 ] = col[i];
            j = j-1;
        }
        return temp;
    }

    public void reverse2darray(char side[][]){
        char[][] temp;
        temp = new char[3][3];
        char[] temp2 = new char[3];
        for(int i = 0 ; i<3 ; i++){
            for (int j = 0 ; j<3 ; j++ ){
                temp[i][j] = side[i][j];
            }
        }
        for(int i = 0; i < 3; i++){
            temp2 = getCol(2-i, temp); // getting the colum i want to reverse 

            setCol(i, side, reversearray(temp2)); //putting the collum in the  side reversed
        }
    }

    public char[] getRow(int row , char side[][]){
        char[] Row;
        Row = new char[3];
        for (int i = 0; i<3 ; i++){
            Row[i] = side[row][i];
        }
        return Row;
    }

    public char[] getCol(int col , char side[][]){
        char[] Col;
        Col = new char[3];
        for (int i = 0; i<3 ; i++){
            Col[i] = side[i][col];
        }
        return Col;
    }
    
    public void  setRow(int row, char side[][], char newValues[]){
        for (int i = 0; i <3 ; i++){
            side[row][i] = newValues[i];
        }
    }
    public void setCol( int col, char side[][], char newValues[]){
        for(int i = 0 ; i<3 ; i++){
            side[i][col] = newValues[i];
        }
    }
    public void RotateLeft(int row){ 
        char[] temp;
        char[] temp2;
        char[][] temp3;
        temp = new char[3];
        temp2 = new char[3];
        temp3 = new char[3][3];
        if(row == 1){// kenourgies grammes
            rotateRight(0);
            rotateRight(2);

        }else{
            temp = getRow(row, this.sides.get("A"));
            temp2 = getRow(row, this.sides.get("D"));
        for(int i =0 ; i<3 ; i ++){
            this.sides.get("A")[row][i] = temp2[i];
        }
        temp2 = getRow(row, this.sides.get("B"));
        for(int i =0 ; i<3 ; i ++){
           this.sides.get("B")[row][i] = temp[i];
        }
        temp =getRow(row, this.sides.get("C"));
        for(int i =0 ; i<3 ; i ++){
            this.sides.get("C")[row][i] = temp2[i];
        }
        for(int i =0 ; i<3 ; i ++){
            this.sides.get("D")[row][i] = temp[i];
        }
        if( row == 0){
            for(int i = 0 ; i<3 ; i++){
                for (int j = 0 ; j<3 ; j++ ){
                    temp3[i][j] = this.sides.get("Up")[i][j];
                }
            }
            setRow(0, this.sides.get("Up"),getCol(2, temp3));
            setRow(1, this.sides.get("Up"),getCol(1, temp3));
            setRow(2, this.sides.get("Up"),getCol(0, temp3));
            
            
            

        }else if( row == 2){
            for(int i = 0 ; i<3 ; i++){
                for (int j = 0 ; j<3 ; j++ ){
                    temp3[i][j] = this.sides.get("Down")[i][j];
                }
            }
            setCol(2, this.sides.get("Down"), getRow(0, temp3));
            setCol(1, this.sides.get("Down"), getRow(1, temp3));
            setCol(0, this.sides.get("Down"), getRow(2, temp3));

        }

        }
            
        
    }

    public void rotateRight(int row){
        char[] temp;
        char[] temp2;
        char[][] temp3;
        temp = new char[3];
        temp2 = new char[3];
        temp3 = new char[3][3];
        if(row == 1){
            RotateLeft(0);
            RotateLeft(2);
        }else{
            temp = getRow(row, this.sides.get("D"));
            temp2 = getRow(row, this.sides.get("A"));
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("D")[row][i] = temp2[i];
            }
            temp2 = getRow(row, this.sides.get("C"));
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("C")[row][i] = temp[i];
            }
            temp =getRow(row, this.sides.get("B"));
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("B")[row][i] = temp2[i];
            }
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("A")[row][i] = temp[i];
            }
            if( row == 0){
                for(int i = 0 ; i<3 ; i++){
                    for (int j = 0 ; j<3 ; j++ ){
                        temp3[i][j] = this.sides.get("Up")[i][j];
                    }
                }
                setCol(0, this.sides.get("Up"), getRow(2, temp3));
                setCol(1, this.sides.get("Up"), getRow(1, temp3));
                setCol(2, this.sides.get("Up"), getRow(0, temp3));
                
                
                

            }else if( row == 2){
                for(int i = 0 ; i<3 ; i++){
                    for (int j = 0 ; j<3 ; j++ ){
                        temp3[i][j] = this.sides.get("Down")[i][j];
                    }
                }
                setRow(0, this.sides.get("Down"),getCol(2, temp3));
                setRow(1, this.sides.get("Down"),getCol(1, temp3));
                setRow(2, this.sides.get("Down"),getCol(0, temp3));
                
                

            }

        }
            
        
    }

    public void rotateDown(int col){
        char[] temp;
        char[] temp2;
        char[][] temp3;
        temp = new char[3];
        temp2 = new char[3];
        temp3 = new char[3][3];
        if(col ==1){
            rotateUp(0);
            rotateUp(2);
        }else{
            temp = getCol(col, this.sides.get("A"));
            temp2 = getCol(col, this.sides.get("Up"));
            reverse2darray(this.sides.get("C"));//reverse side c(back side)
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("A")[i][col] = temp2[i];
            }
            temp2 = getCol(col, this.sides.get("Down"));
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("Down")[i][col] = temp[i];
            }
            temp =getCol(col, this.sides.get("C"));
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("C")[i][col] = temp2[i];
            }
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("Up")[i][col] = temp[i];
            }
            if( col == 0){
                for(int i = 0 ; i<3 ; i++){
                    for (int j = 0 ; j<3 ; j++ ){
                        temp3[i][j] = this.sides.get("D")[i][j];
                    }
                }
                setCol(0, this.sides.get("D"), getRow(2, temp3));
                setCol(1, this.sides.get("D"), getRow(1, temp3));
                setCol(2, this.sides.get("D"), getRow(0, temp3));
                
                
                

            }else if( col == 2){
                for(int i = 0 ; i<3 ; i++){
                    for (int j = 0 ; j<3 ; j++ ){
                        temp3[i][j] = this.sides.get("B")[i][j];
                    }
                }
                setRow(0, this.sides.get("B"), getCol(2, temp3));
                setRow(1, this.sides.get("B"), getCol(1, temp3));
                setRow(2, this.sides.get("B"), getCol(0, temp3));

                
                

            }
            reverse2darray(this.sides.get("C"));

        }
            
        
    }

    public void rotateUp(int col){
        char[] temp;
        char[] temp2;
        char[][] temp3;
        temp = new char[3];
        temp2 = new char[3];
        temp3 = new char[3][3];
        if(col == 1){
            rotateDown(0);
            rotateDown(2);

        }else{
            temp = getCol(col, this.sides.get("Up"));
            temp2 = getCol(col, this.sides.get("A"));
            reverse2darray(this.sides.get("C"));//reverse side c(back side)
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("Up")[i][col] = temp2[i];
            }
            temp2 = getCol(col, this.sides.get("C"));
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("C")[i][col] = temp[i];
            }
            temp =getCol(col, this.sides.get("Down"));
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("Down")[i][col] = temp2[i];
            }
            for(int i =0 ; i<3 ; i ++){
                this.sides.get("A")[i][col] = temp[i];
            }
            if( col == 0){
                for(int i = 0 ; i<3 ; i++){
                    for (int j = 0 ; j<3 ; j++ ){
                        temp3[i][j] = this.sides.get("D")[i][j];
                    }
                }
                setRow(0, this.sides.get("D"),getCol(2, temp3));
                setRow(1, this.sides.get("D"),getCol(1, temp3));
                setRow(2, this.sides.get("D"),getCol(0, temp3));
                
                
                

            }else if( col == 2){
                for(int i = 0 ; i<3 ; i++){
                    for (int j = 0 ; j<3 ; j++ ){
                        temp3[i][j] = this.sides.get("B")[i][j];
                    }
                }
                setCol(0, this.sides.get("B"), getRow(2, temp3));
                setCol(1, this.sides.get("B"), getRow(1, temp3));
                setCol(2, this.sides.get("B"), getRow(0, temp3));
                
            }
            reverse2darray(this.sides.get("C"));
            
        }
            
        
    }

    public void layerRight(int layer){
        char[] temp;
        char[] temp2;
        char[][] temp3;
        temp = new char[3];
        temp2 = new char[3];
        temp3 = new char[3][3];
        

        if(layer == 0){
            
            temp = getRow(2, this.sides.get("Up"));
            setRow(2, this.sides.get("Up"), reversearray(getCol(2, this.sides.get("D"))));
            temp2 = getCol(0, this.sides.get("B"));
            setCol(0, this.sides.get("B"), temp);
            temp = getRow(0, this.sides.get("Down"));
            setRow(0, this.sides.get("Down"),reversearray(temp2));
            setCol(2, this.sides.get("D"), temp);
            for(int i = 0 ; i<3 ; i++){
                for (int j = 0 ; j<3 ; j++ ){
                    temp3[i][j] = this.sides.get("A")[i][j];
                }
            }
            setCol(0, this.sides.get("A"), getRow(2, temp3));
            setCol(1, this.sides.get("A"), getRow(1, temp3));
            setCol(2, this.sides.get("A"), getRow(0, temp3));

        }else if (layer == 1){
            layerLeft(0);
            layerLeft(2);
            // temp = getRow(1, this.sides.get("Up"));
            // setRow(1, this.sides.get("Up"), reversearray(getCol(1, this.sides.get("D"))));
            // temp2 = getCol(1, this.sides.get("B"));
            // setCol(1, this.sides.get("B"), temp);
            // temp = getRow(1, this.sides.get("Down"));
            // setRow(1, this.sides.get("Down"),reversearray(temp2));
            // setCol(1, this.sides.get("D"), temp);
        }else{
            temp = getRow(0, this.sides.get("Up"));
            setRow(0, this.sides.get("Up"), reversearray(getCol(0, this.sides.get("D"))));
            temp2 = getCol(2, this.sides.get("B"));
            setCol(2, this.sides.get("B"), temp);
            temp = getRow(2, this.sides.get("Down"));
            setRow(2, this.sides.get("Down"),reversearray(temp2));
            setCol(0, this.sides.get("D"), temp);

            for(int i = 0 ; i<3 ; i++){
                for (int j = 0 ; j<3 ; j++ ){
                    temp3[i][j] = this.sides.get("C")[i][j];
                }
            }
            setRow(0, this.sides.get("C"),getCol(2, temp3));
            setRow(1, this.sides.get("C"),getCol(1, temp3));
            setRow(2, this.sides.get("C"),getCol(0, temp3));

        }
    }
    public void layerLeft(int layer){
        char[] temp;
        char[] temp2;
        char[][] temp3;
        temp = new char[3];
        temp2 = new char[3];
        temp3 = new char[3][3];

        if(layer == 0){
            
            temp = getRow(2, this.sides.get("Up"));
            setRow(2, this.sides.get("Up"), getCol(0, this.sides.get("B")));
            temp2 = getCol(2, this.sides.get("D"));
            setCol(2, this.sides.get("D"), reversearray(temp));
            temp = getRow(0, this.sides.get("Down"));
            setRow(0, this.sides.get("Down"),temp2);
            setCol(0, this.sides.get("B"), reversearray(temp));
            for(int i = 0 ; i<3 ; i++){
                for (int j = 0 ; j<3 ; j++ ){
                    temp3[i][j] = this.sides.get("A")[i][j];
                }
            }
            setRow(0, this.sides.get("A"),getCol(2, temp3));
            setRow(1, this.sides.get("A"),getCol(1, temp3));
            setRow(2, this.sides.get("A"),getCol(0, temp3));
            

        }else if (layer == 1){
            layerRight(0);
            layerRight(2);
            // temp = getRow(1, this.sides.get("Up"));
            // setRow(1, this.sides.get("Up"), getCol(1, this.sides.get("B")));
            // temp2 = getCol(1, this.sides.get("D"));
            // setCol(1, this.sides.get("D"), reversearray(temp));
            // temp = getRow(1, this.sides.get("Down"));
            // setRow(1, this.sides.get("Down"),temp2);
            // setCol(1, this.sides.get("B"), reversearray(temp));
        }else{
            temp = getRow(0, this.sides.get("Up"));
            setRow(0, this.sides.get("Up"), getCol(2, this.sides.get("B")));
            temp2 = getCol(0, this.sides.get("D"));
            setCol(0, this.sides.get("D"), reversearray(temp));
            temp = getRow(2, this.sides.get("Down"));
            setRow(2, this.sides.get("Down"),temp2);
            setCol(2, this.sides.get("B"), reversearray(temp));

            for(int i = 0 ; i<3 ; i++){
                for (int j = 0 ; j<3 ; j++ ){
                    temp3[i][j] = this.sides.get("C")[i][j];
                }
            }
            setCol(0, this.sides.get("C"), getRow(2, temp3));
            setCol(1, this.sides.get("C"), getRow(1, temp3));
            setCol(2, this.sides.get("C"), getRow(0, temp3));

        }




    }
    public void Move(String move){
        switch (move){
            case "U":
                rotateRight(0);
                break;
            case "U'":
                RotateLeft(0);
                break;
            case "D":
                RotateLeft(2);
                break;  
            case "D'":
                rotateRight(2);
                break;
            case "F":
                layerRight(0);
                break;
            case "F'":
                layerLeft(0);
                break;
            case "B":
                layerLeft(2);
                break;
            case "B'":
                layerRight(2);
                break;
            case "R":
                rotateUp(2);
                break;
            case "R'":
                rotateDown(2);
                break;
            case "L":
                rotateDown(0);
                break;
            case "L'":
                rotateUp(0);
                break;
            case "M":
                rotateUp(1);
                break;
            case "M'":
                rotateDown(1);
                break;
            case "S":
                layerRight(1);
                break;
            case "S'":
                layerLeft(1);
                break;
            case "E":
                rotateRight(1);
                break;
            case "E'":
                RotateLeft(1);
                break;
            

        }

    }
    public boolean isFinalSide(char[][] side){
        char first = side[1][1];
        for(int i = 0 ; i <3; i++){
                
            for(int j = 0 ; j <3; j++){
                if (side[i][j] != first){
                    return false;
                }
            }
                
        }
        return true;
    }
    public boolean isFinal(int k ){
        int counter = 0;
        for( String key : sides.keySet()){
            char[][] side = sides.get(key);
            if(isFinalSide(side)){
                counter++;
            }
        }
        if(counter>= k){
            return true;
        }else{
            return false;
        }
    }
    public void setCost(int cost){
        this.cost = cost;
    }

    public void setPrevMove(String move) {
        this.prevMove = move;
    }
    public void score(){// posa koutakia bika stin sosti plevra //
        this.score  = 0;
        for(String key : sides.keySet() ){
            char[][] side = sides.get(key);
            char col = side[1][1];
            for(int i = 0; i <3 ; i++){
                for(int j = 0 ; j<3 ; j++){
                    if(side[i][j] == col){
                        this.score ++;
                    }
                }
            }
        }
        this.score = 54 - this.score;
    }
    public int getScore(){
        int totalscore = this.score<<3 + this.cost;
        if(this.cost > 30){
            return totalscore<<3;
        }
        return totalscore;
                 
        // if (this.cost >25 & this.cost <50){
        //     return this.score<<3 + this.cost +2; 
            
        
        // }else if(this.cost >50){
        //     return this.score<<3 + this.cost + 4;
        // }else{
        //     return this.score<<3 + this.cost;
        // }
        //return this.score<<3 + this.cost;
    }

    public String getOppositeMove(String move) {
        if (move == null) return move;
        if (move.length() == 2) {
            return move.substring(0, 1);
        }
        return move + "'";
    }

    public String getPrevMove() {
        return prevMove;
    }

    public ArrayList<Cube> getChildren(int cost){
        ArrayList<Cube> children = new ArrayList<Cube>();
        
        for (String move : moves) {
            Cube child = new Cube(this.sides);
            if (move.equals(this.getOppositeMove(this.prevMove))) continue;
            child.Move(move); // correct one
            child.setCost(this.cost + 1);// this.cost +
            child.score();
            child.setFather(this);
            child.setPrevMove(move);
            children.add(child);
        }
        
        // child.Move("U"); // correct one
        // child.setCost(this.cost + 1);// this.cost +
        // child.score();
        // child.setFather(this);
        // children.add(child);

        // // MOVE 2
        // child = new Cube(this.sides); 

        // child.Move("U'");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 3
        // child = new Cube(this.sides); 

        // child.Move("D");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 4
        // child = new Cube(this.sides); 

        // child.Move("D'");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 5
        // child = new Cube(this.sides); 

        // child.Move("F");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 6
        // child = new Cube(this.sides); 

        // child.Move("F'");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 7
        // child = new Cube(this.sides); 

        // child.Move("B");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 8
        // child = new Cube(this.sides); 

        // child.Move("B'");
        // child.setCost(this.cost + 1 );
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 9
        // child = new Cube(this.sides); 

        // child.Move("R");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 10
        // child = new Cube(this.sides); 

        // child.Move("R'");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 11
        // child = new Cube(this.sides); 

        // child.Move("L");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 12
        // child = new Cube(this.sides); 

        // child.Move("L'");
        // child.setCost(this.cost + 1);
        // child.score();
        // child.setFather(this);
        // children.add(child);
        // // MOVE 13
        // child = new Cube(this.sides); 

        //  child.Move("M");
        //  child.setCost(this.cost + 1);
        //  child.score();
        //  child.setFather(this);
        //  children.add(child);
        // // MOVE 14
        //  child = new Cube(this.sides); 

        //  child.Move("M'");
        //  child.setCost(this.cost + 1);
        //  child.score();
        //  child.setFather(this);
        //  children.add(child);
        //  // MOVE 15
          //child = new Cube(this.sides); 

        //   child.Move("S");
        //   child.setCost(this.cost + 1);
        //   child.score();
        //   child.setFather(this);
        //   children.add(child);
        //  // // MOVE 16
        //   child = new Cube(this.sides); 

        //   child.Move("S'");
        //   child.setCost(this.cost + 1);
        //   child.score();
        //   child.setFather(this);
        //   children.add(child);
        // // // MOVE 17
        //   child = new Cube(this.sides); 

        //   child.Move("E");
        //   child.setCost(this.cost + 1);
        //   child.score();
        //   child.setFather(this);
        //   children.add(child);
        //   // MOVE 18
        //   child = new Cube(this.sides); 

        //   child.Move("E'");
        //   child.setCost(this.cost + 1);
        //   child.score();
        //   child.setFather(this);
        //   children.add(child);
           return children;

    }
    
    @Override
    public int compareTo(Cube s){
        
        return Integer.compare(this.getScore(),s.getScore());

    }
    @Override
    public boolean equals(Object obj)
    {
        Cube other = (Cube)obj;

        // check for equality of numbers in the tiles.
        for(String key : sides.keySet() ){
            for(int i = 0; i <3 ; i++){
                for(int j = 0 ; j<3 ; j++){
                    if(this.sides.get(key)[i][j] != other.sides.get(key)[i][j]){
                        return false;
                    }
                }
            }
        }

        return true;
    }



}
