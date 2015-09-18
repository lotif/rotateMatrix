package com.waveapps.sechallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RotateMatrix {

	public static void main(String args[] ) throws Exception {
		Scanner s = new Scanner(System.in);
		
		int d = s.nextInt();
		
		int[][] matrix = new int[d][d];
		for(int i = 0; i < d; i++) {
			for(int j = 0; j < d; j++) {
				matrix[i][j] = Integer.parseInt(s.next());
			}
		}
		
		matrix = rotate(matrix);
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		s.close();
	}
	
	public static int[][] rotate(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix.length == 1) {
			return matrix;
		}
		
		List<Integer> outerCircle = getOuterCircle(matrix);
		matrix = removeOuterCircle(matrix);
		//rotating outer circle
		outerCircle.add(0, outerCircle.remove(outerCircle.size() - 1));
		
		matrix = rotate(matrix);
		
		matrix = addOuterCircle(outerCircle, matrix);
		
		return matrix;
		
	}

	private static int[][] addOuterCircle(List<Integer> outerCircle, int[][] matrix) {
		
		int d = matrix.length + 2;
		int[][] newMatrix = new int[d][d];
		
		//Adding the outer circle to the matrix
		for(int j = 0; j < d; j++) {
			newMatrix[0][j] = outerCircle.remove(0);
		}
		for(int i = 1; i < d; i++) {
			newMatrix[i][d-1] = outerCircle.remove(0);
		}
		for(int j = d-2; j >= 0; j--) {
			newMatrix[d-1][j] = outerCircle.remove(0);
		}
		for(int i = d-2; i >= 1; i--) {
			newMatrix[i][0] = outerCircle.remove(0);
		}
		
		//Adding the inner matrix
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				newMatrix[i + 1][j + 1] = matrix[i][j];
			}
		}
		
		return newMatrix;
		
	}

	private static List<Integer> getOuterCircle(int[][] matrix) {
		int d = matrix.length;
		
		List<Integer> outerCircle = new ArrayList<Integer>();
		
		for(int j = 0; j < d; j++) {
			outerCircle.add(matrix[0][j]);
		}
		for(int i = 1; i < d; i++) {
			outerCircle.add(matrix[i][d-1]);
		}
		for(int j = d-2; j >= 0; j--) {
			outerCircle.add(matrix[d-1][j]);
		}
		for(int i = d-2; i >= 1; i--) {
			outerCircle.add(matrix[i][0]);
		}
		
		return outerCircle;
	}
	
	private static int[][] removeOuterCircle(int[][] matrix) {		
		int d = matrix.length;
		int[][] newMatrix = new int[d-2][d-2];
		
		for(int i = 1; i < d-1; i++) {
			for(int j = 1; j < d-1; j++) {
				newMatrix[i-1][j-1] = matrix[i][j];
			}
		}
		
		return newMatrix;
	}
	
}
