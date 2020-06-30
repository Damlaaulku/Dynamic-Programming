package denemeler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Damla_Ulku_2017510091 {
	
	public static void Score(int[][] blosum,int gap, String first, String second) {
		int l1=first.length();
		int l2=second.length();
		String out1 = "";
		String out2 = "";
		String [][] from = new String [l2+1][l1+1];
		int [][] matris = new int [l2+1][l1+1];
		matris[0][0]=0;
		
		for (int i = 1; i < l1+1; i++) {
			matris[0][i]=matris[0][i-1]+gap;
			from[0][i]="yan";
		}
		
		for (int i = 1; i < l2+1; i++) {
			matris[i][0]=matris[i-1][0]+gap;
			from[i][0]="üst";
		}
		
		for (int i = 1; i < l2+1; i++) {
			char a =second.charAt(i-1);
			for (int j = 1; j < l1+1; j++) {
				char b =first.charAt(j-1);	
				int match = blosum[getIndex(a)][getIndex(b)];
				int çapraz = matris[i-1][j-1]+match;
				int üst = matris[i-1][j]+gap;
				int yan = matris[i][j-1]+gap;
				
				if(çapraz>=üst && çapraz>=yan) {
					matris[i][j]=çapraz;
					from[i][j]="çapraz";
				}
				else if(üst>çapraz && üst>yan) {
					matris[i][j]=üst;
					from[i][j]="üst";
				}
				else if(yan>üst && yan>çapraz) {
					matris[i][j]=yan;
					from[i][j]="yan";
				}				
			}
			
		}
		
		int score=matris[l2][l1];	
		int x=l1;
		int y=l2;
		
		while(x!=-1 && y!=-1) {
			
			if(x!=0 && y!=0) {
				if(from[y][x].equals("çapraz")) {
					out1 += second.charAt(y-1);
					out2 += first.charAt(x-1);
					x--;
					y--;
				}
				else if(from[y][x].equals("üst")) {
					out1 += second.charAt(y-1);
					out2 += "-";
					y--;
				}
				else if(from[y][x].equals("yan")) {			
					out1 += "-";
					out2 += first.charAt(x-1);
					x--;				
				}		
			}
			else {
				if(x==0) {
					out2 += "-";
					out1 += second.charAt(0);
					break;
				}
				else if(y==0) {
					out1 += "-";
					out2 += first.charAt(0);
					break;
				}
			}

				
		}
		
		StringBuilder str1 = new StringBuilder(out1);
		StringBuilder rev1 = str1.reverse();
		
		StringBuilder str2 = new StringBuilder(out2);
		StringBuilder rev2 = str2.reverse();
		
		System.out.println("Score: " + score);
		System.out.println();
		System.out.println(rev2);
		System.out.println(rev1);
		
	}
	
	 private static int getIndex (char a) {
	    	switch ((String.valueOf(a)).toUpperCase().charAt(0)) {
		    	case 'A': return 0;
		    	case 'R': return 1;
		    	case 'N': return 2;
		    	case 'D': return 3;
		    	case 'C': return 4;
		    	case 'Q': return 5;
		    	case 'E': return 6;
		    	case 'G': return 7;
		    	case 'H': return 8;
		    	case 'I': return 9;
		    	case 'L': return 10;
		    	case 'K': return 11;
		    	case 'M': return 12;
		    	case 'F': return 13;
		    	case 'P': return 14;
		    	case 'S': return 15;
		    	case 'T': return 16;
		    	case 'W': return 17;
		    	case 'Y': return 18;
		    	case 'V': return 19;
		    	case 'B': return 20;
		    	case 'Z': return 21;
		    	case 'X': return 22;
		    	case '*': return 23;
	    	}
	    	return -1;
	    }

	public static void main(String[] args) throws IOException {
		//inputs
		int [][] blosum = { {4, -1, -2, -2,  0, -1, -1, 0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0, -2, -1,  0, -4},
				 {-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3, -1,  0, -1, -4 },
				 {-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3,  3,  0, -1, -4 },
				 {-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3,  4,  1, -1, -4 },
				 {  0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1, -3, -3, -2, -4 },
				 { -1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2,  0,  3, -1, -4 },
				 {-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, -4 },
				 { 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3, -1, -2, -1, -4 },
				 {-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3,  0,  0, -1, -4 },
				 { -1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3, -3, -3, -1, -4 },
				 { -1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1, -4, -3, -1, -4 },
				 { -1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2,  0,  1, -1, -4 },
				 { -1, -1, -2, -3, -1,  0 ,-2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1, -3, -1, -1, -4 },
				 { -2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1, -3, -3, -1, -4 },
				 { -1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2, -2, -1, -2, -4 },
				 {  1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2,  0,  0,  0, -4 },
				 { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0, -1, -1,  0, -4 },
				 { -3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3 ,-2, 11,  2, -3, -4, -3, -2, -4 },
				 { -2, -2, -2 ,-3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1, -3, -2, -1, -4 },
				 {  0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4, -3, -2, -1, -4 },
				 { -2, -1,  3,  4, -3 , 0 , 1, -1,  0, -3, -4,  0, -3, -3, -2,  0, -1, -4, -3, -3,  4,  1, -1, -4 },
				 { -1,  0,  0,  1, -3,  3,  4, -2,  0, -3, -3,  1, -1, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, -4 },
				 {  0, -1, -1, -1, -2, -1, -1, -1 ,-1, -1, -1, -1, -1, -1, -2,  0,  0, -2, -1, -1, -1, -1, -1, -4 },
				 { -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4 ,-4, -4, -4, -4, -4, -4, -4 ,-4, -4, -4, -4,  1}};
		
		int gap = -5;
		
		// Reading Input File
		BufferedReader inputfile = new BufferedReader(new FileReader("input.txt"));
		String first = inputfile.readLine().toUpperCase(); // first line
		String second= inputfile.readLine().toUpperCase(); // second line				
		inputfile.close();
		
		Score(blosum,gap,first,second);
	}

}
