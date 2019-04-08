/*package whatever //do not write package name here */

import java.io.*;

class HotelVisitPath {
public static void printPath(int path[], int i) {
    if (i == 0) return;
    printPath(path, path[i]);
    System.out.print(i + " ");
}

public static void main(String args[]) {
    int hotelList[] = {0, 200, 400, 600, 601};
    int penalties[] = {0, (int)Math.pow(200 - hotelList[1], 2), -1, -1, -1};
    int path[] = {0, 0, -1, -1, -1};
    for (int i = 2; i <= hotelList.length - 1; i++) {
        for(int j = 0; j < i; j++){
            int tempPen = (int)(penalties[j] + Math.pow(200 - (hotelList[i] - hotelList[j]), 2));
            if(penalties[i] == -1 || tempPen < penalties[i]){
                penalties[i] = tempPen;
                path[i] = j;
            }
        }
    }
    for (int i = 1; i < hotelList.length; i++) {
        System.out.print("Hotel: " + hotelList[i] + ", penalty: " + penalties[i] + ", path: ");
        printPath(path, i);
        System.out.println();
    }
}
}