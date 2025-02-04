This project was created for the Artificial Inteligence course of the Computer science department in Athens University of Economics and Business during 2023-2024 winter semester.

The Goal of the project is to create a program that solves the Rubik's Cube problem using the A* algorithm with BestFS as the search algorithm and the heuristic used is the number of blocks that are in the right side of the cube.The results were not the best but considered that this is not a memory efficient and an outdated method for solving problems with the use of AI i would say there is not really much room for improvment.

The main function is located at the App.java file.Implementation for the cube (move functions ,  getting the state of the cube as well as calculation the huristic score ) and other cube-related functions are implemented at the Cube.java file.The BestFS search algorithm is located in the TreeSearch.java file.

Execution :
The terminal will ask for the desired number of sides you want the algorithm to solve and afte receiving input it will start the calculation.Before terminating the terminal will show each side of the cube where R , W , G , R , B stands for red , white , green , red and blue and the execution time.The initial state of the Cube is solved meaning each side has a uniqe colour and before the solving proccess starts the program generates 6 random moves to shuffle the cube.

Results:
The results for solving the cube for a shuflle of 6 moves moves were great.Setting more moves for the suffle procces might give erros for not enough heap space especially if the desired number of sides solves is greater than 3. 







