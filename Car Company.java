import java.io.*;
import java.util.ArrayList;

import org.omg.Messaging.SyncScopeHelper;

public class damla_ulku_2017510091 {

	// PART 1
	public static int DPpart1(int x, int R, ArrayList<String> garcosts, int p, int d, ArrayList<String> demands) {
		int matris[][] = new int[x + 1][R + 1];

		// 0th month
		matris[0][0] = 0;
		for (int i = 0; i < R; i++) {
			int garagecost = Integer.parseInt(garcosts.get(i));
			matris[0][i + 1] = garagecost;
		}

		// Calculating and filling the matris
		for (int i = 1; i < x + 1; i++) {
			int demand = Integer.parseInt(demands.get(i - 1));
			for (int j = 0; j < R + 1; j++) {
				int initgarage = matris[0][j];
				int harcama = 0;
				int temp = 0;
				int min = Integer.MAX_VALUE;

				if (demand > p) {
					for (int k = 0; k < R + 1; k++) {
						int precoast = matris[i - 1][k];
						if (demand >= p + k - j) {
							harcama = precoast + initgarage + d * (demand - p - k + j);
							temp = harcama;
							if (temp < min) {
								min = temp;
							}
						}
					}
					matris[i][j] = min;
				}

				else {

					if (j <= p - demand) {
						harcama = matris[i - 1][0] + initgarage;
					} else {
						harcama = matris[i - 1][0] + initgarage + d * (j - (p - demand));
					}

					matris[i][j] = harcama;
				}

			}
		}

		// Finding the min cost
		int mincost = Integer.MAX_VALUE;
		for (int i = 0; i < R + 1; i++) {
			int temp = matris[x][i];

			if (temp < mincost) {
				mincost = temp;
			}
		}

		return mincost;
	}

	public static int GreedyPart1(int x, int p, int d, ArrayList<String> demands) {
		ArrayList<Integer> repo = new ArrayList<Integer>();
		repo.add(0);
		int harcama;

		for (int i = 1; i < x; i++) {
			int demand = Integer.parseInt(demands.get(i - 1));
			if (demand > p) {
				harcama = d * (demand - p);
			} else {
				harcama = 0;
			}
			repo.add(harcama + repo.get(i - 1));
		}

		int mincost = repo.get(repo.size() - 1);

		return mincost;
	}

	// PART 2
	public static int DPpart2(int x, int c, int t, ArrayList<Integer> income, int mat[][]) {

		int matris[][] = new int[x][c];
		int result = 0;

		// Calculating and filling the matris
		for (int i = 0; i < x; i++) {
			int incomes = income.get(i);
			for (int j = 0; j < c; j++) {
				int invest = mat[i][j];
				int temp = 0;
				if (i == 0) {
					result = incomes + (incomes * invest) / 100;
					matris[i][j] = result;
				}

				else {

					int max = 0;

					for (int k = 0; k < c; k++) {

						if (j == k) {
							int buay = incomes + matris[i - 1][k];
							result = buay + (buay * invest) / 100;
							temp = result;
						}

						else {
							int buay = matris[i - 1][k] - matris[i - 1][k] * t / 100;
							buay = buay + incomes;
							result = buay + (buay * invest) / 100;
							temp = result;
						}

						if (temp > max) {
							max = temp;
						}
					}
					matris[i][j] = max;
				}

			}
		}

		// Finding max profit
		int maxprofit = 0;

		for (int i = 0; i < c; i++) {
			int temp = matris[x - 1][i];

			if (temp > maxprofit) {
				maxprofit = temp;
			}
		}

		return maxprofit;

	}

	public static int GreedyPart2(int x, int c, int t, ArrayList<Integer> income, int mat[][]) {
		int matris[][] = new int[x][c];
		int result = 0;
		int initmax = 0;
		int coor = 0;

		for (int i = 0; i < x; i++) {
			int incomes = income.get(i);
			for (int j = 0; j < c; j++) {
				int invest = mat[i][j];

				if (i == 0) {
					result = incomes + ((incomes * invest) / 100);
					matris[i][j] = result;
				}

				else {

					if (j == coor) {
						int buay = incomes + initmax;
						result = buay + (buay * invest) / 100;
						matris[i][j] = result;
					}

					else {
						int buay = initmax - initmax * t / 100;
						buay = buay + incomes;
						result = buay + (buay * invest) / 100;
						matris[i][j] = result;
					}
				}

			}

			for (int k = 0; k < c; k++) {
				int temp = matris[i][k];

				if (temp > initmax) {
					initmax = temp;
					coor = k;
				}
			}
		}

		return initmax;
	}

	///////////////// MAIN/////////////////////

	public static void main(String[] args) throws IOException {

		int x = 3, p = 5, d = 5, B = 100, c = 3, t = 2;

		int m;
		int income;
		int temp;
		int prev = 0;
		int investmatris[][] = new int[x][6];
		ArrayList<Integer> allincome = new ArrayList<Integer>();

		// Reading Files
		BufferedReader demandsfile = new BufferedReader(new FileReader("month_demand.txt"));
		demandsfile.readLine(); // first line
		ArrayList<String> demands = new ArrayList<>();
		String st1;

		while ((st1 = demandsfile.readLine()) != null) {
			String[] split = st1.split("\t");
			String demand;
			for (int i = 1; i < split.length; i = i + 2) {
				demand = split[i];
				demands.add(demand);
			}
		}
		demandsfile.close();

		BufferedReader investmentfile = new BufferedReader(new FileReader("investment.txt"));
		investmentfile.readLine(); // first line
		ArrayList<String> c1 = new ArrayList<>();
		ArrayList<String> c2 = new ArrayList<>();
		ArrayList<String> c3 = new ArrayList<>();
		ArrayList<String> c4 = new ArrayList<>();
		ArrayList<String> c5 = new ArrayList<>();
		ArrayList<String> c6 = new ArrayList<>();
		String st2;

		while ((st2 = investmentfile.readLine()) != null) {
			String[] split = st2.split("\t");
			String c1s;
			String c2s;
			String c3s;
			String c4s;
			String c5s;
			String c6s;

			for (int i = 1; i < split.length; i = i + 7) {
				c1s = split[i];
				c1.add(c1s);
			}
			for (int i = 2; i < split.length; i = i + 7) {
				c2s = split[i];
				c2.add(c2s);
			}
			for (int i = 3; i < split.length; i = i + 7) {
				c3s = split[i];
				c3.add(c3s);
			}
			for (int i = 4; i < split.length; i = i + 7) {
				c4s = split[i];
				c4.add(c4s);
			}
			for (int i = 5; i < split.length; i = i + 7) {
				c5s = split[i];
				c5.add(c5s);
			}
			for (int i = 6; i < split.length; i = i + 7) {
				c6s = split[i];
				c6.add(c6s);
			}
		}
		investmentfile.close();

		BufferedReader garagefile = new BufferedReader(new FileReader("garage_cost.txt"));
		garagefile.readLine(); // first line
		ArrayList<String> garagecosts = new ArrayList<>();
		String st3;

		while ((st3 = garagefile.readLine()) != null) {
			String[] split = st3.split("\t");
			String cost;
			for (int i = 1; i < split.length; i = i + 2) {
				cost = split[i];
				garagecosts.add(cost);
			}
		}
		garagefile.close();

		// Investment matris
		for (int j = 0; j < x; j++) {
			investmatris[j][0] = Integer.parseInt(c1.get(j));
		}
		for (int j = 0; j < x; j++) {
			investmatris[j][1] = Integer.parseInt(c2.get(j));
		}
		for (int j = 0; j < x; j++) {
			investmatris[j][2] = Integer.parseInt(c3.get(j));
		}
		for (int j = 0; j < x; j++) {
			investmatris[j][3] = Integer.parseInt(c4.get(j));
		}
		for (int j = 0; j < x; j++) {
			investmatris[j][4] = Integer.parseInt(c5.get(j));
		}
		for (int j = 0; j < x; j++) {
			investmatris[j][5] = Integer.parseInt(c6.get(j));
		}

		// Calculating R
		int toplam = 0;
		for (int i = 0; i < x; i++) {
			toplam += Integer.parseInt(demands.get(i));
		}

		// Calculating monthly income
		for (int i = 0; i < x; i++) {

			m = Integer.parseInt(demands.get(i));

			temp = (m * B) / 2;

			if (i == 0) {
				income = temp;
			}

			else {
				income = temp + prev;
			}

			prev = temp;
			allincome.add(income);
		}

		int artý = Integer.parseInt((demands.get(x - 1))) * B / 2;
		int costdp = DPpart1(x, toplam, garagecosts, p, d, demands);
		int costGre = GreedyPart1(x, p, d, demands);
		int profitdp = DPpart2(x, c, t, allincome, investmatris);
		int profitGre = GreedyPart2(x, c, t, allincome, investmatris);
		int dpresults = (profitdp + artý) - costdp;
		int greresults = (profitGre + artý) - costGre;

		System.out.println("DP Results: " + dpresults);
		System.out.println("Greedey Results: " + greresults);
	}
}
